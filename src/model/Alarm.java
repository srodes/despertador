package model;

import java.time.LocalTime;

public class Alarm {
    private LocalTime time;
    private String label;
    private boolean active; // ¿Está encendida la alarma individualmente?
    private MathChallenge challenge;

    public Alarm(int hour, int minute, String label) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true; // Activa por defecto al crearse
        this.challenge = null;
    }

    public Alarm(int hour, int minute, String label, MathChallenge challenge) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true;
        this.challenge = challenge;
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public LocalTime getTime() { return time; }
    public String getLabel() { return label; }
    public MathChallenge getChallenge() { return challenge; }
    public boolean hasChallenge() { return challenge != null; }
}