package me.mmtr.wordsapi.service;

import jakarta.annotation.PostConstruct;
import me.mmtr.wordsapi.util.WordsList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class WordsService {
    private List<String> words;

    @PostConstruct
    public void init() {
        try (Stream<String> wordsStream = Files.lines(Path.of("words.txt"))) {
            this.words = wordsStream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WordsList.Builder buildWordsList() {
        return new WordsList.Builder().fromWordsList(words);
    }
}
