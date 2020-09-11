package com.example.web.model;

import com.example.web.repositorys.GraphMapInterface;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GraphMap implements GraphMapInterface {
    private Map<String, Double> graphData;


    public Map<String, Double> getGraphData() {
        return graphData;
    }

    public void setGraphData(Map<String, Double> graphData) {
        this.graphData = graphData;
    }
}
