package com.example.klingerju.flyffmanager.DataLists;

import com.example.klingerju.flyffmanager.Classes.Element;
import com.example.klingerju.flyffmanager.Classes.Monster;

import java.util.ArrayList;

/**
 * Created by klingerju on 17.02.2017.
 */

public class Monsterlist {

    Monsterlist monsterlist;
    ArrayList<Monster> azriaMonsters = new ArrayList<>();
    ArrayList<Monster> rartesiaMonsters = new ArrayList<>();
    ArrayList<Monster> darkRartesiaMonsters = new ArrayList<>();

    public Monsterlist() {
        azriaMonsters.add(new Monster(  "Yeti",                             Element.Earth,      73,     31285,0));
        azriaMonsters.add(new Monster(  "Mutant Yeti",                      Element.Water,      79,     36220,0));
        azriaMonsters.add(new Monster(  "Augu",                             Element.Water,      85,     42497,0));
        azriaMonsters.add(new Monster(  "Mutant Augu",                      Element.Fire,       91,     49286,0));
        azriaMonsters.add(new Monster(  "Ghost of the Forgotten Prince",    Element.Electric,   97,     56593,0));
        azriaMonsters.add(new Monster(  "Ghost of the Forgotten King",      Element.Wind,       103,    64420,0));
        azriaMonsters.add(new Monster(  "Mammoth",                          Element.Water,      109,    86854,0));
        azriaMonsters.add(new Monster(  "Cannibal Mammoth",                 Element.None,       115,    96403,0));
        rartesiaMonsters.add(new Monster("Rabbit Guarder",                  Element.Electric,   116,    108089,0));
        rartesiaMonsters.add(new Monster("Turtle Fighter",                  Element.Wind,       118,  	130787,0));
        rartesiaMonsters.add(new Monster("Turtle Sworder",                  Element.Earth,      120,    130787,0));
        rartesiaMonsters.add(new Monster("Turtle Assassin",                 Element.Fire,       122,    143865,0));
        rartesiaMonsters.add(new Monster("Turtle Axe",                      Element.Water,      124,    158250,0));
        rartesiaMonsters.add(new Monster("Turtle Spear",                    Element.Electric,   126,    174075,0));
        darkRartesiaMonsters.add(new Monster("Turtle Guardian",             Element.Electric,   129,    200000,0));
        darkRartesiaMonsters.add(new Monster("Rabbit Warrior",              Element.Wind,       133,    220000,0));
        darkRartesiaMonsters.add(new Monster("Rabbit Gladiator",            Element.Earth,      137,    240000,0));
        darkRartesiaMonsters.add(new Monster("Rabbit Pikeman",              Element.Fire,       141,    260000,0));
        darkRartesiaMonsters.add(new Monster("Rabbit Assassin",             Element.Water,      145,    280000,0));
        darkRartesiaMonsters.add(new Monster("Rabbit Axe",                  Element.Electric,   149,    300000,0));

    }

    public Monsterlist getMonsterlist() {
        if(monsterlist != null)
        {
            return monsterlist;
        }
        else {
            return new Monsterlist();
        }
    }
}
