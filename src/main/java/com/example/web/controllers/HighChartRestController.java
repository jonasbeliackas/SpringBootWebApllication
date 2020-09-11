package com.example.web.controllers;


import com.example.web.repositorys.GraphMapInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HighChartRestController {
    @Qualifier("graphMap")
    @Autowired
    GraphMapInterface graphMapInterface;
    @GetMapping("/get-data")
    public ResponseEntity<Map<String, Double>> getPieChart() {

          return new ResponseEntity<>(graphMapInterface.getGraphData(), HttpStatus.OK);
    }



}

