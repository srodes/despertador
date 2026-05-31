# despertador

# Sistema de Despertador Inteligente (Java Core)

Este proyecto implementa la lógica interna de un sistema de alarmas inteligente para smartphones modernos. Desarrollado íntegramente en **Java SE sin interfaz gráfica**, el diseño se centra en la aplicación de las buenas prácticas de programación, modularización, principios SOLID y patrones de desarrollo orientado a objetos.

---

## Descripción del Proyecto
El sistema emula el comportamiento de un despertador moderno, permitiendo la gestión eficiente de múltiples alarmas. Para destacar el carácter "inteligente" requerido en la práctica, se han implementado tres funcionalidades avanzadas:
1. **Reto Matemático para apagar:** Obliga al usuario a resolver una operación aleatoria para asegurar que se ha despertado.
2. **Modo Vacaciones global:** Un interruptor maestro que congela todas las alarmas sin perder su configuración.
3. **Despertar Circadiano:** Incremento gradual del volumen del sonido para evitar despertares abruptos.

## Objetivos de Aprendizaje Cumplidos
* Abstracción y modelado de un sistema real en programación orientada a objetos (POO).
* Control de versiones profesional utilizando el flujo **Git Flow** (`main`, `develop`, `feature/*`).
* Uso crítico y responsable de Inteligencia Generativa (IA) para la asistencia en el código y documentación.
* Redacción de especificaciones técnicas y documentación en formato Markdown.

---

## Tecnologías y Herramientas
* **Lenguaje:** Java SE 17+ (Uso de la API moderna `java.time`)
* **IDE:** Visual Studio Code
* **Consola de Trabajo:** Git Bash
* **Modelado:** Mermaid UML

---

## Estructura del Proyecto
El repositorio sigue estrictamente la arquitectura modular requerida:

```text
despertador/
├── src/
│   ├── controller/
│   │   └── AlarmManager.java          # Controlador general y Reglas de Negocio
│   ├── model/
│   │   ├── Alarm.java                 # Entidad Alarma
│   │   ├── MathChallenge.java         # Interfaz del Reto (SOLID - DIP)
│   │   ├── BasicOperationChallenge.java # Implementación concreta del reto
│   │   └── SoundProfile.java          # Perfil de audio y Modo Circadiano
│   └── Main.java                      # Simulador interactivo por consola
├── docs/
│   └── casos_de_uso.md                # Especificación expandida de los Casos de Uso
├── tests/
│   └── .gitkeep                       # Carpeta reservada para pruebas unitarias
└── README.md                          # Documentación principal del proyecto
```
---

## Diseño de Software y Diagramas UML

### 1. Diagrama de Clases (Mermaid)
El diseño del software se ha desacoplado aplicando el **Principio de Inversión de Dependencias (DIP)**. La clase `Alarm` no depende de una operación matemática concreta, sino de la interfaz `MathChallenge`, lo que permite añadir nuevos tipos de retos en el futuro sin modificar el código existente (Principio Open/Closed).

```mermaid
classDiagram
    class AlarmManager {
        - List<Alarm> alarms
        - boolean vacationModeActive
        + addAlarm(Alarm alarm) void
        + setVacationMode(boolean active) void
        + getActiveAlarms() List~Alarm~
    }

    class Alarm {
        - LocalTime time
        - String label
        - boolean active
        - MathChallenge challenge
        - SoundProfile soundProfile
        + trigger() void
        + isActive() boolean
        + hasChallenge() boolean
    }

    class SoundProfile {
        - String trackName
        - int maxVolume
        - boolean circadianMode
        + play() void
    }

    <<interface>> MathChallenge
    class MathChallenge {
        + generateChallenge() String
        + verifyAnswer(String ans) boolean
    }

    class BasicOperationChallenge {
        - int result
        + generateChallenge() String
        + verifyAnswer(String ans) boolean
    }

    AlarmManager "1" o-- "0..*" Alarm : gestiona
    Alarm "1" o-- "1" SoundProfile : contiene
    Alarm "1" o-- "0..1" MathChallenge : requiere
    MathChallenge <|.. BasicOperationChallenge : implementa
