package org.example.springbasic.ood.polymorphism;

import org.junit.jupiter.api.Test;

public class Q7_13 {

    interface Worker {
        void work();
    }

    class Employee implements Worker {
        @Override
        public void work() {
            System.out.println("work");
        }
    }

    class Engineer extends Employee { }

    @Test
    void test() {
        Worker worker = new Employee();
        worker.work();

        worker = new Engineer();
        worker.work();
    }

}
