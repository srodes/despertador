package model;

import java.time.LocalTime;

public class Alarm {
    private LocalTime time;
    private String label;
    private boolean active;
    private MathChallenge challenge;
    private SoundProfile soundProfile; // <-- NUEVO ATRIBUTO

    // Actualizamos el constructor para que pida obligatoriamente un sonido
    public Alarm(int hour, int minute, String label, SoundProfile soundProfile) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true;
        this.challenge = null;
        this.soundProfile = soundProfile;
    }

    public Alarm(int hour, int minute, String label, MathChallenge challenge, SoundProfile soundProfile) {
        this.time = LocalTime.of(hour, minute);
        this.label = label;
        this.active = true;
        this.challenge = challenge;
        this.soundProfile = soundProfile;
    }

    public void trigger() {
        System.out.println("\nEs hora de: " + label + " [" + time + "]");
        // La alarma delega en el perfil de sonido la responsabilidad de cómo sonar
        soundProfile.play();
    }

    public boolean isActive() { return active; }
    public LocalTime getTime() { return time; }
    public String getLabel() { return label; }
    public MathChallenge getChallenge() { return challenge; }
    public boolean hasChallenge() { return challenge != null; }
    public SoundProfile getSoundProfile() { return soundProfile; }
}