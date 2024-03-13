package com.example.choosespin;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Spin extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(requireContext(), "Spin", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_spin, container, false);
    }
}