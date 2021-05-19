package ru.job4j.ood.lsp.storage;

public class Food<T> {
    private String name;
    private int expiryDate;
    private DateOfProduct<T> createDate;
    private double price;
    private int discount;

    public Food(String name, int expiryDate, DateOfProduct<T> createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        price -= (price / 100) * discount;
    }

    public int getDiscount() {
        return discount;
    }

    public DateOfProduct<T> getCreateDate() {
        return createDate;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return  name
                + ", expiration date = " + expiryDate
                + ", date of create = " + createDate
                + ", price = " + price
                + ", discount = " + discount + "%";
    }
}
