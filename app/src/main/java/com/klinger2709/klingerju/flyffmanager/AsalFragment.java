package com.klinger2709.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AsalFragment extends Fragment {

    View view;
    TextView question;
    LinearLayout optionList;

    public final String STR_VALUE = "STR_VALUE";
    public final String MP_VALUE = "MP_VALUE";
    public final String ATK_VALUE = "ATK_VALUE";


    int str;
    int mp;
    int atk;


    public AsalFragment() {
        // Required empty public constructor
    }

    public Button createButton(String text, Object tag, View.OnClickListener listener) {
        Button btn = new Button(getActivity());
        btn.setText(text);
        btn.setTag(tag);
        btn.setOnClickListener(listener);
        btn.setTransformationMethod(null);
        btn.setBackgroundResource(R.drawable.button);
        return btn;

    }

    public TextInputLayout createInputText(String hint,Object tag, int inputType) {
        TextInputLayout textInputLayout = new TextInputLayout(getActivity());
        EditText strET = new EditText(getActivity());
        strET.setInputType(inputType);
        strET.setHint(hint);
        strET.setTag(tag);
        textInputLayout.addView(strET);
        return textInputLayout;
    }

    public TextView createTextView(String text) {
        TextView tv = new TextView(getActivity());
        tv.setText(text);
        return  tv;
    }

    public void prepareView(String q) {
        question.setText(q);
        optionList.removeAllViews();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_asal, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.schadensberechnung_skills));

        optionList = (LinearLayout) view.findViewById(R.id.content);
        question = (TextView) view.findViewById(R.id.question);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((int) v.getTag() == 1) {
                    calculateAsal();
                } else {
                    calculateHop();
                }
            }
        };

        optionList.addView(createButton(getString(R.string.asal), 1, onClickListener));
        optionList.addView(createButton(getString(R.string.hop), 2, onClickListener));

        return view;
    }


    public void calculateAsal() {
        prepareView(getString(R.string.pls_fill_in_values));



        optionList.addView(createInputText(getString(R.string.str), STR_VALUE, InputType.TYPE_CLASS_NUMBER));
        optionList.addView(createInputText(getString(R.string.mp),MP_VALUE,  InputType.TYPE_CLASS_NUMBER));
        optionList.addView(createInputText(getString(R.string.atk),ATK_VALUE,  InputType.TYPE_CLASS_NUMBER));

        optionList.addView(createButton(getString(R.string.berechnen), 1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText strET = (EditText) optionList.findViewWithTag(STR_VALUE);
                EditText mpET = (EditText) optionList.findViewWithTag(MP_VALUE);
                EditText atkET = (EditText) optionList.findViewWithTag(ATK_VALUE);

                if(strET.getText().toString().equals("") || mpET.getText().toString().equals("") || atkET.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), R.string.min_1_value_missing, Toast.LENGTH_SHORT).show();
                } else {
                    str = Integer.parseInt(strET.getText().toString());
                    mp = Integer.parseInt(mpET.getText().toString());
                    atk = Integer.parseInt(atkET.getText().toString());

                    double asalDmgPvE = ((str*mp/10)  + atk + 3000);
                    double asalDmgPvP = asalDmgPvE * 0.6;

                    question.setText(getString(R.string.asalpve) + Math.round(asalDmgPvE) + getString(R.string.asalpvp) + Math.round(asalDmgPvP));

                }
            }
        }));

    }
    public void calculateHop() {
        prepareView(getString(R.string.pls_fill_in_values));
        optionList.addView(createInputText(getString(R.string.str), STR_VALUE, InputType.TYPE_CLASS_NUMBER));
        optionList.addView(createInputText(getString(R.string.atk), ATK_VALUE,  InputType.TYPE_CLASS_NUMBER));

        optionList.addView(createButton(getString(R.string.berechnen), 1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText strET = (EditText) optionList.findViewWithTag(STR_VALUE);
                EditText atkET = (EditText) optionList.findViewWithTag(ATK_VALUE);

                if(strET.getText().toString().equals("") || atkET.getText().toString().equals("")) {
                    Toast.makeText(getActivity(),  R.string.min_1_value_missing, Toast.LENGTH_SHORT).show();
                } else {
                    str = Integer.parseInt(strET.getText().toString());
                    atk = Integer.parseInt(atkET.getText().toString());

                    double hopPvP = (str*20)+(atk*2.13)-600;
                    double hopPvE = hopPvP/0.6;

                    question.setText(getString(R.string.hoppve) + Math.round(hopPvE) + getString(R.string.hoppvm) + Math.round(hopPvP));

                }
            }
        }));


    }
}
