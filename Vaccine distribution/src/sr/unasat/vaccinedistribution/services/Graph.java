package sr.unasat.vaccinedistribution.services;
import sr.unasat.vaccinedistribution.datastructures.Queue;
import sr.unasat.vaccinedistribution.datastructures.Stack;
import sr.unasat.vaccinedistribution.entities.Covid19Station;
import sr.unasat.vaccinedistribution.entities.Vertex;
import sr.unasat.vaccinedistribution.entities.DistPar;

public class Graph {
    public int MAX_VERTS = 12;
    private final int INFINITY = 100000;
    private final Vertex[] stationsList;
    private final Queue queue;
    private final Stack stack;
    private final int[][] adjMat;
    private int nStats;
    private int nTree;
    private final DistPar[] mostExpensivePath;
    private final DistPar[] cheapestPath;
    private int currentStat;
    private int startToCurrent;

    public Graph(){
        stationsList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nStats = 0;
        nTree = 0;
        for(int item = 0; item< MAX_VERTS; item++)
            for (int k = 0; k< MAX_VERTS; k++)
                adjMat[item][k] = INFINITY;
        cheapestPath = new DistPar[MAX_VERTS];
        mostExpensivePath = new DistPar[MAX_VERTS];
        queue = new Queue();
        stack = new Stack();
    }

    public void dfs() {
        stationsList[0].wasvisited = true;
        displayStation(0);
        stack.push(0);

        while (!stack.isEmpty()) {
            int s = getAdjUnvisitedStation(stack.peek());
            if (s == -1)
                stack.pop();
            else {
                stationsList[s].wasvisited = true;
                displayStation(s);
                stack.push(s);
            }
        }
        for (int n = 0; n < nStats; n++)
            stationsList[n].wasvisited = false;
    }

    public void dfsWithCustomStartingVertex(String station) {
        int startingStation = findIndexOf(station);
        if (startingStation == -1) {
            System.out.println("Het station " + station + " bestaat niet");

        } else {
            stationsList[startingStation].wasvisited = true;
            displayStation(startingStation);
            stack.push(startingStation);

            while (!stack.isEmpty()) {
                int s = getAdjUnvisitedStation(stack.peek());
                if (s == -1)
                    stack.pop();
                else {
                    stationsList[s].wasvisited = true;
                    displayStation(s);
                    stack.push(s);
                }
            }
            for (int n = 0; n < nStats; n++)
                stationsList[n].wasvisited = false;
        }
    }

    public void dfsCheckIfAStationExists(String station) {
        int covidStation = findIndexOf(station);
        if (covidStation < 0) {
            System.out.println("Het station bestaat niet");
        } else {
            stationsList[0].wasvisited = true;
            //displayStation(0);
            stack.push(0);

            while (!stack.isEmpty()) {
                int s = getAdjUnvisitedStation(stack.peek());
                if (s == -1)
                    stack.pop();
                else {
                    stationsList[s].wasvisited = true;
                    //displayStation(s);
                    stack.push(s);
                }
            }
            for (int cs = 0; cs < nStats; cs++)
                if (stationsList[cs].covid19Stations.getLabel().equals(station))
                    System.out.println("Het station " + station + " is operationeel.");

            for (int n = 0; n < nStats; n++)
                stationsList[n].wasvisited = false;
        }
    }

    public void bfs(){
        stationsList[0].wasvisited = true;
        displayStation(0);
        queue.insert(0);
        int s2;

        while(!queue.isEmpty()){
            int s1 = queue.remove();
            while( (s2= getAdjUnvisitedStation(s1)) != -1){
                stationsList[s2].wasvisited = true;
                displayStation(s2);
                queue.insert(s2);
            }
        }
        for (int n = 0; n<nStats; n++)
            stationsList[n].wasvisited = false;
    }
    public void bfsWithCustomStartingStation(String station) {
        int startingStation = findIndexOf(station);
        if (startingStation < 0) {
            System.out.println("Het station " + station + " bestaat niet");
        } else {
            stationsList[startingStation].wasvisited = true;
            displayStation(startingStation);
            queue.insert(startingStation);
            int s2;

            while (!queue.isEmpty()) {
                int s1 = queue.remove();
                while ((s2 = getAdjUnvisitedStation(s1)) != -1) {
                    stationsList[s2].wasvisited = true;
                    displayStation(s2);
                    queue.insert(s2);
                }
            }
            for (int n = 0; n < nStats; n++)
                stationsList[n].wasvisited = false;
        }
    }
    public void bfsDirectBereikbareStations(String station) {
        int startingStation = findIndexOf(station);
        if (startingStation < 0) {
            System.out.println("Het station " + station + " bestaat niet");
        } else {
            stationsList[startingStation].wasvisited = true;
            displayStation(startingStation);
            queue.insert(startingStation);
            int s2;
            int s1 = queue.remove();
            while ((s2 = getAdjUnvisitedStation(s1)) != -1) {
                stationsList[s2].wasvisited = true;
                displayStation(s2);
                queue.insert(s2);
            }
            for (int n = 0; n < nStats; n++)
                stationsList[n].wasvisited = false;
        }
    }

