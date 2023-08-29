import java.util.Scanner;

public class BusSimulation {
    int ROWS = 20;
    int COLUMNS = 100;
    char[][] board_box = new char[ROWS][COLUMNS];
    int stopage_size, distance_size;

    public static void main(String[] args) {
        Scanner get_input = new Scanner(System.in);
        BusSimulation busSimulation_object = new BusSimulation();
        Board board_object = new Board();
        Stopage stopage_object = new Stopage();
        Distance distance_object = new Distance();

        board_object.initializeBoard(busSimulation_object);
        board_object.printBoard(busSimulation_object);

        Stopage[] stopageArray = stopage_object.getStopage(busSimulation_object);

        System.out.print("\nAll stopage symbol:");
        for (Stopage stopage : stopageArray) {
            System.out.print(" " + stopage.pos_symbol);
        }
        System.out.print("\n\n");

        board_object.printBoard(busSimulation_object);

        Distance[] distanceArray = distance_object.getDistance(busSimulation_object);

        BellmanFord graph = new BellmanFord(busSimulation_object.stopage_size, busSimulation_object.distance_size);
        for (Distance distance : distanceArray) {
            graph.addEdge(distance.Start, distance.End, (int)distance.Weight);
        }

        char source, destination;
        System.out.print("Enter Source Node: ");
        source = get_input.nextLine().charAt(0);
        System.out.print("Enter Destination Node: ");
        destination = get_input.nextLine().charAt(0);

        // Find shortest path and mark the board with the path
        graph.shortestPath(source, destination);

        System.out.println("\nShortest path from " + source + " to " + destination + ": ");
        markPath(busSimulation_object, source, destination);
        board_object.printBoard(busSimulation_object);
    }

    public static void markPath(BusSimulation busSimulation, char source, char destination) {
        int sourceRow = -1, sourceCol = -1;
        int destinationRow = -1, destinationCol = -1;

        // Find the coordinates of the source and destination
        for (int row = 0; row < busSimulation.ROWS; row++) {
            for (int col = 0; col < busSimulation.COLUMNS; col++) {
                if (busSimulation.board_box[row][col] == source) {
                    sourceRow = row;
                    sourceCol = col;
                } else if (busSimulation.board_box[row][col] == destination) {
                    destinationRow = row;
                    destinationCol = col;
                }
            }
        }

        // Mark the cells along the path with the '*' symbol
        int currentRow = sourceRow, currentCol = sourceCol;
        while (currentRow != destinationRow || currentCol != destinationCol) {
            char cell = busSimulation.board_box[currentRow][currentCol];
            if (cell != source && cell != destination && !isStopage(cell)) {
                busSimulation.board_box[currentRow][currentCol] = '*';
            }

            // Move towards the destination
            if (currentRow < destinationRow) currentRow++;
            else if (currentRow > destinationRow) currentRow--;

            if (currentCol < destinationCol) currentCol++;
            else if (currentCol > destinationCol) currentCol--;
        }
        char cell = busSimulation.board_box[currentRow][currentCol];
        if (cell != source && cell != destination && !isStopage(cell)) {
            busSimulation.board_box[currentRow][currentCol] = '*';
        }
    }

    public static boolean isStopage(char c) {
        // You can modify this method to check if a character is a stopage character
        // For now, it checks if the character is not a space
        return c != ' ';
    }
}