# EmailExercise (TP6)

## Archivo fuente
- `src/emailModule/EmailExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- `EmailSystem` (lógica de negocio)
- `Email`, `Priority`, `Tab`
- TDA: [[SimpleLinkedPriorityQueue]] (vía [[SimplePriorityQueue]])

## Concepto
Sistema de correo con bandeja de entrada y 3 pestañas (Principal, Notificaciones, Spam). Cada cola es una `SimplePriorityQueue<Email>`. Convención: **menor número = mayor prioridad** (`ALTO=1`, `BAJO=2`).

## Flujo (máquina de estados)
- `0` menú principal
- `1` compose (asunto + prioridad)
- `2` inbox (lista + clasificar)
- `3` tabs menu
- `4` classify top (mover el de mayor prioridad de inbox a una pestaña)
- `5` view tab (lista + leer)
- `6` read top (sacar de la pestaña)

## Reglas implementadas
- `inbox` → solo se puede clasificar el de mayor prioridad.
- `read` → consume el mail (lo saca de la cola).
- `listInbox`/`listTab` no destruyen la cola: usan cola auxiliar para restaurar.

## Referencias
- [[SimpleLinkedPriorityQueue]]
- [[SimplePriorityQueue]]
