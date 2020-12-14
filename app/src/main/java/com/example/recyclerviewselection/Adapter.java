package com.example.recyclerviewselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Person> persons;
    private SelectionTracker<Long> tracker;

    public Adapter(ArrayList<Person> persons) {
        this.persons = persons;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(persons.get(position), tracker.isSelected((long) position));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    public void setTracker(SelectionTracker<Long> tracker) {
        this.tracker = tracker;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Person person, boolean isActivated) {
            TextView tvFirstname = itemView.findViewById(R.id.tv_firstname);
            TextView tvLastname = itemView.findViewById(R.id.tv_lastname);
            tvFirstname.setText(person.getFirstname());
            tvLastname.setText(person.getLastname());
            itemView.setActivated(isActivated);
        }

        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return new ItemDetailsLookup.ItemDetails<Long>() {
                @Override
                public int getPosition() {
                    return getAdapterPosition();
                }

                @Nullable
                @Override
                public Long getSelectionKey() {
                    return getItemId();
                }
            };
        }
    }
}
