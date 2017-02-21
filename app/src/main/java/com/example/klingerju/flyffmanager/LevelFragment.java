package com.example.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.klingerju.flyffmanager.Classes.Monster;
import com.example.klingerju.flyffmanager.DataLists.Monsterlist;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFragment extends Fragment {

    Monsterlist monsterlist;
    View view;
    int currentAreaSelected = 0;
    int currentMonsterSelected = 0;

    public LevelFragment() {
        monsterlist = Monsterlist.getMonsterlist();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_level, container, false);

        Spinner itemSelector = (Spinner) view.findViewById(R.id.area_chooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSelector.setAdapter(adapter);
        itemSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentAreaSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });


        return view;
    }

}
