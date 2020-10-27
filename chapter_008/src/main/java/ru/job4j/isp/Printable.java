package ru.job4j.isp;

/**
 * Интерфейс описывающий, может ли итем быть напечатан
 */
public interface Printable {

    /**
     * Напечатать итем.
     *
     * @param indent отступ для печати, если требуется.
     */
    void printSelf(String indent);
}