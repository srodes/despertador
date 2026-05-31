package model;

public interface MathChallenge {
    /**
     * Genera la pregunta matemática en texto (Ej: "¿Cuánto es 7 + 8?")
     */
    String generateChallenge();

    /**
     * Verifica si la respuesta introducida por el usuario es correcta
     */
    boolean verifyAnswer(String answer);
}