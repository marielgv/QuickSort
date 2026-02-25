import java.util.Random;
import java.util.Arrays;

public class QuickSortTest {

    /**
     * Método principal que ejecuta los escenarios de prueba
     */
    public static void main(String[] args) {

        int size = 10000; // Tamaño del arreglo (puedes modificarlo)

        // ============================
        // ESCENARIO 1: Arreglo Aleatorio
        // ============================
        int[] randomArray = generateRandomArray(size);
        int[] copyRandom = Arrays.copyOf(randomArray, randomArray.length);

        long startRandom = System.nanoTime();
        quickSort(copyRandom, 0, copyRandom.length - 1);
        long endRandom = System.nanoTime();
        long timeRandom = endRandom - startRandom;

        // ============================
        // ESCENARIO 2: Arreglo Ordenado
        // ============================
        int[] sortedArray = generateSortedArray(size);
        int[] copySorted = Arrays.copyOf(sortedArray, sortedArray.length);

        long startSorted = System.nanoTime();
        quickSort(copySorted, 0, copySorted.length - 1);
        long endSorted = System.nanoTime();
        long timeSorted = endSorted - startSorted;

        // ============================
        // ESCENARIO 3: Arreglo Inversamente Ordenado
        // ============================
        int[] reverseArray = generateReverseSortedArray(size);
        int[] copyReverse = Arrays.copyOf(reverseArray, reverseArray.length);

        long startReverse = System.nanoTime();
        quickSort(copyReverse, 0, copyReverse.length - 1);
        long endReverse = System.nanoTime();
        long timeReverse = endReverse - startReverse;

        // ============================
        // Mostrar resultados
        // ============================
        System.out.println("RESULTADOS (nanosegundos)");
        System.out.println("-----------------------------------");
        System.out.println("Arreglo Aleatorio: " + timeRandom);
        System.out.println("Arreglo Ordenado: " + timeSorted);
        System.out.println("Arreglo Inverso: " + timeReverse);
    }

    /**
     * Implementación del algoritmo QuickSort utilizando
     * el esquema de partición de Lomuto.
     *
     * @param arr  Arreglo a ordenar
     * @param low  Índice inicial
     * @param high Índice final
     */
    public static void quickSort(int[] arr, int low, int high) {

        // Caso base: si low es menor que high, aún hay elementos por ordenar
        if (low < high) {

            // pi es el índice donde el pivote queda ubicado correctamente
            int pi = partition(arr, low, high);

            // Ordenar recursivamente los elementos antes del pivote
            quickSort(arr, low, pi - 1);

            // Ordenar recursivamente los elementos después del pivote
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Método de partición usando el esquema de Lomuto.
     *
     * El pivote se toma como el último elemento del arreglo.
     * Los elementos menores que el pivote se colocan a la izquierda
     * y los mayores a la derecha.
     *
     * @param arr  Arreglo
     * @param low  Índice inicial
     * @param high Índice final (donde está el pivote)
     * @return índice final del pivote
     */
    private static int partition(int[] arr, int low, int high) {

        // Elegimos el último elemento como pivote
        int pivot = arr[high];

        // i será el índice del elemento menor
        int i = low - 1;

        // Recorremos el arreglo desde low hasta high - 1
        for (int j = low; j < high; j++) {

            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {

                i++; // Incrementamos el índice del elemento menor

                // Intercambiamos arr[i] con arr[j]
                swap(arr, i, j);
            }
        }

        // Colocamos el pivote en su posición correcta
        swap(arr, i + 1, high);

        return i + 1;
    }

    /**
     * Método auxiliar para intercambiar dos posiciones del arreglo
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ============================
    // Métodos generadores de arreglos
    // ============================

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
}