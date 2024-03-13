package com.example.choosespin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomView;
    @SuppressLint({"WrongViewCast", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Randomaizer()).commit();



        bottomView = findViewById(R.id.bottomNavigationView);

        bottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.randomaizer) {
                    fragment = new Randomaizer();
                } else if (itemId == R.id.coin) {
                    fragment = new Coin();
                } else if (itemId == R.id.spin) {
                    fragment = new Spin();
                } else if (itemId == R.id.calendar) {
                    fragment = new Calendar();
                }


                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, fragment)
                            .commit();
                    return true;
                }
                return false;
            }
        });


    }
}