package ru.job4j.gcdemo;

/**
 * Класс демонстрирует работу сборщика мусора.
 */
public class Demo {

    /* Размер объекта 64 байт
     * объект User 16 байт + поле String 48 байт
     */
    public static class User {

        /* Размер поля String
         * сам объект String занимает 16 байт
         * вложенные в String не-статические поля:
         * массив value для хранения значений byte[] - объект массива 16 байт + длина массива 4 байта + 4 байт заполнения
         * кодировка coder, хранящаяся в byte - 1 байт
         * хешкод hashCode, хранящийся в int - 4 байта
         * флажок hashIsZero, хранящийся в boolean - 1 байт
         * заполняющие 2 байта до ^8
         * итого 16 + 16 + 4 + 4 + 1 + 4 + 1 + 2 = 48 байт
         */
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("User finalized: " + this.name);
        }

        @Override
        public String toString() {
            return "User name: " + this.name;
        }
    }

    /**
     * В методе демонстрируется создание 10000 объектов и вызов сборщика мусора средствами самой JVM.
     */
    public static void main(String[] args) throws Throwable {
        memoryInfo();
        for (int i = 0; i < 30000; i++) {
            if (i % 1000 == 0 & i != 0) {
                memoryInfo();
                System.out.println("Next thousand created...");
            }
            User newUser = new User("someuser");
            newUser = null;
        }
    }

    /**
     * Вывод информации о состоянии памяти.
     */
    public static void memoryInfo() {
        long total = Runtime.getRuntime().maxMemory();
        long free = Runtime.getRuntime().freeMemory();
        System.out.println("===========================");
        System.out.println("Total memory: " + total / 1024 + " Kb");
        System.out.println("Free memory: " + free / 1024 + " Kb");
        long used = total - free;
        System.out.println("Used memory: " + used / 1024 + " Kb");
        System.out.println("===========================");
    }
}