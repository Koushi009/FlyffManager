package com.klinger2709.klingerju.flyffmanager;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WCoinsFragment extends Fragment {

    View view;
    LinearLayout optionList;
    TextView question;

    EditText kursET;
    EditText perinET;
    EditText wcoinET;

    public WCoinsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wcoins, container, false);


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.wc_calc);

        optionList = (LinearLayout) view.findViewById(R.id.content);
        question = (TextView) view.findViewById(R.id.question);

        wcoinET = (EditText) view.findViewById(R.id.wcoins);
        perinET = (EditText) view.findViewById(R.id.perin);
        kursET = (EditText) view.findViewById(R.id.kurs);
        ((Button) view.findViewById(R.id.calculate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(v);
            }
        });

        return view;
    }

    public void calculate(View v) {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
        final String kursString = kursET.getText().toString();
        final String perinString = perinET.getText().toString();
        final String wcoinString = wcoinET.getText().toString();

        boolean kursEmpty = kursString.equals("");
        boolean perinEmpty = perinString.equals("");
        boolean wcoinEmpty = wcoinString.equals("");

        if (kursEmpty) {
            if (wcoinEmpty || perinEmpty) {
                Toast.makeText(getActivity(), R.string.pls_fill_in_one_more, Toast.LENGTH_SHORT).show();
            } else {
                calcKurs(perinString, wcoinString);
            }
        } else if (wcoinEmpty) {
            if (perinEmpty) {
                Toast.makeText(getActivity(), R.string.pls_fill_in_one_more, Toast.LENGTH_SHORT).show();
            } else {
                calcWCoin(kursString, perinString);
            }
        } else if (perinEmpty) {
            calcPerin(kursString, wcoinString);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.what_to_calc);
            String[] options = {getString(R.string.kurs), getString(R.string.wcs), getString(R.string.peny)};
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:  calcKurs(perinString, wcoinString); break;
                        case 1:  calcWCoin(kursString, perinString); break;
                        case 2:  calcPerin(kursString, wcoinString); break;
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void calcKurs(String perinString, String wcoinString) {
        double perin = Double.parseDouble(perinString);
        double wcoin = Double.parseDouble(wcoinString);

        double kurs = perin / wcoin * 100;
        kursET.setText(String.valueOf(Math.round(kurs)));
    }

    public void calcWCoin(String kursString, String perinString) {
        double kurs = Double.parseDouble(kursString);
        double perin = Double.parseDouble(perinString);

        double wcoin = perin/kurs*100;

        wcoinET.setText(String.valueOf(Math.round(wcoin)));
    }

    public void calcPerin(String kursString, String wcoinString) {
        double kurs = Double.parseDouble(kursString);
        double wcoin = Double.parseDouble(wcoinString);

        double perin = wcoin * kurs/100;

        perinET.setText(String.valueOf(Math.round(perin)));
    }

}
