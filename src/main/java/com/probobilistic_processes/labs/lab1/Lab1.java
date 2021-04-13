package com.probobilistic_processes.labs.lab1;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Lab1 {

    public static double M_rivnomirne(double n) {
        return (n + 1) / 2;
    }

    public static double D_rivnomirne(double n) {
        return (n * n - 1) / 12;
    }

    public static double M(int[] x, double[] p) {
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * p[i];
        }
        return result;
    }

    public static double D(int[] x, double[] p, double M) {
        double M_x_2 = 0;
        for (int i = 0; i < x.length; i++) {
            M_x_2 += x[i] * x[i] * p[i];
        }
        return M_x_2 - M * M;
    }

    public static int[] randomGenerator(int min, int max, int n) {
        int[] i = new int[n];
        Random random = new Random();

        for (int j = 0; j < i.length; j++) {
            i[j] = random.nextInt(max - min + 1) + min;
        }
        return i;
    }

    public static int duplications(int[] data, int i) {
        int count = 0;
        for (int j = 0; j < data.length; j++) {
            if (data[j] == i) {
                count++;
            }
        }
        return count;
    }

    public static double p(double k, int n) {
        return k / n;
    }

    public static void start(Stage stage){
        int min = 1;
        int max = 6;
        Scanner scanner = new Scanner(System.in);

        System.out.println(M_rivnomirne(6));
        System.out.printf("%.2f\n", D_rivnomirne(6));
        System.out.println("Input n:");
        int n = scanner.nextInt();

        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final BarChart<String, Number> bc =
                new BarChart<>(xAxis, yAxis);
        XYChart.Series series1 = new XYChart.Series();

        int[] data = randomGenerator(min, max, n);

        double[] p = new double[max];
        int[] d = new int[max];
        int[] x = {1, 2, 3, 4, 5, 6};

        for (int i = min; i <= max; i++) {
            d[i - 1] = duplications(data, i);
            series1.getData().add(new XYChart.Data(Integer.toString(i), d[i - 1]));
        }

        System.out.print("Ймовірності: ");
        for (int i = 0; i < p.length; i++) {
            p[i] = p(d[i], n);
            System.out.print(p[i] + " ");
        }

        double M = M(x, p);
        System.out.printf("\nM: %.2f", M);
        System.out.printf("\nD: %.2f", D(x, p, M));

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
}
