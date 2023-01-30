import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ListPane extends BorderPane{
	// Instance Variables
	private ArrayList<Entry> entryList;
	private Label dispWordCount;
	private int totalWordCount;
	private Button loadEntry;
	private VBox entries;
	
	// Constructors
	public ListPane(ArrayList<Entry> entryList) {
		this.entryList = entryList;
		// Word count and entries
		dispWordCount = new Label();
		entries = new VBox();
		
		// Load entry button
		loadEntry = new Button("Load All Previous Entries");
		loadEntry.setOnAction(new LoadEntryButtonHandler());
		
		setTop(dispWordCount);
		setCenter(entries);
		setBottom(loadEntry);
	}
	
	// Handler for Button
	private class LoadEntryButtonHandler implements EventHandler<ActionEvent>{
		// Adds all entries in entryList to a separate check box
		public void handle(ActionEvent event) {
			entries.getChildren().clear();
			for(int i = 0; i < entryList.size(); i++) {
				CheckBox e = new CheckBox(entryList.get(i).toString());
				e.setOnAction(new CheckBoxHandler(entryList.get(i)));
				entries.getChildren().add(e);
			}
			
		}
		
	}
	
	// Handler for CheckBox
	private class CheckBoxHandler implements EventHandler<ActionEvent>{
		// Instance Variable
		private Entry entry;
		
		// Constructor
		public CheckBoxHandler(Entry e) {
			entry = e;
		}
		
		// If selected, will add to totalWordCount to be displayed. If not, will be subtracted.
		public void handle(ActionEvent event) {
			CheckBox c = (CheckBox)event.getSource();
			if(c.isSelected()) {
				totalWordCount += entry.getWordCount();
			}
			else {
				totalWordCount -= entry.getWordCount();
			}
			dispWordCount.setText("Total Word Count: " + totalWordCount);
		}
	}

}
