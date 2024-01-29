package algoritmi.labs.lab1;

import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int[] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    @Override
    public String toString() {
        return String.format("%d", getRevenue());
    }

    public int getRevenue() {
        int sum = 0;
        for (int i = 0; i < numOfSales; i++) {
            sum += revenues[i];
        }
        return sum;
    }

}

class SalesPerson {

    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    @Override
    public String toString() {
        String string = name + "   ";
        for (int i = 0; i < 4; i++) {
            string += quarters[i].toString() + "   ";
        }
        return string;
    }

    public int getQuarters() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += quarters[i].getRevenue();
        }
        return sum;
    }

    public String getName() {
        return name;
    }
}


public class Main {

    public static int sumSales(SalesPerson sp) {
        return sp.getQuarters();
    }

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        int max = 0;
        SalesPerson maxPerson = null;
        for (int i = 0; i < arr.length; i++) {
            if (sumSales(arr[i]) > max) {
                max = sumSales(arr[i]);
                maxPerson = arr[i];
            }
        }
        return maxPerson;
    }

    public static void table(SalesPerson[] arr) {
        System.out.println("SP   1   2   3   4   Total");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.println(sumSales(arr[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.nextLine();
        SalesPerson[] arr = new SalesPerson[n];
        int numOfSales;
        String name;


        for (int i = 0; i < n; i++) {
            name = input.nextLine();
            QuarterlySales[] qs = new QuarterlySales[4];

            for (int j = 0; j < 4; j++) {
                numOfSales = input.nextInt();
                int[] revenues = new int[numOfSales];
                for (int k = 0; k < numOfSales; k++) {
                    revenues[k] = input.nextInt();
                }
                QuarterlySales q = new QuarterlySales(numOfSales, revenues, j + 1);
                qs[j] = q;
            }

            arr[i] = new SalesPerson(name, qs);
            input.nextLine();
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}