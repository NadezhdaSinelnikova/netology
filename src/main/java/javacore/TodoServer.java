package javacore;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private int port;
    private Todos todos;

    public TodoServer(int port, Todos todos) {

        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept( );
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream( ), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream( )))) {
            System.out.println("Starting server at " + port + "...");

            JSONParser jsonParser = new JSONParser( );

            while (true) {
                if (!bufferedReader.ready( )) {
                    break;
                }
                String json = bufferedReader.readLine( );
                Object obj = jsonParser.parse(json);
                JSONObject jsonObj = (JSONObject) obj;
                for (int i = 0; i < jsonObj.size( ); ) {
                    String typeTodos = (String) jsonObj.get("type");
                    if (typeTodos.equals("ADD")) {
                        String addTodos = (String) jsonObj.get("task");
                        todos.addTask(addTodos);
                    } else {
                        String deleteTodos = (String) jsonObj.get("task");
                        todos.removeTask(deleteTodos);
                    }
                    i = i + 2;
                }
            }
            String s = todos.getAllTasks( );
            printWriter.println(s);
        } catch (ParseException e) {
            e.printStackTrace( );
        }
    }
}
