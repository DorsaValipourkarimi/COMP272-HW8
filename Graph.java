
/******************************************************************
 *
 *   Dorsa Valipourkarimi / COMP 272 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Graph traversal exercise
 *
 * The Graph class is a representing an oversimplified Directed Graph of
 * vertices
 * (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices; // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues; // vertex values

  // Constructor
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */

  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
          "Invalid vertex index: " + vertexIndex);
    }
  }

  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */

  public void printGraph() {
    System.out.println(
        "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }

  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */

  public int findRoot() {
    // Count Array to record how many incoming edges each vertex has
    int[] Count = new int[numVertices];

    // Loop through all edges and count incoming edges for each vertex
    for (int s = 0; s < numVertices; s++) {
      for (int dest : adjListArr[s]) {
        Count[dest]++;
      }
    }
    int rootIndex = -1; // To store the index of the root if found
    int rootCount = 0; // To track how many roots is found

    // Look for vertices with zero incoming edges counted
    for (int i = 0; i < numVertices; i++) {
      if (Count[i] == 0) {
        rootIndex = i; // Save the index of the root
        rootCount++; // Count how many roots we find
      }
    }
    // If there's exactly one root, return its value
    if (rootCount == 1) {
      return vertexValues.get(rootIndex);
    } else { // otherwise, return -1
      return -1;
    }
  }
}
