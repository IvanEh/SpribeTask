package com.gmail.at.ivanehreshi.wordcounter.domain;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Concurrent wrapper around WordCounterImpl
 */
@ManagedBean(name="concurrentWordCounter")
@ApplicationScoped
public class ConcurrentWordCounter extends WordCounterImpl{
    // A better implementation would use java.util.concurrent.ConcurrentHashMap
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
