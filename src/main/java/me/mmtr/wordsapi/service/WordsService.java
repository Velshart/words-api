package me.mmtr.wordsapi.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class WordsService {
    private List<String> WORDS;

    public WordsService() {
        this.WORDS = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try (Stream<String> wordsStream = Files.lines(Path.of("words.txt"))) {
            this.WORDS = wordsStream.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}