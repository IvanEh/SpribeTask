# WordCounter

- JSF frontend
 /faces/index.html allows passing words to the WordCounter and retrieve
 how many times was passed a particular word
 
- Domain
 + WordCounter - interface for the task
 + WordCounterImpl - non concurrent implementation
 + ConcurrentWordCounter - concurrent wrapper around WordCounterImpl that
 uses ReentrantReadWriteLock
 
A better implementation of ConcurrentWordCounter would 
use ```ConcurrentHashMap``` instead of HashMap/Collections.synchronizedMap(Map)
 because it is more optimized
