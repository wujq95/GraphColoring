# GraphColoring

## Introduction
The goal of this project is to implement three algorithms to online bipartite graph coloring problem and evaluate the performance of different algorithms. Besides, we compare the performance of algorithms of different inputs. The online graph model used for this problem is the *VAM-PH* model and the number of vertices of the bipartite graph for this problem is at most 1000.

## Online Algorithm
For offline algorithms, the algorithm receives all inputs at the same time, so the algorithm needs to give a result based on all input items. However, the application scenarios of many problems cannot meet the needs of offline algorithms. Also, in theoretical computer science, there is a class of NP-Complete problems. It is difficult to give polynomial-time algorithms for such problems, which makes it more difficult to solve many problems. Therefore, it is valuable to design online algorithms with the competitive ratio. For online algorithms, the algorithm is presented one item of the input sequence at a time. Then the online algorithm needs to make a real-time decision before the next item is presented. The process is finished until all items in the input sequence have been shown. Finally, the result of the online algorithm will be compared with the result of the optimal offline algorithm for all input items to get the competitive ratio, and the competitive ratio can be used to evaluate the performance of the online algorithm. Online algorithms can help solve many practical problems so it has high application value.

## Online Graph Coloring Problem
In graph theory, graph coloring problem is one of the most important problems. Graph coloring includes vertex coloring, edge coloring, and face coloring. In this project, we concentrate on the vertex coloring problem. Vertex coloring problem is one of the NP-Complete problems, so to design an online algorithm for it is valuable. Several models can be used in online vertex coloring problems, and we choose The *Vertex Adjacency Model, Past History (VAM-PH)* model. In this model, each input item contains a vertex and some neighbors among the vertices that have appeared before. The online coloring algorithm needs to assign a color for the newly coming vertex according to the existing information. We focus on bipartite graphs, so in almost all cases the number of colors used by the optimal offline algorithm is 2. 

## Graph Format
In this project, we use *Matrix Market Coordinate(MMC)* Format, which is useful for us to read graphs. Graphs that we use are form [Network Repository](http://networkrepository.com), which is one of the largest free online databases of graphs. We always judge the graph first. If the graph is not a bipartite graph, the program will stop and output the information. If the graph is a bipartite graph, the program can convert it to the format we need.

## Algorithms
#### FirstFit Algorithm
FirstFit algorithm is the simplest online staining algorithm, but it has a good performance. Whenever a new vertex arrives, the algorithm can get the colors of all its neighbors, and set the new vertex the smallest color that is not the same as any of its neighbors. 

#### CBIP Algorithm
Whenever a new vertex arrives, the CBIP algorithm will get the connected component containing this vertex. Since the entire graph is bipartite, the connected component is also bipartite. Therefore, the algorithm can divide the vertices in this connected component into two sets. One set contains vertices that are on the same side of the new vertex and the other set contains vertices that are on the other side. Then the algorithm gets all colors of the vertices in the latter set and assigns the smallest color that is not the same as any of these colors to the new coming vertex.

#### A New Algorithm
Besides, we apply another complex algorithm. The algorithm defines those colors that appear on both sides of the bipartite are mixed and divides all colors into two sets A{a<sub>1</sub>,a<sub>2</sub>……a<sub>n</sub>} and B{b<sub>1</sub>,b<sub>2</sub>……b<sub>n</sub>} (a<sub>1</sub>~a<sub>n</sub> and b<sub>1</sub>~b<sub>n</sub> are different colors). The algorithm can find the smallest index m that color a<sub>m</sub> is not mixed and it can assign a color to new vertex based on this index. The details and proof of this algorithm can be found [here](https://link.springer.com/chapter/10.1007/978-3-319-13075-0_41).

## Reference
##### [1] Ryan RossiandNesreen Ahmed. 2020. Network Repository. http:// networkrepository.com/
##### [2] Micek, Piotr, and Veit Wiechert. "An On-line Competitive Algorithm for Coloring $$ P_8 $$-free Bipartite Graphs." International Symposium on Algorithms and Computation. Springer, Cham, 2014.
