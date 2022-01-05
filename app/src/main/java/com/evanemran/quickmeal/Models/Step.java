package com.evanemran.quickmeal.Models;

import java.util.List;

public class Step {
    public int number;
    public String step;
    public List<Ingredient> ingredients;
    public List<Equipment> equipment;
    public Length length;

    public int getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public Length getLength() {
        return length;
    }
}
