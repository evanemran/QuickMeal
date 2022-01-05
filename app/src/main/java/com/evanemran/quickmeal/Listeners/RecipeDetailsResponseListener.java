package com.evanemran.quickmeal.Listeners;

import com.evanemran.quickmeal.Models.RecipeDetailsResponse;

public interface RecipeDetailsResponseListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
