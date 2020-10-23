package ru.job4j.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса HeavyCar.
 */
public class HeavyCarTest {

    /**
     * Проверяется создание автомобиля.
     */
    @Test
    public void whenCreateCarThenCreateSuccess() {
        Car car = new HeavyCar("GAZ", "Sobol", 2);
        Assert.assertThat(car.getBrand(), is("GAZ"));
        Assert.assertThat(car.getModel(), is("Sobol"));
        Assert.assertThat(car.getSize(), is(2));
    }

    /**
     * Проверяем наличие текущей парковки.
     */
    @Test
    public void whenParkedCarThenParkedSuccess() {
        Car car = new LightCar("Gaz", "Sobol");
        car.setParkedOn("Heavy car parking");
        Assert.assertThat(car.getParkedOn(), is("Heavy car parking"));
    }
}