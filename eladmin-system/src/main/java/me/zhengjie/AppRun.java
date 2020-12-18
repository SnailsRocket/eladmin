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
 * netty 实战     p8
 * 阿里巴巴Java嵩山版  OOP规约
 * 项目1.增加一个功能 基于OCR 识别 图片中的文字并填充到表单中
 *
 *
 * CompletableFuture   https://www.jianshu.com/p/6f3ee90ab7d3/
 * CountDownLatch
 * 集合
 * eladminpro  validcode 中 redis 乱码问题
 * 
 * 微服务事务   https://www.geekdigging.com/2019/09/08/1787080252/
 *
 * ProConSql 项目使用renren-generate 自动生成的前后端代码， 持久层框架使用的是Mybatis-plus
 *  也生成了vue 页面，但是这个暂时不会用，先写 html+ js
 *  弹出框暂未实现
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
 * redis 持久化方式 ： RDF(默认)  AOF
 * 当同时开启两种持久化方式的时候，默认执行AOF 持久化方案
 * RDF 与 AOF 的区别
 *   RDF是过一段时间就持久化，将数据写入 dump.rdb这个文件中
 *   AOF 是将 redis 的命令(写命令 ，查命令应该不会写)写成日志，记录到磁盘里面，当redis重启的时候回去执行这些日志命令
 *   AOF 比 RDF 文件大，且恢复速度慢， 数据大时，AOF比RDF启动效率低
 * redis 的内存淘汰策略 (看具体的业务场景)
 *   当内存不足的时候，移除最近最少使用的key (一般都是采用此种方式)
 *   当内存不足的时候，随机移除key
 *   当内存不足的时候，随机移除设置过期时间的key (设置键过期空间的操作方式)
 *   当内存不足的时候，移除设置过期时间的key中快要过期的key
 * redis 主从复制
 *   master 节点的数据会复制到 slave节点
 * redis 哨兵模式
 *   哨兵至少需要三个实例， 哨兵+ 主从 只是保证了 redis集群的高可用，不能保证数据的零丢失
 *   哨兵集群模式 当主节点(master) 挂掉了，salve节点会通过选举，选出新的master节点，
 *   主服务器负责写 ，从服务器负责读数据
 * redis 缓存雪崩
 *    缓存雪崩是指缓存同一时间内大面积失效，所有的请求都会打到数据库上
 * redis 缓存击穿
 *    指缓存和数据库中都没有的数据，导致所有的请求都打到数据库上
 * 本是后山人，偶做前堂客
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
