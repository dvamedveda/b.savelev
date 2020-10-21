package ru.job4j.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса LightCarStore.
 */
public class LightCarStoreTest {

    /**
     * Проверяем создание стоянки легковых автомобилей.
     */
    @Test
    public void whenCreateStoreThenCreateSuccess() {
        CarStore store = new LightCarStore(2);
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(store.placesAvailable(), is(2));
        Assert.assertThat(store.canNextLight(), is(true));
        Assert.assertThat(store.canNextHeavy(), is(true));
    }

    /**
     * Проверяем размещение легкового автомобиля и наличие свободных мест.
     */
    @Test
    public void whenPlaceLightCarThenCanOneMoreLight() {
        CarStore store = new LightCarStore(2);
        Car car = new LightCar("Honda", "Accord");
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(true));
        Assert.assertThat(store.canNextHeavy(), is(false));
        Assert.assertThat(store.placesAvailable(), is(1));
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(added, is(true));
    }

    /**
     * Проверяем размещение грузового автомобиля и отсутствие свободных мест.
     */
    @Test
    public void whenPlaceHeavyCarThenCannotPlaceMore() {
        CarStore store = new LightCarStore(2);
        Car car = new HeavyCar("Gaz", "Sobol");
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(), is(false));
        Assert.assertThat(store.placesAvailable(), is(0));
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(added, is(true));
    }
}