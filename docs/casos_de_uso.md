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

## Caso de Uso: Activar / Desactivar Modo Vacaciones

* **Nombre:** Gestionar el estado del Modo Vacaciones global.
* **Objetivo:** Permitir al usuario congelar temporalmente la ejecución de todas las alarmas programadas en el sistema (por ejemplo, durante un periodo vacacional) con un solo interruptor, evitando tener que apagar o borrar cada alarma de forma individual.
* **Actor principal:** Usuario.
* **Precondiciones:** El sistema cuenta con al menos una alarma creada y configurada en estado activo.

### Flujo Principal (Camino Feliz)
1. El usuario solicita activar el "Modo Vacaciones" a través del sistema de control.
2. El sistema cambia el estado del interruptor maestro global (`vacationModeActive`) a verdadero (`true`).
3. El sistema emite un mensaje de confirmación por consola indicando que el modo ha sido activado.
4. El sistema evalúa las próximas alarmas a sonar y filtra la lista para que devuelva cero (0) alarmas disponibles.
5. Llega la hora programada de una alarma individual, pero el sistema la ignora por completo al verificar el estado global.

### Flujos Alternativos

#### 1.a. Desactivación del Modo Vacaciones (Vuelta a la rutina)
* **1.a.1.** El usuario solicita apagar el "Modo Vacaciones".
* **1.a.2.** El sistema cambia el estado del interruptor maestro global (`vacationModeActive`) a falso (`false`).
* **1.a.3.** El sistema emite un mensaje confirmando que se ha desactivado el modo.
* **1.a.4.** El sistema vuelve a evaluar las alarmas individuales y restablece el calendario habitual de despertadores basándose en el estado propio de cada una.

### Postcondiciones
* Mientras el Modo Vacaciones esté activo, ninguna alarma del sistema emitirá sonido, pero mantendrán intacta su configuración interna (etiqueta, hora y días de repetición) para cuando el modo se apague.

### Reglas de Negocio
* **RN-1 (Prioridad Absoluta):** El estado del Modo Vacaciones tiene prioridad máxima sobre cualquier regla de repetición semanal o configuración individual de las alarmas. Si está activo, el método `getActiveAlarms()` debe retornar obligatoriamente una lista vacía.
* **RN-2 (Preservación de Estado):** Activar el modo vacaciones NO modifica el atributo `active` de las alarmas individuales. Solo actúa como un escudo o filtro temporal.

## Caso de Uso: Activación de Alarma con Despertar Circadiano

* **Nombre:** Ejecutar alarma con incremento de volumen progresivo.
* **Objetivo:** Despertar al usuario de forma progresiva y no estresante, simulando un amanecer acústico mediante la elevación gradual del volumen del sonido.
* **Actor principal:** Sistema (Disparador automático) / Usuario (Receptor).
* **Precondiciones:** 1. La hora actual coincide con la hora programada de la alarma.
  2. El Modo Vacaciones está desactivado.
  3. La alarma tiene asociado un perfil de sonido (`SoundProfile`) con el `circadianMode` activado (`true`).

### Flujo Principal (Camino Feliz)
1. El sistema detecta que es la hora exacta de la alarma y la dispara (`trigger()`).
2. El sistema comprueba que el perfil de sonido tiene activado el modo circadiano.
3. El sistema inicia la reproducción del archivo de audio a un volumen inicial del 0%.
4. El sistema incrementa el volumen de forma escalonada en intervalos fijos (tramos del 20%).
5. El sistema repite el incremento de volumen de manera progresiva hasta alcanzar el volumen máximo configurado por el usuario (Ej: 70%).
6. La alarma continúa sonando al volumen máximo establecido hasta que el usuario la posponga o la detenga.

### Flujos Alternativos

#### 2.a. El Perfil de Sonido NO tiene activado el modo circadiano
* **2.a.1.** El sistema detecta que el `circadianMode` es falso (`false`).
* **2.a.2.** El sistema dispara el sonido directamente al volumen máximo configurado por el usuario desde el primer segundo (Ej: 90% de golpe).
* **2.a.3.** El flujo salta directamente al punto 6 del flujo principal.

### Postcondiciones
* El usuario es notificado de la alarma mediante un estímulo acústico que alcanza el nivel óptimo de forma escalonada, protegiendo su ciclo de sueño.

### Reglas de Negocio
* **RN-1 (Límite de Escala):** El incremento progresivo nunca debe superar el valor del volumen máximo fijado en el perfil de sonido (`maxVolume`). Si un incremento del 20% va a superar el máximo, el sistema debe ajustar el último tramo exactamente al valor máximo.
* **RN-2 (No Bloqueante):** El bucle que aumenta el volumen de manera gradual no debe bloquear la capacidad del sistema para escuchar si el usuario pulsa los botones de posponer (snooze) o apagar.