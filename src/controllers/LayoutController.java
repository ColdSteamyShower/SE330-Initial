/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author abest
 */
public class LayoutController implements Initializable {
    
    @FXML // main filesystem panel
    private TreeView fileTree;
    
    @FXML // reference to main window
    private AnchorPane anchorpane;
        
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Defaults
        TreeItem<String> rootItem = new TreeItem<>("Sound Files");
        
        TreeItem<String> branchItem1 = new TreeItem<>("Pictures");
        TreeItem<String> branchItem2 = new TreeItem<>("Videos");
        TreeItem<String> branchItem3 = new TreeItem<>("Test");
        
        rootItem.getChildren().addAll(branchItem1,branchItem2,branchItem3);
        
        fileTree.setRoot(rootItem);
    }
    
    //TODO: For selecting a TreeView item
    public void selectItem() {
        
    }
    
    public void loadTree() {
        
    }

    
    // Dump files from within VPK into {local folder}\vpk_files
    public void openVPK() throws IOException {
        
        // Open a filechooser window for VPK file
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open .vpk");
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        
        // Output path of selected file
        String vpkFilePath = filechooser.showOpenDialog(stage).toPath().toString();
        System.out.println("Selected filepath: " + vpkFilePath);
        
        // run decompiler on filepath
        decompilerController decompiler = new decompilerController();        
        decompiler.decompileSounds(vpkFilePath, System.getProperty("user.dir") + "\\vpk_files", false);
    }
    
    public void compileVPK() {
        String homePath = System.getProperty("user.dir");
        File source = new File(homePath + "\\vpk_files\\");
        File dest = new File(homePath + "\\pak_compiler\\pak01_dir");
        try {
            FileUtils.copyDirectory(source, dest);
            // Create command string for running Decompiler command
            String command = 
                    homePath + "\\pak_compiler\\vpk.exe pak01_dir -M";

            System.out.println("Executing: " + command);

            // Execute with windows runtime
            Process process = Runtime.getRuntime().exec(command);

            // Print response if succeeded
            process.getOutputStream().close();
        } catch (IOException e) {
        }
        
        
    }
}
