import java.io.Serializable;

public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String equipo;
    private int edad;
    private int dorsal;

    // Nuevo atributo
    private String posicion;

    public Jugador(String nombre, String equipo, int edad, int dorsal, String posicion) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.edad = edad;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getEdad() {
        return edad;
    }

    public int getDorsal() {
        return dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", equipo='" + equipo + '\'' +
                ", edad=" + edad +
                ", dorsal=" + dorsal +
                ", posicion='" + posicion + '\'' +
                '}';
    }
}