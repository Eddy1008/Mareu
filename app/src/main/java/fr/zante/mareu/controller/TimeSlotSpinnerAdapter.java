package fr.zante.mareu.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.zante.mareu.R;
import fr.zante.mareu.model.TimeSlot;

/**
 * <p>Adapter which handles the list of Time slot</p>
 * <p>to display in a spinner</p>
 * @author Eddy GALMAND
 */
public class TimeSlotSpinnerAdapter extends ArrayAdapter<TimeSlot> {

    private LayoutInflater layoutInflater;

    public TimeSlotSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<TimeSlot> timeSlots) {
        super(context, resource, timeSlots);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.item_time_slop_spinner, null, true);
        TimeSlot timeSlot = getItem(position);
        TextView textview = (TextView) rowView.findViewById(R.id.item_time_slop_spinner_textview);
        textview.setText(timeSlot.getBeginning());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_time_slop_spinner, parent, false);
        }
        TimeSlot timeSlot = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.item_time_slop_spinner_textview);
        textView.setText(timeSlot.getBeginning());
        return convertView;
    }
}
