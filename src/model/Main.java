package model;

import java.util.Scanner;
import model.Alarm;
import model.BasicOperationChallenge;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SIMULADOR DE DESPERTADOR INTELIGENTE ===");

        // Creamos una alarma a las 07:00 con el reto matemático incorporado
        Alarm alarmaConReto = new Alarm(7, 0, "Despertar Obligatorio", new BasicOperationChallenge());

        System.out.println("\n[RIING] Está sonando la alarma: " + alarmaConReto.getLabel());

        // Comprobamos si tiene un reto activo
        if (alarmaConReto.hasChallenge()) {
            boolean resuelto = false;
            // Generamos la pregunta matemática
            String pregunta = alarmaConReto.getChallenge().generateChallenge();

            while (!resuelto) {
                System.out.println("\n[RETO MATEMÁTICO ACTIVO] Para apagar la alarma debes resolver:");
                System.out.print(pregunta + " -> Tu respuesta: ");
                String input = scanner.nextLine();

                if (alarmaConReto.getChallenge().verifyAnswer(input)) {
                    System.out.println("\n ¡Respuesta Correcta! Alarma desactivada con éxito.");
                    resuelto = true;
                } else {
                    System.out.println("Respuesta incorrecta. La alarma sigue sonando...");
                }
            }
        }
        
        scanner.close();
    }
}