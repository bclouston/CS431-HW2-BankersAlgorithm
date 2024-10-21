public class Bankers {

    private int n;                // number of processes
    private int m;                // number of resource types
    private int[][] allocation;   // amount of resources allocated to each process
    private int[][] max;          // amount of resources required of each process to execute
    private int[][] need;         // amount of additional resources required of each process (max - allocation)
    private int[] available;      // amount of resource available of each type

    public Bankers(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;
        n = allocation.length;
        m = allocation[0].length;
        need = new int[n][m];
        needCalc();
        safetyCheck();
    }

    // calculates the amount of additional resources required for each process
    public void needCalc() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    // determines if a safe execution sequence exists and prints the result
    public void safetyCheck() {
        // initialize trackers for currently available resources, execution sequence and visited processes
        int[] current = available;
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        int counter = 0;
        while (counter < n) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        // more resources required than currently available
                        if (need[i][j] > current[j]) {
                            break;
                        }
                    }
                    // all resources required are available
                    if (j == m) {
                        path[counter++] = i;
                        visited[i] = true;
                        flag = true;
                        // release resources allocated to the process
                        for (int w = 0; w < m; w++) {
                            current[w] += allocation[i][w];
                        }
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        if (counter == n) {
            System.out.println("Safe execution sequence found.");
            for (int i = 0; i < n; i++) {
                System.out.print("P" + path[i]);
                if (i < n-1) {
                    System.out.print(" --> ");
                }
            }
            System.out.println();
        }
        else {
            System.out.println("Potential deadlock detected. System is unsafe.");
        }
    }

}
