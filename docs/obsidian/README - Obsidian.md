# Cómo usar estas notas en Obsidian

Tranquilo, es re simple 👌

## Opción recomendada: abrí `docs/` como vault
Obsidian trabaja sobre carpetas locales. No hay "importación" rara.

1. Abrí Obsidian.
2. Click en **Open folder as vault**.
3. Elegí esta carpeta:
   - `/home/mledesma/FACU/PROGRA2_GRUPO13/docs`
4. Abrí `[[00 - Mapa TDAs]]` y empezá a navegar por links.

> **Importante:** abrí el vault en `docs/` (no en `docs/obsidian`). Así el grafo incluye también `clases/`, `consignas/` y `diapos/`, y los links entre notas, clases y consignas (PDF) resuelven bien. Obsidian encuentra los `[[...]]` por nombre de archivo en cualquier subcarpeta del vault.

## Cómo navegar
- Link interno: `[[NombreNota]]`
- Link a una clase: `[[Clase08 - BST y Busquedas]]`
- Link a una consigna (PDF): `[[TP08.pdf]]`
- Panel **Graph View**: te muestra el mapa de relaciones.
- Panel **Backlinks**: te muestra quién referencia la nota actual.

## Estructura del vault (`docs/`)
- `obsidian/` — estas notas (mapa, ejercicios, interfaces, implementaciones, TPs).
- `clases/` — resúmenes `.md` de cada clase teórica.
- `consignas/` — los PDF de cada TP (`TP01.pdf` … `TP10.pdf`).
- `diapos/` — las presentaciones `.pptx` originales.

## Flujo sugerido de estudio
1. `[[00 - Mapa TDAs]]`
2. Un TP (`[[TP08 - TDA Binary Search Tree]]`, por ejemplo)
3. Su clase (`[[Clase08 - BST y Busquedas]]`)
4. El ejercicio (`[[ContactsExercise]]`)
5. El TDA: interfaz e implementaciones (`[[SimpleBST]]`, `[[SimpleAVL]]`)

## Tip
También podés abrir todo el repo como vault:
- `/home/mledesma/FACU/PROGRA2_GRUPO13`

Y navegás igual desde `docs/obsidian/00 - Mapa TDAs.md`.
