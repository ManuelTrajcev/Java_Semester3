package algoritmi.av4;

public class KnapSackIndian {
    int numOfItems;
    int capacity;
    int[][] table;
    int total;
    int[] weight;
    int[] value;

    public KnapSackIndian(int numOfItems, int capacity, int n) {
        this.numOfItems = numOfItems;
        this.capacity = capacity;
        this.table = new int[weight.length + 1][capacity];
        this.total = 0;
        this.weight = new int[n];
        this.value = new int[n];
    }



    public void solve() {
        for (int i = 1; i < numOfItems + 1; i++) {
            for (int w = 1; w < capacity; w++) {
                int notTakingItem = table[i - 1][w];
                int takingItem = 0;

                if (weight[i] <= w) {
                    takingItem = value[i] + table[i-1][w-weight[i]];
                }
                table[i][w] = Math.max(notTakingItem, takingItem);
            }
        }
        total = table[numOfItems][capacity];
    }

    public static void main(String[] args) {

    }
}
