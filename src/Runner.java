import java.util.Scanner;

//В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно.
// Следующие n строк содержат по два целых числа ai и bi (ai≤ib) — координаты концов отрезков. Последняя строка содержит
// mm целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю. Точка считается принадлежащей отрезку,
// если она находится внутри него или на границе. Для каждой точки в порядке появления во вводе выведите, скольким отрезкам
// она принадлежит

//Test Input:
//2 3
//0 5
//7 10
//1 6 11
//Output: 1 0 0

class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] leftEdge = new int[n];
        int[] rightEdge = new int[n];


        for (int i = 0; i < n; i++) {
            leftEdge[i] = scanner.nextInt();
            rightEdge[i] = scanner.nextInt();
        }

        QuickSort quickSort = new QuickSort(leftEdge);
        leftEdge = quickSort.sort(0, n-1);
        quickSort.setA(rightEdge);
        rightEdge = quickSort.sort(0, n-1);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int A = scanner.nextInt();
            int N = goodValuesInLeftEdges(leftEdge, A);
            int M = goodValuesInRightEdges(rightEdge, A);

            stringBuilder.append(N-M).append(" ");
        }

        System.out.println(stringBuilder);
    }

    public static int goodValuesInRightEdges(int[] array, int desired){
        int iterations = 0;
        for (int elem : array) {
            if (desired > elem){
                iterations++;
            } else {
                break;
            }
        }
        return iterations;
    }

    public static int goodValuesInLeftEdges(int[] array, int desired){
        int iterations = 0;
        for (int elem : array) {
            if(desired >= elem){
                iterations++;
            } else {
                break;
            }
        }
        return iterations;
    }
}

class QuickSort{
    public int[] A;

    public QuickSort(int[] A) {
        this.A = A;
    }

    public int[] sort(int l, int r){
        while (l<=r){
            int m = partition(l,r);
            sort(l, m-1);
            l = m + 1;
        }
        return A;
    }

    public int partition(int l, int r){
        int index = rnd(l, r);

        swap(l, index);

        index = l;
        int x = A[l];

        if (r > 2) {
            l++;
        }


        while (l <= r){
            while (A[l] < x && l < r){
                l++;
            }
            while (A[r] > x && r >= l){
                r--;
            }
            if(l <= r){
                swap(l++, r--);
            }
        }

        l--;
        swap(index, l);

        return l;
    }

    public void swap(int first, int second){
        int buffer = A[first];
        A[first] = A[second];
        A[second] = buffer;
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public void setA(int[] a) {
        A = a;
    }
}