import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LsdRadixsort {

    public static boolean isSorted(int[] array) {
        for (int k = 0; k < array.length - 1; k++) {
            if (array[k] < array[k + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int bthComponent(int number, int b) {
        return (int) (number / Math.pow(256, b)) % 256;
    }

    public static int getMaxComponent(int[] componentArray) {
        return Arrays.stream(componentArray).max().getAsInt();
    }

    public static int getMinComponent(int[] componentArray) {
        return Arrays.stream(componentArray).min().getAsInt();
    }

    public static void sortByByte(int[] data, int l, int r, int b) {
        if (l >= r) {
            return;
        }
        int[] count = new int[r - l + 1];
        int[] result = new int[data.length];

        //Frequenz zählen
        for (int i = 0; i < data.length; i++) {
            count[bthComponent(data[i], b) - l]++;
        }

        //Position jedes verschiedenen Wertes reservieren
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < data.length; i++) { //hab mehrmals probiert, weiss nicht warum es geht :)))
            result[(result.length - 1) - (count[bthComponent(data[i], b) - l] - 1)] = data[i];
            count[bthComponent(data[i], b) - l]--;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = result[i];
        }
    }

    public static void lsdRadix(int[] data) {
        int[] componentArray = new int[data.length];
        int max = Arrays.stream(data).max().getAsInt();
        // groesste Exponent
        int bMax = (int) Math.floor((Math.log10(Math.abs(max)))/(Math.log10(256)));
        System.out.println("bMax " + bMax);

        for (int b = 0; b < bMax+1; b++) {
            for (int i = 0; i < componentArray.length; i++) {
                componentArray[i] = bthComponent(data[i], b);
            }
            sortByByte(data, getMinComponent(componentArray), getMaxComponent(componentArray), b);
        }
    }

    public static void main(String[] args) {
        //Eingabe der Werte
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> tempArray = new ArrayList<>();

        //Werte in ArrayList speichern
        while (input.hasNext()) {
            try {
                int value = Integer.parseInt(input.nextLine());
                tempArray.add(value);
            } catch (NumberFormatException e) {
                System.err.println("Input list contains a non-number.");
                return;
            }
        }

        if (tempArray.size() == 0) {
            System.out.println("Bitte mindestens ein integer eingeben.");
            return;
        }

        //Werte in Array convertieren
        int[] data = new int[tempArray.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) tempArray.get(i);
        }

        //einlesen
        System.out.println("Before sorting: " + Arrays.toString(data));
        lsdRadix(data);
        System.out.println("After sorting: " + Arrays.toString(data));

        assert isSorted(data);
    }
}
