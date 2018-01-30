package ru.job4j.cofe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Класс, содержащий тесты для класса Paint.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class CofeMachineTest {

    /**
     * Проверяется случай, когда на купюру 50 рублей покупаем кофе за 35 рублей.
     * Должно выдать сдачу 15 рублей.
     */
    @Test
    public void whenBuyCofeForThirtyFiveAndGiveFiftyThenGetFifteen() {
        CofeMachine machine = new CofeMachine();
        int price = 35;
        int value = 50;
        int[] result = machine.changes(value, price);
        int[] expected = {10, 5};
        assertThat(result, is(expected));
    }

    /**
     * Проверяется случай, когда на купюру 100 рублей покупаем кофе за 35 рублей.
     * Должно выдать сдачу 65 рублей.
     */
    @Test
    public void whenBuyCofeForThirtyFiveAndGiveHundredThenGetSixtyFive() {
        CofeMachine machine = new CofeMachine();
        int price = 35;
        int value = 100;
        int[] result = machine.changes(value, price);
        int[] expected = {10, 10, 10, 10, 10, 10, 5};
        assertThat(result, is(expected));
    }

    /**
     * Проверяется случай, когда на купюру 50 рублей покупаем кофе за 12 рублей.
     * Должно выдать сдачу 38 рублей.
     */
    @Test
    public void whenBuyCofeForElevenAndGiveFiftyThenGetThirtyEight() {
        CofeMachine machine = new CofeMachine();
        int price = 12;
        int value = 50;
        int[] result = machine.changes(value, price);
        int[] expected = {10, 10, 10, 5, 2, 1};
        assertThat(result, is(expected));
    }

    /**
     * Проверяется случай, когда на купюру 10 рублей покупаем кофе за 50 рублей.
     * Не должно выдавать монет.
     */
    @Test
    public void whenBuyCofeForFiftyAndGiveTenThenGetTenBack() {
        CofeMachine machine = new CofeMachine();
        int price = 50;
        int value = 10;
        int[] result = machine.changes(value, price);
        int[] expected = {10};
        assertThat(result, is(expected));
    }

    /**
     * Проверяется случай, когда на купюру 10 рублей покупаем кофе за 50 рублей.
     * Не должно выдавать монет.
     */
    @Test
    public void whenBuyCofeForFiftyAndGiveFiftyThenGetNothing() {
        CofeMachine machine = new CofeMachine();
        int price = 50;
        int value = 50;
        int[] result = machine.changes(value, price);
        int[] expected = {-1};
        assertThat(result, is(expected));
    }
}