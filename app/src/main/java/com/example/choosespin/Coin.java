package com.example.choosespin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Coin extends Fragment {

    private ImageView coinImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(),"", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.fragment_coin, container, false);

        coinImageView = view.findViewById(R.id.coinImageView);
        Button flipCoinButton = view.findViewById(R.id.flipCoinButton);

        flipCoinButton.setOnClickListener(v -> flipСoin());
        Toast.makeText(requireContext(), "Coin", Toast.LENGTH_SHORT).show();
        return view;
    }


        private void flipСoin () {
            Random random = new Random();
            int result = random.nextInt(2);

            int imageResource;
            String resultText;

            if (result == 1) {
                imageResource = R.drawable.orel;
                resultText = "Орел";
            } else {
                imageResource = R.drawable.reshka;
                resultText = "Решка";
            }

            // Установка нового изображения
            coinImageView.setImageResource(imageResource);

            // Отображение результата в виде Toast
            Toast.makeText(getContext(), resultText, Toast.LENGTH_SHORT).show();
        }
    }
