public class Board {
    public void initialize_board(BusSimulation busSimulation) {
        for (int i = 0; i < busSimulation.row; i++) {
            for (int j = 0; j < busSimulation.col; j++) {
                if (i == 0 || i == busSimulation.row - 1 || j == 0 || j== busSimulation.col - 1) {
                    busSimulation.board_box[i][j] = '#';
                } else {
                    busSimulation.board_box[i][j] = ' ';
                }
            }
        }
    }
    public void print_board(BusSimulation busSimulation) {
        for (int i = 0; i< busSimulation.row; i++) {
            for (int j = 0; j < busSimulation.col; j++) {
                System.out.print(busSimulation.board_box[i][j]);
            }
            System.out.println();
        }
    }
}
