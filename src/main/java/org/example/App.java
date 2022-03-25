package org.example;

import org.example.DAOs.MySqlPlayerDao;
import org.example.DAOs.PlayerDaoInterface;
import org.example.DTOs.Player;
import org.example.Exceptions.DaoException;

import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }

    public void start(){
        List<Player> namesList = new ArrayList<>();
        initialiseArrayList(namesList);

        Map<String, ArrayList<Player>> playerTeams = new HashMap<>();

        Map<Player, Jersey> playerJersey = new TreeMap<>(new ComparatorPlayerJersey());
        initialiseTreeMap(playerJersey);

        Queue<Player> playerAgeQueue = new PriorityQueue<>(new PlayerAgeComparator());

        Queue<Player> playerNameAgeQueue = new PriorityQueue<>(new PlayerNameAgeComparator());

        PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();

        try{
            displayMainMenu(namesList, playerTeams, playerJersey, playerAgeQueue, playerNameAgeQueue, IPlayerDao);
        }   catch(IOException e){
            e.printStackTrace();
        }
    }

    private void displayMainMenu(List arraylist, Map hashmap, Map treemap, Queue priorityqueue, Queue priorityqueuetwofield, PlayerDaoInterface IPlayerDao) throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. ArrayList\n"
                + "2. HashMap\n"
                + "3. TreeMap\n"
                + "4. PriorityQueue\n"
                + "5. PriorityQueue Two-Field Comparison\n"
                + "6. Display All Elements\n"
                + "7. Find All Players\n"
                + "8. Find Player by ID\n"
                + "9. Delete Player by ID\n"
                + "10. Insert Player\n"
                + "14. Exit\n"
                + "Enter Option [1,2,3,4,5,6,7,8,9,10,11,12,13,14]";

        final int ARRAYLIST = 1;
        final int HASHMAP = 2;
        final int TREEMAP = 3;
        final int PRIORITYQUEUE = 4;
        final int PRIORITYQUEUETWOFIELD = 5;
        final int DISPLAYALLELEMENTS = 6;
        final int FINDALLPLAYERS = 7;
        final int FINDPLAYERBYID = 8;
        final int DELETEPLAYERBYID = 9;
        final int INSERTPLAYER = 10;
        final int EXIT = 14;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case ARRAYLIST:
                        System.out.println("ArrayList option chosen");
                        displayArrayList(arraylist);
                        break;
                    case HASHMAP:
                        System.out.println("HashMap option chosen");
                        displayHashMap(hashmap);
                        break;
                    case TREEMAP:
                        System.out.println("TreeMap option chosen");
                        displayTreeMap(treemap);
                        break;
                    case PRIORITYQUEUE:
                        System.out.println("PriorityQueue option chosen");
                        displayPriorityQueue(priorityqueue);
                        break;
                    case PRIORITYQUEUETWOFIELD:
                        System.out.println("PriorityQueue Two-Field Comparison option chosen");
                        displayPriorityQueueTwoField(priorityqueuetwofield, arraylist);
                        break;
                    case DISPLAYALLELEMENTS:
                        System.out.println("Display All Elements option chosen");
                        display();
                        break;
                    case FINDALLPLAYERS:
                        System.out.println("Find All Players option chosen");
                        findAllPlayers(IPlayerDao);
                        break;
                    case FINDPLAYERBYID:
                        System.out.println("Find Player by ID option chosen");
                        findPlayerByID(IPlayerDao);
                        break;
                    case DELETEPLAYERBYID:
                        System.out.println("Delete Player by ID option chosen");
                        deletePlayerByID(IPlayerDao);
                        break;
                    case INSERTPLAYER:
                        System.out.println("Insert Player option chosen");
                        insertPlayer(IPlayerDao);
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.println("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    private void displayArrayList(List<Player> list){
        System.out.println("-------------------------------------------");
        System.out.printf("%-15s%-10s%-10s\n", "Name", "Age", "Height");
        System.out.println("-------------------------------------------");
        for(Player arr : list){
            System.out.printf("%-15s%-10s%-10s\n",arr.getName(), arr.getAge(), arr.getHeight());
        }
        System.out.println("-------------------------------------------");
    }

    private void displayHashMap(Map<String, ArrayList<Player>> playerTeams){
        ArrayList<Player> player = new ArrayList<>();
        player.add(new Player("Vincent", 18, 1.9));
        player.add(new Player("Paul", 21, 2.1));
        player.add(new Player("Luke", 21, 1.6));
        playerTeams.put("Atlanta Hawks", player);

        player = new ArrayList<>();
        player.add(new Player("John", 23, 2.2));
        player.add(new Player("Christian", 25, 1.7));
        playerTeams.put("Chicago Bulls", player);

        player = new ArrayList<>();
        player.add(new Player("Andre", 24, 1.68));
        player.add(new Player("Larry", 22, 1.9));
        playerTeams.put("Washington Wizards", player);

        player = new ArrayList<>();
        player.add(new Player("James", 27, 1.75));
        playerTeams.put("Toronto Raptors", player);

        player = new ArrayList<>();
        player.add(new Player("Stephan", 30, 1.88));
        player.add(new Player("Jack", 19, 1.92));
        playerTeams.put("New Orleans Pelicans", player);


        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the team name (Atlanta Hawks, Chicago Bulls, Washington Wizards, Toronto Raptors, New Orleans Pelicans)");
        String key = kb.nextLine();
        if(playerTeams.containsKey(key)){
            System.out.println("-------------------------------------------");
            System.out.println("Players in the team " + key);
            System.out.println("-------------------------------------------");
            System.out.printf("%-15s%-10s%-10s\n", "Name", "Age", "Height");
            System.out.println("-------------------------------------------");
            for(Player p : player){
                System.out.printf("%-15s%-10s%-10s\n",p.getName(), p.getAge(), p.getHeight());
            }
            System.out.println("-------------------------------------------");
        }
        else{
            System.out.println("There are no players on " + key);
        }
    }

    private void displayTreeMap(Map<Player, Jersey> list){
        Set<Player> keySet = list.keySet();

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-15s%-10s%-10s%-10s%-20s%-20s\n", "Name", "Age", "Height", "Size", "Brand", "Colour");
        System.out.println("-------------------------------------------------------------------------");
        for (Player key : keySet) {
            Jersey jersey = list.get(key);
            System.out.printf("%-15s%-10s%-10s%-10s%-20s%-20s\n", key.getName(), key.getAge(), key.getHeight(), jersey.getSize(),jersey.getBrand(),jersey.getColour());
        }
        System.out.println("-------------------------------------------------------------------------");
    }

    private void displayPriorityQueue(Queue<Player> list){
        list.add(new Player("Stephan", 30, 1.88));
        list.add(new Player("Christian", 25, 1.7));


        list.add(new Player("John", 23, 2.2));
        list.add(new Player("Paul", 21, 2.1));

        list.remove();
        System.out.println("-------------------------------------------");
        System.out.printf("%-15s%-10s%-10s\n", "Name", "Age", "Height");
        System.out.println("-------------------------------------------");
        System.out.printf("%-15s%-10s%-10s\n",list.peek().getName(), list.peek().getAge(), list.peek().getHeight());
        System.out.println("\n");

        list.add(new Player("Vincent", 18, 1.9));
        System.out.println("-------------------------------------------");
        System.out.printf("%-15s%-10s%-10s\n", "Name", "Age", "Height");
        System.out.println("-------------------------------------------");
        while(!list.isEmpty()){

            System.out.printf("%-15s%-10s%-10s\n",list.peek().getName(), list.peek().getAge(), list.peek().getHeight());
            list.remove();
        }
        System.out.println("-------------------------------------------");
    }

    private void displayPriorityQueueTwoField(Queue<Player> queueList, List<Player> arrayList){
        for(int i=0;i<arrayList.size();i++){
            queueList.add(arrayList.get(i));
        }

        System.out.println("-------------------------------------------");
        System.out.printf("%-15s%-10s%-10s\n", "Name", "Age", "Height");
        System.out.println("-------------------------------------------");
        while(!queueList.isEmpty()){

            System.out.printf("%-15s%-10s%-10s\n",queueList.peek().getName(), queueList.peek().getAge(), queueList.peek().getHeight());
            queueList.remove();
        }
        System.out.println("-------------------------------------------");
    }

    private void initialiseArrayList(List<Player> list){
        list.add(new Player("Vincent", 18, 1.9));
        list.add(new Player("Paul", 21, 2.1));
        list.add(new Player("John", 23, 2.2));
        list.add(new Player("Larry", 25, 1.6));
        list.add(new Player("Christian", 25, 1.7));
        list.add(new Player("Andre", 24, 1.68));
        list.add(new Player("Larry", 21, 1.9));
        list.add(new Player("James", 27, 1.75));
        list.add(new Player("Stephan", 30, 1.88));
        list.add(new Player("Jack", 19, 1.92));
    }

    private void initialiseTreeMap(Map<Player, Jersey> list){
        list.put(new Player("Vincent", 18, 1.9), new Jersey("XL", "Adidas", "black"));
        list.put(new Player("Paul", 21, 2.1), new Jersey("XXL", "Nike", "Red"));
        list.put(new Player("John", 23, 2.2), new Jersey("XXL", "Reebok", "Blue"));
        list.put(new Player("Luke", 21, 1.6), new Jersey("M", "Jordan", "Orange"));
        list.put(new Player("Christian", 25, 1.7), new Jersey("L", "Puma", "Yellow"));
        list.put(new Player("Andre", 22, 1.9), new Jersey("L", "Under Armour", "White"));
        list.put(new Player("Larry", 18, 1.9), new Jersey("L", "Nike", "Green"));
        list.put(new Player("James", 27, 1.75), new Jersey("M", "Jordan", "Brown"));
        list.put(new Player("Stephan", 30, 1.88), new Jersey("XXL", "Adidas", "Purple"));
        list.put(new Player("Jack", 19, 1.92), new Jersey("L", "Puma", "Grey"));
    }

    private void display(){
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-15s%-10s%-10s%-10s%-20s%-20s\n", "Name", "Age", "Height", "Size", "Brand", "Colour");
        System.out.println("-------------------------------------------------------------------------");

    }

    private void findAllPlayers(PlayerDaoInterface IPlayerDao){
        try
        {
        List<Player> players = IPlayerDao.findAllPlayers();

        if( players.isEmpty() )
            System.out.println("There are no Players");
        else {
            System.out.println("-----------------------------------------------------");
            System.out.printf("%-15s%-15s%-10s%-10s\n","ID", "Name", "Age", "Height");
            System.out.println("-----------------------------------------------------");
            for (Player player : players) {
                System.out.printf("%-15s%-15s%-10s%-10s\n",player.getId(), player.getName(), player.getAge(), player.getHeight());
            }
            System.out.println("-----------------------------------------------------");
        }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    private void findPlayerByID(PlayerDaoInterface IPlayerDao){
        try
        {
            Scanner kb = new Scanner(System.in);
            System.out.println("Enter ID");
            int id = kb.nextInt();
            Player player = IPlayerDao.findPlayerByID(id);

            if( player != null ) {
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n","ID", "Name", "Age", "Height");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n",player.getId(), player.getName(), player.getAge(), player.getHeight());
                System.out.println("-----------------------------------------------------");
            }
            else
                System.out.println("ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    private void deletePlayerByID(PlayerDaoInterface IPlayerDao){
        try
        {
            Scanner kb = new Scanner(System.in);
            findAllPlayers(IPlayerDao);

            System.out.println("Enter ID to delete from this table");
            int id = kb.nextInt();
            Player player = IPlayerDao.findPlayerByID(id);
            if( player != null ) {
                System.out.println("-----------------------------------------------------");
                System.out.println("Player Deleted");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n","ID", "Name", "Age", "Height");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n",player.getId(), player.getName(), player.getAge(), player.getHeight());
                System.out.println("-----------------------------------------------------");
                IPlayerDao.deletePlayerByID(id);
            }
            else
                System.out.println("ID not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    private void insertPlayer(PlayerDaoInterface IPlayerDao){
        try
        {
            Scanner kb = new Scanner(System.in);
            System.out.println("Enter Name");
            String name = kb.nextLine();
            System.out.println("Enter Age");
            int age = kb.nextInt();
            System.out.println("Enter Height");
            double height = kb.nextDouble();
            Player player = IPlayerDao.insertPlayer(name, age, height);

            if( player != null ) {
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n","ID", "Name", "Age", "Height");
                System.out.println("-----------------------------------------------------");
                System.out.printf("%-15s%-15s%-10s%-10s\n",player.getId(), player.getName(), player.getAge(), player.getHeight());
                System.out.println("-----------------------------------------------------");
            }
            else
                System.out.println("Player not found");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
    //Test get by id method

    private void findPlayerUsingFilter(PlayerDaoInterface IPlayerDao){
        try {
            Scanner kb = new Scanner(System.in);
            System.out.println("Enter ID");
            int age = kb.nextInt();
            List<Player> players = IPlayerDao.findPlayerUsingFilter(age);
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
