package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    private void initialiseArrayList(List list){
        list.add(new Player("Vincent", 18, 1.9));
        list.add(new Player("Mary", 21, 2.1));
        list.add(new Player("John", 23, 2.2));
        list.add(new Player("Luke", 21, 1.6));
        list.add(new Player("Christian", 25, 1.7));
        list.add(new Player("Hannah", 24, 1.68));
        list.add(new Player("Andrea", 22, 1.9));
        list.add(new Player("James", 27, 1.9));
        list.add(new Player("Stephan", 30, 1.9));
        list.add(new Player("Jack", 19, 1.9));
    }

    private void initialiseHashMap(HashMap list){

    }
}
