package org.example.BusinessObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.DTOs.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                    + "1. Display Player by Id\n"
                    + "2. Display all Players\n"
                    + "3. Add an Player\n"
                    + "4. Delete Player by ID\n"
                    + "5. Display Players Higher Than Average Height \n"
                    + "6. Exit\n"
                    + "Enter Option [1,2,3,4,5,6]";
            System.out.println(MENU_ITEMS);
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
            String playerJson = null;

            while(!command.equals("6")) {
                if(command.startsWith("1")){
                    String input = socketReader.nextLine();
                    Player founderArray = gsonParser.fromJson(input, Player.class);
                    playerJson = gsonParser.toJson(founderArray);
                    System.out.println("Client message: Response from server: \"" + playerJson + "\"");
                }
                else if(command.equals("2")){
                    String input = socketReader.nextLine();
                    Player[] founderArray = gsonParser.fromJson(input, Player[].class);
                    playerJson = gsonParser.toJson(founderArray);
                    System.out.println("Client message: Response from server: \"" + playerJson + "\"");
                }
                else if(command.startsWith("3")){
                    String input = socketReader.nextLine();
                    Player founderArray = gsonParser.fromJson(input, Player.class);
                    playerJson = gsonParser.toJson(founderArray);
                    System.out.println("Client message: Response from server: \"" + playerJson + "\"");
                }
                else if(command.startsWith("4")){
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else if(command.equals("5")){
                    String input = socketReader.nextLine();
                    Player[] founderArray = gsonParser.fromJson(input, Player[].class);
                    playerJson = gsonParser.toJson(founderArray);
                    System.out.println("Client message: Response from server: \"" + playerJson + "\"");
                }
                else
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }

                System.out.println(MENU_ITEMS);
                command = in.nextLine();
                socketWriter.println(command);
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}
