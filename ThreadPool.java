package com.chico.client.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * @author He Yu
 * @date 2020/12/16 14:31
 * <p>
 * 线程池
 */
public class ThreadPool {

    /**
     * 线程工厂
     */
    static ThreadFactory chicoThreadFactory = new ThreadFactoryBuilder().setNameFormat("my_pool_0").build();


    public static ExecutorService pool = new ThreadPoolExecutor(
            // 核心线程池大小
            2,
            // 最大线程池大小
            100,
            // 线程最大空闲时间
            1000L,
            // 时间单位
            TimeUnit.MILLISECONDS,
            // 线程等待队列
            new LinkedBlockingQueue<Runnable>(1024),
            // 线程创建工厂
            chicoThreadFactory,
            // 拒绝策略(直接抛出异常)
            new ThreadPoolExecutor.AbortPolicy()
    );


}
