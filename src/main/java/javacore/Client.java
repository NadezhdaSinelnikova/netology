package javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static char pickRandomChar() {
        String chars = "ABCDEFG";
        return chars.charAt(new Random( ).nextInt(chars.length( )));
    }

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream( )));
                PrintWriter out = new PrintWriter(socket.getOutputStream( ), true)
        ) {

            out.println("{ \"type\": \"ADD\", \"task\": \"Встать\" }");
            out.println("{ \"type\": \"ADD\", \"task\": \"Подпрыгнуть вверх\" }");
            out.println("{ \"type\": \"ADD\", \"task\": \"Лечь\" }");
            out.println("{ \"type\": \"DELETE\", \"task\": \"Подпрыгнуть вверх\" }");
            out.println("{ \"type\": \"ADD\", \"task\": \"Сьесть булочку\" }");

            System.out.println(in.readLine( ));
        }
    }
}
