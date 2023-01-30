import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	// Instance Variables
	private TabPane tabPane;
	private EntryPane entryPane;
	private ListPane listPane;
	private ArrayList<Entry> entries;
	public static final int WINSIZE_X = 500, WINSIZE_Y = 300;

	// Main method
	public static void main(String[] args) {
		launch(args);

	}

	// Start function
	public void start(Stage primaryStage) throws Exception {
		// Initialize instance variables
		StackPane root = new StackPane();
		entries = new ArrayList<Entry>();
		tabPane = new TabPane();
		entryPane = new EntryPane(entries);
		listPane = new ListPane(entries);

		// Create first tab to add entries
	    Tab tab1 = new Tab();
	    tab1.setText("Add Entry");
	    tab1.setContent(entryPane);

	    // Create second tab to display entries
	    Tab tab2 = new Tab();
	    tab2.setText("All Entries");
	    tab2.setContent(listPane);

	    tabPane.getSelectionModel().select(0);
	    tabPane.getTabs().addAll(tab1, tab2);

	    root.getChildren().add(tabPane);
	    Scene scene = new Scene(root, WINSIZE_X, WINSIZE_Y);
	    primaryStage.setTitle("Journaly: The Mental Health Diary");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

}
