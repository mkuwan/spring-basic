package org.example.springbasic.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcServiceSampleTest {

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

        // act & assertion
        assertEquals(expected, calcServiceSample.add(a, b));
    }

    @Test
    void add_with_override() {
        // arrange
        CalcServiceSample calcServiceSample = new CalcServiceSample() {
            @Override
            public int add(int a, int b) {
                return 100;
            }
        };

        // act & assertion
        assertEquals(100, calcServiceSample.add(1, 2));
    }


    @Mock
    CalcServiceSample calcServiceSampleMock;

    @Test
    void add_with_mock(){
        // arrange
        when(calcServiceSampleMock.add(1, 2)).thenReturn(100);
        when(calcServiceSampleMock.add(100, 20)).thenReturn(50);

        // act & assertion
        assertEquals(100, calcServiceSampleMock.add(1, 2));
        assertEquals(50, calcServiceSampleMock.add(100, 20));
    }

    @Test
    void subtract() {
    }

    @Test
    void multiply() {
    }

    @Test
    void divide() {
    }
}