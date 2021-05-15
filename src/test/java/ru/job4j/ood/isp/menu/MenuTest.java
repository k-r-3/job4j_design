package ru.job4j.ood.isp.menu;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    Item task1 = new Item("1 task1");
    Item task2 = new Item("1.1 task2");
    Item task3 = new Item("1.2 task3");
    Item task4 = new Item("1.2.1 task4");
    Item check = new Check("1.2.1.1 check");
    Menu menu;

    @Before
    public void init() {
        task1.addChildren(Arrays.asList(task2, task3));
        task3.addChildren(Arrays.asList(task4));
        task4.addChildren(Arrays.asList(check));
        menu = new Menu(Arrays.asList(task1));
    }

    @Test
    public void whenGetItem() {
        String expected = task1.getElement("1.2.1.1 check").get().getName();
        assertThat(expected, is("1.2.1.1 check"));
    }

    @Test
    public void whenAddChild() {
        Item item = new Item("new Item");
        check.addChild("1.2.1.1 check", item);
        assertThat(check.getChildren().size(), is(1));
    }

    @Test
    public void whenAddChildOnBranch() {
        Item item = new Item("new Item");
        task3.addChild("1.2.1.1 check", item);
        assertThat(check.getChildren().size(), is(1));
    }

    @Test
    public void whenAddChildWithoutBranch() {
        Item item = new Item("new Item");
        task2.addChild("1.2.1.1 check", item);
        assertThat(check.getChildren().size(), is(1));
    }

    @Test
    public void whenAddChildOnRoot() {
        Item item = new Item("new Item");
        task1.addChild("1.2.1.1 check", item);
        assertThat(check.getChildren().size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotFound() {
        Item item = new Item("new Item");
        task1.addChild("1.2 check", item);
    }

    @Test
    public void whenChildren() {
        assertThat(task1.getChildren().size(), is(2));
        assertThat(task2.getChildren().size(), is(0));
        assertThat(task3.getChildren().size(), is(1));
        assertThat(task4.getChildren().size(), is(1));
        assertThat(check.getChildren().size(), is(0));
    }

    @Test
    public void whenDelChild() {
        task4.deleteChild("1.2.1.1 check");
        assertThat(task4.getChildren().size(), is(0));
    }

    @Test
    public void whenParents() {
        assertThat(check.getParents().size(), is(3));
        assertThat(task4.getParents().size(), is(2));
        assertThat(task3.getParents().size(), is(1));
        assertThat(task2.getParents().size(), is(1));
        assertThat(task1.getParents().size(), is(0));
    }

    @Test
    public void whenMenu() {
        System.out.println(menu);
        String expected = "Menu : \r\n" +
                "1 task1\r\n"
                + "\t1.1 task2\r\n"
                + "\t1.2 task3\r\n"
                + "\t\t1.2.1 task4\r\n"
                + "\t\t\t1.2.1.1 check\r\n";
        assertThat(menu.toString(), is(expected));
    }

    @Test
    public void whenAction() {
        assertThat(check.action(), is(true));
    }

    @Test
    public void whenMenuItemAction() {
        assertThat(menu.choice("1.2.1.1"), is(true));
    }

}