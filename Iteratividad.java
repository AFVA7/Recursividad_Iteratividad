package punto4;

import java.util.ArrayList;
import java.util.List;

public class Iteratividad {

	public static void main(String[] args) {

		try {

			// punto 4.1
			matrizEnEspiral(5);
			System.out.println("Tiempo en milisegundos: " + System.currentTimeMillis());
			System.out.println("Tiempo en nanosegundos: " + System.nanoTime());

			// punto 4.2
			int matrizCuadradoPerfecto[][] = { { 4, 8 }, { 12, 4 } };
			imprimirMatriz(cuadradoPerfecto(matrizCuadradoPerfecto));
			System.out.println("Tiempo en milisegundos: " + System.currentTimeMillis());
			System.out.println("Tiempo en nanosegundos: " + System.nanoTime());

			// punto 4.3
			String[][] matrizPalabrasSimilares = { { "cama", "acma" }, { "oso", "roso" }, { "tela", "late" } };
			System.out.println(palabrasSimilaresIterativo(matrizPalabrasSimilares));
			System.out.println("Tiempo en milisegundos: " + System.currentTimeMillis());
			System.out.println("Tiempo en nanosegundos: " + System.nanoTime());

			// punto 4.4
			String[][] matrizEncadenamiento = { { "Sien", "encima", "mapa" }, { "Pata", "tapa", "papa" },
					{ "Pato", "toma", "mama" }, };
			System.out.println(encadenamientoPalabras(matrizEncadenamiento));
			System.out.println("Tiempo en milisegundos: " + System.currentTimeMillis());
			System.out.println("Tiempo en nanosegundos: " + System.nanoTime());

			// punto 4.5
			int[][] matrizPolidivisibles = { { 2016, 3029 }, { 4233, 381654729 } };
			System.out.println(numerosPolidivisibles(matrizPolidivisibles));
			System.out.println("Tiempo en milisegundos: " + System.currentTimeMillis());
			System.out.println("Tiempo en nanosegundos: " + System.nanoTime());
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
		int contador = 1;
		int inicio = 0;
		int limite = n - 1;
		while (contador <= n * n) {
			for (int i = inicio; i <= limite; i++) {
				matriz[i][inicio] = contador++;
			}
			for (int i = inicio + 1; i <= limite; i++) {
				matriz[limite][i] = contador++;
			}
			for (int i = limite - 1; i >= inicio; i--) {
				matriz[i][limite] = contador++;
			}
			for (int i = limite - 1; i >= inicio + 1; i--) {
				matriz[inicio][i] = contador++;
			}
			inicio++;
			limite--;

		}
		imprimirMatriz(matriz);
	}

	/**
	 * Calcula los cuadrados perfectos para cada elemento de una matriz dada.
	 * 
	 * @param matriz La matriz de entrada.
	 * @return Una nueva matriz con los numeros a multiplicar para que sean cuadrados perfectos.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static int[][] cuadradoPerfecto(int[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		int[][] resultado = new int[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				resultado[i][j] = encontrarMultiplicador(matriz[i][j]);
			}
		}

		return resultado;
	}

	private static int encontrarMultiplicador(int n) {
		if (esCuadradoPerfecto(n)) {
			return 1;
		}
		int multiplicador = 2;
		while (true) {
			int cuadrado = n * multiplicador;
			if (esCuadradoPerfecto(cuadrado)) {
				return multiplicador;
			}
			multiplicador++;
		}
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
	public static List<Integer> palabrasSimilaresIterativo(String[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		List<Integer> vector = new ArrayList<>();

		for (int i = 0; i < matriz.length; i++) {
			String p1 = matriz[i][0];
			String p2 = matriz[i][1];
			if (sonIguales(p1, p2)) {
				vector.add(i);
			}
		}

		return vector;
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
	 * @return true si las palabras están encadenadas correctamente, false en caso contrario.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static boolean encadenamientoPalabras(String[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		String ultimasDosLetras = "";
		String primerasDosLetras = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length - 1; j++) {
				ultimasDosLetras = matriz[i][j].substring(matriz[i][j].length() - 2);
				primerasDosLetras = matriz[i][j + 1].substring(0, 2);

				if (!ultimasDosLetras.equalsIgnoreCase(primerasDosLetras)) {
					return false;
				}
			}
			if (i < matriz.length - 1) {
				ultimasDosLetras = matriz[i][matriz[i].length - 1]
						.substring(matriz[i][matriz[i].length - 1].length() - 2);
				primerasDosLetras = matriz[i + 1][0].substring(0, 2);

				if (!ultimasDosLetras.equalsIgnoreCase(primerasDosLetras)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Encuentra los números polidivisibles en una matriz de enteros.
	 * 
	 * @param matriz La matriz de enteros.
	 * @return Una lista de números polidivisibles.
	 * @throws IllegalArgumentException Si la matriz de entrada está vacía.
	 */
	public static List<Integer> numerosPolidivisibles(int[][] matriz) throws IllegalArgumentException {
		if (matriz.length == 0) {
			throw new IllegalArgumentException("debe ingresar una matriz con valores");
		}
		List<Integer> arreglo = new ArrayList<>();
		for (int[] fila : matriz) {
			for (int num : fila) {
				if (esPolidivisible(num)) {
					arreglo.add(num);
				}
			}
		}
		return arreglo;
	}

	private static boolean esPolidivisible(int num) {
		if (num <= 0) {
			return false;
		}

		String numStr = String.valueOf(num);
		int divisor = 1;

		for (int i = 0; i < numStr.length(); i++) {
			int subNum = Integer.parseInt(numStr.substring(0, i + 1));
			if (subNum % divisor != 0) {
				return false;
			}
			divisor++;
		}

		return true;
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
