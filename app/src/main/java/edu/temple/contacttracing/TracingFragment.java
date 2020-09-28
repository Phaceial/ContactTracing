package edu.temple.contacttracing;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;



public class TracingFragment extends Fragment {
    Button enable, disable;
//    private TracingInterface mCallback;

    public TracingFragment() {
        // Required empty public constructor
    }

    public static TracingFragment newInstance() {
        TracingFragment fragment = new TracingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        enable = (Button) v.findViewById(R.id.enable);
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Enabled", Toast.LENGTH_SHORT).show();
                getActivity().startService(new Intent(getActivity(), TracingService.class));

            }
        });

        disable = (Button) v.findViewById(R.id.disable);
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Disabled", Toast.LENGTH_SHORT).show();
                getActivity().stopService(new Intent(getActivity(), TracingService.class));
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
    
}