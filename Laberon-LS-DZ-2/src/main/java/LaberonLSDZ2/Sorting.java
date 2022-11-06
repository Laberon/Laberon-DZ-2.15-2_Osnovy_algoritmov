package LaberonLSDZ2;

import java.util.Random;
import java.util.function.Consumer;

public class Sorting {
    private static final Random RMD = new Random();

    public static void main(String[] args) {
        double timeForBubbleSort = timeForSort(5, Sorting::sortBubble);
        System.out.println("timeForBubbleSort = " + timeForBubbleSort);
        double timeForSelectSort = timeForSort(5, Sorting::sortSelect);
        System.out.println("timeForSelectSort = " + timeForSelectSort);
        double timeForInsertSort = timeForSort(5, Sorting::sortInsert);
        System.out.println("timeForInsertSort = " + timeForInsertSort);
    }

    private static double timeForSort(int iteration, Consumer<int[]> sorting) {
        double sum = 0;
        for (int i = 0; i < iteration; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sorting.accept(array);
            long end = System.currentTimeMillis() - start;
            sum += end;
        }
        return sum / iteration;
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RMD.nextInt(-100, 100);
        }
        return array;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElement(arr, j, j + 1);
                }
                }
        }
    }

    private static void swapElement(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortSelect(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minElementIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIdx]) {
                    minElementIdx = j;
                }
            }
            swapElement(arr, i, minElementIdx);
        }
    }

    private static void sortInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = 1;
            while (j > 0 && arr[j - 1] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }
}
