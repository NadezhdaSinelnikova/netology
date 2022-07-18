package javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TodosTests {

    @Test
    void addTodosTest() {
        Todos todos = new Todos( );
        String s = "Уехать из страны";
        String s1 = "Купить хлеб";
        String s2 = "Починить телефон";
        String s3 = "Убедить в невиновности";

        todos.addTask(s);
        todos.addTask(s1);
        todos.addTask(s2);
        todos.addTask(s3);


        String expected = "Купить хлеб Уехать из страны Убедить в невиновности Починить телефон";
        String result = todos.getAllTasks( );


        Assertions.assertEquals(expected, result);
    }

    @Test
    void removeTodosTest() {
        Todos todos = new Todos( );
        String s = "Уехать из страны";
        String s1 = "Купить хлеб";
        String s2 = "Починить телефон";
        String s3 = "Убедить в невиновности";

        todos.addTask(s);
        todos.addTask(s1);
        todos.addTask(s2);
        todos.addTask(s3);
        todos.removeTask(s3);


        String expected = "Уехать из страны Убедить в невиновности Починить телефон";
        String result = todos.getAllTasks( );


        Assertions.assertEquals(expected, result);
    }

    @Test
    void removeTodosTestException() {
        Todos todos = new Todos( );
        String s = "Уехать из страны";
        String s1 = "Купить хлеб";
        String s2 = "Починить телефон";
        String s3 = "Убедить в невиновности";
        String s4 = "Покричать как птица";

        todos.addTask(s);
        todos.addTask(s1);
        todos.addTask(s2);
        todos.addTask(s3);

        Assertions.assertThrows(IllegalArgumentException.class, () -> todos.removeTask(s4));
    }

}
