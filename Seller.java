
public class Seller extends User {
	public boolean isBlocked(){
		return false;
	}
	public void setBlocked(String username, String blocker, Item item, Auction a){
		
		a.SetBlocked(username, blocker, item);
		
	}
}
