package ru.job4j.professions;

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
