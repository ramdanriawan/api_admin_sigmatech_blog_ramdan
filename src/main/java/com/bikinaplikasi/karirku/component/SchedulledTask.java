package com.bikinaplikasi.karirku.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 5000)
//    public void reportTask() {
//        System.out.println("The time is now: " + dateFormat.format(new Date()));
//    }

    @Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(fixedRate = 6000)
    public void evictAllcachesAtIntervals() {
//        evictAllCaches();
    }
}
