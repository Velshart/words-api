package me.mmtr.wordsapi.controller;

import me.mmtr.wordsapi.record.Words;
import me.mmtr.wordsapi.service.WordsService;
import me.mmtr.wordsapi.util.WordsList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/words")
public class WordsController {

    private final WordsService WORDS_SERVICE;
    private final AtomicLong ATOMIC_LONG = new AtomicLong();

    public WordsController(WordsService WORDS_SERVICE) {
        this.WORDS_SERVICE = WORDS_SERVICE;
    }

    @GetMapping("/get")
    public ResponseEntity<?> wordsList(@RequestParam(required = false) Integer length,
                                       @RequestParam(required = false) CharSequence containing,
                                       @RequestParam(required = false) String starting,
                                       @RequestParam(required = false) String ending,
                                       @RequestParam(required = false, defaultValue = "1") Integer amount) {
        WordsList.Builder wordsListBuilder = WORDS_SERVICE.buildWordsList();

        if (length != null && length > 0) {
            wordsListBuilder.length(length);
        }

        if (containing != null) {
            wordsListBuilder.containing(containing);
        }

        if (starting != null) {
            wordsListBuilder.startingWith(starting);
        }

        if (ending != null) {
            wordsListBuilder.endingWith(ending);
        }

        if (amount != null && amount > 0) {
            wordsListBuilder.amount(amount);
        }
        return new ResponseEntity<>(new Words(ATOMIC_LONG.incrementAndGet(), wordsListBuilder.build().getWordsList()), HttpStatus.OK);
    }
}
