public class Entry {
	// Instance Variables
	private String entry;
	private int wordCount;
	private int rating;

	// Constructors
	public Entry() {
		entry = "";
		wordCount = 0;
		rating = 1;
	}
	public Entry(String e, int r) {
		entry = e;
		wordCount = wordCount(e);
		rating = r;
	}
	
	// Getters and Setters
	public String getEntry() {
		return entry;
	}
	
	public void setEntry(String e) {
		entry = e;
		wordCount = entry.length();
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int r) {
		rating = r;
	}
	
	public int getWordCount() {
		return wordCount(entry);
	}
	
	// To string method
	public String toString() {
		return entry + "\nRating: " + rating + "\nWord Count: " + wordCount;
	}
	
	// Calculates word count using split method
	public static int wordCount(String s){
		String[] sCount = s.split(" ");
		return sCount.length;
    }
}
