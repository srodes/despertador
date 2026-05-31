import model.Alarm;
import model.SoundProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE PERFILES DE SONIDO (CIRCADIANO) ===\n");

        // 1. Creamos dos perfiles de sonido diferentes
        SoundProfile sonidoNormal = new SoundProfile("HeavyRock_Alerta.mp3", 90, false);
        SoundProfile sonidoCircadiano = new SoundProfile("Aves_Del_Bosque.mp3", 70, true);

        // 2. Creamos las alarmas asociadas a esos sonidos
        Alarm alarmaMolesta = new Alarm(6, 30, "Gimnasio Duro", sonidoNormal);
        Alarm alarmaRelajante = new Alarm(8, 0, "Despertar Fin de Semana", sonidoCircadiano);

        // 3. Simulamos que se activan
        alarmaMolesta.trigger();
        
        System.out.println("\n-------------------------------------------");
        
        alarmaRelajante.trigger();
    }
}