package ru.job4j.bank;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс, реализующий сервисы банка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Bank {

    /**
     * Хранилище клиентов и их счетов.
     */
    Map<User, LinkedList<Account>> accounts = new TreeMap<>();

    /**
     * Добавление нового клиента банка.
     *
     * @param user объект клиента.
     */
    public void addUser(User user) {
        this.accounts.putIfAbsent(user, new LinkedList<Account>());
    }

    /**
     * Удаление существующего клиента банка.
     *
     * @param user объект клиента
     */
    public void deleteUser(User user) {
        this.accounts.remove(user);
    }

    /**
     * Добавить новый счет для клиента.
     *
     * @param passport паспортные данные клиента.
     * @param account  объект счета для добавления.
     */
    public void addAccountToUser(String passport, Account account) {
         if (!this.accounts.get(findUserByPassport(passport)).contains(account)) {
             this.accounts.get(findUserByPassport(passport)).add(account);
         }
    }

    /**
     * Удаление счета у клиента банка.
     *
     * @param passport паспортные данные клиента.
     * @param account  объект счета для удаления.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.accounts.get(findUserByPassport(passport)).remove(account);
    }

    /**
     * Получить список всех счетов клиента.
     *
     * @param passport паспортные данные клиента.
     * @return список счетов клиента.
     */
    public List<Account> getUserAccount(String passport) {
        return this.accounts.get(findUserByPassport(passport));
    }

    /**
     * Найти пользователя в списке по паспортным данным.
     * @param passport паспортные данные
     * @return искомый юзер
     */
    private User findUserByPassport(String passport) {
        User result = null;
        for (User user : this.accounts.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Перечисление денег между счетами клиентов банка.
     *
     * @param sourcePassport       паспортные данные клиента-источника.
     * @param sourceRequisite      номер счета клиента-источника.
     * @param destinationPassport  паспортные данные клиента-получателя.
     * @param destinationRequisite номер счета клиента-получателя.
     * @param amount               сумма для перечисления.
     * @return результат перечисления (успешно или нет)
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        boolean srcReq = false;
        boolean dstReq = false;
        Account srcAccount = null;
        Account dstAccount = null;
        User srcUser = findUserByPassport(sourcePassport);
        User dstUser = findUserByPassport(destinationPassport);
        if (srcUser != null && dstUser != null) {
            for (Account account : this.accounts.get(srcUser)) {
                if (account.getRequisites().equals(sourceRequisite) && account.getValue() >= amount) {
                    srcReq = true;
                    srcAccount = account;
                    break;
                }
            }
            for (Account account : this.accounts.get(dstUser)) {
                if (account.getRequisites().equals(destinationRequisite)) {
                    dstReq = true;
                    dstAccount = account;
                    break;
                }
            }
            result = srcReq && dstReq;
            if (result) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
            }
        }
        return result;
    }
}