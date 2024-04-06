package com.ganesh.duaremart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganesh.duaremart.CategoryProductsActivity;
import com.ganesh.duaremart.ModelResponce.CategoryData;
import com.ganesh.duaremart.databinding.ItemMaincategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.CategoryViewHolder>{
    private Context context;
    private List<CategoryData> categoryData;
    public static final String PRODUCT_CATEGORY_ID = "productCategoryId";

    public MainCategoryAdapter(Context context, List<CategoryData> categoryData) {
        this.context = context;
        this.categoryData = categoryData;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMaincategoryBinding itemMaincategoryBinding = ItemMaincategoryBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoryViewHolder(itemMaincategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.setData(categoryData.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ItemMaincategoryBinding binding;

        CategoryViewHolder(ItemMaincategoryBinding itemMaincategoryBinding){
            super(itemMaincategoryBinding.getRoot());
            binding = itemMaincategoryBinding;
        }
        void setData(CategoryData data){
            binding.textMainCategory.setText(data.getName());
            Picasso.get().load(data.getCategory_image()).into(binding.imageMainCategory);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CategoryProductsActivity.class);
                    intent.putExtra(PRODUCT_CATEGORY_ID,data.getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
