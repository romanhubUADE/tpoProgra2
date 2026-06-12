package graphModule;

import application.Exercise;
import java.util.Scanner;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleList;
import stackModule.SimpleLinkedStack;
import stackModule.SimpleStack;

public class FlightExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private boolean graphChanged = true;

    private Graph<String> graph;

    public FlightExercise(Scanner scanner) {
        super(scanner);
        graph = new ListGraph<>();
        preloadFlights();
    }

    private void preloadFlights() {
        addFlight("Buenos Aires", "Santiago", 120);
        addFlight("Buenos Aires", "Sao Paulo", 150);
        addFlight("Buenos Aires", "Bogota", 480);
        addFlight("Santiago", "Miami", 450);
        addFlight("Sao Paulo", "Miami", 400);
        addFlight("Sao Paulo", "Madrid", 850);
        addFlight("Bogota", "Miami", 250);
        addFlight("Miami", "Nueva York", 180);
        addFlight("Madrid", "Londres", 150);
        addFlight("Madrid", "Nueva York", 600);
        addFlight("Nueva York", "Londres", 550);
    }

    private void addFlight(String a, String b, int cost) {
        graph.addEdge(a, b, cost);
        graph.addEdge(b, a, cost);
    }

    private String resolveCity(String input) {
        SimpleList<String> cities = graph.vertices();
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).equalsIgnoreCase(input)) return cities.get(i);
        }
        return null;
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: findRouteLogic(); break;
            case 2: addCityLogic(); break;
            case 3: addFlightLogic(); break;
            case 4: removeCityLogic(); break;
            case 5: removeFlightLogic(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Flight Route Planner (TP10 - Graph + Dijkstra).");
        }

        if (graphChanged) {
            printGraph();
            graphChanged = false;
        }

        System.out.println("\nChoose an option:"
            + "\nfr - Find cheapest route (origin -> destination)"
            + "\nac - Add city"
            + "\naf - Add flight"
            + "\nrc - Remove city"
            + "\nrf - Remove flight"
            + "\nmm - Main Menu"
        );

        String userInput = scanner.nextLine().trim().toLowerCase();
        switch (userInput) {
            case "fr": currentPhase = 1; break;
            case "ac": currentPhase = 2; break;
            case "af": currentPhase = 3; break;
            case "rc": currentPhase = 4; break;
            case "rf": currentPhase = 5; break;
            case "mm": running = false; break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void findRouteLogic() {
        currentPhase = 0;

        System.out.println("\nOrigin city:");
        String originInput = scanner.nextLine().trim();
        System.out.println("Destination city:");
        String destInput = scanner.nextLine().trim();

        if (originInput.isEmpty() || destInput.isEmpty()) {
            System.out.println("Cities cannot be empty.");
            return;
        }

        String origin = resolveCity(originInput);
        String destination = resolveCity(destInput);
        if (origin == null) {
            System.out.println("Unknown origin city: " + originInput);
            return;
        }
        if (destination == null) {
            System.out.println("Unknown destination city: " + destInput);
            return;
        }
        if (origin.equals(destination)) {
            System.out.println("Origin and destination are the same city.");
            return;
        }

        SimpleDictionary<String, PathInfo<String>> result =
            DijkstraSolver.dijkstraAllNodes(graph, origin);

        PathInfo<String> destInfo = result.get(destination);

        if (destInfo == null || destInfo.cost == Integer.MAX_VALUE) {
            System.out.println("No route from " + origin + " to " + destination + ".");
            return;
        }

        SimpleStack<String> path = new SimpleLinkedStack<>();
        String step = destination;
        while (step != null) {
            path.push(step);
            if (step.equals(origin)) break;
            step = result.get(step).previous;
        }

        int stops = path.size() - 2;

        StringBuilder route = new StringBuilder();
        while (!path.isEmpty()) {
            route.append(path.pop());
            if (!path.isEmpty()) route.append(" -> ");
        }

        System.out.println("\nCheapest route: " + route);
        System.out.println("Total cost: " + destInfo.cost
            + " | Stops: " + (stops < 0 ? 0 : stops));
    }

    private void addCityLogic() {
        currentPhase = 0;
        System.out.println("\nCity name to add:");
        String city = scanner.nextLine().trim();
        if (city.isEmpty()) {
            System.out.println("City cannot be empty.");
            return;
        }
        if (resolveCity(city) != null) {
            System.out.println("City '" + city + "' already exists.");
            return;
        }
        graph.addVertex(city);
        System.out.println("City '" + city + "' added.");
        graphChanged = true;
    }

    private void addFlightLogic() {
        currentPhase = 0;
        System.out.println("\nOrigin city:");
        String fromInput = scanner.nextLine().trim();
        System.out.println("Destination city:");
        String toInput = scanner.nextLine().trim();
        if (fromInput.isEmpty() || toInput.isEmpty()) {
            System.out.println("Cities cannot be empty.");
            return;
        }
        if (fromInput.equalsIgnoreCase(toInput)) {
            System.out.println("A flight needs two different cities.");
            return;
        }

        System.out.println("Cost (positive integer):");
        String costInput = scanner.nextLine().trim();
        int cost;
        try {
            cost = Integer.parseInt(costInput);
        } catch (NumberFormatException e) {
            System.out.println("Cost must be a valid integer.");
            return;
        }
        if (cost <= 0) {
            System.out.println("Cost must be positive (Dijkstra rejects negative weights).");
            return;
        }

        String fromCity = resolveCity(fromInput);
        if (fromCity == null) fromCity = fromInput;
        String toCity = resolveCity(toInput);
        if (toCity == null) toCity = toInput;

        addFlight(fromCity, toCity, cost);
        System.out.println("Flight " + fromCity + " <-> " + toCity + " (cost " + cost + ") added/updated.");
        graphChanged = true;
    }

    private void removeCityLogic() {
        currentPhase = 0;
        System.out.println("\nCity name to remove:");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("City cannot be empty.");
            return;
        }
        String city = resolveCity(input);
        if (city == null) {
            System.out.println("City '" + input + "' not found.");
            return;
        }
        graph.removeVertex(city);
        System.out.println("City '" + city + "' and its flights removed.");
        graphChanged = true;
    }

    private void removeFlightLogic() {
        currentPhase = 0;
        System.out.println("\nOrigin city:");
        String fromInput = scanner.nextLine().trim();
        System.out.println("Destination city:");
        String toInput = scanner.nextLine().trim();
        if (fromInput.isEmpty() || toInput.isEmpty()) {
            System.out.println("Cities cannot be empty.");
            return;
        }
        String fromCity = resolveCity(fromInput);
        String toCity = resolveCity(toInput);
        if (fromCity == null || toCity == null) {
            System.out.println("Flight " + fromInput + " <-> " + toInput + " not found.");
            return;
        }

        boolean removed = graph.removeEdge(fromCity, toCity);
        removed = graph.removeEdge(toCity, fromCity) || removed;
        if (removed) {
            System.out.println("Flight " + fromCity + " <-> " + toCity + " removed.");
            graphChanged = true;
        } else {
            System.out.println("Flight " + fromCity + " <-> " + toCity + " not found.");
        }
    }

    private void printGraph() {
        SimpleList<String> cities = graph.vertices();
        if (cities.size() == 0) {
            System.out.println("\nNo cities loaded.");
            return;
        }
        System.out.println("\nFlight map (city -> city: cost):");
        for (int i = 0; i < cities.size(); i++) {
            String from = cities.get(i);
            SimpleList<Edge<String>> flights = graph.getNeighbors(from);
            for (int j = 0; j < flights.size(); j++) {
                Edge<String> flight = flights.get(j);
                System.out.println(from + " -> " + flight.destination + ": " + flight.weight);
            }
        }
    }
}
