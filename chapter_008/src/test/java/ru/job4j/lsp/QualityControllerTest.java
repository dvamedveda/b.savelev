package ru.job4j.lsp;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.lsp.QualityController.
 */
public class QualityControllerTest {

    /**
     * Текущая дата для использования в тестах.
     */
    private LocalDate now = LocalDate.now();

    /**
     * Проверка сортировки продуктов, чей срок годности превышает 75%.
     */
    @Test
    public void whenSortWarehouseFoodsThenSortsCorrect() {
        Food one = new Bread("Бородинский", now.minusDays(5), now.plusDays(20), 100, 10);
        Food two = new Bread("Ржаной", now.minusDays(1), now.plusDays(10), 90, 10);
        Food three = new Bread("Серый", now.minusDays(1), now.plusDays(5), 110, 10);
        Food four = new Bread("Батон", now.minusDays(2), now.plusDays(10), 100, 10);
        List<Food> expected = new ArrayList<>();
        expected.add(one);
        expected.add(two);
        expected.add(three);
        expected.add(four);
        List<Store> stores = new ArrayList<>();
        Store warehouse = new Warehouse();
        stores.add(warehouse);
        QualityController qc = new QualityController(stores);
        qc.sort(expected);
        List<Food> actual = warehouse.getFoods();
        Assert.assertThat(actual.size(), is(expected.size()));
        Assert.assertThat(actual.get(0), is(expected.get(0)));
        Assert.assertThat(actual.get(1), is(expected.get(1)));
        Assert.assertThat(actual.get(2), is(expected.get(2)));
        Assert.assertThat(actual.get(3), is(expected.get(3)));
    }

    /**
     * Проверка сортировки продуктов, чей срок годности превышает 0%.
     */
    @Test
    public void whenSortWarehouseAndShopFoodsThenSortsCorrect() {
        Food one = new Bread("Бородинский", now.minusDays(5), now.plusDays(20), 100, 10);
        Food two = new Bread("Ржаной", now.minusDays(5), now.plusDays(5), 90, 10);
        Food three = new Bread("Серый", now.minusDays(10), now.plusDays(2), 110, 10);
        Food four = new Bread("Батон", now.minusDays(2), now.plusDays(10), 100, 10);
        List<Food> foods = new ArrayList<>();
        foods.add(one);
        foods.add(two);
        foods.add(three);
        foods.add(four);
        List<Store> stores = new ArrayList<>();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        stores.add(warehouse);
        stores.add(shop);
        QualityController qc = new QualityController(stores);
        qc.sort(foods);
        List<Food> actualWarehouse = warehouse.getFoods();
        List<Food> actualShop = shop.getFoods();
        List<Food> expectedWarehouse = new ArrayList<>();
        List<Food> expectedShop = new ArrayList<>();
        expectedWarehouse.add(one);
        expectedWarehouse.add(four);
        expectedShop.add(two);
        expectedShop.add(three);
        Assert.assertThat(actualWarehouse.size(), is(expectedWarehouse.size()));
        Assert.assertThat(actualShop.size(), is(expectedShop.size()));
        Assert.assertThat(actualWarehouse.get(0), is(expectedWarehouse.get(0)));
        Assert.assertThat(actualWarehouse.get(1), is(expectedWarehouse.get(1)));
        Assert.assertThat(actualShop.get(0), is(expectedShop.get(0)));
        Assert.assertThat(actualShop.get(1), is(expectedShop.get(1)));
    }

    /**
     * Проверка сортировки продуктов, как с истекшим, так и не истекшим сроком годности.
     */
    @Test
    public void whenSortAllFoodsThenSortsCorrect() {
        Food one = new Bread("Бородинский", now.minusDays(5), now.plusDays(20), 100, 10);
        Food two = new Bread("Ржаной", now.minusDays(5), now.plusDays(5), 90, 10);
        Food three = new Milk("Жирное 5%", now.minusDays(10), now.plusDays(2), 200, 10);
        Food four = new Eggs("Деревенские", now.minusDays(10), now.minusDays(1), 300, 20);
        List<Food> foods = new ArrayList<>();
        foods.add(one);
        foods.add(two);
        foods.add(three);
        foods.add(four);
        List<Store> stores = new ArrayList<>();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        QualityController qc = new QualityController(stores);
        qc.sort(foods);
        List<Food> actualWarehouse = warehouse.getFoods();
        List<Food> actualShop = shop.getFoods();
        List<Food> actualTrash = trash.getFoods();
        List<Food> expectedShop = new ArrayList<>();
        expectedShop.add(two);
        expectedShop.add(three);
        Assert.assertThat(actualWarehouse.size(), is(1));
        Assert.assertThat(actualShop.size(), is(expectedShop.size()));
        Assert.assertThat(actualTrash.size(), is(1));
        Assert.assertThat(actualWarehouse.get(0), is(one));
        Assert.assertThat(actualTrash.get(0), is(four));
        Assert.assertThat(actualShop.get(0), is(expectedShop.get(0)));
        Assert.assertThat(actualShop.get(1), is(expectedShop.get(1)));
    }

    /**
     * Проверка цен на продукты после сортировки по хранилищам.
     */
    @Test
    public void whenBarelyValidFoodThenDiscountAdds() {
        Food one = new Bread("Бородинский", now.minusDays(5), now.plusDays(20), 100, 10);
        Food two = new Bread("Ржаной", now.minusDays(4), now.plusDays(3), 90, 10);
        Food three = new Bread("Серый", now.minusDays(5), now.plusDays(1), 110, 10);
        Food four = new Bread("Батон", now.minusDays(10), now.minusDays(1), 140, 5);
        List<Food> expected = new ArrayList<>();
        expected.add(one);
        expected.add(two);
        expected.add(three);
        expected.add(four);
        List<Store> stores = new ArrayList<>();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        QualityController qc = new QualityController(stores);
        qc.sort(expected);
        List<Food> actualWarehouse = warehouse.getFoods();
        List<Food> actualShop = shop.getFoods();
        List<Food> actualTrash = trash.getFoods();
        Assert.assertThat(actualWarehouse.size(), is(1));
        Assert.assertThat(actualWarehouse.get(0), is(expected.get(0)));
        Assert.assertThat(actualWarehouse.get(0).getPrice(), is(100.0));
        Assert.assertThat(actualShop.size(), is(2));
        Assert.assertThat(actualShop.get(0), is(expected.get(1)));
        Assert.assertThat(actualShop.get(1), is(expected.get(2)));
        Assert.assertThat(actualShop.get(0).getPrice(), is(90.0));
        Assert.assertThat((int) actualShop.get(1).getPrice(), is((int) 99.0));
        Assert.assertThat(actualTrash.size(), is(1));
        Assert.assertThat(actualTrash.get(0), is(expected.get(3)));
        Assert.assertThat(actualTrash.get(0).getPrice(), is(140.0));
    }
}