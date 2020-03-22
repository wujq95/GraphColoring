import java.util.LinkedHashMap;
import java.util.Set;

public class BipartiteGraph {

    int N;
    double P;
    Set<String> vertexUSet;
    Set<String> vertexVSet;

    LinkedHashMap<String, Set<String>> adjacentVertices;

    public Set<String> getVertexUSet() {
        return vertexUSet;
    }

    public void setVertexUSet(Set<String> vertexUSet) {
        this.vertexUSet = vertexUSet;
    }

    public Set<String> getVertexVSet() {
        return vertexVSet;
    }

    public void setVertexVSet(Set<String> vertexVSet) {
        this.vertexVSet = vertexVSet;
    }

    public LinkedHashMap<String, Set<String>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(LinkedHashMap<String, Set<String>> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }
}
