package ru.job4j.parking;

/**
 * Реализация грузового автомобиля.
 */
public class HeavyCar implements Car {

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
    private int size = 2;

    /**
     * Конструктор автомобиля.
     *
     * @param brand марка автомобиля.
     * @param model модель автомобиля.
     */
    public HeavyCar(String brand, String model) {
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
        return null;
    }

    /**
     * Получить модель автомобиля.
     *
     * @return модель автомобиля.
     */
    @Override
    public String getModel() {
        return null;
    }

    /**
     * Получить размер автомобиля.
     * Размер автомобиля определяется в классах-наследниках.
     *
     * @return размер автомобиля.
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * Получить текущее место парковки.
     *
     * @return текущее место парковки.
     */
    @Override
    public String getParkedOn() {
        return null;
    }

    /**
     * Установить текущее место парковки.
     *
     * @param parkedOn место парковки.
     */
    @Override
    public void setParkedOn(String parkedOn) {

    }
}