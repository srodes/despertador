package model;

import java.util.Random;

public class BasicOperationChallenge implements MathChallenge {
    private int num1;
    private int num2;
    private int result;

    @Override
    public String generateChallenge() {
        Random random = new Random();
        // Genera dos números aleatorios entre 10 y 50
        this.num1 = random.nextInt(40) + 10;
        this.num2 = random.nextInt(40) + 10;
        this.result = this.num1 + this.num2;
        
        return "¡DESPIERTA! Resuelve para apagar la alarma: ¿Cuánto es " + num1 + " + " + num2 + "?";
    }

    @Override
    public boolean verifyAnswer(String answer) {
        try {
            int userAnswer = Integer.parseInt(answer.trim());
            return userAnswer == this.result;
        } catch (NumberFormatException e) {
            return false; // Si introduce texto no válido, la respuesta es incorrecta
        }
    }
}