package com.example.swiss.re.service;

import java.util.*;

import static com.example.swiss.re.service.SRECAConstant.*;
import static com.example.swiss.re.service.SRECAService.*;

public class SRECAHelper {

    // process the graph to compute avg earning of direct reports
    public void processGraph(Map<String, List<String>> graph, Map<String, Employee> employees){
        List<String> queue = new ArrayList<>();
        queue.add(CEO);
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()){
            String uNode = queue.getFirst();
            visited.add(uNode);
            queue.remove(uNode);
            double earnings = 0;
            int num = 0;
            for(String vNode: graph.getOrDefault(uNode, new ArrayList<>())){
                earnings += Double.parseDouble(employees.get(vNode).getSalary());
                if (!visited.contains(vNode)) {
                    queue.add(vNode);
                }
                num++;
            }
            employees.get(uNode).setChildEarnings(earnings/num);
        }
    }

    // DFS traversal to get the max depth from CEO to intern
    public void DFS(Map<String, List<String>> graph, String uNode, List<String> visited, int pathCount){
        if (pathCount > maxPath){
            maxPath = pathCount;
            longestReporting = uNode;
        }
        if (visited.contains(uNode)) {
            return;
        }
        visited.add(uNode);
        for(String vNode: graph.getOrDefault(uNode, new ArrayList<>())) {
            if (!visited.contains(vNode))
                DFS(graph, vNode, visited, pathCount + 1);
        }
        visited.remove(uNode);
    }

}
