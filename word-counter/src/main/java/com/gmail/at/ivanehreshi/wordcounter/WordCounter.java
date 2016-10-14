package com.gmail.at.ivanehreshi.wordcounter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface WordCounter {
    int passWord(String word);

    default List<Integer> passWords(String words) {
        return passWords(words.split(" "));
    }

    default List<Integer> passWords(String... words) {
        return Arrays.stream(words)
                     .map(this::passWord)
                     .collect(Collectors.toList());
    }

    int count(String word);
}
