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
 * 为什么会出现 list.size()<1000 这种情况了
 * 解析：当一个Thread add(97) ,另一个add(98)  在这之前list最后一次add是(96),但是下次add()的时候，可能97 98 都没有执行完，还是停留在96
 * 也不应该啊，97 98 执行完也会增加size，只能使用线程安全来解决这个现象
 *
 */
public class UnsafeArrayListTest {

    public static List<String> list = new ArrayList<>();
    public static List<String> vector = new Vector<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
//            int finalI = i;
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
        /*System.out.println("af");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        System.out.println(list.size());
        System.out.println(vector.size());


    }

}
