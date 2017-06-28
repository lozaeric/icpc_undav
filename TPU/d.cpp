#include <iostream>
using namespace std;

int main () {
	int longitud,correr;
	string palabra;
	cin>>longitud;
	cin>>palabra;
	cin>>correr;

	for (int i=0; i<longitud; i++) 
		palabra[i] = (palabra[i]-'a'+correr)%26+'a';	
	cout<<palabra<<endl;

	return 0;
}
