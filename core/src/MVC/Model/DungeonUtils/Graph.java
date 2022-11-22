// Kruskal's algorithm in Java, courtesy Programiz, https://www.programiz.com/dsa/kruskal-algorithm
package MVC.Model.DungeonUtils;

import MVC.Model.DungeonItems.Room;

import java.util.*;

public class Graph
{
    public static class Edge implements Comparable<Edge>
    {
        private int src;
        private int dest;
        private int weight;

        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }

        public int getSrc()
        {
            return src;
        }

        public int getDest()
        {
            return dest;
        }
    };

    // Union
    class subset
    {
        private int parent, rank;
    };

    private int vertices, edges;
    private Edge edge[];

    // Graph creation
    private Graph(int v1, int e1)
    {
        vertices = v1;
        edges = e1;
        edge = new Edge[edges];
        for (int i = 0; i < e1; ++i)
            for (int j = 0; j < e1; j++)
            {
                edge[i] = new Edge();
            }
    }

    private int find(subset subsets[], int i1)
    {
        if (subsets[i1].parent != i1)
            subsets[i1].parent = find(subsets, subsets[i1].parent);
        return subsets[i1].parent;
    }

    private void Union(subset subsets[], int x1, int y1)
    {
        int xroot = find(subsets, x1);
        int yroot = find(subsets, y1);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Applying Krushkal Algorithm, minor change of return the type of a Method
    public ArrayList<Edge> KruskalAlgo(int theVertices)
    {
        Edge result[] = new Edge[vertices];
        int e = 0;
        int i = 0;
        for (i = 0; i < vertices; ++i)
            result[i] = new Edge();

        // Sorting the edges
        Arrays.sort(edge);
        subset subsets[] = new subset[vertices];
        for (i = 0; i < vertices; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < vertices; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0;
        while (e < vertices - 1)
        {
            Edge next_edge = new Edge();
            try
            {
                next_edge = edge[i++];
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                break;
            }
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        var answer = new ArrayList<Edge>();
        for (i = 0; i < e; ++i){
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
            int trueVertices = (int) Math.pow( Math.sqrt(theVertices)-2,2);
            if(result[i].dest % Math.sqrt(theVertices) != Math.sqrt(theVertices)-1 &&
                    result[i].dest % Math.sqrt(theVertices) != 0 &&
                        result[i].dest < (theVertices-((int) Math.sqrt(theVertices)))
                           &&  result[i].dest >= (int) Math.sqrt(theVertices)+1)
            {
                answer.add(result[i]);
            }
        }
        return answer;
    }


    //Customized Method
    public static ArrayList<Edge> generateMaze(int theDimension)
    {
        int allVertices = (int) Math.pow((Math.sqrt(theDimension*theDimension)+2),2); // Number of rooms + boundary rooms
        Graph G = new Graph(allVertices, allVertices*theDimension*2);

        for (int i = (int) Math.sqrt(allVertices)+1; i < (theDimension*4+2*((int) Math.sqrt(allVertices))); i++)
        {
            if(i % Math.sqrt(allVertices) == Math.sqrt(allVertices)-1 || i % Math.sqrt(allVertices) == 0)
            {
                continue;
            }
            else
            {
                G.edge[4*i].src = i;
                G.edge[4*i].dest = (int) (i-Math.sqrt(allVertices));
                G.edge[4*i].weight = new Random().nextInt(0,7);
                G.edge[4*i+1].src = i;
                G.edge[4*i+1].dest = (i-1);
                G.edge[4*i+1].weight = new Random().nextInt(0,7);
                G.edge[4*i+2].src = i;
                G.edge[4*i+2].dest = (int) (i+Math.sqrt(allVertices));;
                G.edge[4*i+2].weight = new Random().nextInt(0,7);
                G.edge[4*i+3].src = i;
                G.edge[4*i+3].dest = (i+1);
                G.edge[4*i+3].weight = new Random().nextInt(0,7);
            }
        }
        return G.KruskalAlgo(allVertices);
    }
}