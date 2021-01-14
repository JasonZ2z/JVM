package jvm;

import java.util.ArrayList;

/**
 *
 * @author xzheng
 * @since 1
 */
public class GCDemo {
    public static void main(String[] args) {
        ArrayList<Byte[]> list = new ArrayList<>();
        Byte[] bytes = new Byte[10 * 1024 * 1024];
        while (true) {
            list.add(bytes);
        }
    }
}