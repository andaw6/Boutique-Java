package com.ehacdev.utils;

import java.util.Scanner;

public class Console {

    public static void clear() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }


    public static void pause(Scanner scanner) {
        System.out.println("Appuyez sur Entrée pour continuer...");
        // Attendre que l'utilisateur appuie sur Entrée
        scanner.nextLine();
    }
}
