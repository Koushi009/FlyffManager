package com.klinger2709.klingerju.flyffmanager;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WCoinsFragment extends Fragment {

    View view;
    LinearLayout optionList;
    TextView question;

    public WCoinsFragment() {
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

    public TextInputLayout createInputText(String hint, Object tag, int inputType) {
        TextInputLayout textInputLayout = new TextInputLayout(getActivity());
        EditText strET = new EditText(getActivity());
        strET.setInputType(inputType);
        strET.setHint(hint);
        strET.setTag(tag);
        textInputLayout.addView(strET);
        return textInputLayout;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wcoins, container, false);

        optionList = (LinearLayout) view.findViewById(R.id.content);
        question = (TextView) view.findViewById(R.id.question);



        return view;
    }

}
