package com.example.user.akjoll;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class Img extends Fragment {


    public Img() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_img, container, false);

        /*ImageView imgView = (ImageView)getView().findViewById(R.id.imageView2);


        Picasso.with(getContext()).load("").into(imgView);*/

/*

        try {
            ImageView i = (ImageView)getView().findViewById(R.id.imageView2);

            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://alpharithm.in/manager/test/img/files/products/mushroom-soup.jpg").getContent());
            i.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/

    }
}