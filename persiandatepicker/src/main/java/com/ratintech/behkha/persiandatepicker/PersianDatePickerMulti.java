package com.ratintech.behkha.persiandatepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ratintech.behkha.persiandatepicker.models.Day;
import com.ratintech.behkha.persiandatepicker.models.YearMonth;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PersianDatePickerMulti extends LinearLayout implements View.OnClickListener {

  private TextView mYearMonthText;
  private RecyclerView mDaysRecyclerView;
  private YearMonth mYearMonth;
  private OnDaySelectListener mOnDaySelectListener;
  private Typeface typeface;
  private int selectedPosition = -1;
  private float elevation = 0f;
  private float radius = 0f;

  public PersianDatePickerMulti(Context context) {
    this(context, null);
  }

  public PersianDatePickerMulti(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PersianDatePickerMulti(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PersianDatePickerMulti(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attributeSet) {
    inflate(getContext(), R.layout.persian_date_picker_view, this);

    this.mYearMonthText = findViewById(R.id.year_month_text);
    this.mDaysRecyclerView = findViewById(R.id.days_recycler_view);

  }

  public PersianDatePickerMulti setYearMonth(YearMonth yearMonth) {
    this.mYearMonth = yearMonth;
    return this;
  }

  public PersianDatePickerMulti setTypeFace(Typeface typeface) {
    this.mYearMonthText.setTypeface(typeface);
    this.typeface = typeface;
    return this;
  }

  public PersianDatePickerMulti setListener(OnDaySelectListener onDaySelectListener) {
    mOnDaySelectListener = onDaySelectListener;
    return this;
  }

  public PersianDatePickerMulti setItemElevation(float elevation) {
    this.elevation = elevation;
    return this;
  }

  public PersianDatePickerMulti setItemRadius(float radius) {
    this.radius = radius;
    return this;
  }

  public void load() {
    setupView();
  }

  private void setupView() {
    mYearMonthText.setText(getTitle(mYearMonth));
   /* DaysRecyclerViewAdapterMulti adapter = new DaysRecyclerViewAdapter(getContext(), mYearMonth.getDays(), this.mOnDaySelectListener);
    adapter.setTypeface(this.typeface);
    adapter.setYearMonth(mYearMonth);
    adapter.setElevation(this.elevation);
    adapter.setRadius(this.radius);
    this.mDaysRecyclerView.setAdapter(adapter);*/

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

  public void scrollToPosition(int pos) {
    mDaysRecyclerView.scrollToPosition(pos);
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
       /* int i = v.getId();
        if (i == R.id.left_arrow) {
            gotoNextMonth();

        } else if (i == R.id.right_arrow) {
            gotoPreviousMonth();

        }*/
  }

  public interface OnDaySelectListener {
    void onDaySelect(YearMonth yearMonth, Day day);
  }


}
