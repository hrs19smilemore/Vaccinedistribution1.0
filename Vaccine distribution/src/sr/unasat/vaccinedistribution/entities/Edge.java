package sr.unasat.vaccinedistribution.entities;

public class Edge {
    public int srsStat;
    public int destStat;
    public int cost;

    public Edge(int srsStat, int destStat, int cost) {
        this.srsStat = srsStat;
        this.destStat = destStat;
        this.cost = cost;
    }
}
