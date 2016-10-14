package com.gmail.at.ivanehreshi.wordcounter.domain;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentWordCounter extends WordCounterImpl{
    protected final ReadWriteLock rwLock;

    public ConcurrentWordCounter() {
        super();

        rwLock = new ReentrantReadWriteLock();
    }

    @Override
    public int count(String word) {
        int ret;
        rwLock.readLock().lock();
        ret = super.count(word);
        rwLock.readLock().unlock();
        return ret;
    }

    @Override
    public int passWord(String word) {
        int ret;
        rwLock.writeLock().lock();
        ret = super.passWord(word);
        rwLock.writeLock().unlock();
        return ret;
    }
}
