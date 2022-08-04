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

    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {

        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) { // Стартуем сервер один(!) раз
            while (true) { // В цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    // Обработка одного подключения
                    JSONParser parser = new JSONParser();
                    String type = null;
                    String task = null;
                    final String input = in.readLine();

                    if (input.equals("end")) {
                        System.out.println("Connection closed");
                        return;
                    }

                    try {
                        Object obj = parser.parse(input);
                        JSONObject jsonObject = (JSONObject) obj;
                        type = (String) jsonObject.get("type");
                        task = (String) jsonObject.get("task");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (type.equals("ADD")) {
                        todos.addTask(task);
                    } else {
                        if (type.equals("REMOVE")) {
                            todos.removeTask(task);
                        }
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException ex) {
            System.out.println("Не могу стартовать сервер");
            ex.printStackTrace();
        }
    }
}