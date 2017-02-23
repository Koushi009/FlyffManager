package com.example.klingerju.flyffmanager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
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

import com.example.klingerju.flyffmanager.Classes.Monster;
import com.example.klingerju.flyffmanager.DataLists.Monsterlist;

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

    public void askArea() {
        String[] areas = getResources().getStringArray(R.array.areas);
        int i = 0;
        for (String areaName : areas) {
            Button btn = new Button(getActivity());
            btn.setText(areaName);
            btn.setTag(i++);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("AREA", ((Button) v).getText().toString() + " " + v.getTag());
                    areaSelected = (int) v.getTag();
                    askMobGroup();
                }
            });
            optionList.addView(btn);
        }
    }

    public void askMobGroup() {
        question.setText("Welches Monster tötest du gerade?");
        optionList.removeAllViews();
        final ArrayList<Monster> monsters = Monsterlist.getMonsterlist().areas.get(areaSelected);
        int i = 0;
        for (Monster m : monsters) {
            Button btn = new Button(getActivity());
            btn.setText(m.getName() + " (" + m.getLvl() +")");
            btn.setTag(i++);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("Monster", ((Button) v).getText().toString() + " " + v.getTag());
                    monsterSelected = (int) v.getTag();
                    askLevel();
                    currentMonster = Monsterlist.getMonsterlist().areas.get(areaSelected).get(monsterSelected);
                }
            });
            optionList.addView(btn);
        }
    }

    public void askLevel() {
        question.setText("Welches Level bist du momentan?");
        optionList.removeAllViews();
        final EditText currentLvl = new EditText(getActivity());
        Button confirmbutton = new Button(getActivity());
        confirmbutton.setText("Weiter");
        final CheckBox herocbx = new CheckBox(getActivity());
        herocbx.setText("Ich bin sogar schon Master/Hero!");
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentLvl.getText().toString().equals("")) {
                    lvl = Integer.parseInt(currentLvl.getText().toString());
                    isHero = herocbx.isChecked();
                    try  {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askLeech();
                } else {
                    Toast.makeText(getActivity(), "Bitte trage ein Level ein.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        currentLvl.setInputType(InputType.TYPE_CLASS_NUMBER);
        optionList.addView(currentLvl);
        optionList.addView(herocbx);
        optionList.addView(confirmbutton);
    }

    public void askLeech() {
        question.setText("Hast du einen Leecher?");
        optionList.removeAllViews();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromLeech = (double) v.getTag();
                askExpEvent();
            }
        };

        Button yes = new Button(getActivity());
        yes.setText("Ja hab ich!");
        yes.setTag(1.8);
        yes.setOnClickListener(onClickListener);
        Button no = new Button(getActivity());
        no.setText("Nein ich war zu faul mir einen zu suchen.");
        no.setTag(1.0);
        no.setOnClickListener(onClickListener);
        Button lvl1 = new Button(getActivity());
        lvl1.setText("Der ist sogar Level 1!");
        lvl1.setTag(2.0);
        lvl1.setOnClickListener(onClickListener);
        Button imleech = new Button(getActivity());
        imleech.setText("Ich werd grad gelevelt");
        imleech.setTag(0.4);
        imleech.setOnClickListener(onClickListener);

        optionList.addView(yes);
        optionList.addView(no);
        optionList.addView(lvl1);
        optionList.addView(imleech);
    }


    public void askExpEvent() {
        question.setText("Läuft gerade ein EXP-Event?");
        optionList.removeAllViews();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromEvent = (double) v.getTag();
                try  {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                askScrolls();
            }
        };

        Button not = new Button(getActivity());
        not.setText("Leider nicht...");
        not.setTag(1.0);
        not.setOnClickListener(onClickListener);
        Button times2 = new Button(getActivity());
        times2.setText("2-Fach EXP.");
        times2.setTag(2.0);
        times2.setOnClickListener(onClickListener);
        Button times3 = new Button(getActivity());
        times3.setText("3-Fach EXP!");
        times3.setTag(3.0);
        times3.setOnClickListener(onClickListener);

        final EditText other = new EditText(getActivity());
        other.setInputType(InputType.TYPE_CLASS_NUMBER);
        other.setHint("Ansonsten Bitte in % angeben.");
        other.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    expFromEvent = Double.parseDouble(other.getText().toString()) / 100;
                    try  {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askScrolls();
                }
                return false;
            }
        });

        optionList.addView(not);
        optionList.addView(times2);
        optionList.addView(times3);
        optionList.addView(other);
    }

    public void askScrolls() {
        question.setText("Hast du gerade EXP-Scrolls aktiv?");
        optionList.removeAllViews();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expFromAmp = (double) v.getTag();
                try  {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                askLevelMethod();
            }
        };

        Button not = new Button(getActivity());
        not.setText("Nee...");
        not.setTag(1.0);
        not.setOnClickListener(onClickListener);
        Button times2 = new Button(getActivity());
        times2.setText("Die eine grüne (50%).");
        times2.setTag(1.5);
        times2.setOnClickListener(onClickListener);
        Button times3 = new Button(getActivity());
        times3.setText("5 stapelbare ES(S) (250%)");
        times3.setTag(2.5);
        times3.setOnClickListener(onClickListener);
        Button xrAmps = new Button(getActivity());
        xrAmps.setText("5 XR aus dem Cashshop (Lila) (500%)");
        xrAmps.setTag(5.0);
        xrAmps.setOnClickListener(onClickListener);
        Button qAmps = new Button(getActivity());
        qAmps.setText("5 Scrolls of Experience Ultra (1000%)");
        qAmps.setTag(10.0);
        qAmps.setOnClickListener(onClickListener);

        final EditText other = new EditText(getActivity());
        other.setInputType(InputType.TYPE_CLASS_NUMBER);
        other.setHint("Ansonsten Bitte in % angeben.");
        other.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    expFromAmp = Double.parseDouble(other.getText().toString()) / 100;
                    try  {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {

                    }
                    askLevelMethod();
                }
                return false;
            }
        });

        optionList.addView(not);
        optionList.addView(times2);
        optionList.addView(times3);
        optionList.addView(xrAmps);
        optionList.addView(qAmps);
        optionList.addView(other);
    }

    public void askLevelMethod() {
        question.setText("Auf welche Art levelst du?");
        optionList.removeAllViews();

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

        Button not = new Button(getActivity());
        not.setText("1 vs 1");
        not.setTag(1);
        not.setOnClickListener(onClickListener);
        Button times2 = new Button(getActivity());
        times2.setText("AoE - Mehrere Monster auf einmal");
        times2.setTag(2);
        times2.setOnClickListener(onClickListener);

        optionList.addView(not);
        optionList.addView(times2);
    }

    public void setup1o1() {
        question.setText("Du levelst 1o1. Bitte starte den Timer und töte dann 5 Monster.");
        optionList.removeAllViews();

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

        Button startTimer = new Button(getActivity());
        startTimer.setText("Timer starten");
        startTimer.setTag(1);
        startTimer.setOnClickListener(onClickListener);

        timer = new TextView(getActivity());

        optionList.addView(startTimer);
        optionList.addView(timer);

    }

    public void setupAoE() {
        question.setText("Du levelst AoE. Bitte zähle die Monster und stoppe einen AoE lang die Zeit.");
        optionList.removeAllViews();

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

        Button startTimer = new Button(getActivity());
        startTimer.setText("Timer starten");
        startTimer.setTag(1);
        startTimer.setOnClickListener(onClickListener);

        timer = new TextView(getActivity());

        optionList.addView(startTimer);
        optionList.addView(timer);
    }

    public void AskHowManyMonsters() {
        question.setText("Wie viele Monster hast du gerade getötet?");
        optionList.removeAllViews();

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
                try  {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
            }
        };
        Button finish = new Button(getActivity());
        finish.setText("Fertig");
        finish.setTag(1);
        finish.setOnClickListener(onClickListener);

        optionList.addView(other);
        optionList.addView(finish);

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
