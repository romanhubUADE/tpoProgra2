# AuthExercise (TP7)

## Archivo fuente
- `src/authModule/AuthExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- `AuthService` (lógica de auth)
- `User` (modelo)
- TDA: [[SimpleLinkedDictionary]] (vía [[SimpleDictionary]])

## Concepto
Sistema de login/registro. Cada usuario tiene contador de intentos fallidos; al 3er fallo la cuenta se bloquea. Login exitoso resetea el contador.

## Flujo (máquina de estados)
- `0` menú (register / login / mm)
- `1` register flow
- `2` login flow
- `3` logged in (ENTER para logout → vuelve al 0)

## API de AuthService
- `register(username, password) → RegisterResult`
- `login(username, password) → LoginResult`
- `getRemainingAttempts(username) → int`
- `isBlocked(username) → boolean`

## Resultados (enums)
- `RegisterResult`: `SUCCESS`, `USERNAME_ALREADY_EXISTS`, `INVALID_INPUT`
- `LoginResult`: `SUCCESS`, `ACCOUNT_BLOCKED`, `WRONG_PASSWORD`, `USER_NOT_FOUND`, `INVALID_INPUT`

## Reglas implementadas
- `MAX_FAILED_ATTEMPTS = 3`. Al alcanzarlo, `User.blocked = true`.
- Login con cuenta bloqueada → siempre falla.
- Login exitoso → `resetFailedAttempts`.
- Persistencia: igual que Set/Queue, los datos se vacían al salir del exercise.

## TP
- [[TP07 - TDA Dictionary]]

## Referencias
- [[SimpleDictionary]]
- [[SimpleLinkedDictionary]]
