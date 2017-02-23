package com.example.klingerju.flyffmanager.DataLists;

import com.example.klingerju.flyffmanager.Classes.Element;
import com.example.klingerju.flyffmanager.Classes.Monster;

import java.util.ArrayList;

/**
 * Created by klingerju on 17.02.2017.
 */

public class Monsterlist {

    static Monsterlist monsterlist;
    ArrayList<Monster> towerMonsters = new ArrayList<>();
    ArrayList<Monster> azriaMonsters = new ArrayList<>();
    ArrayList<Monster> rartesiaMonsters = new ArrayList<>();
    ArrayList<Monster> darkRartesiaMonsters = new ArrayList<>();

    public ArrayList<ArrayList<Monster>> areas = new ArrayList<>();

    public Monsterlist() {
        towerMonsters.add(new Monster(  "Small Catsy",                      Element.None,       62,     12405,  7691));
        towerMonsters.add(new Monster(  "Catsy",                            Element.None,       64,     12773,  9476));
        towerMonsters.add(new Monster(  "Captain Catsy",                    Element.None,       66,     13621,  11674));
        towerMonsters.add(new Monster(  "Small Devia",                      Element.Fire,       68,     14005,  14383));
        towerMonsters.add(new Monster(  "Devia",                            Element.Fire,       70,     28602,  17721));
        towerMonsters.add(new Monster(  "Captain Devia",                    Element.Fire,       72,     28981,  21833));
        towerMonsters.add(new Monster("Small Gullah",	                    Element.Water,	    74,	31960,	26899));
        towerMonsters.add(new Monster("Gullah"	,                           Element.Water,      76,	32138,	32138));
        towerMonsters.add(new Monster("Captian Gullah",                 	Element.Water,	    78,	35224,	35224));
        towerMonsters.add(new Monster("Small Abraxas"	,                   Element.Electric 	,80	,37231,	50308));
        towerMonsters.add(new Monster("Abraxas",	                        Element.Electric 	,82,	39295,	61983));
        towerMonsters.add(new Monster("Captian Abraxas"	,                   Element.Electric 	,84	,41415,	76369));
        towerMonsters.add(new Monster("Small Hague"	,                       Element.Wind , 	86	,43593	,94093));
        towerMonsters.add(new Monster("Hague",                              Element.Wind , 	88,	45827	,115931));
        towerMonsters.add(new Monster("Captian Hague",	                    Element.Wind ,	90,	48119	,114551));
        towerMonsters.add(new Monster("Small Tengu",	                    Element.None,	92,	50408,	171276));
        towerMonsters.add(new Monster("Tengu"	,                           Element.None,	94	,52875,	207243));
        towerMonsters.add(new Monster("Captian Tengu",	                    Element.None,	96,	55339,	246224));
        towerMonsters.add(new Monster("Small Asterius"	,                   Element.Fire ,	98,	57861,	292538));
        towerMonsters.add(new Monster("Asterius"	,                       Element.Fire, 	100,	60441,	347563));
        towerMonsters.add(new Monster("Captian Asterius",	                Element.Fire , 	102,	63079,	405397));
        towerMonsters.add(new Monster("Small Dantalian",	                Element.Water,	104,	65775,	472854));
        towerMonsters.add(new Monster("Dantalian",	                        Element.Water	,106,	82007,	579419));
        towerMonsters.add(new Monster("Captian Dantalian",	                Element.Water,	108,	85139,	639473));
        towerMonsters.add(new Monster("Small Gannessa",	                    Element.Electric ,	110,	88583,	756880));
        towerMonsters.add(new Monster("Gannessa",	                        Element.Electric ,	112,	91628,	800746));
        towerMonsters.add(new Monster("Captian Gannessa",	                Element.Electric , 	114,	94583,	774266));
        towerMonsters.add(new Monster("Small Gorgoth",	                    Element.Wind  ,	116,	98236,	975346));
        towerMonsters.add(new Monster("Gorgoth",	                        Element.Wind  ,	118,	120000,	1100000));
        towerMonsters.add(new Monster("Captian Gorgoth",	                Element.Wind,	120,	140000,	1250000));


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

        areas.add(towerMonsters);
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
