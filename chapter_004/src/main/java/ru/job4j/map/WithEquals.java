package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class WithEquals {
    public static void main(String[] args) {
        User first = new User("Vasiliy", 21, Calendar.getInstance());
        User second = new User("Vasiliy", 21, Calendar.getInstance());
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());

        System.out.println(users);
    }
}