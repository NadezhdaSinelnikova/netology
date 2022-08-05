package javacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Todos {

    List<String> taskList;

    public Todos() {
        taskList = new ArrayList<>();
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
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(taskList);
        for (String st : taskList) {
            stringBuilder.append(st);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

}
