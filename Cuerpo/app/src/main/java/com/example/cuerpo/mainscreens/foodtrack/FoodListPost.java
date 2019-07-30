package com.example.cuerpo.mainscreens.foodtrack;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodListPost {
    @SerializedName("branded")
    private ArrayList<BrandedItems> branded_items;
    @SerializedName("common")
    private ArrayList<Commons> commons;

    public String getBrandedItems(){
        String info = "";
        for(BrandedItems branded_item : branded_items){
            info = branded_item.getBrandNameItemName();
        }
        return info;
    }

    public String getCommons(){
        String info = "";
        for(Commons common : commons){
            info = common.getFoodName();
        }
        return info;
    }

    private class BrandedItems{
        private String brand_name_item_name;

        private String getBrandNameItemName(){
            return brand_name_item_name;
        }
    }

    private class Commons{
        private String food_name;

        private String getFoodName(){
            return food_name;
        }
    }

}
