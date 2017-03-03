package com.klinger2709.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreUpgradesFragment extends Fragment {

    View view;
    double[] upgradeJewelry = {1, 1, 0.63, 0.45, 0.33, 0.26, 0.21, 0.17, 0.14, 0.11, 0.09, 0.08, 0.06, 0.05, 0.04, 0.03, 0.02, 0.01, 0.007, 0.001};
    double[] piercingSuit = {0.8, 0.5, 0.2, 0.05};
    double[] upgradeWeapon = {1, 1, 0.7, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05, 0.02};
    double[] piercingWeapon = {0.5, 0.25, 0.125, 0.0625, 0.0313, 0.0156, 0.0078, 0.0039, 0.002, 0.001};
    double[] elementWeapon = {1, 1, 0.7, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05, 0.02, 0.019, 0.018, 0.017, 0.016, 0.015, 0.014, 0.013, 0.012, 0.011, 0.01};
    double[] upgradeUltimate = {0.11, 0.09, 0.07, 0.05, 0.03, 0.009, 0.007, 0.005, 0.003, 0.001};
    double[][] chances = {upgradeJewelry, piercingSuit, upgradeWeapon, piercingWeapon, elementWeapon, upgradeUltimate};
    int currentSelected = 0;

    TextView result;
    EditText aktuelleStufeET;
    EditText wunschStufeET;
    EditText wahrscheinlichkeitET;

    public MoreUpgradesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_more_upgrades, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.calc_scrolls);

        Spinner itemSelector = (Spinner) view.findViewById(R.id.item_selector);
        final TextView chanceList = (TextView) view.findViewById(R.id.chance_list);
        aktuelleStufeET = (EditText) view.findViewById(R.id.aktuelle_stufe);
        wunschStufeET = (EditText) view.findViewById(R.id.wunschstufe);
        wahrscheinlichkeitET = (EditText) view.findViewById(R.id.wahrscheinlichkeit);
        result = (TextView) view.findViewById(R.id.ergebnis);
        result.setText("");

        ((Button) view.findViewById(R.id.calculate_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.upgrading_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSelector.setAdapter(adapter);
        itemSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelected = position;
                String chancesText = "";
                int i = 0;
                for (double d : chances[position]) {
                    chancesText += "+" + ++i + " = " + Math.round(d * 1000) / 10.0 + "%\n";
                }
                chanceList.setText(chancesText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    public boolean checkValues() {
        if(aktuelleStufeET.getText().toString().equals("") || wunschStufeET.getText().toString().equals("") || wahrscheinlichkeitET.getText().toString().equals("")) {
            Toast.makeText(getActivity(), R.string.pls_all_fields, Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(Integer.parseInt(aktuelleStufeET.getText().toString()) > (Integer.parseInt(wunschStufeET.getText().toString()))) {
            Toast.makeText(getActivity(), R.string.not_possible, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(Integer.parseInt(aktuelleStufeET.getText().toString()) > chances[currentSelected].length || Integer.parseInt(aktuelleStufeET.getText().toString()) < 0) {
            Toast.makeText(getActivity(), R.string.no_valid_value, Toast.LENGTH_SHORT).show();
            return false;
        }
         if(Integer.parseInt(wunschStufeET.getText().toString()) > chances[currentSelected].length ||Integer.parseInt(wunschStufeET.getText().toString()) < 1) {
             Toast.makeText(getActivity(), R.string.no_valid_value, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void calculate() {
        if (checkValues()) {
            int aktuelleStufe = aktuelleStufeET.getText().toString().equals("") ? 0 : Integer.parseInt(aktuelleStufeET.getText().toString());
            int wunschStufe = wunschStufeET.getText().toString().equals("") ? 0 : Integer.parseInt(wunschStufeET.getText().toString());
            int wahrscheinlichkeit = wahrscheinlichkeitET.getText().toString().equals("") ? 0 : Integer.parseInt(wahrscheinlichkeitET.getText().toString());


            int gesamtZahlScrolls = 0;
            for (int i = aktuelleStufe; i < wunschStufe; i++) {
                double toLogA = wahrscheinlichkeit / -100f;
                double logA = Math.log10(toLogA + 1); //-0.6989700433
                double logB = Math.log10(1 - chances[currentSelected][i]); // -0.01322826573
                double res = round(logA) / round(logB);
                long scrolls = Math.round(res);
                gesamtZahlScrolls += scrolls;
            }

            TextView ergebnisTV = (TextView) view.findViewById(R.id.ergebnis);
            ergebnisTV.setText(getString(R.string.u_will_need) + gesamtZahlScrolls + getString(R.string.need_scrolls));
        }
    }

    public double round(double doubleToRound) {
        return Math.round(doubleToRound * 100000) / 100000.0;
    }
}
