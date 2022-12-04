package com.example.booker.ui.treatments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booker.model.Treatment;
import com.example.booker.R;
import com.example.booker.adapter.TreatmentAdapter;
import java.util.ArrayList;


public class TreatmentsFragment extends Fragment implements TreatmentAdapter.onListItemListener {

    TreatmentsViewModel viewModel;
    RecyclerView recyclerView;
    TreatmentAdapter adapter;

  @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_treatments, container, false);

        viewModel = new ViewModelProvider(this).get(TreatmentsViewModel.class);
        recyclerView = view.findViewById(R.id.servicesList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new TreatmentAdapter(this);

        getAllServices();

        return view;
  }

    private void getAllServices() {
      viewModel.getAllServices().observe(getViewLifecycleOwner(), new Observer<ArrayList<Treatment>>() {
          @Override
          public void onChanged(ArrayList<Treatment> treatments) {
              adapter.setServiceItems(treatments);
              recyclerView.setAdapter(adapter);
          }
      });
  }

    @Override
    public void onClicked(Treatment treatment) {
        //TODO implement functionality when the service item is clicked
    }
}