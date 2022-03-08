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

        Map<Player, Jersey> playerJerseys = new HashMap<>();
        initialiseHashMap(playerJerseys);

        Map<Team, Stadium> teamStadiums = new TreeMap<>(new ComparatorTeamStadium());
        initialiseTreeMap(teamStadiums);

        try{
            displayMainMenu(namesList, playerJerseys, teamStadiums);
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

    private void displayArrayList(List list){
        for(Object arr : list){
            System.out.println(arr);
        }
    }

    private void displayHashMap(Map list){
        Set<Player> keySet = list.keySet();

        for (Player key : keySet) {
            Jersey jersey = (Jersey) list.get(key);
            System.out.println("Key: " + key + ", Size:"
                    + jersey.getSize() + ", Brand:" + jersey.getBrand() + ", Colour:" + jersey.getColour());
        }
    }

    private void displayTreeMap(Map list){
        Set<Team> keySet = list.keySet();

        for (Team key : keySet) {
            Stadium stadium = (Stadium) list.get(key);
            System.out.println("Key: " + key + " Location: "
                    + stadium.getLocation() + ", Max No. of Audience " + stadium.getMaxNumOfAudience());
        }
    }

    private void initialiseArrayList(List list){
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

    private void initialiseHashMap(Map list){
        list.put(new Player("Vincent", 18, 1.9), new Jersey("XL", "Adidas", "black"));
        list.put(new Player("Mary", 21, 2.1), new Jersey("XXL", "Nike", "Red"));
        list.put(new Player("John", 23, 2.2), new Jersey("XXL", "Reebok", "Blue"));
        list.put(new Player("Luke", 21, 1.6), new Jersey("M", "Jordan", "Orange"));
        list.put(new Player("Christian", 25, 1.7), new Jersey("L", "Puma", "Yellow"));
        list.put(new Player("Andrea", 22, 1.9), new Jersey("L", "Under Armour", "White"));
        list.put(new Player("Vincent", 18, 1.9), new Jersey("L", "Nike", "Green"));
        list.put(new Player("James", 27, 1.75), new Jersey("M", "Jordan", "Brown"));
        list.put(new Player("Stephan", 30, 1.88), new Jersey("XXL", "Adidas", "Purple"));
        list.put(new Player("Jack", 19, 1.92), new Jersey("L", "Puma", "Grey"));
    }

    private void initialiseTreeMap(Map list){
        list.put(new Team("Washington Wizards", "Wizard"), new Stadium(31.35,20.36, 10000));
        list.put(new Team("Chicago Bulls", "Bull"), new Stadium(105.67, 93.65, 15000));
        list.put(new Team("Toronto Raptors", "Raptor"), new Stadium(51.15, 62.26, 20000));
        list.put(new Team("Dallas Mavericks", "Maverick"), new Stadium(53.3456,-6.3421,19000));
        list.put(new Team("Milwaukee Bucks", "Buck"), new Stadium(53.2543,-6.4444, 16000));
        list.put(new Team("Memphis Grizzlies", "Grizzly Bear"), new Stadium(54.021,-7.444, 16000));
        list.put(new Team("Charlotte Hornets", "Hornet"), new Stadium(-53.7654,6.6235, 20000));
        list.put(new Team("Minnesota Timberwolves", "Timber Wolf"), new Stadium(60.24, 10.21, 17000));
        list.put(new Team("Atlanta Hawks", "Hawk"), new Stadium(80.15, -50.18, 16500));
        list.put(new Team("New Orleans Pelicans", "Pelican"), new Stadium(90.61, -20.50, 19500));
    }
}
