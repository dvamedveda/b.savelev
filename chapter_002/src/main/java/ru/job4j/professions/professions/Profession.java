package ru.job4j.professions.professions;

/**
 * Класс, объединяющий общие признаки специалистов разных профессий.
 */
public class Profession {
    /**
     * Имя специалиста.
     */
    private String name;

    /**
     * Вид деятельности специалиста.
     */
    private String specialization;

    /**
     * Конструктор класса профессии.
     * @param name имя специалиста.
     * @param specialization род деятельности специалиста.
     */
    public Profession(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    /**
     * Представиться.
     * @return описание себя.
     */
    public String introduceSelf() {
        return String.format("Здравствуйте, меня зовут %s, я - %s.", this.name, this.specialization);
    }

    /**
     * Вернуть вид деятельности.
     * @return вид деятельности.
     */
    public String getSpecialization() {
        return this.specialization;
    }
}