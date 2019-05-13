package com.ratintech.behkha.persiandatepickertest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by bFMb on 3/23/2018.
 */
public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.ViewHolder> {

  private List<Time> list;
  private OnItemClickListener onItemClickListener;

  public interface OnItemClickListener {
    void onItemClick(int position, View v);
  }

  public TimesAdapter(List<Time> list) {
    this.list = list;
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickLisener) {
    onItemClickListener = onItemClickLisener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    final Time item = list.get(position);
    if (item.getTime() != null) {
      holder.cc_rv_tv_title.setText(item.getTime());
    }
    holder.ed_work_day_times1.setText(item.getCapacity());
    // holder.cc_rv_tv_title.setTypeface(UBase.setMyFont());

    // Set the view to fade in
    //setFadeAnimation(holder.itemView);
    //setScaleAnimation(holder.itemView);
  }

  @Override
  public int getItemCount() {
    return list != null ? list.size() : 0;
  }

  private void setFadeAnimation(View view) {
    AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
    anim.setDuration(1000);
    view.startAnimation(anim);
  }

  private void setScaleAnimation(View view) {
    ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
      Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    anim.setDuration(500);
    view.startAnimation(anim);
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ViewGroup root;
    TextView cc_rv_tv_title;
    TextInputLayout ed_work_day_times;
    EditText ed_work_day_times1;

    public ViewHolder(View view) {
      super(view);
      root = (ViewGroup) view;
      cc_rv_tv_title = view.findViewById(R.id.cc_rv_tv_title);
      ed_work_day_times = view.findViewById(R.id.ed_work_day_times);
      ed_work_day_times1 = view.findViewById(R.id.ed_work_day_times1);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      //onItemClickListener.onItemClick(getAdapterPosition(), root);
    }
  }
}

