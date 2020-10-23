package ru.job4j.parking;

/**
 * Реализация стоянки для грузовых машин.
 */
public class HeavyCarStore implements CarStore {
    /**
     * Массив для хранения автомобилей стоянки.
     */
    private final Car[] store;

    /**
     * Название стоянки.
     */
    private final String parkingName = "Heavy car parking";

    /**
     * Устанавливаем размер стоянки.
     *
     * @param places количество мест.
     */
    public HeavyCarStore(int places) {
        this.store = new Car[places];
    }

    /**
     * Может ли стоянка вместить еще одну грузовую машину.
     * @param size размер грузовой машины.
     * @return да или нет.
     */
    @Override
    public boolean canNextHeavy(int size) {
        boolean result = false;
        for (Car car : this.store) {
            if (car == null) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Может ли стоянка вместить еще одну легковую машину.
     *
     * @return да или нет.
     */
    @Override
    public boolean canNextLight() {
        return false;
    }

    /**
     * Сколько мест еще доступно на стоянке.
     *
     * @return количество мест.
     */
    @Override
    public int placesAvailable() {
        int count = 0;
        for (Car car : this.store) {
            if (car == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Сколько всего мест содержит стоянка.
     *
     * @return количество мест.
     */
    @Override
    public int placesTotal() {
        return this.store.length;
    }

    /**
     * Разместить автомобиль на стоянка.
     *
     * @param car размещаемый автомобиль.
     * @return автомобиль размещен или нет.
     */
    @Override
    public boolean place(Car car) {
        boolean result = false;
        if (car.getSize() > 1) {
            for (int i = 0; i < this.store.length; i++) {
                if (this.store[i] == null) {
                    this.store[i] = car;
                    this.store[i].setParkedOn(this.parkingName);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}