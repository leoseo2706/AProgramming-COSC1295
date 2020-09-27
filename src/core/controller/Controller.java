package core.controller;

import core.service.MainService;
import core.utils.Utils;

import java.util.Scanner;

public class Controller {

    private MainService mainService;

    public Controller() {
        this.mainService = new MainService();
    }

    public void commandLoop() {

        while (true) {
            mainService.showMenu();
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    mainService.addCompany(sc);
                    break;

                case "B":
                    mainService.addProjectOwner(sc);
                    break;

                case "C":
                    mainService.addProject(sc);
                    break;

                case "D":
                    mainService.captureStudentPersonalities(sc);
                    break;

                case "E":
                    mainService.addStudentPreference(sc);
                    break;

                case "F":
                    mainService.shortlistProject();
                    break;

                case "Q" :
                    System.out.println("Bye bye then!");
                    System.exit(0);

                default:
                    System.out.println(Utils.format("Invalid choice: {0}. Please re-enter.",
                            choice));
            }

            System.out.println();
        }

    }
}
