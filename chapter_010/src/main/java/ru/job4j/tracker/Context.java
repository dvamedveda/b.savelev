package ru.job4j.tracker;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, реализующий контекст приложения с механизмом Dependency Injection.
 */
public class Context {

    /**
     * Хранилище зарегистрированных в контексте объектов.
     */
    private final Map<String, Object> instances = new HashMap<>();

    /**
     * Регистрация объекта в контексте.
     *
     * @param someClass класс регистрируемого объекта.
     */
    public void register(Class someClass) {
        Constructor[] constructors = someClass.getConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors: " + someClass.getCanonicalName() + "!");
        }
        Constructor constructor = constructors[0];
        List<Object> constructorArgs = new ArrayList<>();
        for (Class arg : constructor.getParameterTypes()) {
            if (!instances.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object not registered in context: " + arg.getCanonicalName() + "!");
            }
            constructorArgs.add(instances.get(arg.getCanonicalName()));
        }
        try {
            instances.put(someClass.getCanonicalName(), constructor.newInstance(constructorArgs.toArray()));
            boolean isInput = Arrays.stream(someClass.getInterfaces()).map(Class::getCanonicalName).filter(s -> s.equals("ru.job4j.di.ui.Input")).count() > 0;
            boolean isInputChild = someClass.getSuperclass().getCanonicalName().equals("ru.job4j.di.ui.ConsoleInput");
            if (isInput) {
                String iName = getInputCanonicalName(someClass.getInterfaces());
                instances.remove(iName);
                instances.put(iName, this.get(someClass));
            }
            if (isInputChild) {
                String iName = getInputCanonicalName(someClass.getSuperclass().getInterfaces());
                instances.remove(iName);
                instances.put(iName, this.get(someClass));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Can't create an instance of " + someClass.getCanonicalName(), e);
        }
    }

    /**
     * Просто вспомогательный метод для получения имени интерфейса из списка классов.
     *
     * @param classes список классов.
     * @return каноническое название интерфейса Input.
     */
    private String getInputCanonicalName(Class[] classes) {
        return Arrays.stream(classes)
                .map(Class::getCanonicalName)
                .filter(s -> s.equals("ru.job4j.di.ui.Input"))
                .collect(Collectors.toList())
                .get(0);
    }

    /**
     * Получение подготовленного к работе объекта из хранилища контекста.
     *
     * @param instance класс объекта.
     * @param <T>      параметр класса.
     * @return объект заданного класса.
     */
    public <T> T get(Class<T> instance) {
        return (T) instances.get(instance.getCanonicalName());
    }
}