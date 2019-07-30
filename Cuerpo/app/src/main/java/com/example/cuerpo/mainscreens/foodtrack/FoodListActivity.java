package com.example.cuerpo.mainscreens.foodtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cuerpo.R;
import com.example.cuerpo.RecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodListActivity extends AppCompatActivity {
    private static final String CALORIES_CONSUMED = "calories_consumed";
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> calorieList=new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ArrayList<String> filteredList = new ArrayList<>();
    boolean search = false;
    private int position;
    private String food_word;
    private double calories = 0.0;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            position = viewHolder.getAdapterPosition();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://trackapi.nutritionix.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            FoodCalorieAPI foodCalorieAPI = retrofit.create(FoodCalorieAPI.class);

            Call<FoodCaloriePost> call = foodCalorieAPI.getFoodCaloriePosts(food_word);
            call.enqueue(new Callback<FoodCaloriePost>() {
                @Override
                public void onResponse(@NonNull Call<FoodCaloriePost> call, @NonNull Response<FoodCaloriePost> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(FoodListActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    FoodCaloriePost foodCaloriePost = response.body();
                    ArrayList<String> calList1 = foodCaloriePost.getBrandedItems();
                    ArrayList<String> calList2 = foodCaloriePost.getCommons();
                    calorieList.addAll(calList1);
                    calorieList.addAll(calList2);
                    calories += Double.parseDouble(calorieList.get(position));
                    Toast.makeText(FoodListActivity.this, "Added " + calorieList.get(position) + " calories", Toast.LENGTH_SHORT).show();

                }


                @Override
                public void onFailure(@NonNull Call<FoodCaloriePost> call,@NonNull Throwable t) {
                    Toast.makeText(FoodListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        EditText editText = findViewById(R.id.edittext);
        ImageView close_button = findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();

                returnIntent.putExtra(CALORIES_CONSUMED, calories);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setItemClickListener(onItemClickListener);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search = true;
                food_word = s.toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://trackapi.nutritionix.com/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                FoodListAPI foodListAPI = retrofit.create(FoodListAPI.class);

                Call<FoodListPost> call = foodListAPI.getFoodListPosts(food_word);
                call.enqueue(new Callback<FoodListPost>() {
                    @Override
                    public void onResponse(@NonNull Call<FoodListPost> call, @NonNull Response<FoodListPost> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(FoodListActivity.this, "Data Not Found!", Toast.LENGTH_LONG).show();
                            return;
                        }

                        FoodListPost foodListPost = response.body();
                        String branded_content = foodListPost.getBrandedItems();
                        String common_content = foodListPost.getCommons();
                        items.add(branded_content);
                        items.add(common_content);
                        makeList();


                    }


                    @Override
                    public void onFailure(@NonNull Call<FoodListPost> call,@NonNull Throwable t) {
                        Toast.makeText(FoodListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    private void makeList(){
        recyclerViewAdapter.setItems(items);
    }
}
