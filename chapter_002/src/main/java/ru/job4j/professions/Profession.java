package ru.job4j.professions;

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