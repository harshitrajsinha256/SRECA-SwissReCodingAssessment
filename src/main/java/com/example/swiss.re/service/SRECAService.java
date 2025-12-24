package com.example.swiss.re.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.example.swiss.re.service.SRECAConstant.*;


@Service
public class SRECAService {

    private Map<String, List<String>> graph = new HashMap<>();
    private  Map<String, Employee> employees = new HashMap<>();
    private SRECAHelper helper = new SRECAHelper();

    // read the file and build the graph
    private void readFileAndBuildGraph(){
        String fileName="/fileName.csv";

        try (InputStream inputStream = SRECAService.class.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                System.out.println("Error: File not found in resources folder.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split(",");
                    if (split[0].equals("Id"))
                        continue;
                    if(split.length == 4){
                        // for ceo
                        CEO = split[0];
                        employees.put(split[0], new Employee(split[0], split[1], split[2], split[3], split[0]));
                    }else {
                        // for employee
                        graph.putIfAbsent(split[4], new ArrayList<>());
                        List<String> list = graph.get(split[4]);
                        list.add(split[0]);
                        graph.put(split[4],list);
                        employees.put(split[0], new Employee(split[0], split[1], split[2], split[3], split[4]));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during file reading: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // which managers earn less than they should, and by how much
    private List<OUT> getManagerWithLessEarnings(){
        List<OUT> out = new ArrayList<>();
        for(String employeeId: employees.keySet()){
            Employee employee = employees.get(employeeId);
            double per20avg = employee.getChildEarnings() + employee.getChildEarnings()*0.2;
            if(Double.parseDouble(employee.getSalary()) < per20avg ){
                out.add(new OUT(employee, per20avg - Double.parseDouble(employee.getSalary())));
            }
        }
        return out;
    }

    // which managers earn more than they should, and by how much
    private List<OUT> getManagerWithMoreEarnings(){
        List<OUT> out = new ArrayList<>();
        for(String employeeId: employees.keySet()){
            Employee employee = employees.get(employeeId);
            double per50avg = employee.getChildEarnings() + employee.getChildEarnings()*0.5;
            if(Double.parseDouble(employee.getSalary()) > per50avg ){
                out.add(new OUT(employee, per50avg - Double.parseDouble(employee.getSalary())));
            }
        }
        return out;
    }

    // which employees have a reporting line which is too long, and by how much
    private String getEmployeeWithLongestReportingPath(){
        helper.DFS(graph, CEO, new ArrayList<>(),0);
        return longestReporting;
    }

    public void process(){
        readFileAndBuildGraph();
        helper.processGraph(graph, employees);
        System.out.println("Manager with less Earning: " + getManagerWithLessEarnings());
        System.out.println("Manager with More Earning: " + getManagerWithMoreEarnings());
        System.out.println("Employee with longest Reporting: " + getEmployeeWithLongestReportingPath() +  " with reporting length: " + maxPath);
    }

}
