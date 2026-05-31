package controller;

import model.Alarm;
import java.util.ArrayList;
import java.util.List;

public class AlarmManager {
    private List<Alarm> alarms;
    private boolean vacationModeActive;

    public AlarmManager() {
        this.alarms = new ArrayList<>();
        this.vacationModeActive = false; // Desactivado por defecto
    }

    // Añadir una alarma al sistema
    public void addAlarm(Alarm alarm) {
        this.alarms.add(alarm);
        System.out.println("Alarma añadida: [" + alarm.getTime() + "] " + alarm.getLabel());
    }

    // Activar o desactivar el Modo Vacaciones
    public void setVacationMode(boolean active) {
        this.vacationModeActive = active;
        System.out.println("\n🛑 [MODO VACACIONES] " + (active ? "ACTIVADO" : "DESACTIVADO"));
    }

    // Consultar las alarmas que realmente deberían sonar
    public List<Alarm> getActiveAlarms() {
        // REGLA DE NEGOCIO: Si el modo vacaciones está activo, ninguna alarma debe sonar
        if (vacationModeActive) {
            return new ArrayList<>(); // Devolvemos una lista vacía
        }
        
        // Si no está en vacaciones, filtramos las que el usuario tiene activas
        List<Alarm> activeAlarms = new ArrayList<>();
        for (Alarm alarm : alarms) {
            if (alarm.isActive()) {
                activeAlarms.add(alarm);
            }
        }
        return activeAlarms;
    }

    public boolean isVacationModeActive() {
        return vacationModeActive;
    }
}