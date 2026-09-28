#include "Circulo.h"
#include "Cuadrado.h"

int main()
{
	Figura figura[3] = {Circulo(13, "Circulo1"),Cuadrado(5,4,"Cuadrado1"), Circulo(1,"Circulo2")};

	for (int i = 0; i < 3; i++) {
		figura[i].area();
		cout << "La area es: " << figura[i].GetArea() << endl;
	}
}