package ru.job4j.parking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса Parking.
 */
public class ParkingTest {

    /**
     * Проверяем размещение на парковке нескольких типов машин.
     */
    @Test
    public void whenParkFewCarsThenSuccess() {
        Parking parking = new Parking(2, 2);
        Car lightOne = new LightCar("Honda", "Accord");
        Car lightTwo = new LightCar("Toyota", "East");
        Car heavyOne = new HeavyCar("ZIL", "150", 2);
        Assert.assertThat(parking.checkPlace(lightOne), is(true));
        Assert.assertThat(parking.checkPlace(lightTwo), is(true));
        Assert.assertThat(parking.checkPlace(heavyOne), is(true));
        boolean addedOne = parking.park(lightOne);
        boolean addedTwo = parking.park(lightTwo);
        boolean addedThree = parking.park(heavyOne);
        Assert.assertThat(addedOne, is(true));
        Assert.assertThat(addedTwo, is(true));
        Assert.assertThat(addedThree, is(true));
    }

    /**
     * Проверяем парковку машины, когда парковка заполнена.
     */
    @Test
    public void whenParkExtraCarThenNoPlace() {
        Parking parking = new Parking(2, 1);
        Car lightOne = new LightCar("Honda", "Accord");
        Car lightTwo = new LightCar("Toyota", "East");
        Car heavyOne = new HeavyCar("ZIL", "150", 2);
        Car heavyTwo = new HeavyCar("ZIL", "200", 2);
        Assert.assertThat(parking.checkPlace(lightOne), is(true));
        Assert.assertThat(parking.checkPlace(lightTwo), is(true));
        Assert.assertThat(parking.checkPlace(heavyOne), is(true));
        boolean addedOne = parking.park(lightOne);
        boolean addedTwo = parking.park(lightTwo);
        boolean addedThree = parking.park(heavyOne);
        Assert.assertThat(addedOne, is(true));
        Assert.assertThat(addedTwo, is(true));
        Assert.assertThat(addedThree, is(true));
        Assert.assertThat(parking.checkPlace(heavyTwo), is(false));
        boolean addedFour = parking.park(heavyTwo);
        Assert.assertThat(addedFour, is(false));
    }
}