package com.gmail.at.ivanehreshi.wordcounter;

import com.gmail.at.ivanehreshi.wordcounter.domain.ConcurrentWordCounter;
import com.gmail.at.ivanehreshi.wordcounter.domain.WordCounter;
import com.gmail.at.ivanehreshi.wordcounter.domain.WordCounterImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

// TODO: Add test messages
@RunWith(Parameterized.class)
public class WordCounterTest {
    private Class<? extends WordCounter> wordCounterClass;
    private WordCounter wordCounter;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {WordCounterImpl.class},
                {ConcurrentWordCounter.class}
        });
    }

    public WordCounterTest(Class<? extends WordCounter> wordCounterClass) {
        this.wordCounterClass = wordCounterClass;
    }

    @Before
    public void setUp() throws Exception{
        wordCounter = wordCounterClass.newInstance();
    }

    @After
    public void tearDown() throws Exception {
        wordCounter = null;
    }

    @Test
    public void count_NonExistent() {
        assertEquals(0, wordCounter.count("Word"));
    }

    @Test
    public void passWord_New() {
        assertEquals(1, wordCounter.passWord("Word"));
    }

    @Test
    public void passWordsAndCountVarArg_New() {
        assertEquals(Arrays.asList(1), wordCounter.passWordsAndCount(new String[]{"Word3"}));
        assertEquals(Arrays.asList(1, 1), wordCounter.passWordsAndCount("Word1", "Word2"));
    }

    @Test
    public void passWordsAndCountString_New() {
        assertEquals(Arrays.asList(1), wordCounter.passWordsAndCount("Word1"));
        assertEquals(Arrays.asList(1, 1), wordCounter.passWordsAndCount("Word2 Word3"));
    }

    @Test
    public void passWordsVarArg_CountNew() {
        wordCounter.passWords("Word1");
        assertEquals(1, wordCounter.count("Word1"));

        wordCounter.passWords("Word2 Word3");
        assertEquals(1, wordCounter.count("Word1"));
        assertEquals(1, wordCounter.count("Word2"));
        assertEquals(1, wordCounter.count("Word3"));
    }

    @Test
    public void count_AfterTwoPass() {
        wordCounter.passWord("Word");
        wordCounter.passWord("Word");
        assertEquals(2, wordCounter.count("Word"));
    }

    @Test
    public void count_AfterTwoPassWords() {
        wordCounter.passWords("Word");
        assertEquals(1, wordCounter.count("Word"));

        wordCounter.passWords("Word", "Word", "Word2");
        assertEquals(3, wordCounter.count("Word"));
        assertEquals(1, wordCounter.count("Word2"));
    }
}
