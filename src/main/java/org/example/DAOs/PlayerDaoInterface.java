package org.example.DAOs;

import org.example.DTOs.Player;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface PlayerDaoInterface
{
    public List<Player> findAllPlayers() throws DaoException;

    public Player findPlayerByID(int id) throws DaoException;

    public void deletePlayerByID(int id) throws DaoException;

    public Player insertPlayer(String name, int age, double height) throws DaoException;

    public List<Player> findPlayerUsingAgeFilter(int age) throws DaoException;

    public String findAllPlayersJson() throws DaoException;

    public String findPlayerByIDJson(int id) throws DaoException;
}
