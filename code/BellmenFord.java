import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    PathDistance[] dist_arr;
    int[] distances;
    int[] previous;

    BellmanFord(int vertices, int edge) {
        this.vertices = vertices;
        this.edge = edge;
        dist_arr = new PathDistance[edge];
        distances = new int[vertices];
        previous = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
    }

    void addEdge(char start, char end, float weight) {
        dist_arr[edge_ind++] = new PathDistance(start, end, weight);
    }

    List<Character> shortestPath(char source, char destination) {
        distances[source - 'A'] = 0;
        for (int i = 0; i < vertices - 1; i++) {
            for (int j = 0; j < edge; j++) {
                char u = dist_arr[j].start;
                char v = dist_arr[j].end;
                float weight = dist_arr[j].weight;
                if (distances[u - 'A'] != Integer.MAX_VALUE && distances[u - 'A'] + weight < distances[v - 'A']) {
                    distances[v - 'A'] = distances[u - 'A'] + (int) weight;
                    previous[v - 'A'] = u - 'A';
                }
            }
        }

        for (int j = 0; j < edge; j++) {
            char u = dist_arr[j].start;
            char v = dist_arr[j].end;
            float weight = dist_arr[j].weight;
            if (distances[u - 'A'] != Integer.MAX_VALUE && distances[u - 'A'] + weight < distances[v - 'A']) {
                System.out.println("negative weight cycle");
                return new ArrayList<>();
            }
        }

        List<Character> path = new ArrayList<>();
        int current = destination - 'A';
        while (current != source - 'A') {
            path.add((char) (current + 'A'));
            current = previous[current];
            if (current == -1) {
                System.out.println("No path found");
                return new ArrayList<>();
            }
        }
        path.add(source);
        Collections.reverse(path);
        return path;
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
