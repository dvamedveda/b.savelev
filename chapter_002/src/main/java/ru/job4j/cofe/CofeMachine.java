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

    /**
     * Метод выдает сдачу монетами номиналом в 10, 5, 2, 1 рублей.
     * Если номинал купюры меньше суммы покупки - возвращает ту же купюру.
     * Если номинал купюры равен сумме покупки - ничего не возвращает.
     *
     * @param value принятая купюра.
     * @param price сумма покупки.
     * @return массив монет для выдачи.
     */
    public int[] changes(int value, int price) {
        int[] result;
        if (price > value) {
            result = new int[]{value};
        } else if (price == value) {
            result = new int[]{-1};
        } else {
            result = new int[value - price];
            int[] coins = {10, 5, 2, 1};
            int change = value - price;
            int changeIndex = 0;
            for (int par = 0; par < coins.length; par++) {
                while (change >= coins[par]) {
                    result[changeIndex] = coins[par];
                    change -= coins[par];
                    changeIndex++;
                }
            }
            for (int index = 0; index < result.length; index++) {
                if (result[index] == 0) {
                    result = Arrays.copyOf(result, index);
                }
            }
        }
        return result;
    }
}