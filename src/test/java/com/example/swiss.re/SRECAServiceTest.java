package com.example.swiss.re;


import com.example.swiss.re.service.Employee;
import com.example.swiss.re.service.OUT;
import com.example.swiss.re.service.SRECAHelper;
import com.example.swiss.re.service.SRECAService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.*;

import static com.example.swiss.re.service.SRECAConstant.*;
import static org.junit.jupiter.api.Assertions.*;

class SRECAServiceTest {

    private SRECAService service;

    @BeforeEach
    void setup() throws Exception {
        service = new SRECAService();

        // Reset static values
        CEO = "1";
        maxPath = 0;
        longestReporting = "";

        // Inject test data using reflection
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Employee> employees = new HashMap<>();

        // CEO
        employees.put("1", new Employee("1", "CEO", "CEO", "100000", "1"));

        // Manager
        employees.put("2", new Employee("2", "Manager", "IT", "50000", "1"));
        graph.put("1", List.of("2"));

        // Employee
        employees.put("3", new Employee("3", "Dev", "IT", "20000", "2"));
        graph.put("2", List.of("3"));

        // Set fields via reflection
        setPrivateField("graph", graph);
        setPrivateField("employees", employees);

        // Process child earnings
        new SRECAHelper().processGraph(graph, employees);
    }

    private void setPrivateField(String fieldName, Object value) throws Exception {
        var field = SRECAService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(service, value);
    }

    private Object invokePrivateMethod(String methodName) throws Exception {
        Method method = SRECAService.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(service);
    }

    @Test
    void testManagerWithLessEarnings() throws Exception {
        List<OUT> result =
                (List<OUT>) invokePrivateMethod("getManagerWithLessEarnings");

        assertTrue(result.isEmpty());
    }

    @Test
    void testManagerWithMoreEarnings() throws Exception {
        List<OUT> result =
                (List<OUT>) invokePrivateMethod("getManagerWithMoreEarnings");

        assertTrue(!result.isEmpty());
        assertEquals("1", result.getFirst().getEmployee().getId());
    }

    @Test
    void testLongestReportingPath() throws Exception {
        String employeeId =
                (String) invokePrivateMethod("getEmployeeWithLongestReportingPath");

        assertEquals("3", employeeId);
        assertEquals(2, maxPath);
    }
}
