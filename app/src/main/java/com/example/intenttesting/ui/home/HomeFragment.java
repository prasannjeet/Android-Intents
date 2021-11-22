package com.example.intenttesting.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.intenttesting.MainActivity;
import com.example.intenttesting.R;
import com.example.intenttesting.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button sendButton = binding.sendButton;
        sendButton.setOnClickListener(v -> {
             String email = binding.emailAddress.getText().toString();
             String subject = binding.subject.getText().toString();
             String message = binding.message.getText().toString();

             String[] addresses = email.split(",");

             Intent intent = new Intent(Intent.ACTION_SENDTO);
             intent.setData(Uri.parse("mailto:"));
             intent.putExtra(Intent.EXTRA_EMAIL, addresses);
             intent.putExtra(Intent.EXTRA_SUBJECT, subject);
             intent.putExtra(Intent.EXTRA_TEXT, message);

             if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
                 startActivity(intent);
             } else {
                 Toast.makeText(getActivity(), "No App is Installed", Toast.LENGTH_SHORT).show();
             }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}