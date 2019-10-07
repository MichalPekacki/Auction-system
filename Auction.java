import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class Auction {
	
	//Item item = new Item();
	
	private double startPrice;
	private double reservePrice;
	private Calendar closeDate;
	private char status;

	private ArrayList hBidder = new ArrayList();
	private ArrayList seller = new ArrayList();
	private Vector<Double> sPrice = new Vector<Double>();
	private Vector<Double> rPrice = new Vector<Double>();
	private Vector<Calendar> cDate = new Vector<Calendar>();
	private ArrayList Istatus = new ArrayList();

	public void AuctionTesting() {		
		// hard coded auctions for testing
		sPrice.add(3.0);
		rPrice.add(4.0);
		cDate.add(Calendar.getInstance());
		cDate.get(0).add(Calendar.SECOND, 45);
		Istatus.add(true);
		hBidder.add("test");
		seller.add("test");

		sPrice.add(3.0);
		rPrice.add(4.0);
		cDate.add(Calendar.getInstance());
		cDate.get(1).add(Calendar.SECOND, 60);
		Istatus.add(true);
		hBidder.add("test");
		seller.add("test");

		sPrice.add(3.0);
		rPrice.add(4.0);
		cDate.add(Calendar.getInstance());
		cDate.get(2).add(Calendar.SECOND, 80);
		Istatus.add(true);
		hBidder.add("test");
		seller.add("test");

	}

	public void PlaceBid(String search, Double bid, String username, Item item) {
		int count = 0;
		//search for auction
		for (int i = 0; i < item.getDescription().size(); i++) {
			String check = item.getDescription().get(i).toString();
			if (check.toUpperCase().equals(search.toUpperCase())) {
				count++;
				//Check auction is active
				if (Istatus.get(i).equals(true)){
				String price = sPrice.get(i).toString();
				double d = Double.parseDouble(price);
				//check new bid is greater
				if (bid > d) {
					//update bid and highest bidder
					sPrice.set(i, bid);
					hBidder.set(i, username);
					System.out.println("Your bid has been succesfully placed!");
				}
				else {
					System.out.println("Your bid was too low!");
				}
			}
				else {
					System.out.println("That item is blocked for bidding, sorry!");
				}
			}

		}
		if (count==0){
			System.out.println("Sorry, no auctions were found with that name!!");
		}

	}

	public void Verify(double d, double e, Calendar g, String username) {

		sPrice.add(d);

		rPrice.add(e);

		cDate.add(g);

		Istatus.add(true);

		hBidder.add(username);
		
		seller.add(username);
		
		

		
	}
	
	public void Close(Calendar ctime, Item j) {
		for (int i = 0; i < cDate.size(); i++) {
			if (cDate.get(i).getTime().before(ctime.getTime())){
				//Delete the Auction + Winner bidder
				System.out.println(" ");
				if (sPrice.get(i) >= rPrice.get(i)){
					System.out.println("Auction " + j.getDescription().get(i) + " has ended, winner is: " + hBidder.get(i));
				}
				else{
					System.out.println("Auction " + j.getDescription().get(i) + " has expired, reserve price not met");
				}
				
				sPrice.remove(i);
				rPrice.remove(i);
				cDate.remove(i);
				Istatus.remove(i);
				hBidder.remove(i);
				seller.remove(i);
				j.removeItem(i);
			}
		}
	}

	public static boolean isBlocked(int i) {
		
		
		return false;
		
		
		
		
		

	}

	public void SetBlocked(String username, String blocker, Item item) {
		int count=0;
		
		for (int i = 0; i < seller.size(); i++) {
			String check = seller.get(i).toString();
			if (Istatus.get(i).equals(true)){
			if (username.toUpperCase().equals(check.toUpperCase())) {
			
				if (item.getDescription().get(i).toString().equals(blocker)){
					Istatus.set(i, false);
					count++;
					System.out.println(" ");
					System.out.println("Your auction has been blocked");
					System.out.println(" ");
				}
			}
			

			}
			else if (Istatus.get(i).equals(false)){
				if (username.toUpperCase().equals(check.toUpperCase())) {
				
					if (item.getDescription().get(i).toString().equals(blocker)){
						Istatus.set(i, true);
						count++;
						System.out.println(" ");
						System.out.println("Your auction has been unblocked");
						System.out.println(" ");
					}
				}
				

				}
				
			}
				
		if  (count==0){
			System.out.println("You have no auctions with that name!!");
		}
		

	}

	public Vector<Double> getsPrice() {
		return sPrice;
	}

	public Vector<Double> getrPrice() {
		return rPrice;
	}

	public Date getcDate(int i) {
		Date c = cDate.get(i).getTime();
		return c;
	}

	public ArrayList getIstatus() {
		return Istatus;
	}
	public ArrayList getseller(){
		return seller;
	}
	
	
	}

