package com.example.pr17;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClothAdapter extends ArrayAdapter<Cloth> {

    private Context context;
    private List<Cloth> clothList;

    public ClothAdapter(@NonNull Context context, List<Cloth> clothList) {
        super(context, 0, clothList);
        this.context = context;
        this.clothList = clothList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_cloth, parent, false);
        }

        // Получаем текущий элемент
        Cloth currentCloth = clothList.get(position);

        // Устанавливаем название товара
        TextView nameTextView = listItemView.findViewById(R.id.clothNameTextView);
        nameTextView.setText(currentCloth.getName());

        // Устанавливаем цену товара
        TextView priceTextView = listItemView.findViewById(R.id.clothPriceTextView);
        priceTextView.setText(currentCloth.getPrice() + " руб.");

        // Устанавливаем изображение товара
        ImageView imageView = listItemView.findViewById(R.id.clothImageView);
        imageView.setImageBitmap(base64ToBitmap(currentCloth.getImageBase64()));

        return listItemView;
    }

    // Метод для преобразования Base64 в Bitmap
    private Bitmap base64ToBitmap(String base64) {
        byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}