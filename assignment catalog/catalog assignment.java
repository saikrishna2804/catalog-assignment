import java.util.*;
import java.math.BigInteger;

public class ShamirSecretSharing {

    // Function to decode value based on base
    private static double decodeValue(String value, int base) {
        return new BigInteger(value, base).doubleValue();
    }

    // Function to extract (x, y) points from the JSON-like structure
    private static List<double[]> extractPoints(Map<String, Map<String, String>> data, int n) {
        List<double[]> points = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (data.containsKey(String.valueOf(i))) {
                Map<String, String> pointData = data.get(String.valueOf(i));
                int x = i;
                int base = Integer.parseInt(pointData.get("base"));
                String value = pointData.get("value");
                double y = decodeValue(value, base);
                points.add(new double[] { x, y });
            }
        }
        return points;
    }

    // Lagrange interpolation to find the constant term 'c'
    private static double lagrangeInterpolation(List<double[]> points, int k) {
        double secret = 0;
        int n = points.size();

        for (int i = 0; i < k; i++) {
            double xi = points.get(i)[0];
            double yi = points.get(i)[1];
            double li = 1;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    double xj = points.get(j)[0];
                    li *= (0 - xj) / (xi - xj);
                }
            }
            secret += yi * li;
        }
        return secret;
    }

    // Function to simulate the input data
    private static Map<String, Map<String, String>> getTestCase1() {
        Map<String, Map<String, String>> testCase1 = new HashMap<>();

        Map<String, String> keys = new HashMap<>();
        keys.put("n", "4");
        keys.put("k", "3");
        testCase1.put("keys", keys);

        Map<String, String> point1 = new HashMap<>();
        point1.put("base", "10");
        point1.put("value", "4");
        testCase1.put("1", point1);

        Map<String, String> point2 = new HashMap<>();
        point2.put("base", "2");
        point2.put("value", "111");
        testCase1.put("2", point2);

        Map<String, String> point3 = new HashMap<>();
        point3.put("base", "10");
        point3.put("value", "12");
        testCase1.put("3", point3);

        Map<String, String> point6 = new HashMap<>();
        point6.put("base", "4");
        point6.put("value", "213");
        testCase1.put("6", point6);

        return testCase1;
    }

    private static Map<String, Map<String, String>> getTestCase2() {
        Map<String, Map<String, String>> testCase2 = new HashMap<>();

        Map<String, String> keys = new HashMap<>();
        keys.put("n", "9");
        keys.put("k", "6");
        testCase2.put("keys", keys);

        Map<String, String> point1 = new HashMap<>();
        point1.put("base", "10");
        point1.put("value", "28735619723837");
        testCase2.put("1", point1);

        Map<String, String> point2 = new HashMap<>();
        point2.put("base", "16");
        point2.put("value", "1A228867F0CA");
        testCase2.put("2", point2);

        Map<String, String> point3 = new HashMap<>();
        point3.put("base", "12");
        point3.put("value", "32811A4AA0B7B");
        testCase2.put("3", point3);

        Map<String, String> point4 = new HashMap<>();
        point4.put("base", "11");
        point4.put("value", "917978721331A");
        testCase2.put("4", point4);

        Map<String, String> point5 = new HashMap<>();
        point5.put("base", "16");
        point5.put("value", "1A22886782E1");
        testCase2.put("5", point5);

        Map<String, String> point6 = new HashMap<>();
        point6.put("base", "10");
        point6.put("value", "28735619654702");
        testCase2.put("6", point6);

        Map<String, String> point7 = new HashMap<>();
        point7.put("base", "14");
        point7.put("value", "71AB5070CC4B");
        testCase2.put("7", point7);

        Map<String, String> point8 = new HashMap<>();
        point8.put("base", "9");
        point8.put("value", "122662581541670");
        testCase2.put("8", point8);

        Map<String, String> point9 = new HashMap<>();
        point9.put("base", "8");
        point9.put("value", "642121030037605");
        testCase2.put("9", point9);

        return testCase2;
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Get test case 1 data
        Map<String, Map<String, String>> testCase1 = getTestCase1();
        int n1 = Integer.parseInt(testCase1.get("keys").get("n"));
        int k1 = Integer.parseInt(testCase1.get("keys").get("k"));
        List<double[]> points1 = extractPoints(testCase1, n1);
        double secret1 = lagrangeInterpolation(points1, k1);
        System.out.println("Secret for Test Case 1: " + secret1);

        // Get test case 2 data
        Map<String, Map<String, String>> testCase2 = getTestCase2();
        int n2 = Integer.parseInt(testCase2.get("keys").get("n"));
        int k2 = Integer.parseInt(testCase2.get("keys").get("k"));
        List<double[]> points2 = extractPoints(testCase2, n2);
        double secret2 = lagrangeInterpolation(points2, k2);
        System.out.println("Secret for Test Case 2: " + secret2);
    }
}
