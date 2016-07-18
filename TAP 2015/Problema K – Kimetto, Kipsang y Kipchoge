#include <iostream>
#include <cstdlib>

using namespace std;

void Recorrido (char ** arboles, int cantArboles, int comienzo)
{
    bool arbolesVistos[cantArboles] = {};
    int fin = comienzo;
    while (!arbolesVistos[atoi(arboles[2 + fin]) - 1] && fin != 0)
    {
        arbolesVistos[atoi(arboles[2 + fin]) - 1] = true;
        fin--;
    }
    cout<<comienzo - fin<<endl;
}

int main(int argn, char ** argv)
{
    int iRegistro = 3 + int(atoi(argv[1])), cantArboles = atoi(argv[1]);
    for (int dias = 0; dias < atoi(argv[2]); dias++)
        {
            if (argv[iRegistro][0] == 'E')
            {
               Recorrido(argv, cantArboles, atoi(argv[iRegistro + 1]));
               iRegistro += 2;
            }
            else
            {
                argv[2 + atoi(argv[iRegistro + 1])] = argv[iRegistro + 2];
                iRegistro += 3;
            }
        }
    return 0;
}
