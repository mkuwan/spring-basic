package org.example.springbasic.silver;

import jakarta.persistence.Temporal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


@SpringBootTest
public class Q31 {

    @Test
    public void question31(){
        var alphabet = new ArrayList<>(
                Arrays.asList(
                        new Integer[]{1, 2, 5, 4, 3}
                ));
        // -1, 0, 1
        // a:1, b: 2  b - a = 1,  つまりaはbより右である: a b
        alphabet.sort((a, b) -> b.compareTo(a));

        String s = "test";
        System.out.println(s);

        for (Integer i : alphabet) {
            System.out.println(i);
        }

        alphabet.forEach(i -> System.out.println(i * 2));

        alphabet.forEach(System.out::println);

        alphabet.forEach(System.out::println);

        alphabet.forEach(this::kakunin);
    }

    private void kakunin(Object o){
        System.out.println(o);
    }


}
