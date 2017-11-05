package ru.job4j.array;

/**
 * Класс ArrayUnion, который содержит методы для работы с массивами.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayUnion {

    /**
     * Метод, объединяющий два отсортированных массива чисел в один отсортированный.
     * Сортировка результирующего массива производится прямо в процессе слияния, без отдельной сортировки.
     *
     * @param firstArray  первый отсортированный массив, int[].
     * @param secondArray второй отсортированный массив, int[].
     * @return объединенный отсортированный массив, int[].
     */
    public int[] union(int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int resultIndex = 0; resultIndex < result.length; resultIndex++) {
            if (firstIndex == firstArray.length) {
                result[resultIndex] = secondArray[secondIndex];
                secondIndex++;
            } else if (secondIndex == secondArray.length) {
                result[resultIndex] = firstArray[firstIndex];
                firstIndex++;
            } else if (firstArray[firstIndex] < secondArray[secondIndex]) {
                result[resultIndex] = firstArray[firstIndex];
                firstIndex++;
            } else if (firstArray[firstIndex] > secondArray[secondIndex]) {
                result[resultIndex] = secondArray[secondIndex];
                secondIndex++;
            } else if (firstArray[firstIndex] == secondArray[secondIndex]) {
                if (firstIndex < firstArray.length - 2) {
                    result[resultIndex] = firstArray[firstIndex];
                    firstIndex++;
                } else {
                    result[resultIndex] = secondArray[secondIndex];
                    secondIndex++;
                }
            }
        }
        return result;
    }
}