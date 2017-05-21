package com.rhwayfun.springboot.ehcache.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by chubin on 2017/5/20.
 */
@Configuration
public class EhcacheConfigWriteThrough {

    @Bean
    public Cache<String, Object> cache(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
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
                            throws BulkCacheLoadingException, Exception {
                        return null;
                    }

                    @Override
                    public void write(String s, Object o) throws Exception {

                    }

                    @Override
                    public void writeAll(Iterable<? extends Map.Entry<? extends String, ?>> iterable)
                            throws BulkCacheWritingException, Exception {

                    }

                    @Override
                    public void delete(String s) throws Exception {

                    }

                    @Override
                    public void deleteAll(Iterable<? extends String> iterable)
                            throws BulkCacheWritingException, Exception {

                    }
                });
        Cache<String, Object> cache = cacheManager.createCache("springboot", cacheConfig);
        return cache;
    }

}
