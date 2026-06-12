# SimpleAVL<T extends Comparable<T>>

## Archivo fuente
- `src/treeModule/SimpleAVL.java`

## Tipo
BST **autobalanceado** (TP09). `extends` [[SimpleBST]]: el factor de balance de cada nodo siempre queda en {−1, 0, 1}.

## Qué overridea
- `insertRecursive` y `removeRecursive`: tras la recursión, llaman a `updateHeight` + `rebalance`.

## Helpers propios
- `height(node)` — `null` vale **-1**, hoja vale 0.
- `updateHeight(node)` — `1 + max(height(left), height(right))`.
- `balanceFactor(node)` — `height(left) - height(right)`.
- `rebalance(node)` — decide el caso y rota.
- `rotateRight(y)` / `rotateLeft(x)` — rotaciones simples.

## Casos de rotación
- **LL** (FB > 1, FB izq ≥ 0): rotación derecha.
- **RR** (FB < −1, FB der ≤ 0): rotación izquierda.
- **LR** (FB > 1, FB izq < 0): izquierda del hijo + derecha.
- **RL** (FB < −1, FB der > 0): derecha del hijo + izquierda.

## Ideas clave
- Reutiliza `findMin` (heredado, `protected`) en la remoción.
- No toca el `insert`/`remove` públicos: el rebalanceo "se cuela" por la recursión overrideada.

## Referencias
- [[SimpleBST]]
- [[TreeNode]]
