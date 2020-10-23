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
        Assert.assertThat(store.canNextHeavy(2), is(true));
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
        Assert.assertThat(store.canNextHeavy(2), is(false));
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
        Car car = new HeavyCar("Gaz", "Sobol", 2);
        boolean added = store.place(car);
        Assert.assertThat(store.canNextLight(), is(false));
        Assert.assertThat(store.canNextHeavy(car.getSize()), is(false));
        Assert.assertThat(store.placesAvailable(), is(0));
        Assert.assertThat(store.placesTotal(), is(2));
        Assert.assertThat(added, is(true));
    }

    @Test
    public void whenPlaceLightCarThenCanPlaceHeavy() {
        CarStore store = new LightCarStore(4);
        Car lightOne = new LightCar("Honda", "Odissey");
        Car lightTwo = new LightCar("Honda", "Accord");
        Car heavyOne = new HeavyCar("GAZ", "100", 2);
        boolean addedOne = store.place(lightOne);
        boolean addedTwo = store.place(lightTwo);
        Assert.assertThat(store.canNextLight(), is(true));
        Assert.assertThat(store.canNextHeavy(heavyOne.getSize()), is(true));
        Assert.assertThat(store.placesAvailable(), is(2));
        Assert.assertThat(store.placesTotal(), is(4));
        Assert.assertThat(addedOne, is(true));
        Assert.assertThat(addedTwo, is(true));
    }

    @Test
    public void whenPlaceHeavyCarThenCanPlaceLight() {
        CarStore store = new LightCarStore(6);
        Car heavyOne = new HeavyCar("GAZ", "100", 4);
        Car lightOne = new LightCar("Honda", "Odissey");
        boolean addedOne = store.place(heavyOne);
        boolean addedTwo = store.place(lightOne);
        Assert.assertThat(store.canNextLight(), is(true));
        Assert.assertThat(store.canNextHeavy(heavyOne.getSize()), is(false));
        Assert.assertThat(store.placesAvailable(), is(1));
        Assert.assertThat(store.placesTotal(), is(6));
        Assert.assertThat(addedOne, is(true));
        Assert.assertThat(addedTwo, is(true));
    }
}