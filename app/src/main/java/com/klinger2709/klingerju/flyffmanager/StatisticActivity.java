package com.klinger2709.klingerju.flyffmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.klinger2709.klingerju.flyffmanager.Classes.Monster;
import com.klinger2709.klingerju.flyffmanager.DataLists.ExpList;
import com.klinger2709.klingerju.flyffmanager.DataLists.Monsterlist;

public class StatisticActivity extends AppCompatActivity {

    Monster monster;
    int lvl;
    boolean isHero;
    double expBonusAmp;
    double expBonusEvent;
    double expBonusLeech;
    long expNeeded;
    long milliseconds;
    int monsterKilled;

    //Character Data
    TextView lvlTv;
    TextView ampTv;
    TextView eventTv;
    TextView expNeededTv;
    TextView leechTv;


    //Mob Data
    TextView mobNameTV;
    TextView mobLvlTV;
    TextView mobHpTV;
    TextView mobElementTV;
    TextView mobExpTV;


    //survey
    TextView mobsToKillTV;
    TextView estimatedTimeTV;
    TextView timePer5MobsTV;
    TextView expProMobTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        monster = Monsterlist.getMonsterlist().areas.get(intent.getIntExtra("area", 0)).get(intent.getIntExtra("monster", 0));
        lvl = intent.getIntExtra("lvl", 0);
        isHero = intent.getBooleanExtra("isHero", false);
        expBonusAmp = intent.getDoubleExtra("expBonusAmp", 1);
        expBonusEvent = intent.getDoubleExtra("expBonusEvent", 1);
        expBonusLeech = intent.getDoubleExtra("expBonusLeech", 1);
        milliseconds = intent.getLongExtra("milliseconds", 0L);
        monsterKilled = intent.getIntExtra("monstersKilled", 5);

        expNeeded = ExpList.getExpList().levels.get(lvl).getExpNeeded();

        lvlTv = (TextView) findViewById(R.id.Lvl);
        ampTv = (TextView) findViewById(R.id.expbonus_amps);
        eventTv = (TextView) findViewById(R.id.expbonus_event);
        expNeededTv = (TextView) findViewById(R.id.exp_needed);
        leechTv = (TextView) findViewById(R.id.leech);

        mobNameTV = (TextView) findViewById(R.id.mob_name);
        mobLvlTV = (TextView) findViewById(R.id.moblvl);
        mobHpTV = (TextView) findViewById(R.id.mobhp);
        mobElementTV = (TextView) findViewById(R.id.mobelement);
        mobExpTV = (TextView) findViewById(R.id.mobexp);

        mobsToKillTV = (TextView) findViewById(R.id.mobs_to_kill);
        estimatedTimeTV = (TextView) findViewById(R.id.estimatedtime);
        timePer5MobsTV = (TextView) findViewById(R.id.timeper5mobs);
        expProMobTV = (TextView) findViewById(R.id.exppromob);

        String isHeroStr = "";
        if(lvl > 120) {
            isHeroStr = "-H";
        } else if(isHero)  {
            if(lvl > 59 && lvl < 121) {
                isHeroStr = "-M";

            }
        }
        lvlTv.setText(getString(R.string.lvl) + lvl + isHeroStr);
        ampTv.setText(getString(R.string.exp_through_scrolls) + (expBonusAmp * 100) + "%");
        expNeededTv.setText(getString(R.string.exp_needed) + expNeeded);
        eventTv.setText(getString(R.string.exp_trhough_event) + (expBonusEvent * 100) + "%");
        leechTv.setText(getString(R.string.exp_through_leech) + (expBonusLeech * 100) + "%");

        mobNameTV.setText(monster.getName());
        mobLvlTV.setText(getString(R.string.mob_ll) + monster.getLvl());
        mobHpTV.setText(getString(R.string.hp) + monster.getHp());
        mobElementTV.setText(getString(R.string.element_) + monster.getElement().toString());
        mobExpTV.setText(getString(R.string.death) + monster.getExp() +" EXP");

        double monsterExp = monster.getExp() * expBonusAmp * expBonusEvent * expBonusLeech;
        long mobsToBeKilledNr =Math.round(expNeeded / monsterExp);
        mobsToKillTV.setText(getString(R.string.mobs_to_kill) + mobsToBeKilledNr);

        int seconds = (int) (milliseconds / 1000);
        int minutes =seconds / 60;
        seconds = seconds%60;
        int mseconds = (int) (milliseconds % 1000);
        timePer5MobsTV.setText(getString(R.string.time_for) +monsterKilled+  getString(R.string.mobs) + minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", mseconds));
        milliseconds = milliseconds/monsterKilled * mobsToBeKilledNr;

        int hours;
        seconds = (int) (milliseconds / 1000);
        minutes =seconds / 60;
        hours = minutes / 60;
        minutes = minutes % 60;
        seconds = seconds%60;
        mseconds = (int) (milliseconds % 1000);
        estimatedTimeTV.setText(getString(R.string.time_for_lvl_up) + hours + "h " + String.format("%02d",minutes) + "min " + String.format("%02d", seconds) + "sec");

        double expPercentage = monsterExp * 100 / expNeeded;
        expProMobTV.setText(getString(R.string.exp_per_kill_in_) + String.format("%.2f", expPercentage) + "%");
    }
}
