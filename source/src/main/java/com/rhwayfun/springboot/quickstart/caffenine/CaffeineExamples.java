package com.rhwayfun.springboot.quickstart.caffenine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.rhwayfun.springboot.quickstart.memory.RiderBasicInfoDTO;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by chubin on 2017/8/20.
 */
public class CaffeineExamples {

    private static final LoadingCache<Integer, RiderBasicInfoDTO> loadingCache;

    static {
        loadingCache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build(new CacheLoader<Integer, RiderBasicInfoDTO>() {
                    @Override
                    public RiderBasicInfoDTO load(Integer id) throws Exception {
                        RiderBasicInfoDTO rider = new RiderBasicInfoDTO();
                        rider.setId(id);
                        rider.setCityId(1);
                        rider.setStatus((byte) 5);
                        System.out.println("从数据库查询--rider:" + id);
                        return rider;
                    }

                    @Override
                    public Map<Integer, RiderBasicInfoDTO> loadAll(Iterable<? extends Integer> keys) throws Exception {
                        return null;
                    }
                });
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    RiderBasicInfoDTO dto = loadingCache.get(new Random().nextInt(500000));
                }
            }
        }).start();
    }

}
