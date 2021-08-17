package ru.job4j.di;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса Store.
 */
public class StoreTest {

    /**
     * Проверка регистрации в контексте и работы хранилища.
     */
    @Test
    public void whenContextRegThenSuccess() {
        Context context = new Context();
        context.register(Store.class);
        Store store = context.get(Store.class);
        store.add("something");
        List<String> result = store.getAll();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0), is("something"));
    }
}