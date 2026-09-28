#pragma once
#include "Figura.h"
class Cuadrado : public Figura
{
private:
	float base, altura;
public:
	Cuadrado(float _base, float _altura, string _nombre) : base(_base), altura(_altura), Figura(_nombre) {};
	float area() override;
};
