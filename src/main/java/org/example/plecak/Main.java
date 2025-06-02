package org.example.plecak;


public class Main {
    public static void main(String[] args) {

        Problem problem = new Problem(10, 1, 1, 10);
        System.out.println(problem);

        int capacity = 11;
        Result result = problem.solve(capacity);
        System.out.println(result);
    }
}
