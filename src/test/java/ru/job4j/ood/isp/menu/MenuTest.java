package ru.job4j.ood.isp.menu;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    List<Item> list = new ArrayList<>();
    Item task1 = new Item("1 task1", new Children());
    Item task2 = new Item("1.1 task2", new Children());
    Item taskSub2 = new Item("Subtask 2", new Children());
    Item task3 = new Item("1.2 task3", new Children());
    Item task4 = new Item("1.2.1 task4", new Children());
    Item check = new Check("1.2.1.1 check", new Children());
    Menu menu;

    @Before
    public void init() {
        list.add(task1);
        menu = new Menu(list);
        menu.addChild("1 task1", task2);
        menu.addChild("1 task1", task3);
        menu.addChild("1.1 task2", taskSub2);
        menu.addChild("1.2 task3", task4);
        menu.addChild("1.2.1 task4", check);
    }

    @Test
    public void whenGetItem() {
        String expected = menu.getElement("1.2.1.1 check").get().getName();
        assertThat(expected, is("1.2.1.1 check"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotFound() {
        String expected = menu.getElement("1.2.1 check").get().getName();
    }

    @Test
    public void whenChildren() {
        assertThat(task1.getChildren().size(), is(2));
        assertThat(task2.getChildren().size(), is(1));
        assertThat(task3.getChildren().size(), is(1));
        assertThat(task4.getChildren().size(), is(1));
        assertThat(check.getChildren().size(), is(0));
    }

    @Test
    public void whenAddChild() {
        menu.addChild("1.2.1.1 check",
                new Item("new Item", new Children())
        );
        assertThat(check.getChildren().size(), is(1));
    }

    @Test
    public void whenAddTwoChildren() {
        menu.addChild("1 task1",
                new Item("new item1", new Children())
        );
        menu.addChild("1 task1",
                new Item("new item2", new Children())
        );
        assertThat(task1.getChildren().size(), is(4));
    }

    @Test
    public void whenAddManyChildren() {
        menu.addChildren(List.of(new Item("1", new Children()),
                new Item("2", new Children()),
                new Item("3", new Children()),
                new Item("4", new Children()),
                new Item(" ", new Children())), "Subtask 2");
        assertThat(taskSub2.getChildren().size(), is(5));
    }

    @Test
    public void whenDelChild() {
        menu.deleteChild("1.2.1.1 check");
        assertThat(task4.getChildren().size(), is(0));
    }

    @Test
    public void whenParents() {
        assertThat(menu.getParents(check).size(), is(3));
        assertThat(menu.getParents(task4).size(), is(2));
        assertThat(menu.getParents(taskSub2).size(), is(2));
        assertThat(menu.getParents(task3).size(), is(1));
        assertThat(menu.getParents(task2).size(), is(1));
        assertThat(menu.getParents(task1).size(), is(0));
    }

    @Test
    public void whenMenu() {
        System.out.println(menu);
        String expected = "Menu : \r\n" +
                "1 task1\r\n"
                + "\t1.1 task2\r\n"
                + "\t\tSubtask 2\r\n"
                + "\t1.2 task3\r\n"
                + "\t\t1.2.1 task4\r\n"
                + "\t\t\t1.2.1.1 check\r\n";
        assertThat(menu.toString(), is(expected));
    }

    @Test
    public void whenMenuItemDefaultAction() {
        assertThat(menu.choice("1.2.1"), is(false));
    }

    @Test
    public void whenMenuItemOverrideAction() {
        assertThat(menu.choice("1.2.1.1"), is(true));
    }
}