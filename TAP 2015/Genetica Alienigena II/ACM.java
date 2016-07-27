package edu.jdgp;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class ACM {
	private static char[] letras = "bdpqxwvo".toCharArray();
	private static int[] conjugado = {1, -1, 1, -1, 0, 0, 0, 0};

	public static int[] copy(int[] v, int len) {
		int[] c = new int[len];
		for (int i = 0; i < len; i++) {
			c[i] = v[i];
		}
		return c;
	}

	public static String transformar(int[] v, int len) {
		String s = "";
		for (int i = 0; i < len; i++) {
			s += letras[v[i]];
		}
		return s;
	}

	public static class GeneradorMutaciones { 

		private Random rand;
		
		public GeneradorMutaciones() {
			rand = new Random();
		}	
				
		private void show(String titulo, int[] v, int len) {
			System.out. println(titulo + "\t" + transformar(v, len));
		}

		public int[] generarInput(int len) {
			int[] idx = new int[len];
			for (int i = 0; i < len; i++) {
				idx[i] = rand.nextInt(8);
			}
			return idx;
		}
				
		public int[] generarMutacion(int[] v, int len, int mutaciones) {
			int max = len;
			int min = 0;
			show("inicio: ", v, len);
			for (int i = 0; i < mutaciones; i++) {
				int indiceMutacion = rand.nextInt(max - min - 1) + min;
				int iter = (len - indiceMutacion);
				iter = iter / 2 + iter % 2;
				//System.out.println(indiceMutacion + " iter: " + iter);
				for (int j = 0; j < iter; j++) {
					int aux = v[len-j-1];
					v[len-j-1] = v[indiceMutacion + j] + conjugado[v[indiceMutacion + j]];
					v[indiceMutacion + j] = aux + conjugado[aux];
				}			
				min = indiceMutacion + 1;
				show("mutacion: " + i, v, len);
				if (min == len - 1)
					break;				
			}
			return v;
		}
		
	}

	public static class AnalizadorMutaciones { 
		private String actual;
		private String objetivo;
		private int len;
		private static final Map<String, String> conjugados;
			static
			{
				conjugados = new HashMap<String, String>();
				conjugados.put("p", "q");
				conjugados.put("q", "p");
				conjugados.put("d", "b");
				conjugados.put("b", "d");
				conjugados.put("o", "o");
				conjugados.put("x", "x");
				conjugados.put("w", "w");
				conjugados.put("v", "v");
			}
			
		private void log(int posActual, int desde, int hasta, int nroMutacion, String s) {
			System.out.println("posActual: " + posActual + " desde: " + desde + 
								" hasta: " + hasta + " nroMutacion: " + nroMutacion + ": " + s);
		}
				
		private int procesarInterno(int posActual, int desde, int hasta, int nroMutacion) {
			log(posActual,desde,hasta,nroMutacion, "Inicio");
			int minMutaciones = -1;
			if (nroMutacion % 2 == 0) {
				while (posActual < len) {
					log(posActual,desde,hasta,nroMutacion, 
						"actual: " + objetivo.charAt(posActual) + " desde: " + actual.charAt(hasta) + " hasta: " + actual.charAt(hasta));
					if (objetivo.charAt(posActual) == conjugados.get(actual.charAt(hasta) + "").charAt(0)) {						
						if (posActual < len - 1) { // si todavia no llegue al final hago la llamada recursiva
							log(posActual,desde,hasta,nroMutacion, " <1.1> posActual < len - 1");
							int mutaciones = procesarInterno(posActual+1, desde, hasta-1, nroMutacion+1);
							if (minMutaciones == -1 || (mutaciones >= 0 && mutaciones < minMutaciones)) {
								minMutaciones = mutaciones;
							}
						} else {
						// llegue al final: cadena valida
								log(posActual,desde,hasta,nroMutacion, " <1.1> caso base ! posActual == len - 1");
								return nroMutacion;
						}						
					}
					if (objetivo.charAt(posActual) == actual.charAt(desde)) {
						if (posActual < len - 1) {
							log(posActual,desde,hasta,nroMutacion, " <1.2> posActual < len - 1");
							desde++;
						} else {
							log(posActual,desde,hasta,nroMutacion, " <1.2> caso base ! posActual == len - 1");
							return nroMutacion;
						}
						posActual++;
					} else {
						log(posActual,desde,hasta,nroMutacion, " <1.3> cadena invalida !");
						return minMutaciones; // no es prefijo ni conjugado
					}
					
				}
			} else {
				while (posActual < len) {
					log(posActual,desde,hasta,nroMutacion, 
						"actual: " + objetivo.charAt(posActual) + " desde: " + actual.charAt(desde) + " hasta:" + actual.charAt(hasta));
					if (objetivo.charAt(posActual) == actual.charAt(desde)) {
						if (posActual < len - 1) { // si la ultima posicion no hago la llamada recursiva
							log(posActual,desde,hasta,nroMutacion, " <2.1> posActual < len - 1");
							int mutaciones = procesarInterno(posActual+1, desde+1, hasta, nroMutacion+1);
							if (minMutaciones == -1 || (mutaciones >= 0 && mutaciones < minMutaciones)) {
								minMutaciones = mutaciones;
							}
						} else {
							// llegue al final: cadena valida
							log(posActual,desde,hasta,nroMutacion, " <2.1> caso base ! posActual == len - 1");
							return nroMutacion;
						}						
					} 
					if (objetivo.charAt(posActual) == conjugados.get(actual.charAt(hasta) + "").charAt(0)) {
						if (posActual < len - 1) {
							log(posActual,desde,hasta,nroMutacion, " <2.2> posActual < len - 1");
							hasta--;
						} else {
							log(posActual,desde,hasta,nroMutacion, " <2.2> caso base ! posActual == len - 1");
							return nroMutacion;
						}
						posActual++;
					} else {
						log(posActual,desde,hasta,nroMutacion, " <2.3> cadena invalida !");
						return minMutaciones; // no es prefijo ni conjugado
					}					
				}				
			}
			return minMutaciones;
		}
			
		public int procesar(String actual, String objetivo) {
			this.actual = actual;
			this.objetivo = objetivo;
			len = actual.length();
			System.out.println("actual: " + actual + " objetivo: " + objetivo + " len: " + len ); 
			return procesarInterno(0, 0, len-1, 0);
		}
	}
	
	public static void main(String[] args) {
		/*
		int len = 4;		
		GeneradorMutaciones acm = new GeneradorMutaciones();
		int[] actual = acm.generarInput(len);
		int[] objetivo = acm.generarMutacion(copy(actual, len), len, 2);
		*/
		//acm.procesar(transformar(input, len), transformar(target, len));
		AnalizadorMutaciones analizador = new AnalizadorMutaciones();
		//System.out.println("min: " + analizador.procesar(transformar(actual, len), transformar(objetivo, len)));
		System.out.println("min: " + analizador.procesar("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "pppppppppppppppppppppppppppppppppppppppppppppppppppppp"));
		
	}

}
