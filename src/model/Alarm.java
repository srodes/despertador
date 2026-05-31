package model;

import java.time.LocalTime;

public class Alarm {
    private LocalTime time;
    private String label;
    private boolean active;
    private MathChallenge challenge; // Aquí aplicamos POLIMORFISMO

    // Constructor para alarma normal
    public Alarm(int hour, int minute, String label) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true;
        this.challenge = null; // Sin reto por defecto
    }

    // Constructor para alarma con reto matemático
    public Alarm(int hour, int minute, String label, MathChallenge challenge) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true;
        this.challenge = challenge;
    }

    public boolean hasChallenge() {
        return challenge != null;
    }

    public MathChallenge getChallenge() {
        return challenge;
    }

    public String getLabel() { return label; }
    public LocalTime getTime() { return time; }
}