package ru.job4j.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса LightCar.
 */
public class LightCarTest {

    /**
     * Проверяем создание автомобиля.
     */
    @Test
    public void whenCreateCarThenCreateSuccess() {
        Car car = new LightCar("Honda", "Accord");
        Assert.assertThat(car.getBrand(), is("Honda"));
        Assert.assertThat(car.getModel(), is("Accord"));
        Assert.assertThat(car.getSize(), is(1));
    }

    /**
     * Проверяем наличие текущей парковки у автомобиля.
     */
    @Test
    public void whenParkedCarThenParkedSuccess() {
        Car car = new LightCar("Honda", "Accord");
        car.setParkedOn("Light car parking");
        Assert.assertThat(car.getParkedOn(), is("Light car parking"));
    }
}