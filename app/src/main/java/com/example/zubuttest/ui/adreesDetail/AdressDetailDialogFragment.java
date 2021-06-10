package com.example.zubuttest.ui.adreesDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zubuttest.data.entities.AdressEntity;
import com.example.zubuttest.databinding.DialogFragmentDetailBinding;


public class AdressDetailDialogFragment extends DialogFragment {


    private String nombre, direccion, coordenadas;
    private DialogFragmentDetailBinding binding;

    public static AdressDetailDialogFragment newInstance(String nombre, String direccion, String coordenadas) {

        Bundle args = new Bundle();

        args.putString("nombre", nombre);
        args.putString("direccion", direccion);
        args.putString("coordenadas", coordenadas);
        //args.putSerializable("objeto", (Serializable) activeAgreementsRDS);

        AdressDetailDialogFragment fragment = new AdressDetailDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.nombre = getArguments().getString("nombre");
            this.direccion = getArguments().getString("direccion");
            this.coordenadas = getArguments().getString("coordenadas");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = DialogFragmentDetailBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdressEntity adressEntity = new AdressEntity(nombre, direccion, coordenadas);
        binding.setModel(adressEntity);
        binding.btnAceptDetail.setOnClickListener(v -> dismiss());
    }
}
