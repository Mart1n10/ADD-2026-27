
import java.io.Serializable;

public class Pokemon implements Serializable{
    private String nombre;
    private int vida;

    public Pokemon(){
        nombre = "Default";
        vida = 60;
    }
    public Pokemon(String nombre, int vida){
        this.nombre = nombre; //this --> variable de la clase
        this.vida = vida;
    }

    public String GetNombre(){
        return nombre; //no se pone this porque no compite con nada
    }
    public int GetVida(){
        return vida;
    }
}