    public int getAdjUnvisitedStation(int s) {
        for (int item = 0; item < nStats; item++)
            if (adjMat[s][item] < INFINITY && !stationsList[item].wasvisited)
                return item;
        return -1;
    }

    public void displayAdjMatrix() {
        System.out.print("                ");
        int spaceToFill;
        for (Vertex vertex : stationsList) {
            System.out.print(vertex.covid19Stations.getLabel());
            System.out.print(" ");
        }
        System.out.println();
        int adjMatRow = 0;
        for (Vertex vertex : stationsList) {
            String Covid19Station = vertex.covid19Stations.getLabel();
            System.out.print(Covid19Station);
            spaceToFill = 16 - Covid19Station.length();
            for(int i = 0; i < spaceToFill; i++) {
                System.out.print(" ");
            }
            for (int column = 0; column < nStats; column++) {
                System.out.print(adjMat[adjMatRow][column]);
                spaceToFill = (stationsList[column].covid19Stations.getLabel().length() -
                        String.valueOf(adjMat[adjMatRow][column]).length()) + 1;
                for(int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
            }
            adjMatRow++;
            System.out.println();
        }
    }

    public void shortestpath(){
        int startTree = 0;
        stationsList[startTree].isInTree = true;
        nTree =1;
        for (int item =0; item<nStats; item++){
            int tempDist = adjMat[startTree][item];
            cheapestPath[item] = new DistPar(startTree, tempDist);
        }

        while(nTree<nStats){
            int indexMin = getMin();
            int minDist = cheapestPath[indexMin].cost;

            if (minDist == INFINITY){
                System.out.println("There are unreachable stations");
                break;
            }
            else{
                currentStat = indexMin;
                startToCurrent = cheapestPath[indexMin].cost;
            }
            stationsList[currentStat].isInTree = true;
            nTree++;
            adjust_cPath();
        }
        displayPaths();
        nTree = 0;
        for (int item = 0; item<nStats; item++)
            stationsList[item].isInTree = false;
    }
    public void shortestpathWithCustomStartingVertex(String station) {

        int startTree = findIndexOf(station);
        if (startTree < 0) {
            System.out.println("Het station " + station + " bestaat niet");
        } else {
            stationsList[startTree].isInTree = true;
            nTree = 1;
            for (int item = 0; item < nStats; item++) {
                int tempDist = adjMat[startTree][item];
                cheapestPath[item] = new DistPar(startTree, tempDist);
            }

            while (nTree < nStats) {
                int indexMin = getMin();
                int minDist = cheapestPath[indexMin].cost;

                if (minDist == INFINITY) {
                    System.out.println("There are unreachable stations");
                    break;
                } else {
                    currentStat = indexMin;
                    startToCurrent = cheapestPath[indexMin].cost;
                }
                stationsList[currentStat].isInTree = true;
                nTree++;
                adjust_cPath();
            }
            displayPaths();
            nTree = 0;
            for (int item = 0; item < nStats; item++)
                stationsList[item].isInTree = false;
        }
    }

    public void longestpath(){
        int startTree = 0;
        fixInfinityInAdjMatForEPath(true);
        stationsList[startTree].isInTree = true;
        nTree =1;
        for (int item =0; item<nStats; item++){
            int tempDist = adjMat[startTree][item];
            mostExpensivePath[item] = new DistPar(startTree, tempDist);
        }

        while(nTree<nStats){
            int indexMin = getMax();
            int maxDist = mostExpensivePath[indexMin].cost;

            if (maxDist == 0){
                System.out.println("There are unreachable stations");
                break;
            }
            else{
                currentStat = indexMin;
                startToCurrent = mostExpensivePath[indexMin].cost;
            }
            stationsList[currentStat].isInTree = true;
            nTree++;
            adjust_ePath();
        }
        displayPathsMax();
        nTree = 0;
        for (int item = 0; item<nStats; item++)
            stationsList[item].isInTree = false;
    }

    public void longestpathWithCustomStartingVertex(String station) {
        int startTree = findIndexOf(station);
        if (startTree < 0) {
            System.out.println("Het station " + station + " bestaat niet");
        } else {
            fixInfinityInAdjMatForEPath(true);
            stationsList[startTree].isInTree = true;
            nTree = 1;
            for (int item = 0; item < nStats; item++) {
                int tempDist = adjMat[startTree][item];
                mostExpensivePath[item] = new DistPar(startTree, tempDist);
            }

            while (nTree < nStats) {
                int indexMin = getMax();
                int maxDist = mostExpensivePath[indexMin].cost;

                if (maxDist == 0) {
                    System.out.println("There are unreachable stations");
                    break;
                } else {
                    currentStat = indexMin;
                    startToCurrent = mostExpensivePath[indexMin].cost;
                }
                stationsList[currentStat].isInTree = true;
                nTree++;
                adjust_ePath();
            }
            displayPathsMax();
            nTree = 0;
            for (int item = 0; item < nStats; item++)
                stationsList[item].isInTree = false;
        }
    }

    public int getMin(){
        int minDist = INFINITY;
        int indexMin = 0;
        for (int item = 1; item<nStats; item++){
            if (!stationsList[item].isInTree && cheapestPath[item].cost < minDist){
                minDist = cheapestPath[item].cost;
                indexMin = item;
            }
        }
        return indexMin;
    }
    public int getMax(){
        int maxDist = 0;
        int indexMin = 0;
        for (int item = 1; item<nStats; item++){
            if (!stationsList[item].isInTree && (mostExpensivePath[item].cost > maxDist)){
                maxDist = mostExpensivePath[item].cost;
                indexMin = item;
            }
        }
        return indexMin;
    }

    public void displayPaths(){
        int spaceToFill;
        System.out.println("Station \t\t Totale kosten vanuit Paramaribo");
        for (int item = 0; item<nStats; item++){
            String station = stationsList[item].covid19Stations.getLabel();
            int length = station.length();
            spaceToFill =  17 - length;
            System.out.print(station);
            if (cheapestPath[item].cost == INFINITY){
                for (int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
                System.out.println("X");
            }
            else {
                for (int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
                System.out.print(cheapestPath[item].cost);
                String parent = stationsList[cheapestPath[item].parentStation].covid19Stations.getLabel();
                int cost = Integer.toString(cheapestPath[item].cost).length();
                int filler = 8 - cost;
                for (int i = 0; i < filler; i++) {
                    System.out.print(" ");
                }
                System.out.println("via " + parent);
            }
        }
        System.out.println("");
    }
    public void displayPathsMax(){
        int spaceToFill;
        System.out.println("Station \t\t Totale kosten vanuit Paramaribo");
        for (int item = 0; item<nStats; item++){
            String station = stationsList[item].covid19Stations.getLabel();
            int length = station.length();
            spaceToFill =  17 - length;
            System.out.print(station);
            if (mostExpensivePath[item].cost == INFINITY){
                for (int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
                System.out.println("X");
            }
            else {
                for (int i = 0; i < spaceToFill; i++) {
                    System.out.print(" ");
                }
                System.out.print(mostExpensivePath[item].cost);
                String parent = stationsList[mostExpensivePath[item].parentStation].covid19Stations.getLabel();
                int cost = Integer.toString(mostExpensivePath[item].cost).length();
                int filler = 8 - cost;
                for (int i = 0; i < filler; i++) {
                    System.out.print(" ");
                }
                System.out.println("via " + parent);
            }
        }
        System.out.println("");
    }

    public void adjust_cPath()
    {
        int column = 1;
        while(column < nStats) {
            if( stationsList[column].isInTree )
            {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentStat][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = cheapestPath[column].cost;
            if(startToFringe < sPathDist) {
                cheapestPath[column].parentStation = currentStat;
                cheapestPath[column].cost = startToFringe;
            }
            column++;
        }
    }
    public void adjust_ePath()
    {
        int column = 1;
        while(column < nStats) {
            if( stationsList[column].isInTree )
            {
                column++;
                continue;
            }
            int currentToFringe = adjMat[currentStat][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = mostExpensivePath[column].cost;
            if(startToFringe > sPathDist && currentToFringe != 0) {
                mostExpensivePath[column].parentStation = currentStat;
                mostExpensivePath[column].cost = startToFringe;
            }
            column++;
        }
    }
    public int findIndexOf(String station){
        int item;
        for (item = 0; item<nStats; item++) {
            if (stationsList[item].covid19Stations.getLabel().equalsIgnoreCase(station))
                return item;
        }
        return -1;
    }
    public void fixInfinityInAdjMatForEPath(boolean fix) {
        if (fix) {
            for(int row = 0; row < MAX_VERTS; row++) {
                for(int column = 0; column < MAX_VERTS; column++) {
                    if (adjMat[row][column] == INFINITY) {
                        adjMat[row][column] = 0;
                    }
                }
            }
        } else {
            for(int row = 0; row < MAX_VERTS; row++) {
                for(int column = 0; column < MAX_VERTS; column++) {
                    if (adjMat[row][column] == 0) {
                        adjMat[row][column] = INFINITY;
                    }
                }
            }
        }
    }

    public void addCovid19Station(String label){
        stationsList[nStats++] = new Vertex(new Covid19Station(label));
    }

    public void addEdgeByLabel(String startLabel, String destinationLabel, int weight) {
        int startIndexValue = findIndexOf(startLabel);
        int destinationIndexValue = findIndexOf(destinationLabel);
        adjMat[startIndexValue][destinationIndexValue] = weight;
    }

    public void displayStation(int s){
        System.out.println(stationsList[s].covid19Stations.getLabel());
    }
}
