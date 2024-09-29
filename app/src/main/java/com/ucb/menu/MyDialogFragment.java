package com.ucb.menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Recipe Options")
                .setMessage("Would you like to explore another recipe or exit the app?")
                .setPositiveButton("(+)1st Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle navigation to AnotherFragment
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            activity.navigateToAnotherFragment();
                        }
                    }
                })
                .setNegativeButton("(-)3rd Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Show exit dialog
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            activity.showExitDialog();
                        }
                    }
                });

        return builder.create();
    }
}
