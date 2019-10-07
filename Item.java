import java.util.ArrayList;

public class Item {
	private String description;

	@SuppressWarnings("rawtypes")
	private ArrayList item = new ArrayList();

	@SuppressWarnings("unchecked")
	public void testSetup() {
		item.add("coolhat");
		item.add("ljmu");
		item.add("asparagus");
	}

	public ArrayList getDescription() {
		return item;
	}

	@SuppressWarnings("unchecked")
	public void setDescription(String description) {
		this.description = description;

		if (!item.contains(description)) {
			// method will accept anything that inherits from class object
			item.add(description);
			
		}
	}
	
	public void removeItem(int i) {
		item.remove(i);
	}
}
