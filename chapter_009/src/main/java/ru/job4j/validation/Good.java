package ru.job4j.validation;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Модель данных для Товара.
 */
@Entity
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(
            min = 5,
            max = 100
    )
    @Column
    private String name;

    @NotNull
    @PastOrPresent
    @Column
    private Timestamp produced;

    @NotNull
    @Future
    @Column
    private Timestamp expired;

    public Good(@NotNull @Size(
            min = 5,
            max = 100
    ) String name, @NotNull @PastOrPresent Timestamp produced, @NotNull @Future Timestamp expired) {
        this.name = name;
        this.produced = produced;
        this.expired = expired;
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

    public Timestamp getProduced() {
        return produced;
    }

    public void setProduced(Timestamp produced) {
        this.produced = produced;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Good good = (Good) o;
        return id == good.id
                && Objects.equals(name, good.name)
                && Objects.equals(produced, good.produced)
                && Objects.equals(expired, good.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, produced, expired);
    }
}