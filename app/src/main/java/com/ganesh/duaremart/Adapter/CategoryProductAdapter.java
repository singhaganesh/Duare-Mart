package com.ganesh.duaremart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganesh.duaremart.ModelResponce.CategoryProductsResponseData;
import com.ganesh.duaremart.databinding.ItemCategoryProductBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>{
    private Context context;
    private List<CategoryProductsResponseData> data;

    public CategoryProductAdapter(Context context, List<CategoryProductsResponseData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CategoryProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryProductBinding itemCategoryProductBinding = ItemCategoryProductBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CategoryProductViewHolder(itemCategoryProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryProductViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CategoryProductViewHolder extends RecyclerView.ViewHolder{
        ItemCategoryProductBinding binding;
        CategoryProductViewHolder(ItemCategoryProductBinding itemCategoryProductBinding){
            super(itemCategoryProductBinding.getRoot());
            binding = itemCategoryProductBinding;
        }
        void setData(CategoryProductsResponseData data){

            if (!data.getStrike_price().equals("0")) {
                binding.textProductName.setText(data.getProduct_name());
                binding.textProductTitle.setText(data.getProduct_title());
                binding.textProductPrice.setText(data.getPrice());
                binding.textProductStrikePrice.setText(data.getStrike_price());
                Picasso.get().load(data.getImage1()).into(binding.imageCategoryProduct);

                binding.textProductStrikePrice.setVisibility(View.VISIBLE);
                binding.line.setVisibility(View.VISIBLE);
            }else {
                binding.textProductName.setText(data.getProduct_name());
                binding.textProductTitle.setText(data.getProduct_title());
                binding.textProductStrikePrice.setText(data.getStrike_price());
                Picasso.get().load(data.getImage1()).into(binding.imageCategoryProduct);

                binding.textProductStrikePrice.setVisibility(View.GONE);
                binding.line.setVisibility(View.GONE);
            }



    }
}
}
