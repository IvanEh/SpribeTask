package com.gmail.at.ivanehreshi.wordcounter.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface WordCounter extends Serializable {
    /**
     * @param word is the word to count
     * @return number of passWord(word) calls made with the given argument </br>
     *         if <b>word</b> is <b>null</b> then returns 0
     */
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
