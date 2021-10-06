package sr.unasat.vaccinedistribution.entities;

public class Vertex {
    public Covid19Station covid19Stations;
    public boolean wasvisited;
    public boolean isInTree;

    public Vertex(Covid19Station cs) {
        this.covid19Stations = cs;
        this.wasvisited = false;
        this.isInTree = false;
    }
}
