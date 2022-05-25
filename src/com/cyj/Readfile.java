package com.cyj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cyj.graph.Graph;
import com.cyj.graph.Edge;
import com.cyj.graph.Node;



public class Readfile {
    public static final Pattern	EDGE_PATTERN	= Pattern.compile("(\\d+) (\\d+)");
    private final String filename;

    public Readfile(String filename) {
        this.filename = filename;
    }

    public Graph readFromFile() {
        Graph result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            result = new Graph();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                Matcher matcher = EDGE_PATTERN.matcher(currentLine);
                if (!matcher.matches()) {
                    throw new UnsupportedOperationException();
                }
                String sourceNodeId = matcher.group(1);
                String targetNodeId = matcher.group(2);
                Node source = result.getNodesById().computeIfAbsent(sourceNodeId, Node::new);
                Node target = result.getNodesById().computeIfAbsent(targetNodeId, Node::new);
                Edge edge = new Edge(source, target);
                result.getEdges().add(edge);

            }

        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
