package strings.msd;

import java.util.Arrays;

public class MostSignificantDigitFirst {

    private static final int LIST_THRESHOLD = 3;
    private static final int RADIX = 256;

    public static void main(String[] args) {
        var input = new String[]{
                " re",
                " nt",
                "return",
                "bribery",
                "modest",
                "program",
                "are",
                "anyone",
                "anybody",
                "race",
                "jewel",
                "apollo",
                "fine",
                "count",
                "abs",
                "compete",
                "crime"
        };

        sort(input, 0, input.length, 0);
    }

    private static int getKey(String s, int pos) {
        return s.length() > pos ? s.charAt(pos) : -1;
    }

    private static void insertionSort(String[] input, int listLo, int listHi, int charIndex) {
        for (var i = listLo; i < listHi; i++) {
            for (var j = i; j > listLo && less(input, j, j - 1, charIndex); j--) {
                exchange(input, j, j - 1);
            }
        }
    }

    private static boolean less(String[] input, int i, int j, int charIndex) {
        return input[i].substring(charIndex).compareTo(input[j].substring(charIndex)) < 0;
    }

    private static void exchange(String[] input, int i, int j) {
        var t = input[i];
        input[i] = input[j];
        input[j] = t;
        System.out.println(Arrays.toString(input));
    }

    private static void sort(String[] input, int listLo, int listHi, int charIndex) {

        if (listLo + LIST_THRESHOLD >= listHi - 1) {
            insertionSort(input, listLo, listHi, charIndex);
            return;
        }

        var counts = new int[RADIX + 2];

        for (var i = listLo; i < listHi; i++) {
            var s = input[i];
            var key = getKey(s, charIndex);
            counts[key + 2]++;
        }

        for (var i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        var aux = new String[input.length];

        for (var i = listLo; i < listHi; i++) {
            var s = input[i];
            var key = getKey(s, charIndex);
            var index = counts[key + 1]++;
            aux[listLo + index] = s;
        }

        for (var i = listLo; i < listHi; i++) {
            input[i] = aux[i];
        }

        System.out.println(Arrays.toString(input));

        for (var i = 0; i < RADIX; i++) {
            sort(input, listLo + counts[i], listLo + counts[i + 1], charIndex + 1);
        }
    }
}
