package ru.job4j.ood.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenFreshFood() {
        Warehouse warehouse = new Warehouse();
        AvailableStorage storage = new AvailableStorage(List.of(warehouse));
        List<Food> foods = Arrays.asList(new Food("Milk", 6, LocalDate.of(2021, 4, 28),
                45.0, 0));
        new ControllQuality(foods, storage);
        String warehouseExpect = "Milk, expiration date = 6, date of create = 2021-04-28,"
                + " price = 45.0, discount = 0%";
        assertThat(warehouse.getFoods().get(0).toString(), is(warehouseExpect));

    }

    @Test
    public void whenExpiration() {
        Trash trash = new Trash();
        AvailableStorage storage = new AvailableStorage(List.of(trash));
        List<Food> foods = Arrays.asList(new Food("Bread", 4, LocalDate.of(2021, 4, 20),
                25.0, 0));
        new ControllQuality(foods, storage);
        String trashExpect = "Bread, expiration date = 4, date of create = 2021-04-20,"
                + " price = 25.0, discount = 0%";
        assertThat(trash.getFoods().get(0).toString(), is(trashExpect));
    }

    @Test
    public void whenDiscount() {
        Shop shop = new Shop();
        AvailableStorage storage = new AvailableStorage(List.of(shop));
        List<Food> foods = Arrays.asList(new Food("Apples", 8, LocalDate.of(2021, 4, 22),
                300.0, 0));
        new ControllQuality(foods, storage);
        String shopExpect = "Apples, expiration date = 8, date of create = 2021-04-22,"
                + " price = 210.0, discount = 30%";
        assertThat(shop.getFoods().get(0).toString(), is(shopExpect));
    }

    @Test
    public void whenWithoutDiscount() {
        Shop shop = new Shop();
        AvailableStorage storage = new AvailableStorage(List.of(shop));
        List<Food> foods = Arrays.asList(new Food("Apples", 8, LocalDate.of(2021, 4, 26),
                300.0, 0));
        new ControllQuality(foods, storage);
        String shopExpect = "Apples, expiration date = 8, date of create = 2021-04-26,"
                + " price = 300.0, discount = 0%";
        assertThat(shop.getFoods().get(0).toString(), is(shopExpect));
    }

    @Test
    public void whenProductsList() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        AvailableStorage storage = new AvailableStorage(List.of(warehouse, shop, trash));
        List<Food> foods = Arrays.asList(new Food("Apples", 8, LocalDate.of(2021, 4, 26),
                        300.0, 0),
                new Food("Bananas", 8, LocalDate.of(2021, 4, 22),
                        300.0, 0),
                new Food("Bread", 4, LocalDate.of(2021, 4, 20),
                        25.0, 0),
                new Food("Milk", 6, LocalDate.of(2021, 4, 28),
                        45.0, 0)
        );
        ControllQuality shopControl = new ControllQuality(foods, storage);
        String expect = "Foods in Warehouse : "
                + System.lineSeparator()
                + "[Milk, expiration date = 6, date of create = 2021-04-28, price = 45.0, discount = 0%]"
                + System.lineSeparator()
                + "Foods in Shop : "
                + System.lineSeparator()
                + "[Apples, expiration date = 8, date of create = 2021-04-26, price = 300.0,"
                + " discount = 0%, Bananas, expiration date = 8, date of create = 2021-04-22, price = 210.0, discount = 30%]"
                + System.lineSeparator()
                + "Foods in Trash : "
                + System.lineSeparator()
                + "[Bread, expiration date = 4, date of create = 2021-04-20, price = 25.0, discount = 0%]"
                + System.lineSeparator();
        assertThat(shop.getFoods().size(), is(2));
        assertThat(trash.getFoods().size(), is(1));
        assertThat(warehouse.getFoods().size(), is(1));
        assertThat(expect, is(shopControl.report()));

    }

}