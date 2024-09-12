package me.mmtr.wordsapi;

import me.mmtr.wordsapi.controller.WordsController;
import me.mmtr.wordsapi.service.WordsService;
import me.mmtr.wordsapi.util.WordsList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(WordsController.class)
public class WordsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static WordsService wordsService;

    @BeforeEach
    public void setUp() {
        List<String> mockWords =  new ArrayList<>(
                List.of("test", "atest", "btest", "testa", "testb", "testtttttt"));

        WordsList.Builder builder = new WordsList.Builder().fromWordsList(mockWords);

        Mockito.when(wordsService.buildWordsList()).thenReturn(builder);
    }

    @Test
    public void shouldReturnSingleWord() throws Exception {
        this.mockMvc.perform(get("/api/words/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)));
    }

    @Test
    public void shouldReturnSingleWordWithLengthEqualTo4() throws Exception {
        this.mockMvc.perform(get("/api/words/get").param("length", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)))
                .andExpect(jsonPath("$.words[0]").value("test"));
    }

    @Test
    public void shouldReturnSingleWordContainingGivenSequence() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                .param("containing", "ttttttt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)))
                .andExpect(jsonPath("$.words[0]").value("testtttttt"));
    }

    @Test
    public void shouldReturnSingleWordStartingWithGivenSequence() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                .param("starting", "b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)))
                .andExpect(jsonPath("$.words[0]").value("btest"));
    }

    @Test
    public void shouldReturnSingleWordEndingWithGivenSequence() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                        .param("ending", "b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)))
                .andExpect(jsonPath("$.words[0]").value("testb"));
    }

    @Test
    public void shouldReturnTheGivenAmountOfWords() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                        .param("amount", "6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(6)));
    }

    @Test
    public void shouldReturnAllWordsDespiteNegativeAmountParam() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                        .param("amount", "-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(6)));
    }

    @Test
    public void shouldReturnAWordDespiteNegativeWordLengthParam() throws Exception {
        this.mockMvc.perform(get("/api/words/get")
                        .param("length", "-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.words", hasSize(1)));
    }
}
