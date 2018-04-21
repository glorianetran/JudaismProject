package com.judaismproject.gloriane.judaismproject.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.judaismproject.gloriane.judaismproject.R;

import java.util.ArrayList;

public class MangaFragment extends Fragment {

    ImageView imageView;

    ArrayList<Integer> imageIds;

    int currentImage;

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

        imageIds = new ArrayList<Integer>();


        imageIds.add(R.drawable.manga1);
        imageIds.add(R.drawable.manga2);
        imageIds.add(R.drawable.manga3);
        imageIds.add(R.drawable.manga4);
        imageIds.add(R.drawable.manga5);
        imageIds.add(R.drawable.manga6);
        imageIds.add(R.drawable.manga7);
        imageIds.add(R.drawable.manga8);
        imageIds.add(R.drawable.manga9);
        imageIds.add(R.drawable.manga10);
        imageIds.add(R.drawable.manga11);
        imageIds.add(R.drawable.manga12);
        imageIds.add(R.drawable.manga13);
        imageIds.add(R.drawable.manga14);
        imageIds.add(R.drawable.manga15);
        imageIds.add(R.drawable.manga16);
        imageIds.add(R.drawable.manga17);
        imageIds.add(R.drawable.manga18);

        imageView.setImageResource(imageIds.get(0));

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
                            if (distanceX > 0) {
                                onSwipeRight();
                            }else{
                                onSwipeLeft();
                            }
                            return true;
                        }
                        return false;
                    }

                    public void onSwipeLeft() {
                       // Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
                        currentImage++;
                        if(currentImage < imageIds.size()) {
                            imageView.setImageResource(imageIds.get(currentImage));
                        }else{
                            final Snackbar snackbar = Snackbar.make(getView(), "So long folks, that's all for now. Swipe Right.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("DISMISS", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackbar.dismiss();
                                }
                            });
                            snackbar.show();

                        }
                    }

                    public void onSwipeRight(){
                        currentImage--;
                        if(currentImage > -1) {
                            imageView.setImageResource(imageIds.get(currentImage));
                        }else{
                            final Snackbar snackbar = Snackbar.make(getView(), "Beginning of images. Swipe Left.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("DISMISS", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackbar.dismiss();
                                }
                            });
                            snackbar.show();
                        }
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