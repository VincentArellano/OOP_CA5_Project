package org.example.BusinessObject;

import com.google.gson.Gson;
import org.example.DAOs.MySqlPlayerDao;
import org.example.DAOs.PlayerDaoInterface;
import org.example.Exceptions.DaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();

            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber,IPlayerDao)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        PlayerDaoInterface IPlayerDao = new MySqlPlayerDao();
        Gson gsonParser = new Gson();

        public ClientHandler(Socket clientSocket, int clientNumber, PlayerDaoInterface IPlayerDao)
        {
            try
            {

                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        @Override
        public void run()
        {
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("1"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        socketWriter.println(IPlayerDao.findPlayerByIDJson(id));
                    }
                    else if(message.equals("2")){
                        socketWriter.println(IPlayerDao.findAllPlayersJson());
                    }
                    else if(message.startsWith("3")){
                        String[] tokens = message.split(" ");
                        String name = tokens[1];
                        int age = Integer.parseInt(tokens[2]);
                        double height = Double.parseDouble(tokens[3]);
                        String playerJson = gsonParser.toJson(IPlayerDao.insertPlayer(name,age,height));
                        socketWriter.println(playerJson);
                    }
                    else if(message.startsWith("4")){
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);

                        message = "Deleted Player "+IPlayerDao.findPlayerByIDJson(id);
                        IPlayerDao.deletePlayerByID(id);
                        socketWriter.println(message);
                    }
                    else if(message.equals("5")){
                        socketWriter.println(IPlayerDao.findPlayersHigherThanAverageHeight());
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand");
                    }
                }

                socket.close();

            } catch (IOException | DaoException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }
}
