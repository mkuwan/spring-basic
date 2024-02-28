package org.example.springbasic.sample;

import jdk.jshell.spi.ExecutionControl;

public class CalcServiceSample {

    /**
     * 足し算
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b)  {
        return a + b;
    }

    /**
     * 引き算
     * @param a
     * @param b
     * @return
     */
    public int subtract(int a, int b)  {
        return a- b;
    }

    /**
     * とにかくとっても難しい計算をするメソッドです
     *  a=1, b=5 の時は色々計算して1200が返ってきます
     * @param a
     * @param b
     * @return
     */
    public int anyCalc(int a, int b){
        return 0;
    }

    /**
     * 掛け算
     * @param a
     * @param b
     * @return
     */
    public int multiply(int a, int b)  {
        return a * b;
    }

    /**
     * 割り算
     * @param a
     * @param b
     * @return
     */
    public int divide (int a, int b) {

        if(b == 0){
            throw new ArithmeticException("0で割ることはできません");
        }

        return a / b;

    }


    public int troubleCalc(int a, int b) throws Exception {

        if (a == 0){
            throw new IllegalArgumentException("aは0であってはいけません");
        }

        if (b == 0){
            throw new ArithmeticException("bは0であってはいけません");
        }

        if (a + b > 100) {
            throw new Exception("a + b は100を超えてはいけません");
        }

        return (a * b) / b;
    }



}
