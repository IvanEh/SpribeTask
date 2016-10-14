package com.gmail.at.ivanehreshi.wordcounter.web;

import com.gmail.at.ivanehreshi.wordcounter.domain.WordCounter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean
public class WordCounterController implements Serializable {
    @ManagedProperty("#{concurrentWordCounter}")
    private WordCounter wordCounter;

    private String word;

    public WordCounterController() {
    }

    public String count() {
        if(word == null) {
            return "";
        }

        return word + ": " + String.valueOf(wordCounter.count(word));
    }

    public void passWords(String words) {
        System.out.println("pass(" + words + ")");
        wordCounter.passWords(words);
    }

    public WordCounter getWordCounter() {
        return wordCounter;
    }

    public void setWordCounter(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
