package com.evanemran.quickmeal.Listeners;

import com.evanemran.quickmeal.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
