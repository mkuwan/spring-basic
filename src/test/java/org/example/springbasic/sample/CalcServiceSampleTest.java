package org.example.springbasic.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalcServiceSampleTest {

    @Test
    void add_success(){
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        // act
        int actual = calcServiceSample.add(1, 2);
        int actual2 = calcServiceSample.add(0, 0);

        // assertion
        assertEquals(3, actual);
        assertEquals(0, actual2);
    }

    /**
     * 正常系
     * CalcServiceSample.add()のテスト
     * コンディション: a=1, b=2, 期待値: 3
     * コンディション: a=0, b=0, 期待値: 0
     * コンディション: a=-1, b=-2, 期待値: -3
     * コンディション: a=1, b=-2, 期待値: -1
     * コンディション: a=-1, b=2, 期待値: 1
     * コンディション: a=0, b=2, 期待値: 2
     * コンディション: a=1, b=0, 期待値: 1
     */
    @ParameterizedTest
    @CsvSource({"1, 2, 3", "0, 0, 0", "-1, -2, -3", "1, -2, -1", "-1, 2, 1", "0, 2, 2", "1, 0, 1"})
    void add_with_valueList(int a, int b, int expected)  {
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        // act
        int actual = calcServiceSample.add(a, b);

        // assertion
        assertEquals(expected, actual);
    }



//    @Mock
//    CalcServiceSample calcServiceSampleMock;
//
//    @Test
//    void add_with_mock(){
//        // arrange
//        when(calcServiceSampleMock.add(1, 2)).thenReturn(100);
//        when(calcServiceSampleMock.add(100, 20)).thenReturn(50);
//
//        // act
//        var resultA = calcServiceSampleMock.add(1, 2);
//        var resultB = calcServiceSampleMock.add(100, 20);
//
//        // assertion
//        assertEquals(100,resultA);
//        assertEquals(50, resultB);
//    }

    /**
     * a=1, b=5 の時は色々計算して1200が返ってきます
     * a=10, b=8 の時は色々計算して100が返ってきます
     * addメソッドは1回呼ばれます
     */
    @Mock
    CalcServiceSample calcServiceSampleMock;
    @Test
    void anyCalc(){
        //arrange
        when(calcServiceSampleMock.anyCalc(1,5)).thenReturn(1200);
        when(calcServiceSampleMock.add(10,8)).thenReturn(100);

        //act
        var resultA = calcServiceSampleMock.anyCalc(1,5);
        var resultB = calcServiceSampleMock.add(10,8);

        //assertion
        assertEquals(1200,resultA);
        assertEquals(100,resultB);;

        // 呼ばれることを検証
        verify( calcServiceSampleMock, times(1)).add(10,8);

        // 1回呼ばれることを検証
        verify( calcServiceSampleMock, times(1)).anyCalc(1,5);
    }

    @Test
    void subtract_success_by_nakano() {
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        //act
        int result = calcServiceSample.subtract(1,1);
        // assertion
        assertEquals(0,result);
    }

    @ParameterizedTest
    @CsvSource({"1,2,-1","0,0,0","8,5,3"})
    void subtract_success_by_nakanoParam(int a,int b,int expected) {
        //arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        //act
        int result = calcServiceSample.subtract(a,b);

        //assertion
        assertEquals(expected,result);
    }




    @Test
    void divide() {
        //arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        //act
        try {
            System.out.println("割り算の前");
            int result = calcServiceSample.divide(5,0);

            System.out.println("割り算の後");
        }
        catch (Exception e){
            //assertion
            assertEquals("0で割ることはできません", e.getMessage());
        }

        System.out.println("例外発生した続き");

    }

    @Test
    void divide_assertThrow(){
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        // act
        var ex = assertThrows(ArithmeticException.class, ()-> calcServiceSample.divide(3,0));

        // assertion
//        assertEquals("例外が発生しました", ex.getMessage());

    }


//    public int troubleCalc(int a, int b) throws Exception {
//
//        if (a == 0){
//            throw new IllegalArgumentException("aは0であってはいけません");
//        }
//
//        if (b == 0){
//            throw new ArithmeticException("bは0であってはいけません");
//        }
//
//        if (a + b > 100) {
//            throw new Exception("a + b は100を超えてはいけません");
//        }
//
//        return (a * b) / b;
//    }


    /**
     * a=0 の時はIllegalArgumentExceptionが発生します
     */
    @Test
    void troubleCalcTestA(){
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        //act & assertion
        var ex = assertThrows(IllegalArgumentException.class,() -> calcServiceSample.troubleCalc(0,2));
        assertEquals("aは0であってはいけません",ex.getMessage());
    }

    /**
     * b=0 の時はArithmeticExceptionが発生します
     */
    @DisplayName("b=0 の時のテスト")
    @Test
    void troubleCalcTestB(){
        CalcServiceSample calcServiceSample = new CalcServiceSample();

        var ex = assertThrows(ArithmeticException.class,() -> calcServiceSample.troubleCalc(2,0));
         assertEquals("bは0であってはいけません",ex.getMessage());

    }
}