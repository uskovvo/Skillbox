public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.PI * radius * radius;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return 4 * (Math.PI * Math.abs(Math.pow(radius, 3))) / 3;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        return ((a + b > c) && (a + c > b) && (b + c > a)) && (a > 0 && b > 0 && c > 0);
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (!isTrianglePossible(a, b, c)) {
            return -1;
        }
        double p = (a + b+ c) / 2;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
}
