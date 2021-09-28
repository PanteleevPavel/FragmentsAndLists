package com.example.fragmentsandlists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class AboutAppInstance extends Fragment {

    public static AboutAppInstance newInstance(int someInt, String someString) {
        AboutAppInstance f = new AboutAppInstance();

        Bundle args = new Bundle();
        args.putInt("someInt", someInt);
        args.putString("SomeString", someString);
        f.setArguments(args);
        return f;
    }
}
