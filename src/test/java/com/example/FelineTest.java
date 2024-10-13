package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FelineTest {

    Feline feline = new Feline();

    @Test
    public void getFamilyTest(){
        Assert.assertEquals(feline.getFamily(), "Кошачьи");
    }

    @Test
    public void getKittensTest(){
        Assert.assertEquals(feline.getKittens(), 1);
    }

    @Test
    public void getKittensCountTest(){
        Assert.assertEquals(feline.getKittens(3), 3);
    }

    @Test
    public void eatMeatTest() throws Exception{
        Assert.assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }

}
