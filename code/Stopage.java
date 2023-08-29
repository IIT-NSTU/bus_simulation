import java.util.Scanner;
public class Stopage{
    int pos_X,pos_Y;
    char pos_symbol;
    
    public Stopage[] getStopage(BusSimulation busSimulation_object){
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many stopages: ");
        busSimulation_object.stopage_size = scanner.nextInt();
        
        Stopage[] stopageArray = new Stopage[busSimulation_object.stopage_size];
        int[] X = new int[busSimulation_object.stopage_size];
        int[] Y = new int[busSimulation_object.stopage_size];
        char word='A';
        for (int i=0;i<busSimulation_object.stopage_size;i++) 
        {
            System.out.print((i+1)+"th stopage coordinate X & Y: ");
            X[i] = scanner.nextInt();
            Y[i] = scanner.nextInt();
            busSimulation_object.board_box[X[i]][Y[i]]=word;

            Stopage stopage = new Stopage();
            stopageArray[i]=stopage;
            stopageArray[i].pos_X=X[i];
            stopageArray[i].pos_Y=Y[i];
            stopageArray[i].pos_symbol=word;
            word++;
        }
        return stopageArray;
    }
}
