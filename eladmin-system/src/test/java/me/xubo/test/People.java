package me.xubo.test;

import java.beans.ConstructorProperties;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

/**
 * @Author Druid_Xu
 * @Description TODO
 * @Date 2020/9/28 下午 02:22
 */
@Data
public class People {

    private String name;
    private int age;
    private String sex;
    private String address;

    public People(String name, int age, String sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }
}
