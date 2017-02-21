package com.example.klingerju.flyffmanager.DataLists;

import com.example.klingerju.flyffmanager.Classes.Element;
import com.example.klingerju.flyffmanager.Classes.Monster;

import java.util.ArrayList;

/**
 * Created by klingerju on 17.02.2017.
 */

public class Monsterlist {

    static Monsterlist monsterlist;
    ArrayList<Monster> azriaMonsters = new ArrayList<>();
    ArrayList<Monster> rartesiaMonsters = new ArrayList<>();
    ArrayList<Monster> darkRartesiaMonsters = new ArrayList<>();

    public ArrayList<ArrayList<Monster>> areas = new ArrayList<>();

    public Monsterlist() {
        azriaMonsters.add(new Monster(  "Yeti",                             Element.Earth,      73,     31285,  29081));
        azriaMonsters.add(new Monster(  "Mutant Yeti",                      Element.Water,      79,     36220,  54388));
        azriaMonsters.add(new Monster(  "Augu",                             Element.Water,      85,     42497,  101723));
        azriaMonsters.add(new Monster(  "Mutant Augu",                      Element.Fire,       91,     49286,  186847));
        azriaMonsters.add(new Monster(  "Ghost of the Forgotten Prince",    Element.Electric,   97,     56593,  322061));
        azriaMonsters.add(new Monster(  "Ghost of the Forgotten King",      Element.Wind,       103,    64420,  525394));
        azriaMonsters.add(new Monster(  "Mammoth",                          Element.Water,      109,    86854,  828756));
        azriaMonsters.add(new Monster(  "Cannibal Mammoth",                 Element.None,       115,    96403,  1083722));
        rartesiaMonsters.add(new Monster("Rabbit Guarder",                  Element.Electric,   116,    108089, 1787500));
        rartesiaMonsters.add(new Monster("Turtle Fighter",                  Element.Wind,       118,  	130787, 1966250));
        rartesiaMonsters.add(new Monster("Turtle Sworder",                  Element.Earth,      120,    130787, 2162875));
        rartesiaMonsters.add(new Monster("Turtle Assassin",                 Element.Fire,       122,    143865, 2379162));
        rartesiaMonsters.add(new Monster("Turtle Axe",                      Element.Water,      124,    158250, 2617079));
        rartesiaMonsters.add(new Monster("Turtle Spear",                    Element.Electric,   126,    174075, 2878787));
        darkRartesiaMonsters.add(new Monster("Turtle Guardian",             Element.Electric,   129,    200000, 3324998));
        darkRartesiaMonsters.add(new Monster("Rabbit Warrior",              Element.Wind,       133,    220000, 4023248));
        darkRartesiaMonsters.add(new Monster("Rabbit Gladiator",            Element.Earth,      137,    240000, 4868131));
        darkRartesiaMonsters.add(new Monster("Rabbit Pikeman",              Element.Fire,       141,    260000, 5890438));
        darkRartesiaMonsters.add(new Monster("Rabbit Assassin",             Element.Water,      145,    280000, 7127430));
        darkRartesiaMonsters.add(new Monster("Rabbit Axe",                  Element.Electric,   149,    300000, 8624191));
        areas.add(azriaMonsters);
        areas.add(rartesiaMonsters);
        areas.add(darkRartesiaMonsters);
    }

    public static Monsterlist getMonsterlist() {
        if(monsterlist != null)
        {
            return monsterlist;
        }
        else {
            return new Monsterlist();
        }
    }
}
