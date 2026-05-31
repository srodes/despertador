package model;

public class SoundProfile {
    private String trackName;
    private int maxVolume; // De 0 a 100
    private boolean circadianMode;

    public SoundProfile(String trackName, int maxVolume, boolean circadianMode) {
        this.trackName = trackName;
        this.maxVolume = maxVolume;
        this.circadianMode = circadianMode;
    }

    // Lógica para reproducir el sonido simulando el comportamiento físico
    public void play() {
        System.out.println("\n [REPRODUCIENDO] Pista: " + trackName);
        
        if (circadianMode) {
            System.out.println("[MODO CIRCADIANO ACTIVO] Iniciando despertar progresivo...");
            // Simula la subida gradual de volumen en tramos del 20%
            int currentVolume = 0;
            while (currentVolume < maxVolume) {
                currentVolume += 20;
                if (currentVolume > maxVolume) {
                    currentVolume = maxVolume;
                }
                System.out.println("... Volumen actual: " + currentVolume + "%");
                
                // Pequeña simulación de espera (en un entorno real aquí habría un delay de tiempo)
            }
            System.out.println("Se ha alcanzado el volumen óptimo de despertar.");
        } else {
            // Sonido normal e inmediato al volumen configurado
            System.out.println("Sonido inmediato al volumen configurado: " + maxVolume + "%");
        }
    }

    public String getTrackName() { return trackName; }
    public int getMaxVolume() { return maxVolume; }
    public boolean isCircadianMode() { return circadianMode; }
}