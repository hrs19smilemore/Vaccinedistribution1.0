package sr.unasat.vaccinedistribution.entities;

public class DistPar {
    public int cost;
    public int parentStation;

    public DistPar(int parentStation, int cost) {
        this.cost = cost;
        this.parentStation = parentStation;
    }
}
