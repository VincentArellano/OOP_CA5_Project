/*
 * Comparator to compare name fields of two Student Classes
   This comparator is used by the TreeMap data structure
   to determine the order in which Student objects are stored.

*/

package org.example;
import org.example.Team;

import java.util.Comparator;

public class ComparatorTeamStadium implements Comparator<Team>
{
    @Override
    public int compare( Team t1, Team t2)
    {
        return t1.getName().compareTo(t2.getName());
    }
}

