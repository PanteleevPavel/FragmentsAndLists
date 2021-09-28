package com.example.fragmentsandlists;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NotesFragment extends Fragment {

    public static final String CURRENT_CITY = "CurrentCity";
    private Note currentNote;
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentNote = new Note(fi, getResources().getStringArray(R.array.cities)[fi]);
                showCoatOfArms(currentNote);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_CITY);
        } else {
            currentNote = new Note(0, getResources().getStringArray(R.array.cities)[0]);
        }

        if (isLandscape) {
            showLandCoatOfArms(currentNote);
        }
    }

    private void showCoatOfArms(Note currentNote) {
        if (isLandscape) {
            showLandCoatOfArms(currentNote);
        } else {
            showPortCoatOfArms(currentNote);
        }
    }

    private void showLandCoatOfArms(Note currentNote) {
        DescriptionsFragment detail = DescriptionsFragment.newInstance(currentNote);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.descriptionTextView, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortCoatOfArms(Note currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DescriptionsActivity.class);
        intent.putExtra(DescriptionsFragment.ARG_NOTE, currentNote);
        startActivity(intent);
    }
}