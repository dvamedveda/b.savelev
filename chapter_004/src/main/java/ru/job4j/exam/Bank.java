package ru.job4j.exam;

import java.util.*;

/**
 * Класс, описывающий банк.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Bank {

    /**
     * Класс, описывающий визит посетителя.
     */
    public static class Visit {

        /**
         * Время прихода и ухода посетителя.
         */
        private final long in;
        private final long out;

        public Visit(final long in, final long out) {
            this.in = in;
            this.out = out;
        }
    }

    /**
     * Класс, реализующий сводку информации по посетителям.
     */
    public static class Info {
        private long max;
        private long start;
        private long end;

        public Info() {
            this(0, 0, 0);
        }

        public Info(long max, long start, long end) {
            this.max = max;
            this.start = start;
            this.end = end;
        }

        /**
         * Метод для сравнения сводной информации.
         *
         * @param o объект сводной информации.
         * @return результат сравнения.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Info info = (Info) o;

            if (max != info.max) {
                return false;
            }
            if (start != info.start) {
                return false;
            }
            return end == info.end;
        }

        /**
         * Метод для вычисления хеша сводной информации.
         *
         * @return хеш сводной информации.
         */
        @Override
        public int hashCode() {
            int result = (int) (max ^ (max >>> 32));
            result = 31 * result + (int) (start ^ (start >>> 32));
            result = 31 * result + (int) (end ^ (end >>> 32));
            return result;
        }

        /**
         * Переопределенный toString для печати сводной информации.
         *
         * @return сводная информация в виде строки.
         */
        @Override
        public String toString() {
            return "Info{" + "max=" + max + ", start=" + this.toTime(this.start) + ", end=" + this.toTime(this.end) + '}';
        }

        /**
         * Метод для конвертации времени в читаемый формат.
         *
         * @param time время в миллисекундах.
         * @return строка времени в человекочитаемом формате.
         */
        public String toTime(long time) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time);
            return String.format("%s:%s", cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
        }
    }

    /**
     * Метод для вычисления интервала, в котором был пик посетителей банка.
     * Визиты конвертируем в события, события упорядочиваем, за 1 проход вычисляем максимум посетителей
     * а затем за второй проход находим и записываем интервалы, в которых был максимум посетителей.
     * Если интервалов, в которых максимум был одинаковым, несколько - возвращаем их все.
     *
     * @param visits список визитов посетителей.
     * @return список со сводной информацией по пику посетителей.
     */
    public List<Info> max(List<Visit> visits) {
        int count = 0, max = 0;
        long start = 0;
        long end = 0;
        List<Info> periods = new ArrayList<>();
        Map<Long, Boolean> events = new TreeMap<>(Long::compare);
        for (int i = 0; i < visits.size(); i++) {
            events.put(visits.get(i).in, true);
            events.put(visits.get(i).out, false);
        }
        for (Map.Entry<Long, Boolean> event : events.entrySet()) {
            if (event.getValue()) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count--;
            }
        }
        for (Map.Entry<Long, Boolean> event : events.entrySet()) {
            if (event.getValue()) {
                count++;
                if (count == max) {
                    start = event.getKey();
                }
            } else {
                if (count == max) {
                    end = event.getKey();
                    periods.add(new Info(max, start, end));
                    start = 0;
                    end = 0;
                }
                count--;
            }
        }
        return periods;
    }
}
