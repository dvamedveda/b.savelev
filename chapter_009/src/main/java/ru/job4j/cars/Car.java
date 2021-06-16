package ru.job4j.cars;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность Машина.
 */
@Entity
@Table(name = "car")
public class Car {

    /**
     * Идентификатор машины.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Марка машины.
     */
    @Column
    private String brand;

    /**
     * Модель машины.
     */
    @Column
    private String model;

    /**
     * Двигатель машины.
     */
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    /**
     * Текущий владелец машины.
     */
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Car() {
    }

    public Car(String brand, String model, Engine engine, Driver driver) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.driver = driver;
        this.driver.addCar(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", engine=" + engine
                + ", driver=" + driver
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model)
                && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, engine);
    }
}