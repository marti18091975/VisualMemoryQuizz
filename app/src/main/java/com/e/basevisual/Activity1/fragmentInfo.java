package com.e.basevisual.Activity1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.basevisual.R;
import com.e.basevisual.utils.Navigator;
import com.e.basevisual.utils.clickButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class fragmentInfo extends Fragment {
    @Inject
    protected Navigator navigator5;
    @BindView(R.id.floatingActionButton3)
    FloatingActionButton tornar;
    Activity activity;
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tornar.setOnClickListener(view1 -> {
            clickButton.click(getContext());
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().remove(fragmentInfo.this).commit();
            try {
                ((OnCommit) activity).mostrarBoto();
            } catch (ClassCastException cce) {

            }
        });
        }
    public interface OnCommit{
        public void mostrarBoto();
    }




}
