# ÍNDICE PARA EL EXAMEN — ABRE ESTO PRIMERO

**NO memorices todo. Localiza el enunciado más parecido y cambia solamente las líneas `CAMBIAR EN EXAMEN`.**

| Si el profesor pide... | Archivo que debes abrir |
|---|---|
| Crear carpeta y uno o varios archivos | `01_FILE/CrearCarpetaYArchivos.java` |
| Comprobar, listar archivos y tamaños | `01_FILE/ListarDirectorio.java` |
| Leer `nombre;edad;saldo` y mostrar el MAYOR | `02_TXT/MayorSaldo.java` |
| Buscar MENOR valor | `02_TXT/MayorSaldo.java` → cambia `>` por `<` (el primer dato se guarda con `!hayDatos`) |
| Contar, filtrar o calcular MEDIA | `02_TXT/ContarYMedia.java` |
| Crear un TXT, escribir y añadir | `02_TXT/EscribirYAnadirTXT.java` |
| Crear clase NPC con nombre, vida, x, y, z | `03_NPC/NPC.java` |
| Guardar NPC en `.dat` | `03_NPC/GuardarNPC.java` |
| Leer NPC de `.dat` | `03_NPC/LeerNPC.java` |
| Guardar un ArrayList completo | `03_NPC/GuardarListaNPC.java` |
| Leer XML por etiqueta | `04_XML/LeerEtiquetasXML.java` |
| Buscar etiqueta / un NPC concreto | `04_XML/BuscarPorEtiquetaXML.java` |
| Crear XML desde cero | `04_XML/CrearXML.java` |
| Cambiar valor de una etiqueta XML | `04_XML/ModificarEtiquetaXML.java` |
| Guardar tipos básicos en DAT | `05_BINARIO_EXTRA/GuardarLeerDatosDAT.java` |
| Buscar/modificar registro en posición fija | `06_ALEATORIO_EXTRA/RegistroAleatorio.java` |

## Tres cosas que suelen quitar muchos puntos

1. `new File(...)` no crea el archivo: hace falta `createNewFile()` (ni la carpeta: `mkdirs()`).
2. Para crear DOS archivos haz DOS `if` independientes. `else if` puede saltarse el segundo.
3. `renameTo()` recibe otro `File`, no una cadena de texto.

## Rutas: importante

Abre **la carpeta específica del ejercicio** en VS Code (por ejemplo `02_TXT`) antes de pulsar Ejecutar. Así encontrará `personas.txt` mediante la ruta relativa `"personas.txt"`. Si ejecutas desde la raíz del proyecto, cambia la ruta a `"02_TXT/personas.txt"`.

## Datos del ejemplo TXT

`Ana;23;1500.50`, `Pedro;31;2500.30`, `Lucia;19;1200.00`, `Marta;28;3100.25`.

**Solución esperada**: Marta, 28 años, 3100.25.

## NPC: dos opciones distintas de examen

- **Objeto por objeto**: `GuardarNPC.java` y `LeerNPC.java`; ambos deben utilizar el mismo formato.
- **Lista entera**: `GuardarListaNPC.java`; su archivo NO se lee con el bucle de `LeerNPC` porque el formato es diferente.

## XML: ¿qué significa «por etiqueta»?

Estos ejemplos asumen XML con etiquetas como `<npc>`, `<nombre>` y `<vida>`. Si en clase significaba otra cosa, habrá que adaptar este apartado.
