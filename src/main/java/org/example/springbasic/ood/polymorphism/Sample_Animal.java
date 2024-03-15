package org.example.springbasic.ood.polymorphism;

import org.junit.jupiter.api.Test;

public class Sample_Animal {

    abstract class Animal {
        String name;
        abstract String getName();
    }

    interface Food {
        void eat();
    }

    interface Voice {
        void sound();
    }

    class Dog extends Animal implements Food, Voice {
        Dog(String name) {
            this.name = name;
        }
        @Override
        String getName() {
            return this.name;
        }
        @Override
        public void eat() {
            System.out.println("ドッグフード");
        }

        @Override
        public void sound() {
            System.out.println("ワンワン");
        }
    }


    @Test
    void test() {
        Dog dog = new Dog("ポチ");
        System.out.println(dog.getName());
        dog.eat();
        dog.sound();
    }

}
