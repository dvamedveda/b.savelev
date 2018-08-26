package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Класс, содержащий тесты для статистики по коллекциям.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class StoreTest {

    /**
     * Проверяем случай, когда сравниваются две одинаковые коллекции.
     */
    @Test
    public void whenTwoSameCollectionsThenNoDiff() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userOne);
        storeTwo.addUser(userTwo);
        storeTwo.addUser(userThree);
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 0, removed - 0, changed - 0."));
    }

    /**
     * Проверяем случай, когда коллекции отличаются только добавленными пользователями.
     */
    @Test
    public void whenAddedUserThenDiffIsCorrect() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userOne);
        storeTwo.addUser(userTwo);
        storeTwo.addUser(userThree);
        Store.User userFour = new Store.User(4, "Vanya");
        storeTwo.addUser(userFour);
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 1, removed - 0, changed - 0."));
    }

    /**
     * Проверяем случай, когда коллекции отличаются только удаленными пользователями.
     */
    @Test
    public void whenRemoveUserThenDiffIsCorrect() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userOne);
        storeTwo.addUser(userTwo);
        storeTwo.addUser(userThree);
        storeTwo.deleteUser(userOne);
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 0, removed - 1, changed - 0."));
    }

    /**
     * Проверяем случай, когда коллекции отличаются только измененными пользователями.
     */
    @Test
    public void whenChangeUserThenDiffIsCorrect() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userOne);
        storeTwo.addUser(userTwo);
        storeTwo.addUser(userThree);
        storeTwo.editUser(userOne, "Ivan");
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 0, removed - 0, changed - 1."));
    }

    /**
     * Проверяем случай, когда коллекции польностью отличаются друг от друга.
     */
    @Test
    public void whenCompletelyDifferentListThenRemovedAndAdded() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        Store.User userFour = new Store.User(4, "Dasha");
        Store.User userFove = new Store.User(5, "Masha");
        Store.User userSix = new Store.User(6, "Lena");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userFour);
        storeTwo.addUser(userFove);
        storeTwo.addUser(userSix);
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 3, removed - 3, changed - 0."));
    }

    /**
     * Проверяем случай, когда в коллекцию был добавлен пользовател, и сразу изменен.
     */
    @Test
    public void whenChangeNewlyAddedUserThenDiffCorrect() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        Store.User userFour = new Store.User(4, "Dasha");
        Store.User userFove = new Store.User(5, "Masha");
        Store.User userSix = new Store.User(6, "Lena");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userFour);
        storeTwo.addUser(userFove);
        storeTwo.addUser(userSix);
        storeTwo.editUser(userSix, "Tamara");
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 3, removed - 3, changed - 1."));
    }

    /**
     * Проверяем случай, когда во второй коллекции и добавлялись и удалялись и редактировались пользователи.
     */
    @Test
    public void whenBothChangeAddRemoveUserThenDiffCorrect() {
        Store storeOne = new Store();
        Store storeTwo = new Store();
        Store.User userOne = new Store.User(1, "Vasya");
        Store.User userTwo = new Store.User(2, "Dima");
        Store.User userThree = new Store.User(3, "Kolya");
        Store.User userFour = new Store.User(4, "Dasha");
        storeOne.addUser(userOne);
        storeOne.addUser(userTwo);
        storeOne.addUser(userThree);
        storeTwo.addUser(userOne);
        storeTwo.addUser(userTwo);
        storeTwo.addUser(userThree);
        storeTwo.deleteUser(userThree);
        storeTwo.editUser(userTwo, "Tamara");
        storeTwo.addUser(userFour);
        Info info = storeTwo.diff(storeOne.getUsers(), storeTwo.getUsers());
        Assert.assertThat(info.toString(), is("Statistics: added - 1, removed - 1, changed - 1."));
    }
}
