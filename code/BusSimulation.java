import java.util.Scanner;
public class BusSimulation 
{
    int row = 20;
    int col = 100;
    char[][] board_box = new char[row][col];
    int stopage_size, distance_size;
    public static void main(String[] args) 
    {
        Scanner get_input = new Scanner(System.in);
        BusSimulation bus_obj = new BusSimulation();
        Board b_obj = new Board();
        Stopage s_obj = new Stopage();
        Distance dist_obj = new Distance();
        b_obj.initialize_board(bus_obj);
        b_obj.print_board(bus_obj);
        Stopage[] stopageArray = s_obj.getStopage(bus_obj);
        System.out.print("\nAll stopage symbol:");
        for (Stopage stopage : stopageArray) 
        {
            System.out.print(" " + stopage.pos_symbol);
        }
        System.out.print("\n\n");
        b_obj.print_board(bus_obj);
        Distance[] dist_arr = dist_obj.get_dist(bus_obj);
        BellmanFord graph = new BellmanFord(bus_obj.stopage_size, bus_obj.distance_size);
        for (Distance distance : dist_arr) 
        {
            graph.addEdge(distance.Start, distance.End,distance.Weight);
        }
        char source, destination;
        System.out.print("Enter Source Node:");
        source = get_input.nextLine().charAt(0);
        System.out.print("Enter Destination Node: ");
        destination = get_input.nextLine().charAt(0);
        graph.shortestPath(source, destination);
        System.out.println("\nShortest path from " + source + " to " + destination + ": ");
        markPath(bus_obj, source, destination);
        b_obj.print_board(bus_obj);
    }
    public static void markPath(BusSimulation busSimulation, char source, char destination) 
    {
        int sourc_row = -1, sourc_col = -1;
        int dest_row = -1, dest_col = -1;
        for (int i = 0; i < busSimulation.row; i++) 
        {
            for (int j = 0; j < busSimulation.col; j++) 
            {
                if(busSimulation.board_box[i][j] == source) 
                {
                    sourc_row = i;
                    sourc_col = j;
                } 
                else if(busSimulation.board_box[i][j]==destination) 
                {
                    dest_row = i;
                    dest_col = j;
                }
            }
        }
        int curr_row = sourc_row, curr_col = sourc_col;
        while (curr_row != dest_row || curr_col != dest_col) 
        {
            char cell = busSimulation.board_box[curr_row][curr_col];
            if (cell != source && cell != destination && !is_stopage(cell)) 
            {
                busSimulation.board_box[curr_row][curr_col] = '*';
            }
            if (curr_row < dest_row) curr_row++;
            else if (curr_row > dest_row) curr_row--;
            if (curr_col < dest_col) curr_col++;
            else if (curr_col > dest_col) curr_col--;
        }
        
    }
    public static boolean is_stopage(char c) 
    {
        return c != ' ';
    }
}
