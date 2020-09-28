/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

/**
 * 异步任务线程池装配类
 * @author https://juejin.im/entry/5abb8f6951882555677e9da2
 * @date 2019年10月31日15:06:18
 *
 * 400
 * 500
 * 1000
 *
 *
 */
@Slf4j
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    /** 注入配置类 */
    private final AsyncTaskProperties config;

    /** 构造器注入线程池配置类
     *  这个地方为什么不能使用@Autowired 直接注入，只能使用构造器注入
     *  把 final关键字 去掉就可以 使用@Autowired  和  @Resource 这两个注解
     *  final关键字的作用是 AsyncTaskProperties 这个类不能被继承
     *  final 还可以修饰 method variable  表示method variable 不能被修改
     *  加 final 有什么好处
     *
     * */
    public AsyncTaskExecutePool(AsyncTaskProperties config) {
        this.config = config;
    }

    /**
     * 配置异步线程池
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("el-async-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 获取异步未捕获异常处理，打印日志信息
     * lambda 表达式
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("===="+throwable.getMessage()+"====", throwable);
            log.error("exception method:"+method.getName());
        };
    }
}
