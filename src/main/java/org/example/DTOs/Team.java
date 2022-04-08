package org.example.DTOs;

import java.util.Objects;

public class Team {
    private String name;
    private String mascot;

    public Team(String name, String mascot) {
        this.name = name;
        this.mascot = mascot;
    }

    public String getName() {
        return name;
    }

    public String getMascot() {
        return mascot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", mascot='" + mascot + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(mascot, team.mascot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mascot);
    }
}
