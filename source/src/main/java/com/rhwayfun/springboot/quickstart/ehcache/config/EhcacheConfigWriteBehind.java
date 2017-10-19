package com.rhwayfun.springboot.quickstart.ehcache.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by chubin on 2017/5/20.
 */
@Configuration
public class EhcacheConfigWriteBehind {

    @Bean
    public Cache<String, Object> cache(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .using(PooledExecutionServiceConfigurationBuilder
                        .newPooledExecutionServiceConfigurationBuilder()
                        .pool("writeBack", 1, 5).build())
                .build(true);
        CacheConfigurationBuilder<String, Object> cacheConfig = 
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, Object.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1000, EntryUnit.ENTRIES))
                .withDispatcherConcurrency(10)
                .withExpiry(Expirations
                        .timeToLiveExpiration(Duration.of(1000 * 10, TimeUnit.MILLISECONDS)))
                .withLoaderWriter(new CacheLoaderWriter<String, Object>() {
                    @Override
                    public Object load(String s) throws Exception {
                        return null;
                    }

                    @Override
                    public Map<String, Object> loadAll(Iterable<? extends String> iterable) 
                            throws Exception {
                        return null;
                    }

                    @Override
                    public void write(String s, Object o) throws Exception {
                        // TODO: 2017/5/21  
                    }

                    @Override
                    public void writeAll(Iterable<? extends Map.Entry<? extends String, ?>> iterable) 
                            throws Exception {

                    }

                    @Override
                    public void delete(String s) throws Exception {
                        // TODO: 2017/5/21
                    }

                    @Override
                    public void deleteAll(Iterable<? extends String> iterable) 
                            throws Exception {

                    }
                })
                .add(WriteBehindConfigurationBuilder
                        //.newUnBatchedWriteBehindConfiguration() // 实现write和delete
                        .newBatchedWriteBehindConfiguration(3, TimeUnit.SECONDS, 2) // 实现writeAll和deleteAll
                        .queueSize(5)
                        .concurrencyLevel(5)
                        .useThreadPool("writeBack")
                        .build());
        Cache<String, Object> cache = cacheManager.createCache("springboot", cacheConfig);
        return cache;
    }

}
