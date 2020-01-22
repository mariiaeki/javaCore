package lection;

import java.util.Arrays;

public class Recursion {
    public static void main(String[] args) {
        int arr[] = getBiggestArea(new int[]{1680, 640});
        System.out.println(Arrays.toString(arr));

        arr = getBiggestArea(new int[]{1680, 640});
        System.out.println(Arrays.toString(arr));

        //  test("s","sdsd");
        String[] ss = new String[]{null};
        test("s","d");
    }

    public static void test(String s, String... args) {
        // String s = args[0];
        for (String a : args) {
        }
    }

    public static int[] getBiggestArea(int[] dimensions) {
        int[] sorted = getSorted(dimensions);
        if (isCratnaya(dimensions)) {
            return new int[]{sorted[1], sorted[1]};
        } else {
            int[] newDimensions = new int[2];
            newDimensions[0] = sorted[1];
            newDimensions[1] = sorted[0] % sorted[1];
            return getBiggestArea(newDimensions);
        }
    }

    private static boolean isCratnaya(int[] dimensions) {
        int[] sorted = dimensions;
        return sorted[0] % sorted[1] == 0;
    }

    private static int[] getSorted(int[] dimensions) {
        int[] result = new int[2];

        if (dimensions[0] > dimensions[1]) {
            result[0] = dimensions[0];
            result[1] = dimensions[1];
        } else {
            result[1] = dimensions[0];
            result[0] = dimensions[1];
        }

        return result;
    }

}
