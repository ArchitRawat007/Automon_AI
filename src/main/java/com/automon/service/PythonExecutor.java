package com.automon.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class PythonExecutor {

    public String executePythonScript(String scriptName) {
        String result = "";
        try {
            String[] command = new String[]{"python", "src/main/resources/ai/" + scriptName}; // path to your Python scripts
            Process process = new ProcessBuilder(command).start();
            process.waitFor();

            // Read output from the Python script (if needed)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

