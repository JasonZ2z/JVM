package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author xzheng
 */
public class MyArrayList {
    //ConcurrentModificationException
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                for (String s : list) {
                    System.out.println(s);
                }
            }).start();
        }
    }
}
