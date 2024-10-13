package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class LionTest {

    Feline feline = Mockito.mock(Feline.class);
    private final String sex;
    private final boolean hasException;
    private final String exceptionMessage;
    private final boolean hasMane;

    public LionTest(String sex, boolean hasException, String exceptionMessage, boolean hasMane) {
        this.sex = sex;
        this.hasException = hasException;
        this.exceptionMessage = exceptionMessage;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", false, null, true},
                {"Самка", false, null, false},
                {"", true, "Используйте допустимые значения пола животного - самец или самка", false}
        });
    }

    @Test
    public void lionSexTest() throws Exception {
        if (hasException) {
            Exception unknownLionSex = Assert.assertThrows(Exception.class, () -> new Lion(sex, feline));
            Assert.assertTrue(unknownLionSex.getMessage().contains(exceptionMessage));
        } else {
            Lion lion = new Lion(sex, feline);
            Assert.assertEquals(hasMane, lion.doesHaveMane());
        }
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самка", feline);
        Mockito.when(feline.getKittens()).thenReturn(2);
        Assert.assertEquals(2, lion.getKittens());
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion("Самка", feline);
        List<String> predatorFoods = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(predatorFoods);
        Assert.assertEquals(predatorFoods, lion.getFood());
    }
}
