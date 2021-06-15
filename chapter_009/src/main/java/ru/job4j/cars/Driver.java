package ru.job4j.cars;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Сущность Владелец.
 */
@Entity
@Table(name = "driver")
public class Driver {

    /**
     * Идентификатор владельца.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя владельца.
     */
    @Column(unique = true)
    private String name;

    /**
     * Список машин владельца.
     */
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    /**
     * Записи о владении машинами.
     */
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<OwnershipRecord> history = new ArrayList<>();

    public Driver() {

    }

    public Driver(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
        this.addRecord(new OwnershipRecord(car, this, new Date(System.currentTimeMillis())));
    }

    public void removeCar(Car car) {
        for (Car nextCar : this.cars) {
            if (car.equals(nextCar)) {
                this.cars.remove(nextCar);
                break;
            }
        }
    }

    public List<OwnershipRecord> getHistory() {
        return history;
    }

    private void addRecord(OwnershipRecord record) {
        this.history.add(record);
    }

    @Override
    public String toString() {
        return "Driver{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}