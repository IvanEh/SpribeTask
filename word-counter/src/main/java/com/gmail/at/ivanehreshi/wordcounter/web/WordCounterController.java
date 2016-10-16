package com.gmail.at.ivanehreshi.wordcounter.web;

import com.gmail.at.ivanehreshi.wordcounter.domain.WordCounter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

@ManagedBean
public class WordCounterController implements Serializable {
    @ManagedProperty("#{concurrentWordCounter}")
    private WordCounter wordCounter;

    private String testedForm;

    public WordCounterController() {
    }

    public String countMsg() {
        if(testedForm == null) {
            return "";
        }

        return String.format("\"%s\" passed %d times", testedForm, wordCounter.count(testedForm));
    }

    public void passWords(String words) {
        wordCounter.passWords(words);
    }

    public WordCounter getWordCounter() {
        return wordCounter;
    }

    public void setWordCounter(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    public String getTestedForm() {
        return testedForm;
    }

    public void setTestedForm(String testedForm) {
        this.testedForm = testedForm;
    }
}
