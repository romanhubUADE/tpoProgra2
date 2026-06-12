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

   > En este grupo conviven distintas versiones de Java (vimos **openjdk-23**
   > y **openjdk-26** entre las máquinas). No pasa nada: cada uno elige el JDK
   > que tenga (17+). El proyecto compila con cualquiera; el SDK es local a tu
   > máquina y NO se versiona, así que no se pisan entre ustedes.
4. Corré la app desde `src/application/MainProgram.java` (botón ▶ al lado del `main`).

## ¿Por qué se colgaba antes?

La rama anterior tenía un `.idea/misc.xml` (y `modules.xml`) con **marcadores
de conflicto de merge sin resolver** commiteados (`<<<<<<<`, `=======`,
`>>>>>>>`). Eso es **XML inválido**: IntelliJ no puede parsearlo y se traba.
A eso se sumaba que cada uno tenía un SDK distinto (23 vs 26) peleándose en
ese mismo archivo.

Esta rama reemplaza esa config rota por una limpia y portable: `src/` queda
marcado como *Sources Root*, `bin/` como salida (excluida) y el SDK se hereda
del que elijas, sin atarlo a una versión puntual.

> El `.gitignore` de `.idea/` ya excluye los archivos locales de cada máquina
> (`workspace.xml`, etc.), así que no se pisan entre compañeros.
