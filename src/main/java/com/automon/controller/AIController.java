package com.automon.controller;

import com.automon.service.PythonExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final PythonExecutor pythonExecutor;

    @Autowired
    public AIController(PythonExecutor pythonExecutor) {
        this.pythonExecutor = pythonExecutor;
    }

    @GetMapping("/train-model")
    public String trainModel() {
        // Call the Python script to train your model
        String result = pythonExecutor.executePythonScript("/Users/architrawat/IdeaProjects/demo/resources/ai/train.py");
        return result;
    }

    @GetMapping("/predict")
    public String predict() {
        // Call the Python script for prediction
        String result = pythonExecutor.executePythonScript("/Users/architrawat/IdeaProjects/demo/resources/ai/predict.py");
        return result;
    }
}

