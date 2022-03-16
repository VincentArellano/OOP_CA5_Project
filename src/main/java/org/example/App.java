package org.example;

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

        Map<Player, Jersey> teamStadiums = new TreeMap<>(new ComparatorPlayerJersey());
        initialiseTreeMap(teamStadiums);

        try{
            displayMainMenu(namesList, playerTeams, teamStadiums);
        }   catch(IOException e){
            e.printStackTrace();
        }
    }

    private void displayMainMenu(List arraylist, Map hashmap, Map treemap) throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. ArrayList\n"
                + "2. HashMap\n"
                + "3. TreeMap\n"
                + "4. Exit\n"
                + "Enter Option [1,2,3,4]";

        final int ARRAYLIST = 1;
        final int HASHMAP = 2;
        final int TREEMAP = 3;
        final int EXIT = 4;

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
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    private void displayArrayList(List<Player> list){
        System.out.println("-------------------------------------------------------");
        for(Player arr : list){
            System.out.println("--\t"+arr.getName()+"\t\t\t\t"+ arr.getAge()+"\t\t\t\t"+arr.getHeight()+"\t\t\t\t--");
        }
        System.out.println("-------------------------------------------------------");
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
        System.out.println("Enter the team name");
        String key = kb.nextLine();
        if(playerTeams.containsKey(key)){
            for(Player p : player){
                System.out.println(p.getName());
            }
        }
        else{
            System.out.println("There are no players on " + key);
        }
    }

    private void displayTreeMap(Map list){
        Set<Player> keySet = list.keySet();

        System.out.println("-------------------------------------------------------");
        System.out.println("Name\t\tAge\t\tHeight\t\tSize\t\tBrand\t\tColour");
        for (Player key : keySet) {
            Jersey jersey = (Jersey) list.get(key);
            System.out.println(key.getName()+"\t\t"+ key.getAge() + "\t\t" + key.getHeight() + "\t\t"
                    + jersey.getSize() + "\t\t" + jersey.getBrand() + "\t\t" + jersey.getColour());
        }
        System.out.println("-------------------------------------------------------");
    }

    private void initialiseArrayList(List<Player> list){
        list.add(new Player("Vincent", 18, 1.9));
        list.add(new Player("Paul", 21, 2.1));
        list.add(new Player("John", 23, 2.2));
        list.add(new Player("Luke", 21, 1.6));
        list.add(new Player("Christian", 25, 1.7));
        list.add(new Player("Andre", 24, 1.68));
        list.add(new Player("Larry", 22, 1.9));
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
}
