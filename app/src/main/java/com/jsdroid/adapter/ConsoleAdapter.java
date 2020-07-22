package com.jsdroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsdroid.R;
import com.jsdroid.bean.ConsoleMessage;

import java.util.List;

public class ConsoleAdapter extends RecyclerView.Adapter<ConsoleAdapter.VH> {
    private Context context;
    private List<ConsoleMessage> messages;
    public ConsoleAdapter(Context context, List<ConsoleMessage> messages) {
        this.context = context;
        this.messages = messages;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_console_message, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    static class VH extends RecyclerView.ViewHolder{
        TextView textView;
        VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.message);
        }
    }
}
