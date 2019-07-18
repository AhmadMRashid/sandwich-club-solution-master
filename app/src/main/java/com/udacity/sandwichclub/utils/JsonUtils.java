package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich;
        JSONObject name;
        String sandwichName = null;
        String description =null , origins= null,ImgLink= null;
        List<String> listknown= null,ingredientsList= null;

        try {
            JSONObject root = new JSONObject(json);
            name = root.getJSONObject("name");
            sandwichName = name.getString("mainName");

            JSONArray knownArray = name.getJSONArray("alsoKnownAs");
            listknown = new ArrayList<>();
            if (knownArray.length() != 0){
            for(int i = 0; i < knownArray.length(); i++){
                listknown.add(knownArray.getString(i));
            }}
            else { listknown.add("Uknown");}

            description = root.getString("description");
            origins = root.getString("placeOfOrigin");
             ImgLink = root.getString("image");
            JSONArray ingredientsArray = root.getJSONArray("ingredients");

            ingredientsList = new ArrayList<>();
            for(int i = 0; i < ingredientsArray.length(); i++){
                ingredientsList.add(ingredientsArray.getString(i));
            }
        }

        catch (JSONException e){    e.printStackTrace();
        }
        if (origins.isEmpty() | origins == " " ) {origins = "Uknown";}

     sandwich = new Sandwich(sandwichName, listknown, origins ,description ,ImgLink ,ingredientsList );

        return sandwich;
    }
}
