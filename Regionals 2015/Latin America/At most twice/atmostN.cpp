#include <stdio.h>

#define N 2 //cantidad admisible de repeticiones de cada digito

typedef unsigned long long ullong;
typedef unsigned char uchar;

ullong U;
ullong L;
uchar repeticionesDigitosL[10]; // cuenta la cantidad de repeticiones de cada digito
uchar invertedDigitosU[19]; //split de los digitos de U en un vector de forma invertida. Ej U = 54237 -> 7,3,2,4,5 
uchar digitosL[19];
uchar cantDigitosU;
bool termine;

/*
 *
 *  */

void inicializar(ullong _U) {
	// contar la cantidad de digitos de U; split (invertido) de digitos de U en un vector 
	cantDigitosU = 0;
	while (_U >= 1) {
		invertedDigitosU[cantDigitosU] = _U % 10;
		_U = _U / 10;
		cantDigitosU++;
	}
	// inicializar vector de repeticiones de digitos de L
	for (int i = 0; i < 10; i++) {
		repeticionesDigitosL[i] = 0;
	}
	//
	termine = false;
	L = 0;
}

void debug() {
	printf("digitosL: ");
	for (int i = 0; i < cantDigitosU; i++) {
		printf("%d ", digitosL[i]);
	}
	
	printf("\nrepeticionesDigitosL: ");
	for (int i = 0; i < 10; i++) {
		printf("%d ", repeticionesDigitosL[i]);
	}
}	
	
void procesarL(uchar posicionActual) {
	if (posicionActual < cantDigitosU) {
		uchar digito = invertedDigitosU[cantDigitosU - posicionActual - 1];
		if (repeticionesDigitosL[digito] < N) {
			digitosL[posicionActual] = digito;
			repeticionesDigitosL[digito]++;
			procesarL(posicionActual + 1);
			if (!termine) {
				repeticionesDigitosL[digito]--; // revierto el digito anterior porque no sirvio
				//busco el proximo digito valido
				while (--digito != 255 && repeticionesDigitosL[digito] >= N);// en lugar de "--digito >= 0" tuve que poner "--digito != 255" porque digito es unsigned
				if (digito != 255) {
				// ahora hay que poner el digito que encontre y rellenar el resto de L con digitos validos
					digitosL[posicionActual] = digito;
					repeticionesDigitosL[digito]++;
					posicionActual++;
					digito = 9; // para rellenar empiezo por el maximo digito posible					
					while (posicionActual < cantDigitosU) {
						//debug();
						if (repeticionesDigitosL[digito] < N) {
							digitosL[posicionActual] = digito;
							repeticionesDigitosL[digito]++;
							posicionActual++;
						} else {
							digito--;
						}
					}
					termine = true; //en este punto L es valido
				} else {
					return; //no encontre digito valido para esta posicion, corto la recursion
				}
			}
		} else {
			return; // no puedo poner el digito porque se superan las repeticiones, corto la recursion
		}
	} else {
		termine = true; // este es el caso U == L
	}
}

int main() {
	U = 2210102960;
	inicializar(U);
	procesarL(0);
	// construimos L
	for (int i = 0; i < cantDigitosU; i++) {
		L = L * 10 + digitosL[i];
	}					
	printf("U: %lld\n",U);
	printf("L: %lld\n",L);
}

/*
ullong nroMayor(char cantDigitos) {
	ullong nroMayor = 0;
	char digitoActual = 9;
	char i = 0;
	while (cantDigitos>0) {
		nroMayor = nroMayor * 10 + digitoActual;
		i++;
		if (i % 2 == 0) {
			digitoActual--;
		}
		cantDigitos--;
	}
	return nroMayor;
}
*/
