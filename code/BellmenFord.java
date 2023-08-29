import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PathDistance {
    char start;
    char end;
    float weight;

    PathDistance(char start, char end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class BellmanFord {
    int vertices, edge;
    int edge_ind = 0;
    PathDistance[] distanceArray;
    int[] distances;
    int[] previous;

    BellmanFord(int vertices, int edge) {
        this.vertices = vertices;
        this.edge = edge;
        distanceArray = new PathDistance[edge];
        distances = new int[vertices];
        previous = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
    }

    void addEdge(char start, char end, float weight) {
        distanceArray[edge_ind++] = new PathDistance(start, end, weight);
    }

    void shortestPath(char source, char destination) {
        distances[source - 'A'] = 0;
        for (int i = 0; i < vertices - 1; i++) {
            for (int j = 0; j < edge; j++) {
                char u = distanceArray[j].start;
                char v = distanceArray[j].end;
                float weight = distanceArray[j].weight;
                if (distances[u - 'A'] != Integer.MAX_VALUE && distances[u - 'A'] + weight < distances[v - 'A']) {
                    distances[v - 'A'] = distances[u - 'A'] + weight;
                    previous[v - 'A'] = u - 'A';
                }
            }
        }

        
        for(int j=0;j<edge;j++)
        {
            char u=distanceArray[j].start;
            char v=distanceArray[j].end;
            float weight=distanceArray[j].weight;
            if(distance[u-'A']!=Integer.MAX_VALUE&& distance[u-'A']+weight<distance[v-'A'])
            {
                System.out.println("negative weight cycle");
                return;
            }
        }
        System.out.println("shortest path from"+source+" to other nodfe: ");
        for(int i=0;i<vertices;i++)
        {
            if(distance[i]!=Integer.MAX_VALUE)
            {
                System.out.println("Distance to"+(char) (i+'A')+" :"+distance[i]);

            }
            else
            {
                System.out.println("Distance to "+(char) (i+'A')+" :infinity");
            }
            }
            
            if(distance[destination-'A']!=Integer.MAX_VALUE)
            {
                System.out.print("shortest path from "+source+" to"+destination+" : ");
                printPath(source-'A',destination-'A',destination-'A');
                System.out.println();

            }
            else
            {
                System.out.println("No path found"+source+" to "+destination+ " ..");
            }

            }

        void printPath(int source, int current, int destination) {
            List<Character> path = new ArrayList<>();
            while (current != source) {
                path.add((char) (current + 'A'));
                current = previous[current];
                if (current == -1) {
                    System.out.println("No path found");
                    return;
                }
            }
            path.add((char) (source + 'A'));
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i));
                if (i != 0) {
                    System.out.print(" -> ");
                }
            }
        }
    }
