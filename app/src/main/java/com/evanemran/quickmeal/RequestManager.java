package com.evanemran.quickmeal;

import android.content.Context;

import com.evanemran.quickmeal.Listeners.InstructionsListener;
import com.evanemran.quickmeal.Listeners.RandomAPIResponseListener;
import com.evanemran.quickmeal.Listeners.RecipeDetailsResponseListener;
import com.evanemran.quickmeal.Listeners.SimilarRecipeListener;
import com.evanemran.quickmeal.Models.InstructionsResponse;
import com.evanemran.quickmeal.Models.RandomRecipeResponse;
import com.evanemran.quickmeal.Models.RecipeDetailsResponse;
import com.evanemran.quickmeal.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void GetRandomRecipes(RandomAPIResponseListener listener, List<String> tags){
        CallRandomRecipe callRandomRecipe = retrofit.create(CallRandomRecipe.class);
        Call<RandomRecipeResponse> call = callRandomRecipe.callRandomRecipe(context.getString(R.string.api_key), "80", tags );
        call.enqueue(new Callback<RandomRecipeResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeResponse> call, Response<RandomRecipeResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body().getRecipes(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void GetRecipeDetails(RecipeDetailsResponseListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void GetSimilarRecipe(SimilarRecipeListener listener, int id){
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipes.callSimilarRecipe(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void GetInstructions(InstructionsListener listener, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipe{

        @GET("recipes/random")
        Call<RandomRecipeResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }
    private interface CallRecipeDetails{

        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
    private interface CallSimilarRecipes{

        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
    private interface CallInstructions{

        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
