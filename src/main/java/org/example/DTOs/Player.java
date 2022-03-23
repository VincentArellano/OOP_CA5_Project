package org.example.DTOs;

import java.util.Objects;

public class Player {
    private int id;
    private String name;
    private int age;
    private double height;

    public Player(String name) {
        this.name = name;
    }

    public Player(int id, String name, int age, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Player(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return age == player.age && Double.compare(player.height, height) == 0 && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, height);
    }
}
