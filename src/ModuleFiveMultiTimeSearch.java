import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

/**
 * This program will creater a HUGE array of integers and seed it
 * with random values. After this is completed it will then make
 * three two additional copies of the random array of integer values
 * and pass them to three different sorting algorithms: InsertSort,
 * QuickSort, and MergeSort. The running time of each algorithm will be
 * recorded and output into a file in the solution home directory. The file
 * name will be called "out.txt." It will be delimited with a ; for easy
 * transfer into an Excel spreadsheet for graphing purposes.
 *
 * @author Luis Cortez (lac0084@auburn.edu)
 * @version 0411191448
 */
public class ModuleFiveMultiTimeSearch {

    /**
     * Main function to drive merge quickSort algorithm.
     * @param args not used
     * @throws FileNotFoundException for file output
     */
    public static void main(String[] args) throws FileNotFoundException {


        PrintStream out = new PrintStream(new File("out.txt"));
        Random randomNumber = new Random();

        int[] arrayG = new int[0xffffff];
        for (int i = 0; i < arrayG.length; i++) {
            arrayG[i] = Math.abs(randomNumber.nextInt());
        }

        for (int n = 10; n < arrayG.length; n += 1000) {
            int[] mergeSortArray = new int [n];
            int[] insertSortArray = new int [n];
            int[] quickSortArray = new int [n];

            System.arraycopy(arrayG, 0, mergeSortArray,0, n);
            System.arraycopy(arrayG, 0, insertSortArray,0, n);
            System.arraycopy(arrayG, 0, quickSortArray,0, n);


            long startMergeSortTime = System.currentTimeMillis();
            mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
            long finishMergeSortTime = System.currentTimeMillis();
            long mergeSortTime = finishMergeSortTime - startMergeSortTime;

            long startInsertSortTime = System.currentTimeMillis();
            insertSort(insertSortArray);
            long finishInsertSortTime = System.currentTimeMillis();
            long insertSortTime = finishInsertSortTime - startInsertSortTime;

            long startQuickSortTime = System.currentTimeMillis();
            quickSort(quickSortArray,0, quickSortArray.length - 1);
            long finishQuickSortTime = System.currentTimeMillis();
            long quickSortTime = finishQuickSortTime - startQuickSortTime;

//            double tnSquare = ((double) mergeSortTime * n) / ((double) n * (double) n);
//            PrintStream console = System.out;
//            System.setOut(out);
//            System.out.println(n + ";" + tnSquare);
            /**
             * For console output uncomment the next two lines.
             */
            PrintStream console = System.out;
            System.setOut(console);
            System.out.println(n + ";" + mergeSortTime + ";" + n + ";"
                    + insertSortTime + ";" + n + ";" + quickSortTime);
        }
        out.close();

    }

    /**
     * This method is the mergeSort algorithm.
     *
     * @param A - an array of integers to be sorted in random value
     * @param p - left hand limit/index of values starting at 0
     * @param r - right hand limit/index defined by value of A.length
     */
    private static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (int) Math.floor((p + r) / 2);
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = A[q + 1 + j];
        }

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i = i + 1;
            }

            else {
                A[k] = R[j];
                j = j + 1;
            }
        }
    }

    /**
     * InsertSort algorithm to perform quickSort function.
     * @param A - an array of integers to quickSort
     */
    private static void insertSort(int[] A) {
        int R = A.length;
        for (int i = 0; i < R - 1; ++i) {
            int key = A[i];

            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }

    /**
     * QuickSort sorting algorithm - consist of next two methods.
     * quickSort method use the partitionIndex method to recursively
     * call itself until sort is complete.
     * @param A - an array of integers to be sorted
     * @param left - left hand index/lowest index
     * @param right - right hand index/highest index
     */
    private static void quickSort(int A[], int left, int right) {
        if (left < right) {
            int partitionIndex = partition(A, left, right);


            quickSort(A, left, partitionIndex - 1);
            quickSort(A, partitionIndex + 1, right);
        }
    }



    private static int partition(int[] A, int left, int right)
    {
        int pivot = A[right];
        int i = (left - 1);
        for (int j = left; j < right; j++)
        {

            if (A[j] <= pivot)
            {
                i++;

                int buffer = A[i];
                A[i] = A[j];
                A[j] = buffer;
            }
        }

        int buffer = A[i + 1];
        A[i + 1] = A[right];
        A[right] = buffer;

        return i + 1;
    }





}








