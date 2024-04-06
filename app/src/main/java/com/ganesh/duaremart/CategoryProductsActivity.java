package com.ganesh.duaremart;

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

import com.ganesh.duaremart.Adapter.CategoryProductAdapter;
import com.ganesh.duaremart.Adapter.MainCategoryAdapter;
import com.ganesh.duaremart.ModelResponce.CategoryProductsResponse;
import com.ganesh.duaremart.ModelResponce.CategoryProductsResponseData;
import com.ganesh.duaremart.Network.RetrofitClint;
import com.ganesh.duaremart.databinding.ActivityCategoryProductsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductsActivity extends AppCompatActivity {
    private ActivityCategoryProductsBinding binding;
    private CategoryProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCategoryProductsBinding.inflate(getLayoutInflater());
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


        String categoryId = getIntent().getStringExtra(MainCategoryAdapter.PRODUCT_CATEGORY_ID);

        getAllCategoryProductData(categoryId);

        setListener();
    }

    private void setListener() {
        binding.imageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData(List<CategoryProductsResponseData> data){
        adapter = new CategoryProductAdapter(CategoryProductsActivity.this,data);
        binding.categoryProductRecyclerView.setAdapter(adapter);
    }

    private void getAllCategoryProductData(String categoryId) {
        loading(true);
        Call<CategoryProductsResponse> call = RetrofitClint
                .getInstance()
                .getApi()
                .getAllCategoryProductDetails(categoryId);
        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Call<CategoryProductsResponse> call, Response<CategoryProductsResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    CategoryProductsResponse categoryProductsResponse = response.body();
                    loading(false);
                    loadData(categoryProductsResponse.getData());
                }else {
                    Toast.makeText(CategoryProductsActivity.this, "Failed to retrieving data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryProductsResponse> call, Throwable t) {
                Toast.makeText(CategoryProductsActivity.this, "Error occurred while retrieving data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loading(boolean isLoading){
        if (isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.categoryProductRecyclerView.setVisibility(View.GONE);
        }else {
            binding.progressBar.setVisibility(View.GONE);
            binding.categoryProductRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}