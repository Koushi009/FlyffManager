package com.klinger2709.klingerju.flyffmanager.Classes;

/**
 * Created by klingerju on 22.02.2017.
 */


public class Level {
    int level;
    long expNeeded;

    public Level(int level, long expNeeded) {
        this.level = level;
        this.expNeeded = expNeeded;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(long expNeeded) {
        this.expNeeded = expNeeded;
    }
}