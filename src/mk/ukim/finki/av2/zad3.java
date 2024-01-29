package mk.ukim.finki.av2;

public class zad3 {
    public static class Locker {
        private int combination;
        private boolean isOpened;

        public Locker(int combination) {
            this.combination = combination;
            this.isOpened = false;
        }

        public boolean open(int combination) {
            this.isOpened = (this.combination == combination);
            return isOpened;
        }

        public boolean changeCombo(int oldCombo, int newCombo) {
            if (this.combination == oldCombo){
                this.combination = newCombo;
                return true;
            } else {
                return false;
            }
        }
        public static void main(String[] args) {
            Locker katanec = new Locker(1234);
            System.out.println( katanec.open(1234));
            System.out.println( katanec.changeCombo(124, 456));
            System.out.println( katanec.changeCombo(1234, 456));
            System.out.println( katanec.open(1234));
            System.out.println( katanec.open(456));
        }
    }


}
