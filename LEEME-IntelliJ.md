# Abrir el proyecto en IntelliJ IDEA

Esta rama (`IntelliJ`) ya viene con la configuración del proyecto lista
(`.idea/` + `tpoProgra2.iml`), para que IntelliJ **no se cuelgue** al abrirlo.

## Pasos

1. **File → Open** y elegí la carpeta del proyecto (la raíz, donde está `src/`).
   - NO uses "New Project". Abrí la carpeta tal cual.
2. IntelliJ detecta el módulo automáticamente: `src/` ya está marcado como
   **Sources Root** y `bin/` como carpeta de salida (excluida).
3. **Configurar el SDK** (solo la primera vez, si te aparece "SDK undefined"):
   - **File → Project Structure → Project**.
   - En **SDK** elegí cualquier JDK **17 o superior** que tengas instalado
     (si no tenés ninguno, IntelliJ te ofrece descargarlo ahí mismo).
   - En **Language level** dejá **17** (o el que use tu SDK).
4. Corré la app desde `src/application/MainProgram.java` (botón ▶ al lado del `main`).

## ¿Por qué se colgaba antes?

Sin `.idea/`, IntelliJ no sabía que `src/` era el *Sources Root* ni qué SDK
usar, así que se quedaba indexando mal o sin reconocer las clases. Estos
archivos se lo dicen de entrada.

> El `.gitignore` de `.idea/` ya excluye los archivos locales de cada máquina
> (`workspace.xml`, etc.), así que no se pisan entre compañeros.
