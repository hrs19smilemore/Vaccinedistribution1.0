package sr.unasat.vaccinedistribution.app;
import sr.unasat.vaccinedistribution.services.Graph;

public class Application {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addCovid19Station("Paramaribo");
        graph.addCovid19Station("Commewijne");
        graph.addCovid19Station("Wanica");
        graph.addCovid19Station("Saramacca");
        graph.addCovid19Station("Marowijne");
        graph.addCovid19Station("Brownsberg");
        graph.addCovid19Station("Coronie");
        graph.addCovid19Station("Nickerie");
        graph.addCovid19Station("Apoera");
        graph.addCovid19Station("Kwamalasamutu");
        graph.addCovid19Station("Cottica");
        graph.addCovid19Station("Kabalebo");

        graph.addEdgeByLabel("Paramaribo","Saramacca",500);
        graph.addEdgeByLabel("Saramacca","Coronie",700);
        graph.addEdgeByLabel("Coronie","Nickerie",500);
        graph.addEdgeByLabel("Nickerie","Apoera",1000);
        graph.addEdgeByLabel("Paramaribo","Commewijne",100);
        graph.addEdgeByLabel("Commewijne","Marowijne",2000);
        graph.addEdgeByLabel("Commewijne","Brownsberg",4000);
        graph.addEdgeByLabel("Commewijne","Wanica",1000);
        graph.addEdgeByLabel("Wanica","Brownsberg",2000);
        graph.addEdgeByLabel("Brownsberg","Kabalebo",12000);
        graph.addEdgeByLabel("Brownsberg","Cottica",5000);
        graph.addEdgeByLabel("Kabalebo","Kwamalasamutu",7500);
        graph.addEdgeByLabel("Cottica","Kwamalasamutu",10000);
        graph.addEdgeByLabel("Brownsberg","Kwamalasamutu",20000);


        //graph.bfs();
        //graph.bfsDirectBereikbareStations("Comewijne");
        //graph.bfsWithCustomStartingStation("Commewijne");

        //graph.dfs();
        //graph.dfsCheckIfAStationExists("Comewijne");
        //graph.dfsWithCustomStartingVertex("wanica");

        //graph.shortestpath();
        //graph.shortestpathWithCustomStartingVertex("Commewijne");

        //graph.longestpath();
        //graph.longestpathWithCustomStartingVertex("Wanica");

        //graph.displayAdjMatrix();

    }

}


