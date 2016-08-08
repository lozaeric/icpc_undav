package edu.jdgp;

import java.util.Random;

//Arbol binario de busqueda
//Fuente: Introduction to Algorithms (Binary Search Trees)

public class ABB {
	protected ABB arbolDerecho;
	protected ABB arbolIzquierdo;
	protected ABB padre;
	protected Valor valor;

	public static class Valor {
		public int valor;
		
		public Valor(int v) {
			valor = v;
		}
				
		public int cmp(Valor v) {
			return valor > v.valor ? 1 :
					valor == v.valor ? 0 : -1;
		}
		
		public static int cmp(Valor v1, Valor v2) {
			return v1.cmp(v2);
		}
	}
	
	public static interface Procesador {
		public void preProcesar();
		public void procesar(Valor v);
		public void postProcesar();
	}
	
	public static class ProcesadorImprimir implements Procesador {
		public void preProcesar() {
			
		}
		public void procesar(Valor v) {
			System.out.print(v.valor + " ");
		}
		public void postProcesar() {
			System.out.println();
		}
	}
	
	public ABB (Valor v) {
		valor = v;
	}
	
	public Valor getValor() {
		return valor;
	}
	
	public ABB buscar(Valor v) {
		int cmp;
		ABB abb = this;		
		while (abb != null) {
			cmp = abb.valor.cmp(v);
			if (cmp == 1) 
				abb = arbolIzquierdo;
			else if (cmp == -1)
				abb = arbolDerecho;
			else if (cmp == 0) 
				break;
		}
		return abb;
	}
	
	public ABB minimo() {
		ABB abb = this;
		while (abb.arbolIzquierdo != null)
			abb = abb.arbolIzquierdo;		
		return abb;
	}

	public ABB maximo() {
		ABB abb = this;
		while (abb.arbolDerecho != null)
			abb = abb.arbolDerecho;
		return abb;
	}
	
	public ABB siguiente() {
		if (arbolDerecho != null)
			return arbolDerecho.minimo();
		ABB actual = this;
		ABB padre = this.padre;
		while (padre != null && padre.arbolDerecho == actual) {
			actual = padre;
			padre = actual.padre;
		}
		return padre;
	}
	
	public ABB anterior() {
		if (arbolIzquierdo != null)
			return arbolIzquierdo.maximo();
		ABB actual = this;
		ABB padre = this.padre;
		while (padre != null && padre.arbolIzquierdo == actual) {
			actual = padre;
			padre = actual.padre;
		}
		return padre;
	}
	
	public void insertar(ABB nuevo) {
		ABB actual = null; 
		ABB siguiente = this;
		while (siguiente != null) {
			actual = siguiente;
			if (nuevo.valor.cmp(siguiente.valor) == -1)
				siguiente = siguiente.arbolIzquierdo;
			else
				siguiente = siguiente.arbolDerecho; //si nuevo.valor == siguiente.valor, se agrega a la derecha
		}
		nuevo.padre = actual;
		if (nuevo.valor.cmp(actual.valor) == -1)
			actual.arbolIzquierdo = nuevo;
		else
			actual.arbolDerecho = nuevo;
	}
	
	public void insertar(Valor v) {
		insertar(new ABB(v));
	}
	
	public static void inorder(ABB arbol, Procesador p) {
		if (arbol != null) {
			inorder(arbol.arbolIzquierdo, p);
			p.procesar(arbol.valor);
			inorder(arbol.arbolDerecho, p);
		}
	}

	public static ABB transplantar(ABB raiz, ABB viejo, ABB nuevo) {
		ABB padre = viejo.padre;
		if (raiz == viejo)
			raiz = nuevo;		
		else if (viejo == padre.arbolIzquierdo)
			padre.arbolIzquierdo = nuevo;
		else
			padre.arbolDerecho = nuevo;
		if (nuevo != null)
			nuevo.padre = padre;
		return raiz;
	}

	//  "this" debe ser la raiz del arbol
	public ABB borrar(ABB abb) {
		ABB raiz = this;
		if (abb.arbolIzquierdo == null) {
			raiz = transplantar(raiz, abb, abb.arbolDerecho);
		} else if (abb.arbolDerecho == null) {
			raiz = transplantar(raiz, abb, abb.arbolIzquierdo);
		} else {
			ABB min = abb.arbolDerecho.minimo();
			if (min.padre != abb) {
				raiz = transplantar(raiz, min, min.arbolDerecho);
				min.arbolDerecho = abb.arbolDerecho;
				min.arbolDerecho.padre = min;
			}
			raiz = transplantar(raiz, abb, min);			
			min.arbolIzquierdo = abb.arbolIzquierdo;
			min.arbolIzquierdo.padre = min;
		}
		return raiz;
	}
	
	public void map(Procesador procesador) {
		procesador.preProcesar();
		ABB.inorder(this, procesador);
		procesador.postProcesar();
	}
	
	public void log() {
		map(new ProcesadorImprimir());
	}
		
	public static void main(String[] args) {
		/*
		Random rand = new Random();
		ABB raiz = new ABB(new Valor(rand.nextInt(1000)));
		System.out.println("raiz: " + raiz.getValor().valor);
		for (int i = 0; i < 10; i++) {
			raiz.insertar(new Valor(rand.nextInt(1000)));
		}
		System.out.println("min: " + raiz.minimo().getValor().valor);
		System.out.println("min: " + raiz.maximo().getValor().valor);
		raiz.log();
		*/
		/*
		raiz.borrar(raiz.minimo()); 
		System.out.println("borrando el minimo, nuevo min: " + raiz.minimo().getValor().valor);
		raiz.map(procesador);
		raiz.borrar(raiz.maximo());
		System.out.println("borrando el maximo, nuevo max: " + raiz.maximo().getValor().valor);
		raiz.map(procesador);
		*/
		ABB raiz = new ABB(new Valor(10));
		raiz.insertar(new Valor(8));
		ABB v = new ABB(new Valor(13));
		raiz.insertar(v);
		raiz.insertar(new Valor(11));
		raiz.insertar(new Valor(12));
		raiz.insertar(new Valor(15));
		raiz.log();
		raiz = raiz.borrar(raiz);
		raiz.log();
	}
}
