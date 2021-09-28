package com.example.fragmentsandlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DescriptionsFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    private Note note;

    public static DescriptionsFragment newInstance(Note note) {
        DescriptionsFragment f = new DescriptionsFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReenterTransition(true);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descriptions, container, false);
        TextView noteNameView = view.findViewById(R.id.textView);
        TextView descriptionView = view.findViewById(R.id.descriptionTextView);
        noteNameView.setText(note.getCityName());
        descriptionView.setText(getResources().getStringArray(R.array.descriptions)[note.getDescriptionIndex()]);
        descriptionView.setTextSize(24);
        return view;
    }
}