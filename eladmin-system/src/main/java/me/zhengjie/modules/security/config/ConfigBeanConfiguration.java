/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zhengjie.modules.security.config;

import me.zhengjie.modules.security.config.bean.LoginProperties;
import me.zhengjie.modules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @apiNote 配置文件转换Pojo类的 统一配置 类
 * 这个配置Bean 初始化了 LoginProperties 和 SecurityProperties
 * 之前还奇怪为什么在AuthorizationController 这个里面 LoginProperties这个实体类已经初始化了
 *
 * @author: liaojinlong
 * @date: 2020/6/10 19:04
 */
@Configuration
public class ConfigBeanConfiguration {

    /**
     * ConfigurationProperties 这个注解会读取application-dev.yml 里面的login下面的配置信息，并将对应的属性写入到 LoginProperties
     * 这个Bean里面去，并注入到Spring容器里面
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "login", ignoreUnknownFields = true)
    public LoginProperties loginProperties() {
        System.out.println("ConfigBeanConfiguration配置类的loginProperties Bean 注入容器");
        return new LoginProperties();
    }

    /**
     * 这类就是截取 application-dev.yml 配置文件里面的jwt 部分的属性，并加载到SecurityProperties这个Bean里面
     * @return
     *
     */
    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
