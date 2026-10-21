# Apuntes de examen Java — ejercicios resueltos y comentados

Basados en los ejemplos comunicados del examen y relacionados con la unidad *Persistència en fitxers* (MP 0486): `File`, streams, texto, serialización, acceso aleatorio y XML DOM.

## Cómo usarlo rápidamente en VS Code

1. Descomprime el ZIP. En VS Code abre la **carpeta del tema** en vez de la raíz cuando ejecutes ejemplos con datos relativos (`personas.txt`, `personajes.xml`).
2. Abre `INDICE_EXAMEN.md` para localizar la plantilla apropiada.
3. Copia el Java a tu solución y cambia las líneas `CAMBIAR EN EXAMEN`.
4. Ejecuta y verifica el resultado; no sobrescribas los ficheros que te entregue el profesor.
5. Los ejemplos de NPC son tres archivos `NPC.java`, `GuardarNPC.java`, `LeerNPC.java` en la misma carpeta. Ejecuta primero GuardarNPC y después LeerNPC.

### Orden aconsejado (poco tiempo)

1. `01_FILE/CrearCarpetaYArchivos.java` (5 min)
2. `02_TXT/MayorSaldo.java` (10 min)
3. `03_NPC/NPC.java` + GuardarNPC/LeerNPC (10 min)
4. `04_XML/LeerEtiquetasXML.java` (10 min)

### Advertencias para el examen

- `FileWriter` sin `true` sobrescribe.
- `ObjectOutputStream` también sobrescribe si se abre con un `FileOutputStream` nuevo: utiliza el archivo creado por tu código, no el fichero de examen que te pasen, sin comprobarlo.
- `split(";")` separa los campos. Revisa los índices: `0` nombre, `1` edad, `2` saldo.
- Para el mayor inicializa correctamente: `boolean hayDatos = false` y `if (!hayDatos || valor > maximo)`.
- `getElementsByTagName` sirve para XML **con etiquetas**; si el profesor habla de etiquetas en otro formato, estos ejemplos no bastan.
- `Serializable` no es lo mismo que `DataOutputStream` ni `RandomAccessFile`. Comprueba qué pide exactamente el enunciado.

**Todos los códigos están pensados para Java 21 sin dependencias de terceros.**
