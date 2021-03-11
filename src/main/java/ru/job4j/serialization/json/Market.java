package ru.job4j.serialization.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public boolean isOpen() {
        return isOpen;
    }

    public int getAmountWorkingDays() {
        return amountWorkingDays;
    }

    public String getMarketName() {
        return marketName;
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
        final Market moreMarket = gson.fromJson(jsonString, Market.class);
        System.out.println(moreMarket);

        Market newMarket = new Market(false, 7,
                new ProductFacts("Fish"), "Ocean", "Retail", "state company");
        JSONObject jsonProduct = new JSONObject("{\"name\":\"Meat\"}");
        List<String> types = new ArrayList<>(Arrays.asList("Retail", "state company"));
        JSONArray jsonTypes = new JSONArray(types);
        JSONObject newObject = new JSONObject();
        newObject.put("market is open", newMarket.isOpen());
        newObject.put("working days", newMarket.getAmountWorkingDays());
        newObject.put("Product Facts", jsonProduct);
        newObject.put("market name", newMarket.getMarketName());
        newObject.put("type", jsonTypes);
        System.out.println(newObject.toString());
        System.out.println(new JSONObject(newMarket).toString());
    }
}
