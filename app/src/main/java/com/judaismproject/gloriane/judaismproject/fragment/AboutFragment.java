package com.judaismproject.gloriane.judaismproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.judaismproject.gloriane.judaismproject.MainActivity;
import com.judaismproject.gloriane.judaismproject.R;


public class AboutFragment extends Fragment {


    public AboutFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setNavItemChecked(0);
        ((MainActivity)getActivity()).setTitle("About");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
