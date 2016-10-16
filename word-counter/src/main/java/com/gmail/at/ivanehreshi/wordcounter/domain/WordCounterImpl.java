package com.gmail.at.ivanehreshi.wordcounter.domain;

import java.util.HashMap;
import java.util.Map;

public class WordCounterImpl implements WordCounter{
    private Map<String, Integer> wordsMap;

    public WordCounterImpl() {
        wordsMap = new HashMap<>();
    }

    @Override
    public int passWord(String word) {
        if(word == null) {
            return 0;
        }

        Integer wordCount = wordsMap.getOrDefault(word, 0);
        ++wordCount;
        wordsMap.put(word, wordCount);
        return wordCount;
    }

    @Override
    public int count(String word) {
        if(word == null) {
            return 0;
        }
        return wordsMap.getOrDefault(word, 0);
    }

    protected Map<String, Integer> getWordsMap() {
        return wordsMap;
    }

    protected void setWordsMap(Map<String, Integer> wordsMap) {
        this.wordsMap = wordsMap;
    }
}
