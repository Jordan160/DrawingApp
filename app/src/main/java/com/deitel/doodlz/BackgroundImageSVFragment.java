package com.deitel.doodlz;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundImageSVFragment extends DialogFragment {
    String tag;
    ImageButton sonicImage;
    AlertDialog dialog;

    public BackgroundImageSVFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        View backgroundImageDialogView =
                getActivity().getLayoutInflater().inflate(
                        R.layout.fragment_background_image_sv, null);
        builder.setView(backgroundImageDialogView); // add GUI to dialog

        // set the AlertDialog's message
        builder.setTitle("Select a background image");

        final DoodleView doodleView = getDoodleFragment().getDoodleView();


        //set names of the buttons and create an onClickListener for all
        String [] buttonNames = {"image1", "image2", "image3", "image4"};

        for(String button : buttonNames) {
            int resID = getResources().getIdentifier(button, "id", getContext().getPackageName());
            ImageButton image = backgroundImageDialogView.findViewById(resID);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag = String.valueOf(v.getTag());
                    doodleView.setBackgroundImage(getContext(), tag);
                    dialog.dismiss();
                }
            });
        }

        dialog = builder.create();
        return dialog; // return dialog
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_background_image_sv, container, false);
    }

    // gets a reference to the MainActivityFragment
    private MainActivityFragment getDoodleFragment() {
        return (MainActivityFragment) getFragmentManager().findFragmentById(
                R.id.doodleFragment);
    }

    // tell MainActivityFragment that dialog is now displayed
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivityFragment fragment = getDoodleFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(true);
    }

    // tell MainActivityFragment that dialog is no longer displayed
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivityFragment fragment = getDoodleFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(false);
    }
}
