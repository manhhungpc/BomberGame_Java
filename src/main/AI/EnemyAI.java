package main.AI;

import main.worlds.World;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import java.util.Collections;
import java.util.List;

public class EnemyAI {
    private World world;

    public EnemyAI(World world){
        this.world = world;
    }
    public void connectUp(int i, int j, int currVertex, Graph<Integer, DefaultEdge> graph ){
        int upVertex = (i - 1) * (world.getWidth() - 2) + j;
        graph.addVertex(upVertex);
        graph.addEdge(currVertex, upVertex);
    }

    public void connectDown(int i, int j, int currVertex, Graph<Integer, DefaultEdge> graph ){
        int downVertex = (i + 1) * (world.getWidth() - 2) + j ;
        graph.addVertex(downVertex);
        graph.addEdge(currVertex, downVertex);
    }

    public void connectLeft(int i, int j, int currVertex, Graph<Integer, DefaultEdge> graph ){
        int leftVertex = i * (world.getWidth() - 2) + j - 1 ;
        graph.addVertex(leftVertex);
        graph.addEdge(currVertex, leftVertex);
    }

    public void connectRight(int i, int j, int currVertex, Graph<Integer, DefaultEdge> graph ){
        int rightVertex = i * (world.getWidth() - 2) + j + 1 ;
        graph.addVertex(rightVertex);
        graph.addEdge(currVertex, rightVertex);
    }

    public List<Integer> MoveAI(World world, int playerX, int playerY, int monsterX, int monsterY) {
        int height = world.getHeight();
        int width = world.getWidth();
        Graph<Integer, DefaultEdge> graph =
                new DefaultUndirectedGraph<>(DefaultEdge.class);
        char currTile;

        int player2D = (playerY - 1) * (width - 2) + playerX;
        int monster2D = (monsterY - 1) * (width - 2) + monsterX;
        graph.addVertex(player2D);
        graph.addVertex(monster2D);
        //System.out.println(player2D + "-" + monster2D);

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++) {
                currTile = world.getTile(j, i).getId();

                //convert to matrix like this
                /*
                 * 1  2  3  4  5  6  7  8  9
                 * 10 11 12 13 14 15 16 17 18
                 * 19 20 21 22 23 24 25 26 27 ...
                 * */
                if (currTile == ' ') {
                    char upTile = world.getCharTile(j, i - 1);
                    char downTile = world.getCharTile(j, i + 1);
                    char leftTile = world.getCharTile(j - 1, i);
                    char rightTile = world.getCharTile(j + 1, i);
                    int currVertex = (i-1) * (width - 2) + j;
                    graph.addVertex(currVertex);
                    if (upTile == ' ')  connectUp(i-1, j, currVertex, graph);
                    if(downTile == ' ') connectDown(i-1, j, currVertex, graph);
                    if(leftTile == ' ') connectLeft(i-1, j, currVertex, graph);
                    if(rightTile == ' ') connectRight(i-1, j, currVertex, graph);
                }
            }
        }

        DijkstraShortestPath<Integer, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        ShortestPathAlgorithm.SingleSourcePaths<Integer, DefaultEdge> monsterToPlayer
                = dijkstraAlg.getPaths(monster2D);
        if(monsterToPlayer.getPath(player2D) == null) return Collections.emptyList();
        return monsterToPlayer.getPath(player2D).getVertexList();
    }

    public static void main(String[] args) {
        World w = new World(".\\src\\resource\\map\\level1.txt");
        EnemyAI e = new EnemyAI(w);
        List<Integer> res =  e.MoveAI(w,1,1, 6, 3);
        System.out.println(res);
    }
}
