package com.example.cuerpo.mainscreens.foodtrack;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodCaloriePost {

    @SerializedName("branded")
    private ArrayList<BrandedItems> branded_items;
    @SerializedName("common")
    private ArrayList<Commons> commons;

    public ArrayList<String> getBrandedItems(){
        ArrayList<String> calorieList=new ArrayList<>();
        for(BrandedItems branded_item : branded_items){
            calorieList.add(branded_item.getNfCalories());
        }
        return calorieList;
    }

    public ArrayList<String> getCommons(){
        ArrayList<String> calorieList=new ArrayList<>();
        for(Commons common : commons){
            calorieList.add(common.getCalories());
        }
        return calorieList;
    }

    private class BrandedItems{
        private String nf_calories;

        private String getNfCalories(){
            return nf_calories;
        }
    }

    private class Commons{
        @SerializedName("nf_calories")
        private String calories;

        private String getCalories(){
            return calories;
        }
    }

}
