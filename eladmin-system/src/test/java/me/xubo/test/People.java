package me.xubo.test;

import java.beans.ConstructorProperties;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @Author Druid_Xu
 * @Description TODO
 * @Date 2020/9/28 下午 02:22
 */
@Getter
@Setter
//@RequiredArgsConstructor()
@AllArgsConstructor
//@NoArgsConstructor
public class People {

    private String name;
    private int age;
    private String sex;
    private String address;

    public People() {
    }
}
