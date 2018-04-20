package com.judaismproject.gloriane.judaismproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.judaismproject.gloriane.judaismproject.R;

import java.util.ArrayList;

public class MangaFragment extends Fragment{

    ImageView imageView;

    ArrayList imageIds;

    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;


    public MangaFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manga, container, false);
        imageView = view.findViewById(R.id.image);

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        float distanceX = e2.getX() - e1.getX();
                        float distanceY = e2.getY() - e1.getY();
                        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (distanceX > 0)
                                onSwipeRight();
                            else
                                onSwipeLeft();
                            return true;
                        }
                        return false;
                    }

                    public void onSwipeLeft() {
                        Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();

                    }

                    public void onSwipeRight() {
                        Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                    }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return view;
    }

}
