package com.example.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.klingerju.flyffmanager.Classes.Monster;
import com.example.klingerju.flyffmanager.DataLists.Monsterlist;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelEasyFragment extends Fragment {

    View view;
    LayoutInflater inflater;
    LinearLayout optionList;
    TextView question;


    int areaSelected;
    int monsterSelected;

    public LevelEasyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_level_easy, container, false);

        question = (TextView) view.findViewById(R.id.question);
        optionList = (LinearLayout) view.findViewById(R.id.option_list);

       setupQuestion1();

        return view;
    }

    public void setupQuestion1() {
        String[] areas = getResources().getStringArray(R.array.areas);
        int i = 0;
        for(String areaName : areas) {
            Button btn = new Button(getActivity());
            btn.setText(areaName);
            btn.setTag(i++);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("AREA", ((Button) v).getText().toString() + " " + v.getTag());
                    areaSelected = (int) v.getTag();
                    setupQuestion2();
                }
            });
            optionList.addView(btn);
        }
    }

    public void setupQuestion2() {
        question.setText("Welches Monster tötest du gerade?");
        optionList.removeAllViews();
        final ArrayList<Monster> monsters = Monsterlist.getMonsterlist().areas.get(areaSelected);
        int i = 0;
        for(Monster m : monsters) {
            Button btn = new Button(getActivity());
            btn.setText(m.getName());
            btn.setTag(i++);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("Monster", ((Button) v).getText().toString() + " " + v.getTag());
                    monsterSelected = (int) v.getTag();
                    setupQuestion3();
                }
            });
            optionList.addView(btn);
        }
    }

    public void setupQuestion3() {
        question.setText("Welches Level bist du momentan?");
        optionList.removeAllViews();
        EditText currentLvl = new EditText(getActivity());
        optionList.addView(currentLvl);
    }

}
