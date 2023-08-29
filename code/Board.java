public class Board {
    public void initializeBoard(BusSimulation busSimulation) {
        for (int row = 0; row < busSimulation.ROWS; row++) {
            for (int col = 0; col < busSimulation.COLUMNS; col++) {
                if (row == 0 || row == busSimulation.ROWS - 1 || col == 0 || col == busSimulation.COLUMNS - 1) {
                    busSimulation.board_box[row][col] = '#';
                } else {
                    busSimulation.board_box[row][col] = ' ';
                }
            }
        }
    }

    public void printBoard(BusSimulation busSimulation) {
        for (int row = 0; row < busSimulation.ROWS; row++) {
            for (int col = 0; col < busSimulation.COLUMNS; col++) {
                System.out.print(busSimulation.board_box[row][col]);
            }
            System.out.println();
        }
    }

    // public void clearBoardMarks(BusSimulation busSimulation) {
    //     for (int row = 0; row < busSimulation.ROWS; row++) {
    //         for (int col = 0; col < busSimulation.COLUMNS; col++) {
    //             if (busSimulation.board_box[row][col] == '*') {
    //                 busSimulation.board_box[row][col] = ' ';
    //             }
    //         }
    //     }
    // }
}
