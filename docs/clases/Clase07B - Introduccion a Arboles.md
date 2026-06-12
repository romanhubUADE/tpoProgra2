# Clase 07B - Introducción a Árboles

## Concepto

Un **Tree** (árbol) es una **colección de nodos con estructura jerárquica**: los nodos tienen una relación **padre-hijo**. Cada nodo puede tener **un solo padre** y **cero o más hijos**.

A diferencia de un conjunto plano (sin jerarquía), el árbol organiza los elementos en niveles.

---

## Anatomía

- **Raíz** (*root*): el nodo inicial, **no tiene padre**.
- **Hojas** (*leaves*): nodos que **no tienen hijos**.
- **Nodos internos**: los que tienen **padre e hijos**.
- **Arista**: cada conexión entre dos nodos.
- **Subárbol**: un nodo no-root junto con todos sus hijos (y descendientes).

---

## Medidas

- **Dirección**: "arriba" es hacia el root, "abajo" es alejándose del root.
- **Profundidad**: cuántas aristas hay **desde el root hasta un nodo**. Es "absoluta" (todos comparten el root).
- **Altura**: cuántas aristas hay **desde un nodo hasta su hoja más lejana**. Es "relativa".
- **Nivel**: lo mismo que profundidad, pero por convención **empieza en 1**.

### Altura vs profundidad

- En un mismo nivel, la **profundidad es igual** pero **la altura no siempre**.
- Para el **árbol completo**:
  - Altura del árbol = altura del root.
  - Profundidad del árbol = máxima profundidad entre sus nodos.
  - **Para un árbol, la altura y la profundidad son iguales.**

---

## Por qué importa

El árbol es la base de los TDAs que siguen:
- [[Clase08 - BST y Busquedas|Binary Search Tree]] (búsqueda ordenada).
- [[Clase09 - Arbol AVL|AVL]] (BST autobalanceado).
