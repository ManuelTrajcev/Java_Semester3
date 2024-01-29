package mk.ukim.finki.av5;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class MyMathClass {

    public static void printStatistics(List<? extends Number> numbers) {
        DoubleSummaryStatistics doubleSummaryStatistics =
                numbers.stream().mapToDouble(Number::doubleValue).summaryStatistics();
        System.out.println(doubleSummaryStatistics.getMin());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getSum());

        double sum = 0;
        for (Number n :
                numbers) {
            sum += (n.doubleValue() - doubleSummaryStatistics.getAverage()) *
                    (n.doubleValue() - doubleSummaryStatistics.getAverage());
        }
    }
}
