package model;

import controller.AlarmManager;
import model.Alarm;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DEL MODO VACACIONES ===\n");

        AlarmManager manager = new AlarmManager();

        // 1. Creamos las alarmas del día a día
        Alarm alarmaTrabajo = new Alarm(7, 0, "Ir a trabajar");
        Alarm alarmaGimnasio = new Alarm(19, 30, "Gimnasio");

        manager.addAlarm(alarmaTrabajo);
        manager.addAlarm(alarmaGimnasio);

        // 2. Comprobamos cuántas alarmas van a sonar normalmente
        System.out.println("\nAlarmas listas para sonar hoy: " + manager.getActiveAlarms().size());

        // 3. ¡Llegan las vacaciones! Activamos el modo global
        manager.setVacationMode(true);

        // 4. Volvemos a comprobar. Debería dar 0 porque estamos de vacaciones
        System.out.println("Alarmas listas para sonar hoy: " + manager.getActiveAlarms().size());
        if (manager.getActiveAlarms().isEmpty()) {
            System.out.println("🌴 Disfruta de tus vacaciones, no sonará ningún despertador.");
        }

        // 5. Se acaban las vacaciones, volvemos a la rutina
        manager.setVacationMode(false);
        System.out.println("Alarmas listas para sonar hoy: " + manager.getActiveAlarms().size());
    }
}