package ru.job4j.cars;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

/**
 * Промежуточная сущность Запись о владении машиной.
 */
@Entity
@Immutable
@Table(name = "history_owner")
public class OwnershipRecord {

    /**
     * Идентификатор записи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Ссылка на сущность автомобиля в записи.
     */
    @ManyToOne
    @JoinColumn(
            name = "car_id"
    )
    private Car car;

    /**
     * Ссылка на сущность водителя в записи.
     */
    @ManyToOne
    @JoinColumn(
            name = "driver_id"
    )
    private Driver driver;

    /**
     * Дата начала владения машиной владельцем.
     */
    @Column(name = "start_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    public OwnershipRecord() {

    }

    public OwnershipRecord(Car car, Driver driver, Date from) {
        this.car = car;
        this.driver = driver;
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "OwnershipRecord{"
                + "id=" + id
                + ", car=" + car
                + ", driver=" + driver
                + ", from=" + from
                + '}';
    }
}