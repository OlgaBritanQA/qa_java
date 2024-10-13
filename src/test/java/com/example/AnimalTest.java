package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class AnimalTest {

    private final String animalKind;
    private final List<String> typeOfFood;
    private final boolean hasException;
    private final String exceptionMessage;
    Animal animal = new Animal();

    public AnimalTest(String animalKind, List<String> typeOfFood, boolean hasException, String exceptionMessage) {
        this.animalKind = animalKind;
        this.typeOfFood = typeOfFood;
        this.hasException = hasException;
        this.exceptionMessage = exceptionMessage;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Травоядное", List.of("Трава", "Различные растения"), false, null},
                {"Хищник", List.of("Животные", "Птицы", "Рыба"), false, null},
                {"", null, true, "Неизвестный вид животного, используйте значение Травоядное или Хищник"}
        });
    }

    @Test
    public void animalKindTest() throws Exception {
        if (hasException) {
            Exception unknownLionSex = Assert.assertThrows(Exception.class, () -> animal.getFood(animalKind));
            Assert.assertTrue(unknownLionSex.getMessage().contains(exceptionMessage));
        } else {
            Animal animal = new Animal();
            Assert.assertEquals(typeOfFood, animal.getFood(animalKind));
        }
    }

    @Test
    public void getFamilyTest(){
        Assert.assertEquals(animal.getFamily(), "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи");
    }
}
