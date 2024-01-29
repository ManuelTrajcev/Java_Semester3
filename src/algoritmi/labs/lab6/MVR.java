package algoritmi.labs.lab6;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


public class MVR {

    static class Gragjanin {
        String imePrezime;
        int lKarta;
        int pasos;
        int vozacka;

        public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
            this.imePrezime = imePrezime;
            this.lKarta = lKarta;
            this.pasos = pasos;
            this.vozacka = vozacka;
        }

        @Override
        public String toString() {
            return imePrezime;
        }

        public String getImePrezime() {
            return imePrezime;
        }

        public int getlKarta() {
            return lKarta;
        }

        public int getPasos() {
            return pasos;
        }

        public int getVozacka() {
            return vozacka;
        }
    }


    public static void main(String[] args) {

        int n;

        Scanner br = new Scanner(System.in);

        Queue<Gragjanin> licniKarti = new ArrayDeque<>();
        Queue<Gragjanin> pasosi = new ArrayDeque<>();
        Queue<Gragjanin> vozacki = new ArrayDeque<>();


        int N = Integer.parseInt(br.nextLine());
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            if (lKarta == 1) {
                licniKarti.add(covek);
            } else if (pasos == 1) {
                pasosi.add(covek);
            } else if (vozacka == 1) {
                vozacki.add(covek);
            }
        }

        n = licniKarti.size();

        for (int i = 0; i < n; i++) {
            if (licniKarti.element().getPasos() == 1) {
                pasosi.add(licniKarti.element());
                licniKarti.remove();
            } else if (licniKarti.element().getVozacka() == 1) {
                vozacki.add(licniKarti.element());
                licniKarti.remove();
            } else {
                System.out.println(licniKarti.peek());
                licniKarti.remove();
            }
        }

        n = pasosi.size();

        for (int i = 0; i < n; i++) {
            if (pasosi.element().getVozacka() == 1) {
                vozacki.add(pasosi.element());
                pasosi.remove();
            } else {
                System.out.println(pasosi.peek());
                pasosi.remove();
            }
        }

        n = vozacki.size();

        for (int i = 0; i < n; i++) {
            System.out.println(vozacki.peek());
            vozacki.remove();
        }

    }
}