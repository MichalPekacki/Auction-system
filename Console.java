
import java.util.Calendar;
import java.util.Scanner;

class CurrentTime implements Runnable {
	   private Thread t;
	   private String threadName;
	   private Calendar ctime = Calendar.getInstance();
	   Auction a = new Auction();
	   Item k = new Item();
	   
	   CurrentTime( String name, Auction num, Item id) {
	      threadName = name;
	      a = num;
	      k = id;
	      }
	   
	   public void run() {
	      while (true){
	    	  ctime = Calendar.getInstance();
	    	  a.Close(ctime, k);
	      }
	   }
	   
	   public void start () {
	      if (t == null) {
	         t = new Thread (this);
	         t.start ();
	      }
	   }
	}


public class Console {
	private static final Scanner S = new Scanner(System.in);
	
	User user = new User();
	Seller seller = new Seller();
	Auction auction = new Auction();
	Item item = new Item();
	CurrentTime cTime = new CurrentTime("Time Thread", auction, item);
	
	static boolean logedOn = false;

	public void run() {
	    cTime.start();
	    
		String choice = "";

		System.out.println("---Auction System---");
		System.out.println();

		// Adds users and auctions to the console (hard coding for testing)
		auction.AuctionTesting();
		user.testSetup();
		item.testSetup();

		do {
			System.out.println("-- MAIN MENU --");
			System.out.println("1 - Browse Auctions And Bid (You must be logged in to bid) ");
			if (logedOn == false) {
				System.out.println("2 - Setup Acount / Login");
			}
			if (logedOn == true) {
				System.out.println("3 - list an item");
				System.out.println("4 - Log Off");
				System.out.println("5 - Block/unblock your auction");

			}
			System.out.println("Q - Quit");
			System.out.println(" ");
			System.out.print("Pick : ");

			choice = S.next().toUpperCase();

			switch (choice) {
			case "1": {
				browseAuction();
				break;
			}
			case "2": {
				setupAccount();
				break;
			}
			case "3": {
				placeAuction();
				break;
			}
			case "4": {
				logOff();
				break;
			}
			case "5": {
				int i = 0;
				int p = 0;
				int count = 0;

				for (i = 0; i < item.getDescription().size(); i++) {
					if (auction.getseller().get(i).equals(user.username)) {
						if (auction.getIstatus().get(i).equals(true)) {
							while (p == 0) {
								System.out.println(" ");
								System.out.println("---The active auctions you currently have are:---");
								p++;
							}
							System.out.println(" ");
							count++;
							System.out.println(item.getDescription().get(i));
						}
					}
				}
				p = 0;

				for (i = 0; i < item.getDescription().size(); i++) {
					if (auction.getseller().get(i).equals(user.username)) {
						if (auction.getIstatus().get(i).equals(false)) {
							while (p == 0) {
								System.out.println(" ");
								System.out.println("---The blocked auctions you currently have are:---");
								p++;
							}
							System.out.println(" ");
							count++;
							System.out.println(item.getDescription().get(i));

						}
					}
				}
				if (count == 0) {
					System.out.println(" ");
					System.out.println("You currently have no auctions!!!");
					System.out.println(" ");
					break;
				}

				System.out.println(" ");
				System.out.println("---Enter the description of the item you wish to block/unblock or q to quit---");
				String blocker = S.next();
				if (blocker.toUpperCase().equals("Q")) {
					break;
				}
				seller.setBlocked(user.username, blocker, item, auction);
				break;
			}
			}
		} while (!choice.equals("Q"));

		// always advisable to close scanners
		S.close();

		System.out.println("Bye");

		System.exit(0);
	}

	public void browseAuction() {
		System.out.println(" ");
		System.out.println("---Active Auctions---");

		for (int i = 0; i < item.getDescription().size(); i++) {
			if (auction.getIstatus().get(i).equals(true)) {
				System.out.println(" ");
				System.out.println("--------------");
				System.out.println("Description:" + " " + item.getDescription().get(i));
				System.out.println("Current Price:" + " " + "£" + auction.getsPrice().get(i));
				System.out.println("Reserve Price:" + " " + "£" + auction.getrPrice().get(i));
				System.out.println("Finishing Date:" + " " + auction.getcDate(i));
				System.out.println("--------------");
				System.out.println(" ");

			}

		}

		if (logedOn == true) {

			System.out.println("To bid on an item, enter its description. To return to the menu, press q");
			String search = S.next();

			if (!search.toUpperCase().equals("Q")) {
				System.out.println("Enter your bid");
				Double bid = S.nextDouble();
				auction.PlaceBid(search, bid, user.username, item);
			}
		}

	}

	public void setupAccount() {
		String input;
		String userIn;
		String passIn;
		System.out.println(" ");
		System.out.print("Insert Username: ");
		userIn = S.next();
		System.out.println(" ");
		System.out.print("Insert Password: ");
		passIn = S.next();

		if (user.checkPassword(userIn, passIn) == false) {
			System.out.println("Wrong username or password, try again.");
			System.out.println("Would you like to make an account? (yes/no)");
			input = S.next();
			if (input.equals("yes")) {
				user.user.add(userIn);
				user.pass.add(passIn);
				user.checkPassword(userIn, passIn);
				logedOn = true;
				System.out.println("You have successfully setup an account");
				System.out.println(user.username + " " + "you are logged in");
			} else {
				System.out.println("Try logging in again");
			}
		} else {
			user.checkPassword(userIn, passIn);
			logedOn = true;
			System.out.println(" ");
			System.out.println(user.username + ", " + "you are logged in");
			System.out.println(" ");
		}
	}

	public void logOff() {
		user.username = null;
		user.password = null;
		logedOn = false;
	}

	public void placeAuction() {
		Calendar g = Calendar.getInstance();

		System.out.println("Enter a description of the item. (With no spaces)");
		String c = S.next();
		System.out.println("Enter a starting price for the item.");
		double d = S.nextDouble();

		int p = 0;
		int tester = 0;
		double e = 0;
		while (tester == 0) {
			System.out.println("Enter a reserve price for the item.");
			e = S.nextDouble();
			if (e < d) {
				System.out.println("Enter a reserve price that is greater than the starting price!");
			} else {
				tester = 1;
			}
		}
		int f = 8;
		do {

			System.out.println("How many days do you want your auction to last? (max 7) ");
			f = S.nextInt();
		} while (f > 7);
		g.add(Calendar.DATE, f); // Adding number of days
		System.out.println("Your auction will end on" + " " + g.getTime().toString());
		System.out.format("your starting price is " + " " + "£%,.2f", d);
		System.out.format("\n" + "your reserve price is " + "£%,.2f" + "\n", e);

		System.out.println("Would you like to verify this auction?(yes/no)");

		String input = S.next();
		if (input.toUpperCase().equals("YES")) {
			item.setDescription(c);
			auction.Verify(d, e, g, user.username);
			System.out.println("Your auction has begun");
		} else {
			System.out.println("Returning to the menu");
		}

	}
}
