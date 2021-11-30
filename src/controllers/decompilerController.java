/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.TextArea;

/**
 *
 * @author abest
 */
public class decompilerController {
    private String decompilerLocation;
    
    public decompilerController() {
        decompilerLocation = // Store the path of the decompiler executable
                "\"" + System.getProperty("user.dir") + "\\decompiler\\Decompiler.exe\"";
    }
    
    /**
     * function that de-compiles a VPK file into a file tree. Currently only supports sound\weapons\hero directory to reduce de-compile
     * time and focus on the specific use case
     * 
     * @param pak_dir is the path to a VPK file
     * @param destination is the folder to unload files to
     * @param decompile denotes whether to keep inner files in default format or to de-compile them into MP3s
     * @throws IOException if there is some error with the fileStream for reading/writing from executable
    */
    public void decompileSounds(String pak_dir, String destination, boolean decompile) throws IOException {
        // Create command string for running Decompiler command
        String command = 
                decompilerLocation +
                " -i \"" + pak_dir + "\"" +
                " -o \"" + destination + "\"" +
                " -f sounds\\weapons\\hero\\ ";
        if (decompile) {
            command += " -d";
        }
        
        System.out.println("Executing: " + command);

        // Execute with windows runtime
        Process process = Runtime.getRuntime().exec(command);
        
        // Print response if succeeded
        process.getOutputStream().close();
        String line;
        System.out.println("Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
          process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
        }
        stdout.close();
        
        // Get Error message if failed
        System.out.println("Retrieved Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(
            process.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();
    }
   
    
}
