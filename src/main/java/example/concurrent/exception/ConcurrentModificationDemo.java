package example.concurrent.exception;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        //list.add("c");
        for (String i : list) {
            if(i.equals("b")) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}