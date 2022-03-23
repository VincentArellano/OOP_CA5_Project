package org.example;
import org.example.DTOs.Player;

import java.util.Comparator;

public class ComparatorPlayerJersey implements Comparator<Player>
{
    @Override
    public int compare( Player t1, Player t2)
    {
        return t1.getName().compareTo(t2.getName());
    }
}