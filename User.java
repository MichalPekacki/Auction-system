
import java.util.Vector;

public class User {
	protected String username;
	protected String password;
	
	Vector<String> user = new Vector<String>();
	Vector<String> pass = new Vector<String>();
	
	public void testSetup(){
		user.add("test");
		user.add("admin");
		pass.add("password");
		pass.add("admin");
	}
	
	public boolean checkPassword(String u, String s){
		for (int i=0;i<user.size();i++) {
		    if (user.get(i).equals(u)) {
		        username = u;
		        if (pass.get(i).equals(s)) {
			        password = s;
			    }
		    }
		}
		if (username != null && password != null){
			return true;
		}
		else{
			return false;
		}
	}
}
