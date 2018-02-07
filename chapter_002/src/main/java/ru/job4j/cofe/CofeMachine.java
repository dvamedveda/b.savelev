package ru.job4j.cofe;

import java.util.Arrays;

/**
 * Класс, содержащий реализацию метода кофемашины, выдающего сдачу.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class CofeMachine {

    final static int[] COINS = {10, 5, 2, 1};

    /**
     * Метод выдает сдачу монетами номиналом в 10, 5, 2, 1 рублей.
     *
     * @param value принятая купюра.
     * @param price сумма покупки.
     * @return массив монет для выдачи.
     */
    public int[] changes(int value, int price) {
        int[] result;
        result = new int[value - price];
        int change = value - price;
        int changeIndex = 0;
        for (int par = 0; par < COINS.length; par++) {
            while (change >= COINS[par]) {
                result[changeIndex] = COINS[par];
                change -= COINS[par];
                changeIndex++;
            }
        }
        return Arrays.copyOf(result, changeIndex);
    }
}