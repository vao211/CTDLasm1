
public class test {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        for (int i = arr.length - 1; i > -1; i--) {
            for (int j = arr.length - 1; j > -1; j--) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 1, 25, 12, 22, 11, 1, 90, 0};
        sort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\n\n");
        sort2(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
