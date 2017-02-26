package com.klinger2709.klingerju.flyffmanager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.klinger2709.klingerju.flyffmanager.Classes.Monster;
import com.klinger2709.klingerju.flyffmanager.DataLists.Monsterlist;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LevelEasyFragment extends Fragment {

    View view;
    LayoutInflater inflater;
    LinearLayout optionList;
    TextView question;
    TextView timer;

    int areaSelected;
    int monsterSelected;
    Monster currentMonster;
    int lvl;
    boolean isHero;
    double expFromLeech;
    double expFromEvent;
    double expFromAmp;
    int monstersKilled;

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

        askArea();

        return view;
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

    public void prepareView(String q) {
        question.setText(q);
        optionList.removeAllViews();
    }

    public void askArea() {
        String[] areas = getResources().getStringArray(R.array.areas);
        int i = 0;
        for (String areaName : areas) {
            optionList.addView(createButton(areaName, i++, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("AREA", ((Button) v).getText().toString() + " " + v.getTag());
                    areaSelected = (int) v.getTag();
                    askMobGroup();
                }
            }));
        }
    }

    public void askMobGroup() {
        prepareView("Welches Monster tötest du gerade?");
        final ArrayList<Monster> monsters = Monsterlist.getMonsterlist().areas.get(areaSelected);
        int i = 0;
        for (Monster m : monsters) {
            optionList.addView(createButton(m.getName() + " (" + m.getLvl() + ")", i++, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("Monster", ((Button) v).getText().toString() + " " + v.getTag());
                    monsterSelected = (int) v.getTag();
                    askLevel();
                    currentMonster = Monsterlist.getMonsterlist().areas.get(areaSelected).get(monsterSelected);
                }
            }));
        }
    }

    public void askLevel() {
        prepareView("Welches Level bist du momentan?");
        final EditText currentLvl = new EditText(getActivity());
        final CheckBox herocbx = new CheckBox(getActivity());
        herocbx.setText("Ich bin sogar schon Master/Hero!");
        currentLvl.setInputType(InputType.TYPE_CLASS_NUMBER);
        optionList.addView(currentLvl);
        optionList.addView(herocbx);

        optionList.addView(createButton("Weiter", 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentLvl.getText().toString().equals("")) {
                    lvl = Integer.parseInt(currentLvl.getText().toString());
                    if(lvl > 154) {
                        Toast.makeText(getActivity(), "Das ist zu hoch!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    isHero = herocbx.isChecked();
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askLeech();
                } else {
                    Toast.makeText(getActivity(), "Bitte trage ein Level ein.", Toast.LENGTH_SHORT).show();
                }

            }
        }));
    }

    public void askLeech() {
        prepareView("Hast du einen Leecher?");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromLeech = (double) v.getTag();
                askExpEvent();
            }
        };
        optionList.addView(createButton("Ja hab ich!", 1.8, onClickListener));
        optionList.addView(createButton("Nein ich war zu faul mir einen zu suchen.", 1.0, onClickListener));
        optionList.addView(createButton("Der ist sogar Level 1!", 2.0, onClickListener));
        optionList.addView(createButton("Ich werd grad gelevelt", 0.4, onClickListener));
    }

    public TextInputLayout createInputText(String hint, Object tag, int inputType, View.OnKeyListener onKeyListener) {
        TextInputLayout textInputLayout = new TextInputLayout(getActivity());
        EditText strET = new EditText(getActivity());
        strET.setInputType(inputType);
        strET.setHint(hint);
        strET.setTag(tag);
        strET.setOnKeyListener(onKeyListener);
        textInputLayout.addView(strET);
        return textInputLayout;
    }

    public void askExpEvent() {
        prepareView("Läuft gerade ein EXP-Event?");
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromEvent = (double) v.getTag();
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                askScrolls();
            }
        };

        optionList.addView(createButton("Leider nicht...", 1.0, onClickListener));
        optionList.addView(createButton("2-Fach EXP.", 2.0, onClickListener));
        optionList.addView(createButton("3-Fach EXP!", 3.0, onClickListener));
        optionList.addView(createInputText("Ansonsten bitte in % angeben.", "EXP_EVENT_TV", InputType.TYPE_CLASS_NUMBER, new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    expFromEvent = Double.parseDouble(((EditText) optionList.findViewWithTag("EXP_EVENT_TV")).getText().toString()) / 100;
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askScrolls();
                }
                return false;
            }
        }));
    }

    public void askScrolls() {
        prepareView("Hast du gerade EXP-Scrolls aktiv?");


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromAmp = (double) v.getTag();
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                askLevelMethod();
            }
        };

        optionList.addView(createButton("Nein...",1.0,onClickListener));
        optionList.addView(createButton("Die eine grüne (50%).",1.5,onClickListener));
        optionList.addView(createButton("5 stapelbare ES(S) (250%)",2.5,onClickListener));
        optionList.addView(createButton("5 XR aus dem Cashshop (Lila) (500%)",5.0,onClickListener));
        optionList.addView(createButton("5 Scrolls of Experience Ultra (1000%)",10.0,onClickListener));
        optionList.addView(createInputText("Ansonsten bitte in % angeben.", "AMP_TV", InputType.TYPE_CLASS_NUMBER, new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    expFromAmp = Double.parseDouble(((EditText) optionList.findViewWithTag("AMP_TV")).getText().toString()) / 100;
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askLevelMethod();
                }
                return false;
            }
        }));
    }

    public void askLevelMethod() {
        prepareView("Auf welche Art levelst du?");
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == 1) {
                    setup1o1();
                } else {
                    setupAoE();
                }
            }
        };
        optionList.addView(createButton("1 vs 1",1,onClickListener));
        optionList.addView(createButton("AoE - Mehrere Monster auf einmal",2,onClickListener));
    }

    public void setup1o1() {
        prepareView("Du levelst 1o1. Bitte starte den Timer und töte dann 5 Monster.");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == 1) {
                    question.setText("Töte 5 Monster und stoppe dann den Timer");
                    v.setTag(2);
                    ((Button) v).setText("Timer stop");
                    startTimer();
                } else if ((int) v.getTag() == 2) {
                    monstersKilled = 5;
                    stopTimer(1);

                }

            }
        };
        timer = new TextView(getActivity());
        timer.setGravity(Gravity.CENTER);
        timer.setTextSize(22);
        timer.setTextColor(getResources().getColor(R.color.my_dark_blue));

        optionList.addView(createButton("Timer starten", 1, onClickListener));
        optionList.addView(timer);

    }

    public void setupAoE() {
        prepareView("Du levelst AoE. Bitte zähle die Monster und stoppe einen AoE lang die Zeit.");


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == 1) {
                    question.setText("Drücke Stop sobald alle gesammelten Monster tot sind. Vergiss nicht mitzuzählen!");
                    v.setTag(2);
                    ((Button) v).setText("Timer stop");
                    startTimer();
                } else if ((int) v.getTag() == 2) {
                    stopTimer(2);
                }
            }
        };

        timer = new TextView(getActivity());
        timer.setGravity(Gravity.CENTER);
        timer.setTextSize(22);
        timer.setTextColor(getResources().getColor(R.color.my_dark_blue));

        optionList.addView(createButton("Timer starten", 1, onClickListener));
        optionList.addView(timer);
    }

    public void AskHowManyMonsters() {
        prepareView("Wie viele Monster hast du gerade getötet?");

        final EditText other = new EditText(getActivity());
        other.setInputType(InputType.TYPE_CLASS_NUMBER);
        other.setHint("Anzahl getöter Monster");
        other.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    monstersKilled = Integer.parseInt(other.getText().toString());
                    finishQuestions();
                }
                return false;
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monstersKilled = Integer.parseInt(other.getText().toString());
                finishQuestions();
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
            }
        };

        optionList.addView(other);
        optionList.addView(createButton("Fertig", 1, onClickListener));

    }


    public void finishQuestions() {
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        intent.putExtra("area", areaSelected);
        intent.putExtra("monster", monsterSelected);
        intent.putExtra("lvl", lvl);
        intent.putExtra("isHero", isHero);
        intent.putExtra("expBonusAmp", expFromAmp);
        intent.putExtra("expBonusEvent", expFromEvent);
        intent.putExtra("expBonusLeech", expFromLeech);
        intent.putExtra("milliseconds", millisecondTime);
        intent.putExtra("monstersKilled", monstersKilled);
        startActivity(intent);
    }

    long startTime;
    long millisecondTime;
    long updateTime;
    int seconds, minutes, milliseconds;
    Handler handler;

    public void startTimer() {
        handler = new Handler();
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public void stopTimer(int mode) {
        handler.removeCallbacks(runnable);

        if (mode == 1) // 1o1
            finishQuestions();
        else
            AskHowManyMonsters();
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            seconds = (int) (millisecondTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            milliseconds = (int) (millisecondTime % 1000);
            timer.setText(minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliseconds));
            handler.postDelayed(this, 0);
        }
    };

}
