import java.io.*; // Imprescindible para permitir la serialización en disco

// Al implementar Serializable, Java permite convertir objetos de esta clase en bytes
public class Libro implements Serializable {
    
    // Identificador único para la serialización (recomendado por Java)
    private static final long serialVersionUID = 1L;

    // Atributos pedidos por el enunciado
    private String titulo;
    private int paginas;
    private String isbn;
    private String editorial;

    // ----------------------------------------------------
    // CONSTRUCTORES
    // ----------------------------------------------------
    
    // Constructor vacío (por defecto)
    public Libro() {
    }

    // Constructor con todos los parámetros
    public Libro(String titulo, int paginas, String isbn, String editorial) {
        this.titulo = titulo;
        this.paginas = paginas;
        this.isbn = isbn;
        this.editorial = editorial;
    }

    // ----------------------------------------------------
    // GETTERS Y SETTERS
    // ----------------------------------------------------
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    // ----------------------------------------------------
    // MÉTODO toString()
    // ----------------------------------------------------
    @Override
    public String toString() {
        return "Libro [Título=" + titulo + ", Páginas=" + paginas + 
               ", ISBN=" + isbn + ", Editorial=" + editorial + "]";
    }
}