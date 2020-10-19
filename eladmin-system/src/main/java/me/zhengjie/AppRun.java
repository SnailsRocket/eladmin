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
package me.zhengjie;

import io.swagger.annotations.Api;
import me.zhengjie.annotation.rest.AnonymousGetMapping;
import me.zhengjie.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启审计功能 -> @EnableJpaAuditing
 *
 * @author Zheng Jie
 * @date 2018/11/15 9:20:19
 * https://github.com/elunez/eladmin
 * https://el-admin.vip/guide/hdsc.html#%E6%95%B0%E6%8D%AE%E4%BA%A4%E4%BA%92
 *
 * eladmin-common 为系统的公共模块，各种工具类，公共配置存在该模块
 * eladmin-system 为系统核心模块也是项目入口模块，也是最终需要打包部署的模块
 * eladmin-logging 为系统的日志模块，其他模块如果需要记录日志需要引入该模块
 * eladmin-tools 为第三方工具模块，包含：图床、邮件、云存储、本地存储、支付宝
 * eladmin-generator 为系统的代码生成模块，代码生成的模板在 system 模块中
 *
 * Spring 实战3   完
 * netty 实战
 * 阿里巴巴Java嵩山版  OOP规约
 *
 * CompletableFuture   https://www.jianshu.com/p/6f3ee90ab7d3/
 * CountDownLatch
 * 集合
 * eladminpro  validcode 中 redis 乱码问题
 * 
 * 微服务事务   https://www.geekdigging.com/2019/09/08/1787080252/
 *
 *
 *
 * 3 time
 *
 * gcs      max 5.4 + 0.4
 * zlgcs    avg 4.8 + 0.3
 *
 * CAP
 * 一致性  Consistency
 * 可用性  Availability
 * 分区容忍性    Partition tolerance
 * 2pc
 *
 * 集合、分布式事务、redis 、 Spring 、SpringCloudAlibaba
 * CompletableFeture 、
 *
 */
@EnableAsync
@RestController
@Api(hidden = true)
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service started successfully";
    }
}
