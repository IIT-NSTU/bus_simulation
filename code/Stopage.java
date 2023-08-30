import java.util.Scanner;
public class Stopage{
    int pos_X,pos_Y;
    char pos_symbol;
    
     Stopage[] getStopage(BusSimulation bus_obj){
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many stopages: ");
        bus_obj.stopage_size = scanner.nextInt();
        
        Stopage[] stopageArray = new Stopage[bus_obj.stopage_size];
        int[] X = new int[bus_obj.stopage_size];
        int[] Y = new int[bus_obj.stopage_size];
        char word='A';
        for (int i=0;i<bus_obj.stopage_size;i++) 
        {
            System.out.print((i+1)+"th stopage coordinate X & Y: ");
            X[i] = scanner.nextInt();
            Y[i] = scanner.nextInt();
            bus_obj.board_box[X[i]][Y[i]]=word;

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
