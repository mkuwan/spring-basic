package org.example.springbasic.ood.polymorphism;

import org.junit.jupiter.api.Test;

public class Sample_Animal {
    /**
     * 抽象クラス
     */
    abstract class Animal {
        String name;
        abstract String getName();
    }

    /**
     * インターフェース
     */
    interface Food {
        void eat();
    }

    /**
     * インターフェース
     */
    interface Voice {
        void sound();
    }

    interface Fake {
        void fake();
    }

    /**
     * 抽象クラスとインターフェースの継承
     */
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

    class Cat extends Animal implements Food, Voice {
        Cat(String name) {
            this.name = name;
        }
        @Override
        String getName() {
            return this.name;
        }
        @Override
        public void eat() {
            System.out.println("キャットフード");
        }

        @Override
        public void sound() {
            System.out.println("ニャーニャー");
        }
    }


    @Test
    void test() {
        Dog dog = new Dog("ポチ");
        System.out.println(dog.getName());
        dog.eat();
        dog.sound();
    }

    @Test
    void test2() {
        Animal dog = new Dog("ポチ");
        System.out.println(dog.getName());

        ((Food) dog).eat();

        ((Voice) dog).sound();

        ((Fake) dog).fake();
    }

    @Test
    void test3() {
        Food dog = new Dog("ポチ");
        ((Animal) dog).getName();
        dog.eat();
        ((Voice) dog).sound();
    }

    @Test
    void test4() {
        Voice dog = new Dog("ポチ");
        ((Animal) dog).getName();
        ((Food) dog).eat();
        dog.sound();
    }

}
