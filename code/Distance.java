import java.util.Scanner;
public class Distance{
     char Start,End;
     float Weight;

    public Distance[] get_dist(BusSimulation bus_obj){
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many distance: ");
        bus_obj.distance_size=scanner.nextInt();
        
        Distance[] dist_arr = new Distance[bus_obj.distance_size];
        for(int i=0;i<bus_obj.distance_size;i++){
            System.out.print("Enter Starting Stopage, Ending Stopage, Distance: ");
            Distance dist = new Distance();
            dist_arr[i]=dist;
            dist_arr[i].Start=scanner.next().charAt(0);
            dist_arr[i].End=scanner.next().charAt(0);
            dist_arr[i].Weight=scanner.nextFloat();
        }
        return dist_arr;
    }
}
