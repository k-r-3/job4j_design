package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

public class ControllQuality<T> {
    private DateOfProduct<T> date;
    private List<Food<T>> foods;
    private List<Storage> storages;

    public ControllQuality(List<Food<T>> foods, List<Storage> storage, DateOfProduct<T> date) {
        this.foods = foods;
        this.storages = storage;
        date.setNow();
        this.date = date;
        validateSort(foods);
    }

    private float getRemaining(Food<T> food) {
        return food.getExpiryDate() - (
                date.getDayOfYear() - food.getCreateDate().getDayOfYear()
        );
    }

    void validateSort(List<Food<T>> foods) {
        for (Food<T> food : foods) {
            float daysLeft = getRemaining(food);
            for (Storage storage : storages) {
                storage.add(food, daysLeft);
            }
        }
    }

    public void resort() {
        if (!date.getDate().equals(LocalDate.now())) {
            for (Storage storage : storages) {
                ListIterator<Food> list = storage.getFoods().listIterator();
                while (list.hasNext()) {
                    Food food = list.next();
                    if (!storage.validate(food, getRemaining(food))) {
                        list.remove();
                        validateSort(List.of(food));
                    }
                }
            }
        }
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Storage storage : storages) {
            sb.append("Foods in " + storage.getClass().getSimpleName() + " : ")
                    .append(System.lineSeparator())
                    .append(storage.getFoods().toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}