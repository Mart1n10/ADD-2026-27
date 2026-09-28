#pragma once
#include "Figura.h"
class Circulo : public Figura
{
private:
	float radio;
public:
	Circulo(float _radio, string _nombre) : radio(_radio), Figura(_nombre) {};
	float area() override;
};