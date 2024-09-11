package me.mmtr.wordsapi.controller;

import me.mmtr.wordsapi.service.WordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/words")
public class WordsController {

    private final WordsService WORDS_SERVICE;

    public WordsController(WordsService WORDS_SERVICE) {
        this.WORDS_SERVICE = WORDS_SERVICE;
    }


}
