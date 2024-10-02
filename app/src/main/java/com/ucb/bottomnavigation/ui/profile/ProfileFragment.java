package com.ucb.bottomnavigation.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ucb.bottomnavigation.R;

public class ProfileFragment extends Fragment {

    private EditText editTextName, editTextEmail;
    private RadioGroup radioGroupGender;
    private CheckBox checkboxChatMessenger ,checkboxPhoneCall, checkboxText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        radioGroupGender = view.findViewById(R.id.radioGroupGender);
        checkboxChatMessenger = view.findViewById(R.id.checkboxChatMessenger);
        checkboxPhoneCall = view.findViewById(R.id.checkboxPhoneCall);
        checkboxText = view.findViewById(R.id.checkboxText);
        Button buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void saveProfile() {
        // Retrieve values from EditTexts
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        // Get selected radio button
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton radioButtonGender = getView().findViewById(selectedGenderId);
        String gender = radioButtonGender != null ? radioButtonGender.getText().toString() : "";

        // Get checkbox states
        boolean wantsChatMessenger = checkboxChatMessenger .isChecked();
        boolean wantsPhoneCall = checkboxPhoneCall.isChecked();
        boolean wantsText = checkboxText.isChecked();

         //Display Toast to saved values temporarily
        String message = "Name: " + name + "\nEmail: " + email + "\nGender: " + gender +
                "\nChat: " + wantsChatMessenger + "\nPhoneCall: " + wantsPhoneCall + "\nText: " + wantsText;

        // Display saved information
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
