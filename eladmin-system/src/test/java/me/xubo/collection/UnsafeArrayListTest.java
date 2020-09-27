package me.xubo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Author: Xubo
 * @Date: 2020/9/27 10:27
 *
 * 测试 ArrayList  与  Vector的线程安全
 * for循环里面创建匿名的线程  遍历100 次 不会出现异常
 *
 */
public class UnsafeArrayListTest {

    public static List<String> list = new ArrayList<>();
    public static List<String> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(Thread.currentThread().getName());
                vector.add(Thread.currentThread().getName());
            }).start();
        }

//        等待全部运行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        System.out.println(vector.size());


    }

}
