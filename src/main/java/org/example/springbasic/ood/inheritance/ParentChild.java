package org.example.springbasic.ood.inheritance;

import org.junit.jupiter.api.Test;

public class ParentChild {

    private class Parent {
        public void print() {
            System.out.println("Parent");
        }
    }

    private class Child extends Parent {
        @Override
        public void print() {
            System.out.println("Child");
        }
    }

    @Test
    void test() {
        Parent parent = new Parent();
        parent.print();

        Child child = new Child();
        child.print();
    }

    interface Job {
        void work();
    }

    class JobClass {
        void work() {
            System.out.println("JobClass");
        }
    }

    class EmployeeJob extends JobClass {
        @Override
        void work() {
            System.out.println("EmployeeJob");
        }

        void work2() {
            System.out.println("EmployeeJob2");
        }
    }

    @Test
    void test1() {
        JobClass jobClass = new JobClass();
        jobClass.work();

        EmployeeJob employeeJob = new EmployeeJob();
        employeeJob.work();

        JobClass jobClass2 = new EmployeeJob();
        jobClass2.work();

//        EmployeeJob employeeJob2 =  new JobClass();
    }



    class Employee implements Job {
        @Override
        public void work() {
            System.out.println("お仕事");
        }

        public void print() {
            System.out.println("Employee");
        }
    }

    class Engineer extends Employee {

    }


    @Test
    void test2() {
        Employee employee = new Employee();
        employee.work();
        employee.print();

        Job worker = new Employee();
        worker.work();
//        worker.print();

//        worker = new Engineer();
//        worker.work();
    }




}
