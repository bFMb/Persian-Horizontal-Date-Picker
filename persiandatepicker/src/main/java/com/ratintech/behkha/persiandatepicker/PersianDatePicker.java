package com.ratintech.behkha.persiandatepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ratintech.behkha.persiandatepicker.adapters.DaysRecyclerViewAdapter;
import com.ratintech.behkha.persiandatepicker.models.Day;
import com.ratintech.behkha.persiandatepicker.models.YearMonth;

import java.util.ArrayList;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PersianDatePicker extends LinearLayout implements View.OnClickListener {

  private TextView mYearMonthText;
  private RecyclerView mDaysRecyclerView;
  private ArrayList<YearMonth> mYearMonths;
  private YearMonth mYearMonth;
  private OnDaySelectListener mOnDaySelectListener;
  private Typeface typeface;
  private int selectedPosition = -1;
  private float elevation = 0f;
  private float radius = 0f;
  private int selectedItemBackgroundColor = R.color.colorPrimary;
  private int selectedItemBackground = 0;
  private int selectedItemTextColor = R.color.colorAccent;
  private int defaultItemBackgroundColor = R.color.colorPrimary;
  private int defaultItemTextColor = R.color.colorAccent;
  private int mYearMonthIndex = 0;
  private boolean hasAnimation = false;

  public PersianDatePicker(Context context) {
    this(context, null);
  }

  public PersianDatePicker(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PersianDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PersianDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attributeSet) {
    inflate(getContext(), R.layout.persian_date_picker_view, this);

    this.mYearMonthText = findViewById(R.id.year_month_text);
    this.mDaysRecyclerView = findViewById(R.id.days_recycler_view);

  }

  public PersianDatePicker setYearMonth(YearMonth yearMonth) {
    this.mYearMonth = yearMonth;
    return this;
  }

  public PersianDatePicker setYearMonths(ArrayList<YearMonth> yearMonths) {
    this.mYearMonths = yearMonths;
    return this;
  }

  public PersianDatePicker setTypeFace(Typeface typeface) {
    this.mYearMonthText.setTypeface(typeface);
    this.typeface = typeface;
    return this;
  }

  public PersianDatePicker setTitleTextColor(@ColorRes int color) {
    this.mYearMonthText.setTextColor(getResources().getColor(color));
    return this;
  }

  public PersianDatePicker setTitleTextSize(int size) {
    this.mYearMonthText.setTextSize(size);
    return this;
  }

  public PersianDatePicker setSelectedItemBackgroundColor(@ColorRes int color) {
    this.selectedItemBackgroundColor = color;
    return this;
  }

  public PersianDatePicker setSelectedItemBackground(@DrawableRes int background) {
    this.selectedItemBackground = background;
    return this;
  }

  public PersianDatePicker setSelectedItemTextColor(@ColorRes int color) {
    this.selectedItemTextColor = color;
    return this;
  }

  public PersianDatePicker setDefaultItemBackgroundColor(@ColorRes int color) {
    this.defaultItemBackgroundColor = color;
    return this;
  }

  public PersianDatePicker setDefaultItemTextColor(@ColorRes int color) {
    this.defaultItemTextColor = color;
    return this;
  }

  public PersianDatePicker setListener(OnDaySelectListener onDaySelectListener) {
    mOnDaySelectListener = onDaySelectListener;
    return this;
  }

  public PersianDatePicker setItemElevation(float elevation) {
    this.elevation = elevation;
    return this;
  }

  public PersianDatePicker setItemRadius(float radius) {
    this.radius = radius;
    return this;
  }

  public PersianDatePicker hasAnimation(boolean hasAnimation) {
    this.hasAnimation = hasAnimation;
    return this;
  }

  public void load() {
    if(mYearMonth == null){
      if (this.mYearMonths.size() == 0)
        return;
      setupView(mYearMonthIndex);
    }else{
      setupView(0);
    }
  }

  private void setupView(int index) {

    if(mYearMonth == null){
      YearMonth yearMonth = this.mYearMonths.get(index);
      mYearMonthText.setText(getTitle(yearMonth));
      DaysRecyclerViewAdapter adapter = new DaysRecyclerViewAdapter(getContext(), yearMonth.getDays(), this.mOnDaySelectListener);
      adapter.setSelectedItemBackgroundColor(this.selectedItemBackgroundColor);
      adapter.setSelectedItemBackground(this.selectedItemBackground);
      adapter.setSelectedItemTextColor(this.selectedItemTextColor);
      adapter.setDefaultItemBackgroundColor(this.defaultItemBackgroundColor);
      adapter.setDefaultItemTextColor(this.defaultItemTextColor);
      adapter.setTypeface(this.typeface);
      adapter.setYearMonth(yearMonth);
      adapter.setElevation(this.elevation);
      adapter.setRadius(this.radius);
      adapter.setAnimation(this.hasAnimation);
      adapter.setSelectionPosition(selectedPosition);
      this.mDaysRecyclerView.setAdapter(adapter);
    }else {
      mYearMonthText.setText(getTitle(mYearMonth));
      DaysRecyclerViewAdapter adapter = new DaysRecyclerViewAdapter(getContext(), mYearMonth.getDays(), this.mOnDaySelectListener);
      adapter.setSelectedItemBackgroundColor(this.selectedItemBackgroundColor);
      adapter.setSelectedItemBackground(this.selectedItemBackground);
      adapter.setSelectedItemTextColor(this.selectedItemTextColor);
      adapter.setDefaultItemBackgroundColor(this.defaultItemBackgroundColor);
      adapter.setDefaultItemTextColor(this.defaultItemTextColor);
      adapter.setTypeface(this.typeface);
      adapter.setYearMonth(mYearMonth);
      adapter.setElevation(this.elevation);
      adapter.setRadius(this.radius);
      adapter.setAnimation(this.hasAnimation);
      adapter.setSelectionPosition(selectedPosition);
      this.mDaysRecyclerView.setAdapter(adapter);
    }

    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
    this.mDaysRecyclerView.setLayoutManager(linearLayoutManager);
    this.mDaysRecyclerView.scrollToPosition(0);

//        this.mDaysRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItemCount = linearLayoutManager.getChildCount();
//                int totalItemCount = linearLayoutManager.getItemCount();
//                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                if ( visibleItemCount + pastVisibleItems + 1 >= totalItemCount ){
//                    gotoNextMonth();
//                }
//            }
//        });

  }

  private String getTitle(YearMonth yearMonth) {
    return yearMonth.getYear() + " " + yearMonth.getMonth();
  }

  public int setItemSelected(String date) {
    String[] strings = date.split("-");
    if (strings.length != 3)
      throw new IllegalArgumentException("date must divided by -");
    int year = Integer.valueOf(strings[0]);
    int day = Integer.valueOf(strings[2]);
    int index = findYearMonthIndex(year, strings[1]);
    if (index != -1) {
      mYearMonthIndex = index;
      selectedPosition = findDayIndex(mYearMonths.get(index), day);
      setupView(mYearMonthIndex);
    }
    return selectedPosition;
  }

  public void scrollToPosition(int pos) {
    mDaysRecyclerView.scrollToPosition(pos);
  }

  private int findYearMonthIndex(int year, String month) {
    for (int i = 0; i < mYearMonths.size(); i++) {
      if (mYearMonths.get(i).getYear() == year && mYearMonths.get(i).getMonthNumber().equals(month))
        return i;
    }
    return -1;
  }

  private int findDayIndex(YearMonth yearMonth, int day) {
    for (int i = 0; i < yearMonth.getDays().size(); i++) {
      if (yearMonth.getDays().get(i).getNumber() == day)
        return i;
    }
    return -1;
  }

  @Override
  public void onClick(View v) {
    int i = v.getId();
   /* if (i == R.id.left_arrow) {
      gotoNextMonth();

    } else if (i == R.id.right_arrow) {
      gotoPreviousMonth();

    }*/
  }

  private void gotoNextMonth() {
    if (mYearMonthIndex == mYearMonths.size() - 1)
      return;
    setupView(++mYearMonthIndex);
  }

  private void gotoPreviousMonth() {
    if (mYearMonthIndex == 0)
      return;
    setupView(--mYearMonthIndex);
  }

  public interface OnDaySelectListener {
    void onDaySelect(YearMonth yearMonth, Day day);
  }


}
