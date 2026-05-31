# Especificación de Casos de Uso - Sistema de Despertador Inteligente

## Caso de Uso: Apagar Alarma con Reto Matemático

* **Nombre:** Apagar alarma activa mediante resolución de reto matemático.
* **Objetivo:** Garantizar que el usuario se despierte y esté lo suficientemente alerta obligándole a resolver una operación matemática para silenciar el dispositivo.
* **Actor principal:** Usuario (Durmiente).
* **Precondiciones:** 1. El sistema tiene una alarma programada para la hora actual.
  2. La alarma tiene configurada la funcionalidad avanzada de "Reto Matemático".
  3. La alarma comienza a sonar de manera efectiva.

### Flujo Principal (Camino Feliz)
1. El sistema detecta que la hora actual coincide con la hora de la alarma y comienza a emitir el sonido configurado.
2. El sistema bloquea la opción de apagado directo y muestra en la terminal el reto matemático generado aleatoriamente (Ej: *¿Cuánto es 24 + 17?*).
3. El usuario introduce la respuesta numérica a través de la consola.
4. El sistema valida la respuesta introducida.
5. El sistema verifica que la respuesta es **correcta**.
6. El sistema detiene la emisión de sonido y cambia el estado de la alarma a inactiva (o reprogramada para la siguiente semana si es recurrente).

### Flujos Alternativos

#### 4.a. Respuesta Incorrecta
* **4.a.1.** El sistema detecta que el resultado introducido por el usuario no coincide con el reto generado.
* **4.a.2.** El sistema muestra un mensaje de error indicando que la respuesta es incorrecta.
* **4.a.3.** El sonido de la alarma continúa reproduciéndose.
* **4.a.4.** El sistema genera un nuevo reto matemático (o mantiene el actual) y vuelve al punto 3 del flujo principal.

#### 4.b. Entrada No Válida (Texto en lugar de Números)
* **4.b.1.** El usuario introduce caracteres no numéricos (Ej: "no sé", "hola").
* **4.b.2.** El sistema captura la excepción de formato (`NumberFormatException`) de manera controlada para evitar el colapso de la aplicación.
* **4.b.3.** El sistema informa al usuario que solo se admiten números como respuesta.
* **4.b.4.** El flujo regresa al punto 3 del flujo principal sin detener la alarma.

### Postcondiciones
* La alarma se detiene exitosamente solo tras una respuesta válida y correcta. 
* El sistema queda en estado de espera para la próxima alarma programada.

### Reglas de Negocio
* **RN-1 (Generación Aleatoria):** Los números del reto deben ser generados de forma pseudoaleatoria en cada intento y deben comprender valores de dos dígitos (entre 10 y 50) para garantizar una dificultad moderada que fuerce la actividad cerebral sin llegar a ser frustrante.
* **RN-2 (Persistencia del Sonido):** Bajo ninguna circunstancia se detendrá el sonido o el hilo de la alarma hasta que la comprobación del paso 5 devuelva un valor afirmativo (`true`).