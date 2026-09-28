#pragma once
#include <iostream>
using namespace std;
class Figura
{
protected:
	string nombre;
	float areaT = 0;
public:
	Figura(string _nombre) : nombre(_nombre) { cout << "Contructor padre llamado\n"; };
	virtual float area()const { return 0; };
	float GetArea()const;
};

