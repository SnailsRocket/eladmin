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
package me.zhengjie.config;

import me.zhengjie.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * 在验证的时候默认给admin用户放行
 * elPermissions.contains("admin")  这个是后来加上的，
 * 由于每个接口都需要给admin用户放行，而使用hasAnyRole('admin','user:list')每次都需要重复的添加 admin 权限
 * 因此在新版本 (2.3) 中加入了自定义权限验证方式，在验证的时候默认给拥有admin权限的用户放行
 *
 */
@Service(value = "el")
public class ElPermissionConfig {

    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限  JDK1.8 新特性 Stream API
        List<String> elPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限或者是admin
        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }
}
