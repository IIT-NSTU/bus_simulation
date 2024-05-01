import java.util.List;
import java.util.Scanner;

public class BusSimulation {
    int row = 20;
    int col = 100;
    char[][] board_box = new char[row][col];
    int stopage_size, distance_size;

    public static void main(String[] args) {
        Scanner get_input = new Scanner(System.in);
        BusSimulation bus_obj = new BusSimulation();
        Board b_obj = new Board();
        Stopage s_obj = new Stopage();
        Distance dist_obj = new Distance();
        b_obj.initialize_board(bus_obj);
        b_obj.print_board(bus_obj);
        Stopage[] stopageArray = s_obj.getStopage(bus_obj);
        System.out.print("\nAll stopage symbol:");
        for (Stopage stopage : stopageArray) {
            System.out.print(" " + stopage.pos_symbol);
        }
        System.out.print("\n\n");
        b_obj.print_board(bus_obj);
        Distance[] dist_arr = dist_obj.get_dist(bus_obj);
        BellmanFord graph = new BellmanFord(bus_obj.stopage_size, bus_obj.distance_size);
        for (Distance distance : dist_arr) {
            graph.addEdge(distance.Start, distance.End, distance.Weight);
        }
        char source, destination;
        System.out.print("Enter Source Node:");
        source = get_input.nextLine().charAt(0);
        System.out.print("Enter Destination Node: ");
        destination = get_input.nextLine().charAt(0);
        List<Character> shortestPath = graph.shortestPath(source, destination);
        System.out.println("\nShortest path from " + source + " to " + destination + ": ");

        for (int i = 0; i < shortestPath.size(); i++) {
            System.out.print(shortestPath.get(i));
            if (i < shortestPath.size() - 1) {
                System.out.print(" -> ");
            }
        }        
        
        System.out.println();

        markPath(bus_obj, shortestPath, source, destination);
        b_obj.print_board(bus_obj);
    }

    public static void markPath(BusSimulation busSimulation, List<Character> path, char source, char destination) {
        char[][] board = busSimulation.board_box;
        int[] sourceLoc = busSimulation.getLoc(source);
        for (int i = 1; i < path.size(); i++) {
            int[] mid = busSimulation.getLoc(path.get(i));
            if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                board[sourceLoc[0]][sourceLoc[1]] = '*';
            }
            
            if (sourceLoc[0] > mid[0]) {
                while (sourceLoc[0] != mid[0]) {
                    if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                        board[sourceLoc[0]][sourceLoc[1]] = '*';
                    }
                    sourceLoc[0]--;
                }
                if (sourceLoc[1] > mid[1]) {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]--;
                    }
                } else {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]++;
                    }
                }
            }
            else if (sourceLoc[0] < mid[0]) {
                while (sourceLoc[0] != mid[0]) {
                    if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                        board[sourceLoc[0]][sourceLoc[1]] = '*';
                    }
                    sourceLoc[0]++;
                }
                if (sourceLoc[1] > mid[1]) {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]--;
                    }
                } else {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]++;
                    }
                }
            }
            else{
                while (sourceLoc[0] != mid[0]) {
                    if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                        board[sourceLoc[0]][sourceLoc[1]] = '*';
                    }
                    sourceLoc[0]++;
                }
                if (sourceLoc[1] > mid[1]) {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]--;
                    }
                } else {
                    while (sourceLoc[1] != mid[1]) {
                        if (!is_stopage(board[sourceLoc[0]][sourceLoc[1]])) {
                            board[sourceLoc[0]][sourceLoc[1]] = '*';
                        }
                        sourceLoc[1]++;
                    }
                }
            }
        }
    }
    
    public int[] getLoc(char ch){
        int[] loc = new int[2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board_box[i][j] == ch) {
                    loc[0]=i;
                    loc[1]=j;
                    break;
                }
            }
        }
        return loc;
    }
    public static boolean is_stopage(char c) {
        return c != ' ';
    }
}
