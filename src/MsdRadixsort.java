import java.util.Arrays;

public class MsdRadixsort {
    public static boolean isSorted(int[] array) {
        for (int k = 0; k < array.length - 1; k++) {
            if (array[k] < array[k + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && key > arr[j]) {
                // For ascending order, change key> arr[j] to key< arr[j].
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
    }

    public static int bthComponent(int number, int b) {
        return (int) (number / Math.pow(10, b)) % 10;
    }

    public static void msdRadix(int[] data, int l, int r, int b) {
        if (l >= r) {
            return;
        }

        int[] count = new int[11];
        int[] count2 = new int[11];
        int[] temp = new int[r - l + 1];

        for (int i = l; i < r + 1; i++) {
            count[bthComponent(data[i], b)]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < count2.length; i++) {
            count2[i]=count[i];
        }

        System.out.println("count: " + Arrays.toString(count));

        for (int i = l; i < r + 1; i++) {
            temp[temp.length - 1 - (count[bthComponent(data[i], b)] - 1)] = data[i];
            count[bthComponent(data[i], b)]--;
        }

        for (int i = l; i < r + 1; i++) {
            int j = 0;
            if(j<temp.length){
                data[i] = temp[j];
                j++;
            }
        }

        for (int i = 0; i < count.length-1; i++) {
//            if (i == 0) {
//                msdRadix(data, l + count[i], l + count[i + 1], b - 1);
//            } else {
                msdRadix(data, data.length - l - count2[i], data.length-1 - l - count[i], b - 1);
//            }
        }
    }

    public static void msdRadix(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        // groesste Exponent
        int bMax = (int) Math.floor((Math.log10(Math.abs(max))) /*/(Math.log10(256))*/);
        System.out.println("bMax: " + bMax);

        msdRadix(data, 0, data.length - 1, bMax);
    }

    public static void main(String[] args) {
//        //Eingabe der Werte
//        Scanner input = new Scanner(System.in);
//        ArrayList<Integer> tempArray = new ArrayList<>();
//
//        //Werte in ArrayList speichern
//        while (input.hasNext()) {
//            try {
//                int value = Integer.parseInt(input.nextLine());
//                tempArray.add(value);
//            } catch (NumberFormatException e) {
//                System.err.println("Input list contains a non-number.");
//                return;
//            }
//        }
//
//        if (tempArray.size() == 0) {
//            System.out.println("Bitte mindestens ein integer eingeben.");
//            return;
//        }
//
//        //Werte in Array convertieren
//        int[] data = new int[tempArray.size()];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = (int) tempArray.get(i);
//        }

        //einlesen
        int[] data = {123, 134, 234, 222, 225, 345};
        System.out.println("Before sorting: " + Arrays.toString(data));
//        insertionSort(data);
//        System.out.println(Arrays.toString(data));
        msdRadix(data);
        System.out.println("After sorting: " + Arrays.toString(data));

        assert isSorted(data);
    }
}
