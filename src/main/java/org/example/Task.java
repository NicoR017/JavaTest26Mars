package org.example;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Task {
    public ArrayList<Task> TaskList;
    private Integer number;

    private String description;

    private Integer statut;

    private Date date;


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Task(Integer number, String description, Integer statut, Date date) {
        this.number = number;
        this.description = description;
        this.statut = statut;
        this.date = date;
    }

    public static void ReturneList(ArrayList<Task> taskList) {
        System.out.println("|   Numero      |       Description         |       Statut      |       date        |");
        for (Task task : taskList) {
            System.out.print("|" + task.getNumber() + "     |");
            System.out.print(task.getDescription() + "     |");
            System.out.print(task.getStatut() + "     |");
            System.out.print(task.getDate() + "     |");
            System.out.println("");
        }
    }

    public static void ReturneListStatut(ArrayList<Task> taskList) {
        System.out.println("|   Numero      |       Description         |       Statut      |       date        |");
        for (Task task : taskList) {
            if (task.getStatut() == 1) {
                System.out.print("|" + task.getNumber() + "     |");
                System.out.print(task.getDescription() + "     |");
                System.out.print(task.getStatut() + "     |");
                System.out.print(task.getDate() + "     |");
                System.out.println("");
            }

        }
    }

    public static void SaveTaskFile(ArrayList<Task> taskList, String fileName) {
        BufferedWriter writer = null;

        try {
            String filePath = fileName + ".txt";

            writer = new BufferedWriter(new FileWriter(filePath, true));
            for (Task task : taskList) {
                writer.write(task.getNumber().toString() + "|");
                writer.write(task.getDescription().toString() + "|");
                writer.write(task.getStatut().toString() + "|");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                writer.write(format.format(task.getDate()));;
                writer.newLine(); // pour écrire chaque tâche sur une nouvelle ligne
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<Task> LoadTaskFile(ArrayList<Task> taskList, String fileName) {
        BufferedReader reader = null;

        try {
            String s = ".txt";
            String filePath = fileName + s;
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            ArrayList<Integer> liste = new ArrayList<Integer>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int i = 0;
                int number = 0;
                int statut = 0;
                String description = null;
                Date date = null;

                for (String part : parts) {
                    if (i == 0) {
                        number = Integer.parseInt(part.trim());
                        i++;
                    } else if (i == 1) {
                        description = part.trim();
                        i++;
                    } else if (i == 2) {
                        statut = Integer.parseInt(part.trim());
                        i++;
                    } else if (i == 3) {

                        String strDate = part.trim();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            date = format.parse(strDate);
                            System.out.println(date);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i = 0;
                    }
                }
                try {

                    Task task = new Task(number, description, statut, date);
                    taskList.add(task);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur lors de la conversion de la chaîne en entier : " + e.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }


    public static void TaskListDate(ArrayList<Task> taskList, Date date1, Date date2) {

        System.out.println("|   Numero      |       Description         |       Statut      |       date        |");
        for (Task task : taskList) {
            if (task.getDate().getTime() >= date1.getTime() && task.getDate().getTime() <= date2.getTime()) {
                System.out.print("|" + task.getNumber() + "     |");
                System.out.print(task.getDescription() + "     |");
                System.out.print(task.getStatut() + "     |");
                System.out.print(task.getDate() + "     |");
                System.out.println("");
            }
        }

    }


}

