package punto4;

import java.util.ArrayList;
import java.util.List;

public class Recursividad {

	public static void main(String[] args) {
		try {
			// punto 4.1
			matrizEnEspiral(5);

			// punto 4.2
			int matrizCuadradoPerfecto[][] = { { 4, 8 }, { 12, 4 } };
			cuadradoPerfecto(matrizCuadradoPerfecto);

			// punto 4.3
			String[][] matrizPalabrasSimilares = { { "cama", "acma" }, { "oso", "roso" }, { "tela", "late" } };
			palabrasSimilares(matrizPalabrasSimilares);

			// punto 4.4
			String[][] matrizEncadenamiento = { { "Sien", "encima", "mapa" }, { "Pata", "tapa", "papa" },
					{ "Pato", "toma", "mama" }, };
			encadenamientoPalabras(matrizEncadenamiento);

			// punto 4.5
			int[][] matrizPolidivisibles = { { 2016, 3029 }, { 4233, 381654729 } };
			encontrarPolidivisibles(matrizPolidivisibles);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Genera una matriz en espiral de tamaño n x n.
	 * 
	 * @param n El tamaño de la matriz en filas y columnas.
	 * @throws IllegalArgumentException Si n es menor o igual a cero.
	 */
	public static void matrizEnEspiral(int n) throws IllegalArgumentException {
		if (n <= 0) {
			throw new IllegalArgumentException("El tamaño de la matriz debe ser mayor que cero");
		}
		int[][] matriz = new int[n][n];
		llenarMatrizEnEspiral(matriz, 0, n - 1, 1);
		imprimirMatriz(matriz);
	}

	private static void llenarMatrizEnEspiral(int[][] matriz, int inicio, int limite, int contador) {
		if (inicio > limite) {
			return;
		}
		// Llenar la columna izquierda de abajo hacia arriba
		for (int i = inicio; i <= limite; i++) {
			matriz[i][inicio] = contador++;
		}
		// Llenar la fila inferior de derecha a izquierda
		for (int i = inicio + 1; i <= limite; i++) {
			matriz[limite][i] = contador++;
		}
		// Llenar la columna izquierda de abajo hacia arriba
		for (int i = limite - 1; i >= inicio; i--) {
			matriz[i][limite] = contador++;
		}
		// Llenar la fila superior de izquierda a derecha
		for (int i = limite - 1; i >= inicio + 1; i--) {
			matriz[inicio][i] = contador++;
		}

		// Llenar la siguiente capa de la matriz
		llenarMatrizEnEspiral(matriz, inicio + 1, limite - 1, contador);

	}

	/**
	 * Calcula los cuadrados perfectos para cada elemento de una matriz dada.
	 * 
	 * @param matriz La matriz de entrada.
	 * @return Una nueva matriz con los numeros a multiplicar para que sean
	 *         cuadrados perfectos.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static void cuadradoPerfecto(int[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		int[][] resultado = new int[matriz.length][matriz[0].length];
		resultado = cuadradoPerfectoRecursivo(matriz, 0, 0, resultado);
		imprimirMatriz(resultado);
	}

	private static int[][] cuadradoPerfectoRecursivo(int[][] matriz, int i, int j, int[][] resultado) {
		if (i == matriz.length) {
			return resultado;
		}
		if (j == matriz.length) {
			return cuadradoPerfectoRecursivo(matriz, i + 1, 0, resultado);
		}
		resultado[i][j] = encontrarMultipllicador(matriz[i][j], 2);

		return cuadradoPerfectoRecursivo(matriz, i, j + 1, resultado);
	}

	private static int encontrarMultipllicador(int n, int multiplicador) {
		if (esCuadradoPerfecto(n)) {
			return 1;
		}
		int cuadrado = n * multiplicador;
		if (esCuadradoPerfecto(cuadrado)) {
			return multiplicador;
		}
		return encontrarMultipllicador(n, multiplicador + 1);
	}

	private static boolean esCuadradoPerfecto(int n) {
		int raiz = (int) Math.sqrt(n);
		return raiz * raiz == n;
	}

	/**
	 * Encuentra palabras similares en una matriz de palabras.
	 * 
	 * @param matriz La matriz de palabras.
	 * @return Una lista de índices de filas que contienen palabras similares.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static void palabrasSimilares(String[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		List<Integer> resultado = palabrasSimilares(matriz, new ArrayList<>(), 0);
		System.out.println(resultado);
	}

	public static List<Integer> palabrasSimilares(String[][] matriz, List<Integer> vector, int i) {
		if (i == matriz.length) {
			return vector;
		}
		String p1 = matriz[i][0];
		String p2 = matriz[i][1];
		if (sonIguales(p1, p2)) {
			vector.add(i);
		}
		return palabrasSimilares(matriz, vector, i + 1);
	}

	private static boolean sonIguales(String p1, String p2) {
		if (p1.length() != p2.length()) {
			return false;
		}

		StringBuilder sb2 = new StringBuilder(p2);

		for (char c : p1.toCharArray()) {
			int i = sb2.indexOf(String.valueOf(c));
			if (i == -1) {
				return false;
			}
			sb2.deleteCharAt(i);
		}
		return true;
	}

	/**
	 * Verifica si una matriz de palabras está encadenada correctamente.
	 * 
	 * @param matriz La matriz de palabras.
	 * @return true si las palabras están encadenadas correctamente, false en caso
	 *         contrario.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static void encadenamientoPalabras(String[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		System.out.println(verificarEncadenamiento(matriz, 0, 0));
	}

	private static boolean verificarEncadenamiento(String[][] matriz, int fila, int columna) {
		if (fila == matriz.length || columna == matriz[0].length - 1) {
			return true;
		}

		String palabraActual = matriz[fila][columna];
		String palabraSiguiente = matriz[fila][columna + 1];
		String ultimasDosLetras = palabraActual.substring(palabraActual.length() - 2);
		String primerasDosLetras = palabraSiguiente.substring(0, 2);

		if (!ultimasDosLetras.equalsIgnoreCase(primerasDosLetras)) {
			return false;
		}

		// Verificar encadenamiento para la siguiente columna o fila
		if (columna == matriz[0].length - 2) {
			return verificarEncadenamiento(matriz, fila + 1, 0);
		} else {
			return verificarEncadenamiento(matriz, fila, columna + 1);
		}
	}

	/**
	 * Encuentra los números polidivisibles en una matriz de enteros.
	 * 
	 * @param matriz La matriz de enteros.
	 * @return Una lista de números polidivisibles.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static void encontrarPolidivisibles(int[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		System.out.println(encontrarPolidivisiblesRecursivo(matriz, 0, 0, new ArrayList<>()));

	}

	private static List<Integer> encontrarPolidivisiblesRecursivo(int[][] matriz, int fila, int columna,
			List<Integer> arreglo) {

		if (fila == matriz.length) {
			return arreglo;
		}
		if (columna == matriz[fila].length) {
			return encontrarPolidivisiblesRecursivo(matriz, fila + 1, 0, arreglo);

		}
		if (esPolidivisibleRecursivo(matriz[fila][columna], 1)) {
			arreglo.add(matriz[fila][columna]);
		}
		return encontrarPolidivisiblesRecursivo(matriz, fila, columna + 1, arreglo);
	}

	private static boolean esPolidivisibleRecursivo(int num, int longitud) {
		if (num <= 0) {
			return false;
		}
		String numStr = String.valueOf(num);
		if (longitud > numStr.length()) {
			return true;
		}
		int subNum = Integer.parseInt(numStr.substring(0, longitud));
		if (subNum % longitud != 0) {
			return false;
		}
		return esPolidivisibleRecursivo(num, longitud + 1);
	}

	private static void imprimirMatriz(int matriz[][]) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

}
