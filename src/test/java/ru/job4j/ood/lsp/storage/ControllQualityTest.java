package ru.job4j.ood.lsp.storage;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenToTrash() {
        DateOfProduct<LocalDate> dateNow = new Date<>();
        DateOfProduct<LocalDate> createDate = new Date<>();
        Trash trash = new Trash(new ArrayList<>());
        Warehouse warehouse = new Warehouse(new ArrayList<>());
        List<Food<LocalDate>> foods = Collections.singletonList(new Food<>("Milk", 6, createDate,
                45.0, 0));
        createDate.setDate(2021, 5, 18);
        ControllQuality<LocalDate> controll = new ControllQuality<>(foods, List.of(trash, warehouse), dateNow);
        assertThat(warehouse.getFoods().size(), is(1));
        dateNow.setDate(2021, 5, 24);
        controll.resort();
        assertThat(warehouse.getFoods().size(), is(0));
        assertThat(trash.getFoods().size(), is(1));
    }

    @Test
    public void whenToShop() {
        Date<LocalDate> dateNow = new Date<>();
        Date<LocalDate> createDate = new Date<>();
        Warehouse warehouse = new Warehouse(new ArrayList<>());
        Shop shop = new Shop(new ArrayList<>());
        List<Food<LocalDate>> foods = Collections.singletonList(new Food<>("Milk", 6,
                createDate,
                45.0, 0));
        createDate.setDate(2021, 5, 19);
        ControllQuality<LocalDate> controll = new ControllQuality<>(foods, List.of(warehouse, shop), dateNow);
        assertThat(warehouse.getFoods().size(), is(1));
        dateNow.setDate(2021, 5, 24);
        controll.resort();
        assertThat(warehouse.getFoods().size(), is(0));
        assertThat(shop.getFoods().size(), is(1));
    }

    @Test
    public void whenToShopWithDiscount() {
        Date<LocalDate> dateNow = new Date<>();
        Date<LocalDate> createDate = new Date<>();
        Warehouse warehouse = new Warehouse(new ArrayList<>());
        Shop shop = new Shop(new ArrayList<>());
        List<Food<LocalDate>> foods = Collections.singletonList(new Food<>("Milk", 6,
                createDate,
                45.0, 0));
        createDate.setDate(2021, 5, 18);
        ControllQuality<LocalDate> controll = new ControllQuality<>(foods, List.of(warehouse, shop), dateNow);
        assertThat(warehouse.getFoods().size(), is(1));
        dateNow.setDate(2021, 5, 23);
        controll.resort();
        assertThat(warehouse.getFoods().size(), is(0));
        assertThat(shop.getFoods().size(), is(1));
        assertThat(shop.getFoods().get(0).getDiscount(), is(30));
    }

    @Test
    public void whenAllResort() {
        Date<LocalDate> dateNow = new Date<>();
        Date<LocalDate> createDate1 = new Date<>();
        Date<LocalDate> createDate2 = new Date<>();
        Date<LocalDate> createDate3 = new Date<>();
        Date<LocalDate> createDate4 = new Date<>();
        Warehouse warehouse = new Warehouse(new ArrayList<>());
        Shop shop = new Shop(new ArrayList<>());
        Trash trash = new Trash(new ArrayList<>());
        List<Food<LocalDate>> foods = Arrays.asList(new Food<LocalDate>("Apples", 8, createDate1,
                        200, 0),
                new Food("Bananas", 8, createDate2,
                        300.0, 0),
                new Food("Bread", 4, createDate3,
                        25.0, 0),
                new Food("Milk", 6, createDate4,
                        45.0, 0)
        );
        createDate1.setDate(2021, 5, 19);
        createDate2.setDate(2021, 5, 13);
        createDate3.setDate(2021, 5, 16);
        createDate4.setDate(2021, 5, 12);
        ControllQuality<LocalDate> controll = new ControllQuality<>(foods, List.of(warehouse, shop, trash), dateNow);
        assertThat(shop.getFoods().size(), is(2));
        assertThat(trash.getFoods().size(), is(1));
        assertThat(warehouse.getFoods().size(), is(1));
        dateNow.setDate(2021, 5, 23);
        controll.resort();
        assertThat(shop.getFoods().size(), is(1));
        assertThat(trash.getFoods().size(), is(3));
        assertThat(warehouse.getFoods().size(), is(0));
    }
}