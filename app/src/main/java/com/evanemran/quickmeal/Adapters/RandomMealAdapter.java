package com.evanemran.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.quickmeal.Listeners.CustomOnClickListener;
import com.evanemran.quickmeal.Models.RandomRecipe;
import com.evanemran.quickmeal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealViewHolder>{

    Context context;
    List<RandomRecipe> list;
    CustomOnClickListener listener;

    public RandomMealAdapter(Context context, List<RandomRecipe> list, CustomOnClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomMealViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomMealViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_ready_time.setText(String.valueOf(list.get(position).getReadyInMinutes())+" Min");
        holder.textView_likes.setText(String.valueOf(list.get(position).aggregateLikes)+" Likes");
        holder.textView_serving_cost.setText(String.valueOf(list.get(position).getServings())+" Servings");
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView_food);
        if (list.get(position).isVegetarian()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Vegetarian");
        }
        if (list.get(position).isVegan()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Vegan");
        }
        if (list.get(position).isGlutenFree()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" GlutenFree");
        }
        if (list.get(position).isDairyFree()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" DairyFree");
        }
        if (list.get(position).isVeryHealthy()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Very Healthy");
        }
        if (list.get(position).isCheap()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Cheap");
        }
        if (list.get(position).isVeryPopular()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Very Popular");
        }
        if (list.get(position).isSustainable()){
            holder.textView_tags_frees.setText(holder.textView_tags_frees.getText()+" Sustainable");
        }
        holder.textView_tags_frees.setSelected(true);

        holder.home_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(String.valueOf(list.get(holder.getAdapterPosition()).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomMealViewHolder extends RecyclerView.ViewHolder {

    TextView textView_title, textView_ready_time, textView_serving_cost, textView_likes, textView_tags_frees, textView_tags_includes;
    ImageView imageView_food;
    CardView home_list_container;

    public RandomMealViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_title = itemView.findViewById(R.id.textView_title);
        textView_ready_time = itemView.findViewById(R.id.textView_ready_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_serving_cost = itemView.findViewById(R.id.textView_serving_cost);
        textView_tags_includes = itemView.findViewById(R.id.textView_tags_includes);
        textView_tags_frees = itemView.findViewById(R.id.textView_tags_frees);
        home_list_container = itemView.findViewById(R.id.home_list_container);
    }
}
