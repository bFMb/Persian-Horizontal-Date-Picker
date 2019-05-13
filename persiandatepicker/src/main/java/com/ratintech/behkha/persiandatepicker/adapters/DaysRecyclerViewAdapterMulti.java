package com.ratintech.behkha.persiandatepicker.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ratintech.behkha.persiandatepicker.PersianDatePicker;
import com.ratintech.behkha.persiandatepicker.R;
import com.ratintech.behkha.persiandatepicker.models.Day;
import com.ratintech.behkha.persiandatepicker.models.YearMonth;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class DaysRecyclerViewAdapterMulti extends RecyclerView.Adapter<DaysRecyclerViewAdapterMulti.ViewHolder> {

  private Context mContext;
  private YearMonth yearMonth;
  private ArrayList<Day> mDays;
  private PersianDatePicker.OnDaySelectListener mOnDaySelectListener;
  private Typeface typeface;
  private float elevation;
  private float radius;
  private ArrayList<Day> days;

  public DaysRecyclerViewAdapterMulti(Context mContext, ArrayList<Day> mDays, PersianDatePicker.OnDaySelectListener onDaySelectListener) {
    this.mContext = mContext;
    this.mDays = mDays;
    this.mOnDaySelectListener = onDaySelectListener;
    days = new ArrayList<>();
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView mDay;
    TextView mWeekDay;
    CardView mRoot;

    ViewHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      mDay = itemView.findViewById(R.id.day);
      mWeekDay = itemView.findViewById(R.id.week_day);
      mRoot = itemView.findViewById(R.id.root);

      mRoot.setCardElevation(elevation);
      mRoot.setRadius(radius);
      mRoot.setCardBackgroundColor(Color.WHITE);

      if (typeface != null) {
        mDay.setTypeface(typeface);
        mWeekDay.setTypeface(typeface);
      }
    }


    void setItem(Day day) {
      bind(day);
    }

    private void bind(final Day day) {
      mDay.setText(String.valueOf(day.getNumber()));
      mWeekDay.setText(day.getDay());

      mRoot.setCardBackgroundColor(day.isSelected() ? Color.CYAN : Color.WHITE);
      mRoot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          day.setSelected(!day.isSelected());
          mRoot.setCardBackgroundColor(day.isSelected() ? Color.CYAN : Color.WHITE);
          if (!day.isSelected()) {
            days.remove(day);
          } else {
            days.add(day);
          }
          if (mOnDaySelectListener != null) {
           // mOnDaySelectListener.onDaySelect(yearMonth, days);
          }
        }
      });


    }

    @Override
    public void onClick(View v) {
          /*  int prevSelectPosition = selectedPosition;
            selectedPosition = getAdapterPosition();
            Day day = mDays.get( selectedPosition );
            if ( hasAnimation ) {
                notifyItemChanged(selectedPosition);
                notifyItemChanged(prevSelectPosition);
            } else
                notifyDataSetChanged();
            if (mOnDaySelectListener != null) {
                mOnDaySelectListener.onDaySelect( yearMonth , day );
            } */
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(
      LayoutInflater.from(mContext).inflate(R.layout.day_item_layout, parent, false)
    );
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

    final Day day = mDays.get(position);
    holder.setItem(day);

  }

  @Override
  public int getItemCount() {
    return mDays.size();
  }

  public void setYearMonth(YearMonth yearMonth) {
    this.yearMonth = yearMonth;
  }

  public void setTypeface(Typeface typeface) {
    this.typeface = typeface;
  }

  public void setElevation(float elevation) {
    this.elevation = elevation;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }
}
