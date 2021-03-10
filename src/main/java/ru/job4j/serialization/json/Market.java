package ru.job4j.serialization.json;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Market {
    private final boolean isOpen;
    private final int amountWorkingDays;
    private final ProductFacts facts;
    private final String marketName;
    private final String[] type;

    public Market(boolean isOpen, int amountWorkingDays, ProductFacts facts,
                  String marketName, String... type) {
        this.isOpen = isOpen;
        this.amountWorkingDays = amountWorkingDays;
        this.facts = facts;
        this.marketName = marketName;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Market{" + "isOpen=" + isOpen
                + ", amountWorkingDays=" + amountWorkingDays
                + ", facts=" + facts + ", marketName='" + marketName
                + '\'' + ", type="
                + Arrays.toString(type) + '}';
    }

    public static void main(String[] args) {
        final Market market = new Market(true, 7,
                new ProductFacts("Bread"), "Auchan",
                "Private company", "Retail");
        final Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(market);
        System.out.println(jsonString);
        String modified =
                "{"
                        + "\"isOpen\":true,"
                        + "\"amountWorkingDays\":7,"
                        + "\"facts\":"
                        + "{"
                        + "\"name\":\"Oil\""
                        + "},"
                        + "\"marketName\":\"Auchan\","
                        + "\"type\":"
                        + "[\"Private company\",\"Retail\"]"
                + "}";
        final Market marketFromJson = gson.fromJson(modified, Market.class);
        System.out.println(marketFromJson);
    }
}
