package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса Bank.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BankTest {

    /**
     * Проверяется добавление нового клиента.
     */
    @Test
    public void whenAddUserThenUserExists() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        bank.addUser(vasya);
        Assert.assertTrue(bank.accounts.keySet().contains(vasya));
    }

    /**
     * Проверяется удаление нового клиента.
     */
    @Test
    public void whenDeleteUserThenUserDeleting() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        User petya = new User("Petya", "Petr Petrov");
        bank.addUser(vasya);
        bank.addUser(petya);
        bank.deleteUser(vasya);
        Assert.assertFalse(bank.accounts.keySet().contains(vasya));
    }

    /**
     * Проверяется добавление счета для клиента.
     */
    @Test
    public void whenAddAccountToUserThenAccountAdding() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        bank.addUser(vasya);
        bank.addAccountToUser("Vasiliy Pupkin", new Account("0123456789", 0.0));
        Assert.assertThat(bank.accounts.get(vasya).get(0).getRequisites(), is("0123456789"));
        Assert.assertThat(bank.accounts.get(vasya).get(0).getValue(), is(0.0));
    }

    /**
     * Проверяется удаление счета у клиента.
     */
    @Test
    public void whenDeleteAccountFromUserThenAccountDeleting() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        Account account = new Account("0123456789", 0.0);
        bank.addUser(vasya);
        bank.addAccountToUser("Vasiliy Pupkin", account);
        bank.deleteAccountFromUser("Vasiliy Pupkin", account);
        Assert.assertTrue(bank.accounts.get(vasya).isEmpty());
    }

    /**
     * Проверяется получение списка счетов клиента.
     */
    @Test
    public void whenCallUserAccountsThenGotListOfAccounts() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        Account accountOne = new Account("0123456789", 0.0);
        Account accountTwo = new Account("0123456789", 0.0);
        bank.addUser(vasya);
        bank.addAccountToUser("Vasiliy Pupkin", accountOne);
        bank.addAccountToUser("Vasiliy Pupkin", accountTwo);
        List<Account> expected = new LinkedList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        List<Account> result = bank.getUserAccount("Vasiliy Pupkin");
        Assert.assertThat(result, is(expected));
    }

    /**
     * Проверяется перечисление денег со счета на счет, когда денег для перечисления достаточно.
     */
    @Test
    public void whenTransferEnoughMoneyThenTransfers() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        User petya = new User("Petya", "Petr Petrov");
        bank.addUser(vasya);
        bank.addUser(petya);
        Account accountOne = new Account("0123456789", 10.0);
        Account accountTwo = new Account("9876543210", 0.0);
        bank.addAccountToUser("Vasiliy Pupkin", accountOne);
        bank.addAccountToUser("Petr Petrov", accountTwo);
        boolean result = bank.transferMoney("Vasiliy Pupkin", "0123456789",
                "Petr Petrov", "9876543210", 6.5);
        Assert.assertThat(result, is(true));
    }

    /**
     * Проверяется перечисление денег со счета на счет, когда денег для перечисления недостаточно.
     */
    @Test
    public void whenTransferNotEnoughMoneyThenTransfers() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        User petya = new User("Petya", "Petr Petrov");
        bank.addUser(vasya);
        bank.addUser(petya);
        Account accountOne = new Account("0123456789", 10.0);
        Account accountTwo = new Account("9876543210", 0.0);
        bank.addAccountToUser("Vasiliy Pupkin", accountTwo);
        bank.addAccountToUser("Petr Petrov", accountOne);
        boolean result = bank.transferMoney("Vasiliy Pupkin", "0123456789",
                "Petr Petrov", "9876543210", 6.5);
        Assert.assertThat(result, is(false));
    }

    /**
     * Проверяется перечисление денег со счета на счет, когда счеь получателя некорректен.
     */
    @Test
    public void whenTransferMoneyToUnexistAccountThenTransferFails() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        User petya = new User("Petya", "Petr Petrov");
        bank.addUser(vasya);
        bank.addUser(petya);
        Account accountOne = new Account("0123456789", 10.0);
        Account accountTwo = new Account("9876543210", 0.0);
        bank.addAccountToUser("Vasiliy Pupkin", accountTwo);
        bank.addAccountToUser("Petr Petrov", accountOne);
        boolean result = bank.transferMoney("Vasiliy Pupkin", "0123456789",
                "Petr Petrov", "1234509876", 6.5);
        Assert.assertThat(result, is(false));
    }

    /**
     * Проверяется перечисление денег со счета на счет, когда счет источника некорректен.
     */
    @Test
    public void whenTransferMoneyFromUnexistAccountThenTransferFails() {
        Bank bank = new Bank();
        User vasya = new User("Vasya", "Vasiliy Pupkin");
        User petya = new User("Petya", "Petr Petrov");
        bank.addUser(vasya);
        bank.addUser(petya);
        Account accountOne = new Account("0123456789", 10.0);
        Account accountTwo = new Account("9876543210", 0.0);
        bank.addAccountToUser("Vasiliy Pupkin", accountTwo);
        bank.addAccountToUser("Petr Petrov", accountOne);
        boolean result = bank.transferMoney("Vasiliy Pupkin", "1234509876",
                "Petr Petrov", "9876543210", 6.5);
        Assert.assertThat(result, is(false));
    }
}