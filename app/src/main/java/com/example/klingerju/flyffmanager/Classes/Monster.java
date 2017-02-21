package com.example.klingerju.flyffmanager.Classes;

/**
 * Created by klingerju on 17.02.2017.
 */

public class Monster {

    public String name;
    public Element element;
    public int hp;
    public int lvl;
    public int exp;

    public Monster(String name, Element element, int lvl, int hp, int exp) {
        this.name = name;
        this.element = element;
        this.hp = hp;
        this.lvl = lvl;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
