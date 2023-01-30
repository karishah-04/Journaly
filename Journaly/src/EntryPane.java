import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EntryPane extends HBox{
	// Instance Variables
	private ArrayList<Entry> entryList;
	private BorderPane borderPane;
	private HBox hTop;
	private HBox hBot;
	private Label ratingLabel;
	private ComboBox<String> ratings;
	private String selectedRating;
	private TextArea entry;
	private Label wordCount;
	private Button createEntry;
	private Label entryAdded;

	// Constructors
	public EntryPane(ArrayList<Entry> entryList) {
		this.entryList = entryList;
		
		borderPane = new BorderPane();
		
		hTop = new HBox();
		// Rating labels and rating ComboBox
		ratingLabel = new Label("How was your day today on a scale of 1-10?");		
		ratings = new ComboBox<String>();
		String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		ratings.setValue("Choose a number");
		ratings.getItems().addAll(nums);
		ratings.setOnAction(new RatingComboBoxHandler());
		hTop.getChildren().addAll(ratingLabel, ratings);
		hTop.setSpacing(5);
		
		// Entry text area
		entry = new TextArea();
		entry.setWrapText(true);
		entry.setOnKeyPressed(new EntryHandler());
		
		hBot = new HBox();
		// Word count and create entry button and label
		wordCount = new Label("Word Count is : 0");
		createEntry = new Button("Create Entry");
		createEntry.setOnAction(new CreateEntryButtonHandler());
		entryAdded = new Label("");
		
		hBot.getChildren().addAll(wordCount, createEntry, entryAdded);
		hBot.setSpacing(10);

		borderPane.setTop(hTop);
		borderPane.setCenter(entry);
		borderPane.setBottom(hBot);
		
		borderPane.setPadding(new Insets(10, 20, 10, 20));
		
		this.getChildren().addAll(borderPane);
		
	}
	
	// Handler for ComboBox
	private class RatingComboBoxHandler implements EventHandler<ActionEvent>{
		// Sets selected rating to a variable
		public void handle(ActionEvent event) {
			selectedRating = ratings.getSelectionModel().getSelectedItem();
		}
	}
	
	// Handler for TextArea
	private class EntryHandler implements EventHandler<KeyEvent>{
		// Updates word count when user is typing
		public void handle(KeyEvent event) {
			wordCount.setText("Word Count: " + Entry.wordCount(entry.getText()));
		}
		
	}
	
	// Handler for Button
	private class CreateEntryButtonHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			// Checks if rating was selected and entry was written. Then adds it to a new entry, adds entry to entryList, and clears the ComboBox
			// and TextArea. Implements try and catch to make sure everything works.
			if(event.getEventType() == ActionEvent.ACTION) {
				Entry e;
				try {
					if(selectedRating == null) {
						throw new Exception("No rating selected!");
					}
					String data = entry.getText().trim();
					if(data.equals("")) {
						throw new Exception("No entry written!");
					}
					int rate = Integer.parseInt(selectedRating);
					e = new Entry(data, rate);
					entryList.add(e);
					entryAdded.setText("Entry Added!");
					entry.clear();
					ratings.valueProperty().set(null);
					
				}
				catch(Exception exception) {
					entryAdded.setText(exception.getMessage());
				}
			}
		}
	}

}
