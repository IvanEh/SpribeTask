package com.gmail.at.ivanehreshi.wordcounter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface WordCounter {
    int passWord(String word);

    default List<Integer> passWordsAndCount(String words) {
        return passWordsAndCount(words.split(" "));
    }

    default List<Integer> passWordsAndCount(String... words) {
        return Arrays.stream(words)
                     .map(this::passWord)
                     .collect(Collectors.toList());
    }

    default void passWords(String... words) {
        Arrays.stream(words)
                     .forEach(this::passWord);
    }

    default void passWords(String words) {
        passWords(words.split(" "));
    }

    int count(String word);
}
