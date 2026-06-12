# ContactsExercise

## Archivo fuente
- `src/treeModule/ContactsExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleAVL]] (TP09 — el árbol concreto, hereda de [[SimpleBST]]).
- [[Contact]]

## Flujo (máquina de estados)
- `0` menú
- `1` add
- `2` search (con sub-menú edit/delete/back)
- `3` editMenu
- `4` editName
- `5` editPhone
- `6` editEmail
- `7` delete
- `8` showAll

## Métodos relevantes
- `menuLogic()`, `addLogic()`, `searchLogic()`
- `editMenuLogic()`, `editNameLogic()`, `editPhoneLogic()`, `editEmailLogic()`
- `deleteLogic()`, `showAllLogic()`
- `loadSampleData()` — carga 8 contactos de ejemplo
- `readNonEmpty(field)` — input con opción `back`

## Reglas implementadas
- No permite nombres duplicados (la key del árbol es el nombre).
- Editar el nombre = `remove` + `insert` (reubica en el árbol).
- `showAll` usa `inOrder()` → lista **alfabética**.
- TP09: el árbol subyacente es AVL, así que se mantiene balanceado en alta y baja.

## TP
- [[TP08 - TDA Binary Search Tree]] (BST)
- [[TP09 - TDA AVL Tree]] (migración a AVL)

## Referencias
- [[SimpleBST]]
- [[SimpleAVL]]
- [[Contact]]
