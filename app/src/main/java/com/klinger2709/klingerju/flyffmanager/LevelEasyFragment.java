package com.klinger2709.klingerju.flyffmanager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.gem_tlich_leveln));

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
        prepareView(getString(R.string.whichmonster));
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
        prepareView(getString(R.string.whichlvl));
        final EditText currentLvl = new EditText(getActivity());
        final CheckBox herocbx = new CheckBox(getActivity());
        herocbx.setText(R.string.im_hero);
        currentLvl.setInputType(InputType.TYPE_CLASS_NUMBER);
        optionList.addView(currentLvl);
        optionList.addView(herocbx);

        optionList.addView(createButton(getString(R.string.next), 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentLvl.getText().toString().equals("")) {
                    lvl = Integer.parseInt(currentLvl.getText().toString());
                    if(lvl > 154) {
                        Toast.makeText(getActivity(), R.string.too_high, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), R.string.pls_fill_in_lvl, Toast.LENGTH_SHORT).show();
                }

            }
        }));
    }

    public void askLeech() {
        prepareView(getString(R.string.have_leech));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromLeech = (double) v.getTag();
                askExpEvent();
            }
        };
        optionList.addView(createButton(getString(R.string.yes), 1.8, onClickListener));
        optionList.addView(createButton(getString(R.string.no_leech), 1.0, onClickListener));
        optionList.addView(createButton(getString(R.string.lvl_1_leech), 2.0, onClickListener));
        optionList.addView(createButton(getString(R.string.getting_leveled), 0.4, onClickListener));
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
        prepareView(getString(R.string.is_exp_event));
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

        optionList.addView(createButton(getString(R.string.no_exp_event), 1.0, onClickListener));
        optionList.addView(createButton(getString(R.string.exp_times_2), 2.0, onClickListener));
        optionList.addView(createButton(getString(R.string.exp_times_3), 3.0, onClickListener));
        optionList.addView(createInputText(getString(R.string.exp_in_), "EXP_EVENT_TV", InputType.TYPE_CLASS_NUMBER, new View.OnKeyListener() {
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
        prepareView(getString(R.string.have_exp_scrolls));


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

        optionList.addView(createButton(getString(R.string.no_scrolls),1.0,onClickListener));
        optionList.addView(createButton(getString(R.string.scroll_50),1.5,onClickListener));
        optionList.addView(createButton(getString(R.string.scroll_250),2.5,onClickListener));
        optionList.addView(createButton(getString(R.string.scroll_500),5.0,onClickListener));
        optionList.addView(createButton(getString(R.string.scroll_1000),10.0,onClickListener));
        optionList.addView(createInputText(getString(R.string.exp_in_), "AMP_TV", InputType.TYPE_CLASS_NUMBER, new View.OnKeyListener() {
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
        prepareView(getString(R.string.level_art));
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
        optionList.addView(createButton(getString(R.string.one_vs_one),1,onClickListener));
        optionList.addView(createButton(getString(R.string.aoe),2,onClickListener));
    }

    public void setup1o1() {
        prepareView(getString(R.string.leveling_1o1_kill5));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == 1) {
                    question.setText(R.string.kill_5_monster_stop);
                    v.setTag(2);
                    ((Button) v).setText(R.string.stop_timer);
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

        optionList.addView(createButton(getString(R.string.start_timer), 1, onClickListener));
        optionList.addView(timer);

    }

    public void setupAoE() {
        prepareView(getString(R.string.leveling_aoe));


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) v.getTag() == 1) {
                    question.setText(R.string.stop_and_count);
                    v.setTag(2);
                    ((Button) v).setText(R.string.stop_timer);
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

        optionList.addView(createButton(getString(R.string.start_timer), 1, onClickListener));
        optionList.addView(timer);
    }

    public void AskHowManyMonsters() {
        prepareView(getString(R.string.how_many_mobs));

        final EditText other = new EditText(getActivity());
        other.setInputType(InputType.TYPE_CLASS_NUMBER);
        other.setHint(R.string.anzahl_monster);
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
        optionList.addView(createButton(getString(R.string.finish), 1, onClickListener));

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
