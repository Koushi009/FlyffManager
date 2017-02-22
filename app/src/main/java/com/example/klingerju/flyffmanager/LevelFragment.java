package com.example.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.klingerju.flyffmanager.Classes.Monster;
import com.example.klingerju.flyffmanager.DataLists.ExpList;
import com.example.klingerju.flyffmanager.DataLists.Monsterlist;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelFragment extends Fragment {

    ExpList expList;
    Monsterlist monsterlist;
    Monster currentMonster;
    View view;
    int currentAreaSelected = 0;
    int currentMonsterSelected = 0;
    List<String> monsterNames = new ArrayList<>();
    ArrayAdapter<String> monsterAdapter;
    public LevelFragment() {
        monsterlist = Monsterlist.getMonsterlist();
        expList = ExpList.getExpList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_level, container, false);

        ((Button) view.findViewById(R.id.calculate_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        Spinner itemSelector = (Spinner) view.findViewById(R.id.area_chooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSelector.setAdapter(adapter);
        itemSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentAreaSelected = position;
                loadMonsterNames(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        loadMonsterNames(currentAreaSelected);
        currentMonster = monsterlist.areas.get(currentAreaSelected).get(currentMonsterSelected);
        Spinner monsterSelector = (Spinner) view.findViewById(R.id.monster_chooser);
         monsterAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, monsterNames);
        monsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monsterSelector.setAdapter(monsterAdapter);
        monsterSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMonsterSelected = position;
                currentMonster = monsterlist.areas.get(currentAreaSelected).get(currentMonsterSelected);
                ((TextView) LevelFragment.this.view.findViewById(R.id.monster_lvl)).setText("Lvl: " + currentMonster.getLvl());
                ((TextView) LevelFragment.this.view.findViewById(R.id.monster_hp)).setText("HP: " + currentMonster.getHp());
                ((TextView) LevelFragment.this.view.findViewById(R.id.monster_element)).setText(currentMonster.getElement().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        return view;
    }


    public void loadMonsterNames(int pos) {
        monsterNames.removeAll(monsterNames);
        for(Monster m : monsterlist.areas.get(pos)) {
            monsterNames.add(m.toString());
        }

        if(monsterAdapter != null)
        monsterAdapter.notifyDataSetChanged();
    }


    public void calculate() {
        TextView expToNextLevel = (TextView) view.findViewById(R.id.exp_to_next_level);
        TextView mobsToBeKilled = (TextView) view.findViewById(R.id.mobs_to_be_killed);

        boolean isMaster = ((CheckBox) view.findViewById(R.id.hero_cbx)).isChecked();
        boolean lvl1Leech = ((CheckBox) view.findViewById(R.id.lvl1_leech)).isChecked();
        double bonusViaAmps = Double.parseDouble(((TextView) view.findViewById(R.id.bonus_through_amps)).getText().toString());
        bonusViaAmps = bonusViaAmps == 0 ? 1 : bonusViaAmps;
        double bonusViaEvent = Double.parseDouble(((TextView) view.findViewById(R.id.bonus_through_event)).getText().toString());
        bonusViaEvent = bonusViaEvent == 0 ? 1 : bonusViaEvent;

        int currentLvl = Integer.parseInt(((TextView) view.findViewById(R.id.current_lvl)).getText().toString());
        long expForLvl = expList.levels.get(currentLvl).getExpNeeded();


        if(currentLvl >= 60 && currentLvl < 130) {
            expForLvl = isMaster ? expForLvl * 2 : expForLvl;
        }

        double monsterExp = currentMonster.getExp() * bonusViaAmps * bonusViaEvent;
        long mobsToBeKilledNr =Math.round(expForLvl / monsterExp);
        mobsToBeKilledNr = lvl1Leech ? mobsToBeKilledNr / 2 : mobsToBeKilledNr;

        expToNextLevel.setText("EXP bis Lvl " + (currentLvl +1) + ": " +expForLvl + "\nExp/Mob: " + currentMonster.getExp());
        mobsToBeKilled.setText("Anzahl Mobs zu töten: " + mobsToBeKilledNr);
    }
}
