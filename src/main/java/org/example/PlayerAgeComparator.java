package org.example;

import java.util.Comparator;

public class PlayerAgeComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2){
        return p1.getAge() - (p2.getAge());
    }
}
