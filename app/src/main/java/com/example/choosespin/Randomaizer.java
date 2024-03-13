package com.example.choosespin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Randomaizer extends Fragment {

    private TextView textViewRandomaizer;
    private EditText editTextMinRange;
    private EditText editTextMaxRange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Надуваем макет фрагмента
        View view = inflater.inflate(R.layout.fragment_randomaizer, container, false);

        Button buttonToGenerate = view.findViewById(R.id.buttonToGenerate);
        textViewRandomaizer = view.findViewById(R.id.textViewRandomaizer);
        editTextMinRange = view.findViewById(R.id.editTextMinRange);
        editTextMaxRange = view.findViewById(R.id.editTextMaxRange);

        final Random random = new Random();

        buttonToGenerate.setOnClickListener(v -> {
            // Получаем введенные значения минимального и максимального диапазона
            String minRangeText = editTextMinRange.getText().toString();
            String maxRangeText = editTextMaxRange.getText().toString();

            if (minRangeText.isEmpty() || maxRangeText.isEmpty()) {
                // Показываем сообщение, если значения не введены
                Toast.makeText(requireContext(), "Введите минимальное и максимальное значение", Toast.LENGTH_SHORT).show();
            } else {
                int minRange = Integer.parseInt(minRangeText);
                int maxRange = Integer.parseInt(maxRangeText);

                if (minRange > maxRange) {
                    // Показываем сообщение, если минимальное значение больше максимального
                    Toast.makeText(requireContext(), "Поменяйте значения. Минимальное значение не должно быть больше максимального.", Toast.LENGTH_SHORT).show();
                } else {
                    // Генерируем случайное число в заданном диапазоне и устанавливаем его в текстовое поле
                    int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
                    textViewRandomaizer.setText(String.valueOf(randomNumber));


                }
            }
        });

        Toast.makeText(requireContext(), "Randomaizer", Toast.LENGTH_SHORT).show();
        // Возвращаем представление фрагмента после настройки элементов
        return view;
    }
}
