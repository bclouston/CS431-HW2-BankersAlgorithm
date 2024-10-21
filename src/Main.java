public class Main {

    public static void main(String[] args) {
        int[][] ALLOCATION = {{0,1,0}, {2,0,0}, {3,0,2}, {2,1,1}, {0,0,2}};
        int[][] MAX = {{7,5,3}, {3,2,2}, {9,0,2}, {2,2,2}, {4,3,3}};
        int[] AVAILABLE = {3,3,2};

        Bankers b = new Bankers(ALLOCATION, MAX, AVAILABLE);
    }

}