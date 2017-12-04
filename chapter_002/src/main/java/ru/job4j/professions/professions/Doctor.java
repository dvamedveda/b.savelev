package ru.job4j.professions.professions;

import ru.job4j.professions.common.Diagnose;
import ru.job4j.professions.common.Patient;
import ru.job4j.professions.common.Queue;

/**
 * Класс, описывающий доктора.
 * Имеет разнообразные методы, определяющие его поведение.
 */
public class Doctor extends Profession {
    /**
     * Очередь пациентов.
     */
    private Queue queue;

    /**
     * Конструктор класса Doctor.
     * Использует конструктор суперкласса для инициализации основных полей.
     * @param name имя специалиста.
     * @param specialization род деятельности специалиста.
     * @param queue очередь пациентов
     */
    public Doctor(String name, String specialization, Queue queue) {
        super(name, specialization);
        this.queue = queue;
    }

    /**
     * Принять пациента, вернуть диагноз
     * @param patient пациент нуждающийся в диагнозе.
     * @return возвращается диагноз.
     */
    public Diagnose acceptPatient(Patient patient) {
        return new Diagnose();
    }

    /**
     * Послать пациента к другому доктору.
     * @param patient пациент, которого нужно послать к другому доктору
     * @param doctor доктор, к которому нужно послать пациента.
     */
    public void sendToOtherDoctor(Patient patient, Doctor doctor) {

    }

    /**
     * Начать лечение пациента.
     * @param patient пациент, нуждающийся в лечении.
     */
    public void startHeal(Patient patient) {

    }

    /**
     * Проконсультироваться с другим доктором.
     * @param doctor другой доктор, с которым нужно проконсультироваться.
     * @param diagnose диагноз, по которому нужна консультация.
     */
    public void consultateWith(Doctor doctor, Diagnose diagnose) {

    }

    /**
     * Проконсультировать другого врача и уточнить диагноз.
     * @param diagnose первоначальный диагноз.
     * @return уточненный диагноз.
     */
    public Diagnose consultate(Diagnose diagnose) {
        return new Diagnose();
    }

    /**
     * Закончить приемное время и идти домой.
     */
    public void goHome() {

    }
}
