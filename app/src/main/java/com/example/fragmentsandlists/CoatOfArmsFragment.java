package com.example.fragmentsandlists;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    public static final String ARG_CITY = "city";
    private City city;

    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();    // создание

        Bundle args = new Bundle();
        args.putParcelable(ARG_CITY, city);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setReenterTransition(true);
        if (getArguments() != null) {
            city = getArguments().getParcelable(ARG_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        AppCompatImageView imageCoatOfArms = view.findViewById(R.id.coat_of_arms);
        TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        try {
            imageCoatOfArms.setImageResource(images.getResourceId(city.getImageIndex(), 0));
        } catch (Exception e) {
            imageCoatOfArms.setImageResource(images.getResourceId(0, 0));
        }
        TextView cityNameView = view.findViewById(R.id.textView);
        try {
            cityNameView.setText(city.getCityName());
        } catch (Exception e) {
            cityNameView.setText(getResources().getStringArray(R.array.cities)[0]);
        }
        return view;
    }
}