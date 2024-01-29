package algoritmi.vtorKolokvium;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Weather {

    private static class Par {
        String start;
        String end;
        Double stepeni;

        public Par(String start, String end, Double stepeni) {
            this.start = start;
            this.end = end;
            this.stepeni = stepeni;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public Double getStepeni() {
            return stepeni;
        }

        public void setStepeni(Double stepeni) {
            this.stepeni = stepeni;
        }
    }

    public static void main(String[] args) {
        Hashtable<String, List<Par>> table = new Hashtable<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String grad = scanner.next();
            String start = scanner.next();
            String end = scanner.next();
            double stepeni = scanner.nextDouble();
            List<Par> pars = table.get(grad);

            if (pars == null) {
                pars = new ArrayList<>();
                table.put(grad, pars);
            }

            Par e = new Par(start, end, stepeni);
            int i1 = pars.indexOf(e);
            if (i1 == -1) {
                pars.add(e);
            } else {
                Par staroMerenje = pars.get(i1);
                staroMerenje.setStepeni((staroMerenje.stepeni + e.stepeni) / 2);
            }
        }
        String grad = scanner.next();
        List<Par> find = table.get(grad);
        if (find == null) {
            System.out.println(grad + ": does not exist");
        } else {
            System.out.println(grad + ": ");
            for (Par par : find) {
                System.out.println(par.start + "-" + par.end + " " + par.stepeni);
            }
        }
    }
}
