package me.xubo.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.lang.Collections;

/**
 * @Author Druid_Xu
 * @Description TODO
 * @Date 2020/9/28 下午 02:23
 */
public class JdkNewFeture {

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        List<String> list_result = new LinkedList<>();
        list.add(new People("Druid",23,"男","1837039333"));
        list.add(new People("xubo",24,"男","41324151"));
        list.add(new People("chenzi",25,"男","4123513235"));
        list.add(new People("kun",26,"男","12343543"));
        list_result = list.stream().map(People::getName).collect(Collectors.toList());

        for (String s : list_result) {
            System.out.println(s);
        }
    }

}
