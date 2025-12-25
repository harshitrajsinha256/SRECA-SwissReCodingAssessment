Below is a **clean, interview-ready README.md** tailored **exactly** to:

* ✅ Your **problem statement**
* ✅ Your **SRECAService implementation**
* ✅ Your **pom.xml (Spring Boot + Maven + Java 25)**
* ✅ Swiss Re expectations (simplicity, readability, correctness)

You can **copy-paste this directly** into your repo.

---

# SRECA – Swiss Re Coding Assessment

This repository contains a Java implementation of **Code Exercise 106**, submitted as part of the **Swiss Re Coding Assessment**.
The goal of the exercise is to analyze a company’s organizational structure and identify potential improvements related to **manager compensation** and **reporting line depth**.

---

## 📌 Problem Statement

BIG COMPANY employs a large number of employees and wants to analyze its organizational structure.

The company wants to ensure:

1. **Manager Salary Rules**

   * Every manager should earn **at least 20% more** than the **average salary of their direct subordinates**
   * No manager should earn **more than 50% more** than that average

2. **Reporting Line Rule**

   * Employees should not have **more than 4 managers** between themselves and the CEO

The input is a **CSV file** containing employee information (up to 1000 rows).

### Required Output

The program must report:

* Managers who earn **less than they should**, and by how much
* Managers who earn **more than they should**, and by how much
* Employees with **too long reporting lines**, and by how much

---

## 📂 CSV File Format

Each row represents one employee:

```
Id,Name,Department,Salary,ManagerId
```

* The **CEO has no ManagerId**
* Salaries are treated as numeric values
* The CSV file must be placed in the **resources folder**

Example:

```
Id,Name,Department,Salary,ManagerId
1,John Doe,Management,100000
2,Alice Smith,IT,50000,1
3,Bob Brown,IT,20000,2
```

---

## 🛠️ Tech Stack & Constraints

| Technology | Details                        |
| ---------- | ------------------------------ |
| Language   | Java SE (Java 25)              |
| Build Tool | Maven                          |
| Framework  | Spring Boot (no web APIs used) |
| Testing    | JUnit                          |
| Input      | CSV file                       |
| Output     | Console                        |
| GUI        | ❌ Not required                 |

---

## 📁 Project Structure

```
swiss.re/
├── src/
│   ├── main/java/com/example/swiss/re
│   │   ├── service/
│   │   │   ├── SRECAService.java
│   │   │   ├── SRECAHelper.java
│   │   │   ├── Employee.java
│   │   │   ├── OUT.java
│   │   │   └── SRECAConstant.java
│   └── test/java/
│       └── com/example/swiss/re/service/
│           └── SRECAServiceTest.java
├── src/main/resources/
│   └── fileName.csv
├── pom.xml
└── README.md
```

---

## 🧠 Solution Approach

### 1️⃣ Reading & Parsing Data

* CSV file is read from the **classpath**
* Employees are stored in a `Map<String, Employee>`
* A **manager → subordinates graph** is built using an adjacency list

### 2️⃣ Salary Analysis

* For each manager:

  * Calculate the **average salary of direct subordinates**
  * Compare against:

    * **+20% threshold (minimum)**
    * **+50% threshold (maximum)**

### 3️⃣ Reporting Line Analysis

* A **DFS traversal** is performed starting from the CEO
* The depth of each reporting path is calculated
* Employees exceeding **4 levels** are flagged

---

## 🧪 Testing

* Unit tests are written using **JUnit**
* Business logic is validated independently of file reading
* Edge cases (CEO, leaf employees, deep hierarchies) are covered

Run tests using:

```bash
mvn test
```

---

## ▶️ How to Run the Application

1. Place the CSV file at:

```
src/main/resources/fileName.csv
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application (via main class or Spring context):

```bash
mvn spring-boot:run
```

---

## 🖥️ Sample Console Output

```
Manager with less Earning: [Employee{id=2, diff=5000}]
Manager with More Earning: []
Employee with longest Reporting: 7 with reporting length: 5
```

---

## ⚠️ Assumptions Made

* Salary values are valid numeric strings
* Each employee has **only one manager**
* CSV structure is consistent
* CEO is uniquely identifiable (row without manager)
* Reporting depth is counted as number of edges from CEO

All assumptions are made sensibly as permitted by the problem statement.

---

## ⭐ Code Quality Notes

* Focused on **readability and simplicity**
* Business logic separated from traversal logic
* No unnecessary frameworks or UI
* Clear naming and minimal dependencies

---

## 📜 License

This project is intended **only for assessment and evaluation purposes** as part of the Swiss Re hiring process.

---

If you want, I can also:

* ✅ Review this README from an **interviewer’s perspective**
* ✅ Improve test coverage explanation
* ✅ Refactor service for better testability
* ✅ Add complexity analysis section

Just say the word 👍
