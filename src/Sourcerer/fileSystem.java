package Sourcerer;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
 
/**
 *
 *
 * @author abest
 */
public class fileSystem extends TreeItem<File> {
 

	public fileSystem(File f) {
		super(f);
	}
        
        private boolean isFirstTimeChildren = true;
	private boolean isFirstTimeLeaf = true;
	private boolean isLeaf;
 
	@Override
	public ObservableList<TreeItem<File>> getChildren() {
		if (isFirstTimeChildren) {
			isFirstTimeChildren = false;
 
			/*
			 * First getChildren() call, so we actually go off and determine the
			 * children of the File contained in this TreeItem.
			 */
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}
 
        // we build the tree recursively, so we need to be able to decide which are the leaves
	@Override
	public boolean isLeaf() {
		if (isFirstTimeLeaf) {
			isFirstTimeLeaf = false;
			File f = (File) getValue();
			isLeaf = f.isFile();
		}
 
		return isLeaf;
	}
 
        // Head of subsystem
	private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
		File f = TreeItem.getValue();
		if (f != null && f.isDirectory()) {
			File[] files = f.listFiles();
			if (files != null) {
				ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

				for (File childFile : files) {
					children.add(new fileSystem(childFile));
				}
 
				return children;
			}
		}
 
		return FXCollections.emptyObservableList();
	}
}