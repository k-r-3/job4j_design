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
        List<Food> foods = Arrays.asList(new Food("Milk", 6, LocalDate.of(2021, 4, 27),
                        45.0, 0));
        ControllQuality warehouseControl = new ControllQuality(foods, warehouse);
        String warehouseExpect = "[Milk, expiration date = 6, date of create = 2021-04-27,"
                + " price = 45.0, discount = 0%]";
        assertThat(warehouseControl.getStorage().getFoods(), is(warehouseExpect));

    }

    @Test
    public void whenExpiration() {
        Trash trash = new Trash();
        List<Food> foods = Arrays.asList(new Food("Bread", 4, LocalDate.of(2021, 4, 20),
                        25.0, 0));
        ControllQuality trashControl = new ControllQuality(foods, trash);
        String trashExpect = "[Bread, expiration date = 4, date of create = 2021-04-20,"
                + " price = 25.0, discount = 0%]";
        assertThat(trashControl.getStorage().getFoods(), is(trashExpect));
    }

    @Test
    public void whenDiscount() {
        Shop shop = new Shop();
        List<Food> foods = Arrays.asList(new Food("Apples", 8, LocalDate.of(2021, 4, 22),
                        300.0, 0));
        ControllQuality shopControl = new ControllQuality(foods, shop);
        String shopExpect = "[Apples, expiration date = 8, date of create = 2021-04-22,"
                + " price = 210.0, discount = 30%]";
        assertThat(shopControl.getStorage().getFoods(), is(shopExpect));

    }

}