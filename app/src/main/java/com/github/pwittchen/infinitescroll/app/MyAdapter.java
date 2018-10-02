package com.github.pwittchen.infinitescroll.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import pwittchen.github.com.infinitescroll.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

  private final List<String> items;

  public MyAdapter(final List<String> items) {
    this.items = items;
  }

  @Override public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    final Context context = parent.getContext();
    final View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
    final String itemText = items.get(position);
    holder.tvItem.setText(itemText);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  public List<String> getItems() {
    return items;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    protected TextView tvItem;

    public ViewHolder(final View itemView) {
      super(itemView);
      tvItem = (TextView) itemView.findViewById(R.id.tv_item);
    }
  }
}
