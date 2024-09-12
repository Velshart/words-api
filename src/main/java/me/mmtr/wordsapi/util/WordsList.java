package me.mmtr.wordsapi.util;

import java.util.Collections;
import java.util.List;

public class WordsList {
    private final List<String> WORDS;

    public WordsList(Builder builder) {
        this.WORDS = builder.words;
    }

    public List<String> getWordsList() {
        return WORDS;
    }

    public static class Builder {
        private List<String> words;

        public Builder fromWordsList(List<String> words) {
            this.words = words;
            Collections.shuffle(this.words);

            return this;
        }

        public void length(int length) {
            this.words = this.words.stream()
                    .filter(word -> word.length() == length)
                    .toList();
        }

        public void containing(CharSequence sequence) {
            this.words = this.words.stream()
                    .filter(word -> word.contains(sequence))
                    .toList();
        }

        public void startingWith(String prefix) {
            this.words = this.words.stream()
                    .filter(word -> word.startsWith(prefix))
                    .toList();
        }

        public void endingWith(String prefix) {
            this.words = this.words.stream()
                    .filter(word -> word.endsWith(prefix))
                    .toList();
        }

        public void amount(int amount) {
            if (words.size() > amount) {
                this.words = this.words.subList(0, amount);
            }
        }

        public WordsList build() {
            return new WordsList(this);
        }
    }
}
