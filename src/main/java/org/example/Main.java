package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        Integer action = 0;
        while (action != 7) {
            System.out.println("Bienvenue");
            System.out.println("Dans votre gestionnnaire de tâches");
            System.out.println("|-------------------------------------------------------|");
            System.out.println("|  0. Quitter                                           |");
            System.out.println("|  1. Ajouter une tâche                                 |");
            System.out.println("|  2. Supprimer une tâche                               |");
            System.out.println("|  3. Afficher les tâche finis                          |");
            System.out.println("|  4. Enregistrer la liste des tâche                    |");
            System.out.println("|  5. Charger la liste                                  |");
            System.out.println("|  6. Afficher la liste des tâche entre deux date       |");
            System.out.println("|-------------------------------------------------------|");
            Scanner scanner = new Scanner(System.in);
            boolean continuer = true;
            while (continuer == true) {
                System.out.println("Entrer une option: ");
                try {
                    action = Integer.parseInt(scanner.nextLine());
                    continuer = false;

                } catch (NumberFormatException e) {
                    System.out.println("Rentrer un nombre et non une chaine " + e.getMessage());
                    continuer = true;
                }
            }


            if (action == 1) { // Ajouter une tache
                int number = 0;
                int statut = 0;
                String description = null;
                Date date = null;
                continuer = true;

                while (continuer == true) {
                    System.out.println("Rentrer un numéro de la tâche");
                    try {
                        number = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Rentrer un nombre et non une chaine " + e.getMessage());
                        continuer = true;
                    }

                    System.out.println("Rentrer la description de la tâche");
                    description = scanner.nextLine();
                    System.out.println("Rentrer le statut de la tâche 1 ou 0");

                    try {
                        statut = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Rentrer un nombre et non une chaine " + e.getMessage());
                        continuer = true;
                    }
                    System.out.println("Rentrer la date de la tâche (format dd/MM/yyyy)");
                    String input = scanner.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date = format.parse(input);
                        continuer = false;
                    } catch (Exception e) {
                        System.out.println("Format de date incorrect");
                        continuer = true;
                    }

                }

                Task task = new Task(number, description, statut, date);
                taskList.add(task);


                //
            } else if (action == 2) { //Supprimer une tache
                Integer number = null;
                Task.ReturneList(taskList);
                System.out.println("Rentrer un numéro de la tâche");
                continuer = false;
                while (continuer == false) {
                    try {
                        number = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Rentrer un nombre et non une chaine " + e.getMessage());
                        continuer = true;
                    }
                }
                Iterator<Task> iterator = taskList.iterator();
                while (iterator.hasNext()) {
                    Task task = iterator.next();
                    if (task.getNumber() == number) {
                        iterator.remove();
                        System.out.println("La tache a bien été supprimer");
                        break;
                    }
                }

            }
            else if (action == 3) {//afficher la liste {
                Task.ReturneListStatut(taskList);
            }
            else if (action == 4){ //Enregister le fichier {
                System.out.println("Entrer un nom: ");
                String fileName=scanner.nextLine();
                Task.SaveTaskFile(taskList,fileName);
            }
            else if (action == 5) {//charger le fichier {
                System.out.println("Entrer un nom: ");
                String fileName=scanner.nextLine();
                taskList=Task.LoadTaskFile(taskList,fileName);
                Task.ReturneList(taskList);
            }
            else if (action == 6) {//afficher le liste par date {
                Date date1=null;
                Date date2=null;
                continuer = true;
                while (continuer==true) {
                    System.out.println("Rentrer la première date de la tâche (format dd/MM/yyyy)");
                    String input = scanner.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date1 = format.parse(input);
                        continuer = false;
                    } catch (Exception e) {
                        System.out.println("Format de date incorrect");
                        continuer = true;
                    }
                }
                continuer = true;
                while (continuer==true) {
                    System.out.println("Rentrer la deuxième date de la tâche (format dd/MM/yyyy)");
                    String input = scanner.nextLine();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        date2 = format.parse(input);
                        continuer = false;
                    } catch (Exception e) {
                        System.out.println("Format de date incorrect");
                        continuer = true;
                    }
                }
                Task.TaskListDate(taskList,date1,date2);
            }
            else if (action == 0) {//Quitter {
               action=7;
            }
        }
    }
}

