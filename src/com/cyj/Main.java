package com.cyj;

import java.io.IOException;
import java.io.PrintStream;

import com.cyj.graph.Graph;

public class Main {
    public static void main(String[] args) throws IOException {
        Readfile reader = new Readfile("PatternIndexCFG.txt");
        Graph graph = reader.readFromFile();
        Verfier graphVerfier = new Verfier(graph);
        graphVerfier.buildSimplePaths();
        graphVerfier.printSimplePaths();
        graphVerfier.buildPrimePaths();

        System.out.println("\nPrime paths:");
        int count=0;
        PrintStream ps = new PrintStream("PatternIndexPrimePath.txt");
        for (SimplePath primePath : graphVerfier.getPrimePaths()) {
            System.setOut(ps);
            System.out.println(primePath);
            count++;
        }
        System.setOut(ps);
        System.out.println("number of prime path："+count);
        ps.close();
    }
}
