package com.chico.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author heyu
 * @date 2022/11/3
 */
@Configuration
public class ThreadPoolConfig {


    public static final String POOL = "ZK_POOL";

    @Bean(ThreadPoolConfig.POOL)
    public ThreadPoolExecutor pool1() {
        var pool = new ThreadPoolExecutor(
                // 核心线程池大小
                4,
                // 最大线程池大小
                100,
                // 线程最大空闲时间
                5,
                // 时间单位
                TimeUnit.SECONDS,
                // 线程等待队列
                new LinkedBlockingQueue<>(1024),
                // 拒绝策略(直接抛出异常)
                new ThreadPoolExecutor.AbortPolicy()
        );
        return pool;
    }

}
