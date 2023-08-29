import java.util.Scanner;
public class Distance{
     char Start,End;
     float Weight;

    public Distance[] getDistance(BusSimulation busSimulation_object){
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many distance: ");
        busSimulation_object.distance_size=scanner.nextInt();
        
        Distance[] distanceArray = new Distance[busSimulation_object.distance_size];
        for(int i=0;i<busSimulation_object.distance_size;i++){
            System.out.print("Enter Starting Stopage, Ending Stopage, Distance: ");
            Distance dist = new Distance();
            distanceArray[i]=dist;
            distanceArray[i].Start=scanner.next().charAt(0);
            distanceArray[i].End=scanner.next().charAt(0);
            distanceArray[i].Weight=scanner.nextFloat();
        }
        return distanceArray;
    }
}