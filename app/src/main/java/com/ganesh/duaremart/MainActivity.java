package com.ganesh.duaremart;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganesh.duaremart.Adapter.MainCategoryAdapter;
import com.ganesh.duaremart.ModelResponce.CategoryData;
import com.ganesh.duaremart.ModelResponce.CategoryResponse;
import com.ganesh.duaremart.Network.RetrofitClint;
import com.ganesh.duaremart.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainCategoryAdapter adapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        getAllCategoryData();

        
    }

    private void loadData(List<CategoryData> data) {
        adapter = new MainCategoryAdapter(MainActivity.this,data);
        binding.mainCategoryRecyclerView.setAdapter(adapter);
    }

    private void getAllCategoryData() {
        loading(true);
        Call<CategoryResponse> call = RetrofitClint
                .getInstance()
                .getApi()
                .getAllCategoryDetails();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    CategoryResponse categoryResponse = response.body();
                    loading(false);
                    loadData(categoryResponse.getData());
                }else {
                    Toast.makeText(MainActivity.this, "Failed to retrieving data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occurred while retrieving data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loading(boolean isLoading){
        if (isLoading){
            binding.mainProgressBar.setVisibility(View.VISIBLE);
            binding.mainCategoryRecyclerView.setVisibility(View.GONE);
        }else {
            binding.mainProgressBar.setVisibility(View.GONE);
            binding.mainCategoryRecyclerView.setVisibility(View.VISIBLE);
        }
    }

}