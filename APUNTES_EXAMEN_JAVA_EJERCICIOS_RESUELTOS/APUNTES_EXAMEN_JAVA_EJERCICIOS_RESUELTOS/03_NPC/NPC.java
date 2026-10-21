import java.io.Serializable;

// Necesario para guardar objetos con ObjectOutputStream.
public class NPC implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int vida;
    private double x, y, z; // CAMBIAR EN EXAMEN si piden otras coordenadas

    public NPC(String _nombre, int _vida, double _x, double _y, double _z) {
        nombre = _nombre;
        vida = _vida;
        x = _x;
        y = _y;
        z = _z;
    }
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    // Facilita mostrar los valores al leer el archivo.
    @Override
    public String toString() {
        return nombre + " | vida=" + vida + " | posicion=(" + x + ", " + y + ", " + z + ")";
    }
}
