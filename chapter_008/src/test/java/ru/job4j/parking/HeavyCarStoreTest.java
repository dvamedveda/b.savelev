package ru.job4j.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса HeavyCarStore.
 */
public class HeavyCarStoreTest {

    /**
     * Проверяем создание стоянки.
     */
    @Test
    public void whenCreateStoreThenCreateSuccess() {
        CarStore store = new HeavyCarStore(2);
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(store.placesAvailable(), is(2));
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(true));
    }

    /**
     * Проверяем наличие мест на стоянке, когда они есть.
     */
    @Test
    public void whenPlaceHeavyCarThenCanOneMoreHeavy() {
        CarStore store = new HeavyCarStore(2);
        Car car = new HeavyCar("GAZ", "Sobol");
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(true));
        Assert.assertThat(store.placesAvailable(), is(1));
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(added, is(true));
    }

    /**
     * Проверяем наличие мест на стоянке, когда их нет.
     */
    @Test
    public void whenPlaceAllCarThenCannotMore() {
        CarStore store = new HeavyCarStore(1);
        Car car = new HeavyCar("GAZ", "Sobol");
        Car car2 = new HeavyCar("URAL", "300");
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(false));
        Assert.assertThat(store.placesAvailable(), is(0));
        Assert.assertThat(store.placesTotal(), is(1));
        Assert.assertThat(added, is(true));
        boolean added2 = store.place(car2);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(false));
        Assert.assertThat(store.placesAvailable(), is(0));
        Assert.assertThat(store.placesTotal(), is(1));
        Assert.assertThat(added2, is(false));
    }

    /**
     * Проверяем что на грузовую стоянку нельзя разместить легковой автомобиль.
     */
    @Test
    public void whenPlaceLightCarThenCannotPlace() {
        CarStore store = new HeavyCarStore(2);
        Car car = new LightCar("Honda", "Accord");
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(true));
        Assert.assertThat(store.placesAvailable(), is(2));
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(added, is(false));
    }
}