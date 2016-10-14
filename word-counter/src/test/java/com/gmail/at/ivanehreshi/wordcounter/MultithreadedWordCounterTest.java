package com.gmail.at.ivanehreshi.wordcounter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class MultithreadedWordCounterTest {
    private Class<? extends WordCounter> wordCounterClass;
    private WordCounter wordCounter;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
//                {WordCounterImpl.class}, // this should not pass
                {ConcurrentWordCounter.class}
        });
    }

    public MultithreadedWordCounterTest(Class<? extends WordCounter> wordCounterClass) {
        this.wordCounterClass = wordCounterClass;
    }

    @Before
    public void setUp() throws Exception {
        wordCounter = wordCounterClass.newInstance();
    }

    @After
    public void tearDown(){
        wordCounter = null;
    }

    @Test
    public void multithreadedCountPass() throws InterruptedException {
        int repeats = 100;
        int threadCount = 4;

        Thread[] countThreads = new Thread[threadCount];
        Thread[] passThreads = new Thread[threadCount];
        String[] words = {"Word1", "Word2", "Word3"};

        for (int i = 0; i < threadCount; i++) {
            Runnable countTask = new CyclicTask((k) -> wordCounter.count(words[k]), words.length);
            Runnable repeatedCountTask = times(countTask, repeats*words.length);
            countThreads[i] = new Thread(repeatedCountTask);

            Runnable passTask = new CyclicTask((k) -> wordCounter.passWord(words[k]), words.length);
            Runnable repeatedPassTask = times(passTask, repeats*words.length);
            passThreads[i] = new Thread(repeatedPassTask);
        }

        Arrays.stream(countThreads).forEach(Thread::start);
        Arrays.stream(passThreads).forEach(Thread::start);

        Stream.concat(Arrays.stream(countThreads), Arrays.stream(passThreads))
              .forEach((thread) -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        assertTrue(false);
                    }
              });

        int n = repeats*threadCount;

        assertEquals(n, wordCounter.count("Word1"));
        assertEquals(n, wordCounter.count("Word2"));
        assertEquals(n, wordCounter.count("Word3"));
        assertEquals(0, wordCounter.count("Word"));
    }

    private static class CyclicTask implements Runnable {
        private final Task task;
        private final int max;
        private int i = 0;

        public CyclicTask(Task task, int max) {
            this.max = max;
            this.task = task;
        }

        @Override
        public void run() {
            task.doTask(i);
            i++;
            if(i >= max) {
                i = 0;
            }
        }

        interface Task {
            void doTask(int i);
        }
    }

    /**
     * Create a new Runnable that executes <b>n</b> times the target Runnable
     * @param r - target runnable
     * @param n - how many times execute <b>r</b>
     * @return a new Runnable that executes <b>n</b> times the target Runnable
     */
    private static Runnable times(Runnable r, int n) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < n; i++) {
                    r.run();
                }
            }
        };
    }

}
