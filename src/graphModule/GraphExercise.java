package graphModule;

import application.Exercise;
import java.util.Scanner;

public class GraphExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;

    private Graph<String> graph;

    public GraphExercise(Scanner scanner) {
        super(scanner);
        graph = new ListGraph<>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic(); break;
            case 1: addVertexLogic(); break;
            case 2: removeVertexLogic(); break;
            case 3: addEdgeLogic(); break;
            case 4: removeEdgeLogic(); break;
            case 5: containsVertexLogic(); break;
            case 6: containsEdgeLogic(); break;
            case 7: getWeightLogic(); break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Graph Exercise.");
        } else {
            printGraph();
        }

        System.out.println("\nChoose an option:"
            + "\nav - Add vertex"
            + "\nrv - Remove vertex"
            + "\nae - Add edge"
            + "\nre - Remove edge"
            + "\ncv - Contains vertex"
            + "\nce - Contains edge"
            + "\ngw - Get weight of an edge"
            + "\nmm - Main Menu"
        );

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "av": currentPhase = 1; break;
            case "rv": currentPhase = 2; break;
            case "ae": currentPhase = 3; break;
            case "re": currentPhase = 4; break;
            case "cv": currentPhase = 5; break;
            case "ce": currentPhase = 6; break;
            case "gw": currentPhase = 7; break;
            case "mm": running = false; break;
            default:
                System.out.println("Invalid input, try again.");
                break;
        }
    }

    private void addVertexLogic() {
        System.out.println("\nEnter the vertex name:");
        String vertex = scanner.nextLine().trim();
        if (vertex.isEmpty()) {
            System.out.println("Vertex cannot be empty.");
            currentPhase = 0;
            return;
        }
        boolean added = graph.addVertex(vertex);
        if (added) {
            System.out.println("Vertex '" + vertex + "' added.");
        } else {
            System.out.println("Vertex '" + vertex + "' already exists.");
        }
        currentPhase = 0;
    }

    private void removeVertexLogic() {
        System.out.println("\nEnter the vertex name to remove:");
        String vertex = scanner.nextLine().trim();
        if (vertex.isEmpty()) {
            System.out.println("Vertex cannot be empty.");
            currentPhase = 0;
            return;
        }
        boolean removed = graph.removeVertex(vertex);
        if (removed) {
            System.out.println("Vertex '" + vertex + "' removed.");
        } else {
            System.out.println("Vertex '" + vertex + "' not found.");
        }
        currentPhase = 0;
    }

    private void addEdgeLogic() {
        System.out.println("\nEnter the origin vertex:");
        String from = scanner.nextLine().trim();
        System.out.println("Enter the destination vertex:");
        String to = scanner.nextLine().trim();
        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Vertices cannot be empty.");
            currentPhase = 0;
            return;
        }

        System.out.println("Enter the weight (integer):");
        String weightInput = scanner.nextLine().trim();
        int weight;
        try {
            weight = Integer.parseInt(weightInput);
        } catch (NumberFormatException e) {
            System.out.println("Weight must be a valid integer.");
            currentPhase = 0;
            return;
        }

        boolean changed = graph.addEdge(from, to, weight);
        if (changed) {
            System.out.println("Edge " + from + " -> " + to + " (weight " + weight + ") added/updated.");
        } else {
            System.out.println("Edge " + from + " -> " + to + " already exists with the same weight.");
        }
        currentPhase = 0;
    }

    private void removeEdgeLogic() {
        System.out.println("\nEnter the origin vertex:");
        String from = scanner.nextLine().trim();
        System.out.println("Enter the destination vertex:");
        String to = scanner.nextLine().trim();
        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Vertices cannot be empty.");
            currentPhase = 0;
            return;
        }
        boolean removed = graph.removeEdge(from, to);
        if (removed) {
            System.out.println("Edge " + from + " -> " + to + " removed.");
        } else {
            System.out.println("Edge " + from + " -> " + to + " not found.");
        }
        currentPhase = 0;
    }

    private void containsVertexLogic() {
        System.out.println("\nEnter the vertex name:");
        String vertex = scanner.nextLine().trim();
        if (vertex.isEmpty()) {
            System.out.println("Vertex cannot be empty.");
            currentPhase = 0;
            return;
        }
        System.out.println(graph.containsVertex(vertex)
            ? "Vertex '" + vertex + "' exists."
            : "Vertex '" + vertex + "' does not exist.");
        currentPhase = 0;
    }

    private void containsEdgeLogic() {
        System.out.println("\nEnter the origin vertex:");
        String from = scanner.nextLine().trim();
        System.out.println("Enter the destination vertex:");
        String to = scanner.nextLine().trim();
        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Vertices cannot be empty.");
            currentPhase = 0;
            return;
        }
        System.out.println(graph.containsEdge(from, to)
            ? "Edge " + from + " -> " + to + " exists."
            : "Edge " + from + " -> " + to + " does not exist.");
        currentPhase = 0;
    }

    private void getWeightLogic() {
        System.out.println("\nEnter the origin vertex:");
        String from = scanner.nextLine().trim();
        System.out.println("Enter the destination vertex:");
        String to = scanner.nextLine().trim();
        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Vertices cannot be empty.");
            currentPhase = 0;
            return;
        }
        if (!graph.containsEdge(from, to)) {
            System.out.println("Edge " + from + " -> " + to + " does not exist.");
        } else {
            System.out.println("Weight of " + from + " -> " + to + ": " + graph.getWeight(from, to));
        }
        currentPhase = 0;
    }

    private void printGraph() {
        Object[] vertices = graph.vertices();
        if (vertices.length == 0) {
            System.out.println("\nGraph is empty.");
            return;
        }

        System.out.println("\nGraph (vertex -> neighbour(weight)):");
        for (int i = 0; i < vertices.length; i++) {
            String from = (String) vertices[i];
            String line = from + " ->";
            boolean hasEdges = false;
            for (int j = 0; j < vertices.length; j++) {
                String to = (String) vertices[j];
                if (graph.containsEdge(from, to)) {
                    line += " " + to + "(" + graph.getWeight(from, to) + ")";
                    hasEdges = true;
                }
            }
            if (!hasEdges) line += " (no outgoing edges)";
            System.out.println(line);
        }
    }
}
