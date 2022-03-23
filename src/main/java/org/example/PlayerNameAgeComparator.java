package org.example;

import org.example.DTOs.Player;

import java.util.Comparator;

public class PlayerNameAgeComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2){
        if (p1.getName().compareTo(p2.getName()) == 0) {
            return p1.getAge() - (p2.getAge());
        } else {
            return p1.getName().compareTo(p2.getName());
        }
    }
}
