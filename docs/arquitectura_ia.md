# Memoria Técnica y Reflexión sobre el Uso de IA

##  1. Decisiones de Diseño y Patrones (Reflexión Técnica)
Para el desarrollo de este despertador inteligente se han tomado las siguientes decisiones de arquitectura bajo la filosofía de código limpio:

* **Encapsulación estricta:** Todos los atributos de las entidades (`Alarm`, `SoundProfile`) se han definido como privados (`private`) para proteger la coherencia de los datos, exponiendo únicamente métodos de acceso (`getters`) y lógica de negocio controlada (como `snooze()` o `trigger()`).
* **Principio de Inversión de Dependencias (SOLID - DIP):** La clase `Alarm` no está acoplada a un tipo de reto matemático fijo. En su lugar, depende de la abstracción `MathChallenge`.
* **Desacoplamiento de la Interfaz:** Siguiendo las restricciones de la práctica, toda la lógica de negocio se ejecuta de forma independiente a la capa de presentación, lo que facilitaría integrar una interfaz gráfica (GUI) en el futuro sin alterar las reglas del gestor (`AlarmManager`).

##  2. Uso Responsable de la Inteligencia Artificial
Durante el ciclo de desarrollo incremental se ha utilizado IA generativa como asistente de código bajo una estricta validación humana.

### Ejemplos de Prompts Utilizados
1. *“Genera una interfaz en Java llamada MathChallenge con métodos para generar un reto en texto y validar una respuesta, aplicando principios SOLID de desacoplamiento.”*
2. *“Escribe una clase en Java que implemente un perfil de sonido y simule un incremento gradual de volumen (Modo Circadiano) sin usar librerías de audio externas, solo salida por consola.”*

### Validación y Corrección Crítica
* **Ventajas encontradas:** Agilidad en la maquetación de estructuras repetitivas en Java y generación rápida de plantillas Markdown para la documentación técnica.
* **Limitaciones y errores corregidos:** En las primeras propuestas, la IA tendía a diseñar la lógica del reto matemático directamente dentro de la clase `Alarm`. Se intervino manualmente para refactorizar el código, abstrayendo la lógica en una interfaz independiente para respetar el principio de Responsabilidad Única (SRP) de SOLID. Todas las soluciones fueron probadas y ejecutadas mediante el simulador `Main.java` para certificar su robustez.
Considero que me ha ayudado mucho, pero es verdad que he tenido que tratar de guiarle en algunas ocasiones para que haga lo que le pido. Eso es algo que se debería corregir.