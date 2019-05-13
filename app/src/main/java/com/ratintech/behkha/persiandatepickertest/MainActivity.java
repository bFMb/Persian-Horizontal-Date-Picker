package com.ratintech.behkha.persiandatepickertest;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.ratintech.behkha.persiandatepicker.PersianDatePicker;
import com.ratintech.behkha.persiandatepicker.models.Calendar;
import com.ratintech.behkha.persiandatepicker.models.Day;
import com.ratintech.behkha.persiandatepicker.models.YearMonth;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class MainActivity extends AppCompatActivity {
  ArrayList<Day> days = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView tv_timeSelect = findViewById(R.id.tv_timeSelect);
    final LinearLayout lineAllWorkDayCapacity = findViewById(R.id.lineAllWorkDayCapacity);
    final RecyclerView rv_selectedDates = findViewById(R.id.rv_selectedDates);
    rv_selectedDates.setLayoutManager(new GridLayoutManager(this, 5));


    PersianDatePicker persianDatePicker = findViewById(R.id.persian_date_picker);
    persianDatePicker.setYearMonth(new Calendar(new PersianCalendar().getPersianLongDate()).getYearMonths().get(java.util.Calendar.ERA))
      .setDefaultItemBackgroundColor(R.color.defaultBackgroundColor)  // background color of non-selected item
      .setDefaultItemTextColor(R.color.defaultTextColor)  // text color of non-selected item
      .setSelectedItemBackgroundColor(R.color.colorPrimary) // background color of selected item
      .setSelectedItemTextColor(R.color.selectText) // text color of selected item
      .setListener(new PersianDatePicker.OnDaySelectListener() {
        @Override
        public void onDaySelect(final YearMonth yearMonth, final Day day) {
          // right your code here when item is selected
          //  Toast.makeText(getApplicationContext() , yearMonth.getYear() + "-"
          //  + yearMonth.getMonthNumber() + "-" + day.getNumberString() , Toast.LENGTH_SHORT).show();
          //Log.e("Days", yearMonth.getMonth() + " - " + yearMonth.getMonthNumber() + "-" + day.getNumberString());
         // tv_timeSelect.setText( " " + day.getDay() + " - " + day.getNumberString() + "/" + yearMonth.getMonth() + "/" + yearMonth.getYear());
          if(!days.contains(day)){
            days.add(day);
          }else{
            days.remove(day);
          }
        //  Log.e("ddays" , days.get(0).getDay() + " " );
          DateSelectedAdapter dateSelectedAdapter = new DateSelectedAdapter(days);
          dateSelectedAdapter.setOnItemClickListener(new DateSelectedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
              showTimesDialog(" " + days.get(position).getDay() + " - "
                + days.get(position).getNumberString() + "/" + yearMonth.getMonth() + "/" + yearMonth.getYear());
            }
          });
          rv_selectedDates.setAdapter(dateSelectedAdapter);
        }
      })
      .setItemElevation(4f)  // default is 0
      .hasAnimation(false) // Animation for item selection . default is false
      .setItemRadius(2f)  // default is 0
      .load();


    PersianDatePicker persianDatePicker1 = findViewById(R.id.persian_date_picker_next);
    YearMonth yearMonth = new Calendar(new PersianCalendar().getPersianLongDate()).getYearMonths().get(java.util.Calendar.ERA + 1);
    ArrayList<Day> tenDays = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      tenDays.add(yearMonth.getDays().get(i));
    }
    yearMonth.setDays(tenDays);
    persianDatePicker1.setYearMonth(yearMonth)
      .setDefaultItemBackgroundColor(R.color.defaultBackgroundColor)  // background color of non-selected item
      .setDefaultItemTextColor(R.color.defaultTextColor)  // text color of non-selected item
      .setSelectedItemBackgroundColor(R.color.colorPrimary) // background color of selected item
      .setSelectedItemTextColor(R.color.selectText) // text color of selected item
      .setListener(new PersianDatePicker.OnDaySelectListener() {
        @Override
        public void onDaySelect(final YearMonth yearMonth, Day day) {
          // right your code here when item is selected
          //  Toast.makeText(getApplicationContext() , yearMonth.getYear() + "-"
          //  + yearMonth.getMonthNumber() + "-" + day.getNumberString() , Toast.LENGTH_SHORT).show();
          if(!days.contains(day)){
            days.add(day);
          }else{
            days.remove(day);
          }
          DateSelectedAdapter dateSelectedAdapter = new DateSelectedAdapter(days);
          dateSelectedAdapter.setOnItemClickListener(new DateSelectedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
              showTimesDialog(" " + days.get(position).getDay() + " - "
                + days.get(position).getNumberString() + "/" + yearMonth.getMonth() + "/" + yearMonth.getYear());
            }
          });
          rv_selectedDates.setAdapter(dateSelectedAdapter);
        }
      })
      .setItemElevation(4f)  // default is 0
      .hasAnimation(false) // Animation for item selection . default is false
      .setItemRadius(2f)  // default is 0
      .load();




  }


  public void showTimesDialog(String day){

    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
    LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
    View view = layoutInflater.inflate(R.layout.times_dialog, null);
    builder.setView(view);

    final TextView tv_selectedDate = view.findViewById(R.id.tv_selectedDate);
    tv_selectedDate.setText(day);
    final RecyclerView recyclerView = view.findViewById(R.id.rv_times);
    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

    final TextInputLayout ed_allwork_day_times = view.findViewById(R.id.ed_allwork_day_times);
    final EditText ed_allwork_day_times1 = view.findViewById(R.id.ed_allwork_day_times1);
    final Button btn_confirm_allwork_day_capacity = view.findViewById(R.id.btn_confirm_allwork_day_capacity);

    ArrayList<Time> times = new ArrayList<>();
    Time time = new Time();
    time.setTime("ساعت 10 تا 11");
    time.setCapacity("10");
    Time time1 = new Time();
    time1.setTime("ساعت 11 تا 12");
    time1.setCapacity("10");
    times.add(time);
    times.add(time1);
    TimesAdapter timesAdapter = new TimesAdapter(times);
    recyclerView.setAdapter(timesAdapter);
    btn_confirm_allwork_day_capacity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ArrayList<Time> times = new ArrayList<>();
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
          Time time = new Time();
          //time.setTime();
          time.setCapacity(ed_allwork_day_times1.getText().toString());
          times.add(time);
        }
        TimesAdapter timesAdapter = new TimesAdapter(times);
        recyclerView.setAdapter(timesAdapter);
      }
    });


    Dialog dialog = builder.create();
    //dialog.getWindow().setLayout(600, 400); //Controlling width and height.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      dialog.getWindow().setStatusBarColor(ContextCompat.getColor(UBase.getCurrentActivity(), R.color.colorPrimaryDark));
    }

    dialog.show();

  }


}
