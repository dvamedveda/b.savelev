package ru.job4j.parking;

/**
 * Реализация легкового автомобиля.
 */
public class LightCar implements Car {

    /**
     * Марка автомобиля.
     */
    private String brand = "";

    /**
     * Модель автомобиля.
     */
    private String model = "";

    /**
     * Где припаркован автомобиль в данный момент.
     */
    private String parkedOn = "";

    /**
     * Размер автомобиля.
     */
    private int size = 1;

    /**
     * Конструктор автомобиля.
     *
     * @param brand марка автомобиля.
     * @param model модель автомобиля.
     */
    public LightCar(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    /**
     * Получить марку автомобиля.
     *
     * @return марка автомобиля.
     */
    @Override
    public String getBrand() {
        return this.brand;
    }

    /**
     * Получить модель автомобиля.
     *
     * @return модель автомобиля.
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * Получить размер автомобиля.
     * Размер автомобиля определяется в классах-наследниках.
     *
     * @return размер автомобиля.
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Получить текущее место парковки.
     *
     * @return текущее место парковки.
     */
    @Override
    public String getParkedOn() {
        return this.parkedOn;
    }

    /**
     * Установить текущее место парковки.
     *
     * @param parkedOn место парковки.
     */
    @Override
    public void setParkedOn(String parkedOn) {
        this.parkedOn = parkedOn;
    }
}