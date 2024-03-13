package com.example.choosespin;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Calendar extends Fragment {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch mySwitch;
    CalendarView calendarView;
    java.util.Calendar myCalendar;
    boolean enableColorMarking = false;
    long selectedDateMillis = 0;
    List<Long> selectedDatas = new ArrayList<>();
    SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        myCalendar = java.util.Calendar.getInstance();
        mySwitch = view.findViewById(R.id.colendarSwitch);
        preferences = requireActivity().getSharedPreferences("calendar_preferens", Context.MODE_PRIVATE);
        loadSelectedData();
        getDate();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

               selectedDateMillis = myCalendar.getTimeInMillis();
               updateDataBackground();

                //setDate(year, month, dayOfMonth);
                getDate();
            }
        });

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                enableColorMarking = isChecked;
                if(isChecked){
                    enableDateColorChange();
                }else{
                    disableDataColorChange();
                }
            }
        });

        Toast.makeText(requireContext(), "Calendar", Toast.LENGTH_SHORT).show();
        return view;
    }

    private void enableDateColorChange(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(enableColorMarking){
                    myCalendar.set(java.util.Calendar.YEAR,year);
                    myCalendar.set(java.util.Calendar.MONTH,month);
                    myCalendar.set(java.util.Calendar.DAY_OF_MONTH,dayOfMonth);

                    long selectedDataMillis = myCalendar.getTimeInMillis();
                    if(!selectedDatas.contains(selectedDataMillis)){
                        selectedDatas.add(selectedDataMillis);
                    }
                    updateDataBackground();
                    getDate();
                }
            }
        });
    }


    private void disableDataColorChange(){
        calendarView.setOnDateChangeListener(null);
    }
    private void updateDataBackground(){
        calendarView.setDate(System.currentTimeMillis());
        for(long date: selectedDatas){
            calendarView.setDate(date);
            calendarView.setBackgroundColor(Color.RED);
        }
    }

      /*public void setDate ( int year, int month, int day){
            myCalendar.set(java.util.Calendar.YEAR, year);
            myCalendar.set(java.util.Calendar.MONTH, month - 1);
            myCalendar.set(java.util.Calendar.DAY_OF_MONTH, day);
            long milli = myCalendar.getTimeInMillis();
            calendarView.setDate(milli);
      }*/

        public void getDate () {
            long date = calendarView.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
            myCalendar.setTimeInMillis(date);
            String select_data = simpleDateFormat.format(myCalendar.getTime());
            Toast.makeText(requireContext(), select_data, Toast.LENGTH_SHORT).show();
        }
        private void saveSelecteDates(){
            SharedPreferences.Editor editor = preferences.edit();
            StringBuilder datesStringBuilder = new StringBuilder();
            for(long date: selectedDatas){
                datesStringBuilder.append(date).append(",");
                editor.apply();
            }
            editor.putString("selected_dates",datesStringBuilder.toString());
            editor.apply();
        }
        private void loadSelectedData(){
            String savedDatasString= preferences.getString("selected_dates","");
            if(!savedDatasString.isEmpty()){
                String[] datesArray = savedDatasString.split(",");
                for(String data: datesArray){
                    selectedDatas.add(Long.parseLong(data));
                }
            }
        }
    }
