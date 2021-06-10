package com.example.zubuttest.ui.adress;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zubuttest.R;
import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.databinding.FragmentAdressBinding;
import com.example.zubuttest.sys.di.components.DaggerViewModelComponent;
import com.example.zubuttest.sys.di.modules.ContextModule;
import com.example.zubuttest.ui.adreesDetail.AdressDetailDialogFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AdressFragment extends Fragment implements OnAdressSelected{

    private List<AdressEntity> mList = new ArrayList<>();
    private MenuItem menuDelete;

    @Inject
    AdressFragmentViewModel viewModel;

    private FragmentAdressBinding dataBinding;
    private AdressAdapter adapter;

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

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (dataBinding = FragmentAdressBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new AdressAdapter(this);
        dataBinding.adressRecycler.setLayoutManager( new LinearLayoutManager(getContext()));
        dataBinding.adressRecycler.setAdapter(adapter);

        viewModel.getAllAdress().observe(getViewLifecycleOwner(), list->{
            if(list.size()>0){
                adapter.addItems(list);
                mList = list;
            }else{
                dataBinding.adressRecycler.setVisibility(View.GONE);
                dataBinding.lyNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

            AdressActivity activity = (AdressActivity) getActivity();
            activity.setToolbar(dataBinding.toolbar, getString(R.string.mis_direcciones));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_delete, menu);
        this.menuDelete = menu.findItem(R.id.menu_delete);
        this.menuDelete.setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (getActivity() != null && mList.size() > 0) {
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    List<AdressEntity> list = new ArrayList<>();
                    adapter.addItems(list);
                    viewModel.deleteAdress();
                    break;
            }
        }
        return false;
    }

    @Override
    public void onClickAdress(String nombre, String direccion, String coordenadas){
        AdressDetailDialogFragment.newInstance(nombre, direccion, coordenadas).show(getParentFragmentManager(), "DialogFragment");
    }

}
