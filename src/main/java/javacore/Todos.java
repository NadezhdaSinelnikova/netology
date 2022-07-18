package javacore;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.Collectors;

public class Todos {

    List<String> taskList;
    StringBuilder stringBuilder = new StringBuilder( );

    public Todos() {
        taskList = new ArrayList<>( );
    }

    public void addTask(String task) {

        taskList.add(task);
    }

    public void removeTask(String task) {

        if (taskList.contains(task)) {
            taskList.remove(task);
        } else {
            throw new IllegalArgumentException("Такой задачи нет в списке");
        }
    }

    public String getAllTasks() {

        Collections.sort(taskList);
        for (String st : taskList) {
            stringBuilder.append(st + " ");
        }
        return stringBuilder.toString( ).trim( );
    }

}
