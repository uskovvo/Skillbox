import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Arithmetic {
    private int first = 0;
    private int second = 0;

    public Arithmetic(int a, int b) {
        this.first = a;
        this.second = b;
    }

    public void sumNumbers() {
        int sum = this.first + this.second;
        System.out.println("\nСумма чисел a и b: " + sum);
    }

    public void multiplicationNumbers() {
        int multiplication = this.first * this.second;
        System.out.println("Произведения чисел a и b: " + multiplication);
    }

    public void maxOfNumbers() {
        int max = Math.max(this.first, this.second);
        System.out.println("Максимальное из двух чисел a и b: " + max);
    }

    public void minOfNumbers() {
        int min = Math.min(this.first, this.second);
        System.out.println("Минимальное из двух чисел a и b: " + min);
    }
}
