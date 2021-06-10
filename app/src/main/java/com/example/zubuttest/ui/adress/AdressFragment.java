package com.example.zubuttest.ui.adress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zubuttest.databinding.FragmentAdressBinding;
import com.example.zubuttest.sys.di.components.DaggerViewModelComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;

import javax.inject.Inject;

public class AdressFragment extends Fragment {

    @Inject
    AdressFragmentViewModel viewModel;

    private FragmentAdressBinding dataBinding;
    private AdressAdapter adapter = new AdressAdapter();

    static AdressFragment newInstance() {
        AdressFragment fragment = new AdressFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerViewModelComponent.builder()
                .contextModule(new ContextModule(this))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (dataBinding = FragmentAdressBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dataBinding.adressRecycler.setLayoutManager( new LinearLayoutManager(getContext()));
        dataBinding.adressRecycler.setAdapter(adapter);

        viewModel.getAllAdress().observe(getViewLifecycleOwner(), list->{
            if(list != null){
                adapter.addItems(list);
            }else{
                dataBinding.adressRecycler.setVisibility(View.GONE);
                dataBinding.lyNoData.getRoot().setVisibility(View.VISIBLE);
            }
        });
    }

}
