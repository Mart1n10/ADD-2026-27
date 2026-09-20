import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Agenda {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcio = 0;

        while (opcio != 4) {

            System.out.println("---- Agenda ----");
            System.out.println("1. Afegir contacte");
            System.out.println("2. Llistar contactes");
            System.out.println("3. Cercar per nom");
            System.out.println("4. Sortir");
            System.out.println("Tria una opció: ");

            opcio = sc.nextInt(); //lee el número que introduce el usuario
            sc.nextLine(); //consumir el salto de línea que queda después de nextInt().

            if (opcio == 1) {

                System.out.println("Has elegido la opción 1");

                System.out.println("Nom: ");
                String nom = sc.nextLine();

                System.out.println("Telèfon: ");
                String telefon = sc.nextLine();

                System.out.println("Correu: ");
                String correu = sc.nextLine();

                // Creamos una línea con los datos separados por punto y coma.
                String linia = nom + ";" + telefon + ";" + correu + "\n";

                try {

                    // El true significa mode append, añadir al final sin borrar los contactos anteriores.
                    FileWriter fw = new FileWriter("contactes.txt", true);

                    // Escribimos la línea en el archivo.
                    fw.write(linia);
                    fw.close();

                    System.out.println("Contacte afegit correctament.");

                } catch(IOException e) {
                    System.out.println("Error en escriure el fitxer.");
                }
            }

            if (opcio == 2) {

                try {

                    // FileReader abre el archivo y BufferedReader permite leerlo línea por línea.
                    BufferedReader br = new BufferedReader(
                            new FileReader("contactes.txt"));

                    String linia;

                    // readLine() lee una línea cada vez.
                    // Cuando llega al final del archivo devuelve null.
                    while ((linia = br.readLine()) != null) {

                        // Separamos la línea utilizando ; como separador.
                        String[] dades = linia.split(";");

                        System.out.println("Nom: " + dades[0]);
                        System.out.println("Telèfon: " + dades[1]);
                        System.out.println("Correu: " + dades[2]);

                        System.out.println("--------------------");
                    }
                    br.close();

                } catch (IOException e) {
                    System.out.println("El fitxer encara no existeix.");
                }
            }

            if (opcio == 3) {

                System.out.println("Introdueix el nom a cercar: ");
                String nomCercat = sc.nextLine();

                try {
                    // Abrimos el archivo para poder buscar dentro de él.
                    BufferedReader br = new BufferedReader(
                            new FileReader("contactes.txt"));

                    String linia;

                    // Esta variable nos permite saber si hemos encontrado el contacto.
                    boolean trobat = false;

                    // Leemos el archivo línea por línea.
                    while ((linia = br.readLine()) != null) {

                        // Separamos los datos de cada contacto por ;
                        String[] dades = linia.split(";");

                        // Comparamos el nombre guardado con el nombre buscado.
                        // equalsIgnoreCase() no distingue entre mayúsculas y minúsculas.
                        if (dades[0].equalsIgnoreCase(nomCercat)) {

                            System.out.println("Nom: " + dades[0]);
                            System.out.println("Telèfon: " + dades[1]);
                            System.out.println("Correu: " + dades[2]);
                            trobat = true;
                        }
                    }
                    br.close();

                    if (!trobat) {
                        System.out.println("Contacte no trobat.");
                    }
                
                } catch (IOException e) {
                    System.out.println("El fitxer encara no existeix.");
                }
            }

            if (opcio == 4) {
                System.out.println("Sortint de l'agenda");
            }
        }
        sc.close();
    }
}