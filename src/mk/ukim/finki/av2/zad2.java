package mk.ukim.finki.av2;

public class zad2 {
    public static double sum(double [][] a){
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sum+=a[i][j];
            }
        }
        return sum;
    }

    public static double average(double [][] a){
        double sum = sum(a);
        double lgt = 0;
        for (int i = 0; i < a.length; i++) {
            lgt += a[i].length;
        }
        return sum/lgt;
    }

    public static void main(String[] args) {
        double [][] matrix = {{1,2,3,4,5}, {6,7,8,8,9,10,11}};
        System.out.println(sum(matrix));
        System.out.printf("%.2f",average(matrix));
    }
}
