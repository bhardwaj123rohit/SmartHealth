package console;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Admin;
import entities.Moderator;
import entities.NUser;
import entities.User;



public class Main {

	public	static ArrayList<NUser>  nusers = new ArrayList<NUser>();
	public	static ArrayList<Moderator>  moderators = new ArrayList<Moderator>();
	public	static ArrayList<Admin>  admins = new ArrayList<Admin>();
	
	private static final String URL = "jdbc:mysql://localhost/smarthealthdb";
    private static final String USER = "root";
    private static final String PASSWORD = "2073AceHood";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 

	private static Connection createConnection() throws ClassNotFoundException {
		Class.forName(DRIVER_CLASS);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	

	public static void main(String[] args) throws ClassNotFoundException , NumberFormatException, IOException {

		int ch;
		User u = null;
		createAdmin();
		
		Connection con;
		try {
			con = createConnection();
			System.out.println(con);
			DatabaseMetaData md = con.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
			  System.out.println(rs.getString(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		do{
			System.out.println("<=============  Smart Health  ==============>");
			System.out.println("1.Login\n2.Register as User\n3.Register as Moderator\n4.Exit\nEnter your choice: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(br.readLine());
			if(ch==1)
			{


				System.out.println("Enter Primary Email:"); 
				String username = br.readLine();
				System.out.println("Enter Password:"); 
				String pass = br.readLine();
				u = loginAndGetType(username,pass);
				if (u==null){
					System.out.println("Authentication Faliure!!");
					continue;

				}
				if(u.getType().equals("new")||u.getType().equals("middle")||u.getType().equals("old") || u.getType().equals("moderator"))
				{
					int ch1;
					do
					{
						System.out.println("Logged in as "+username+"\n1.Update Profile\n2.Quit\n3.Logout\nEnter your choice: ");
						ch1 = Integer.parseInt(br.readLine());
						if(ch1==3)
						{
							u=null;
							System.out.println("Logged out.");
							break;
						}
						else if(ch1 == 1)
						{
							u.display();
							if(u.getType().equals("moderator"))
								updateModerator(u);
							else
								updateNUser(u);

						}
						else if(ch1 == 2)
						{
							u.quitUser();
							break;
						} 
						else
						{
							System.out.println("Invalid option");
						}
					}while(ch1!=2 || ch!=3);
				}
				else if(u.getType().equals("admin"))
				{
					System.out.println("Logged in as admin.");
					//u.display();
					int ch1=0;
					do
					{
						System.out.println("Logged in as "+username+"\n1.Display All \n2.Logout\nEnter your choice: ");
						ch1 = Integer.parseInt(br.readLine());

						if(ch1==2)
						{
							u=null;
							System.out.println("Logged out.");
							break;
						}
						else if(ch1 == 1)
						{
							u.display();

						}
						else
						{
							System.out.println("Invalid option");
						}
					}while(ch1!=2);
				}

				else {
					System.out.println("Invalid details !");
				}


			}	
			else if(ch==2)
			{
				createNUser();
			}
			else if(ch==3)
			{
				createModerator();
			}
			else if(ch==4)
			{
				System.exit(0);
			}
			else
			{
				System.out.println("No such choice");
			}
		}while(ch!=4 || u==null);

	}

	public static void createNUser() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter username:"); 
		String username = br.readLine();		
		System.out.println("Enter primary email:"); 
		String emailp = br.readLine();
		System.out.println("Enter password:"); 
		String pass = br.readLine();
		System.out.println("Enter firstname:"); 
		String fname = br.readLine();
		System.out.println("Enter lastname:"); 
		String lname = br.readLine();	

		//		//---------------------------------------
		//		//System.out.println("Enter emails:"); 
		//		String emails = "emails@email.com";//br.readLine();
		//		//	System.out.println("Enter paddress:"); 
		//		String paddress = "address";//br.readLine();
		//		//System.out.println("Enter about:"); 
		//		String about = "about";//br.readLine();
		//		//System.out.println("Enter url1:"); 
		//		String url1 = "url1";//br.readLine();
		//		//System.out.println("Enter url2:"); 
		//		String url2 = "url2";//br.readLine();
		//		//System.out.println("Enter url3:"); 
		//		String url3 = "url3";//br.readLine();
		//		//--------------------------------------
		//---------------------------------------
		System.out.println("Enter secondary email:"); 
		String emails = br.readLine();
		System.out.println("Enter address:"); 
		String paddress = br.readLine();
		System.out.println("Enter about you:"); 
		String about =br.readLine();
		System.out.println("Enter url1 for profile pic:"); 
		String url1 = br.readLine();
		System.out.println("Enter url2 for profile pic:"); 
		String url2 = br.readLine();
		System.out.println("Enter url3 for profile pic:"); 
		String url3 = br.readLine();
		//--------------------------------------

		if(checkValidEmail(emailp))
		{
			if(checkUsernameAndEmailAvailable(username, emailp))
			{
				NUser newnuser = new NUser(username,emailp,emails,fname,lname,paddress, about, pass,url1, url2, url3,"new","active",0);
				nusers.add(newnuser);
				System.out.println("Successfully registered.");
			}
			else
			{
				System.out.println("Username or email is already in use !!");
			}
		}
		else
		{
			System.out.println("Invalid email id !!");
		}



	}

	public static void createAdmin()
	{

		String username ="n";
		String emailp = "n@n.com";
		String pass = "1234";
		String fname = "Pappu";
		String lname = "Dance";	

		//---------------------------------------
		//System.out.println("Enter emails:"); 
		String emails = "emails";//br.readLine();
		//System.out.println("Enter paddress:"); 
		String paddress = "address";//br.readLine();
		//System.out.println("Enter about:"); 
		String about = "about";//br.readLine();
		//System.out.println("Enter url1:"); 
		String url1 = "url1";//br.readLine();
		//System.out.println("Enter url2:"); 
		String url2 = "url2";//br.readLine();
		//System.out.println("Enter url3:"); 
		String url3 = "url3";//br.readLine();
		//--------------------------------------

		if(checkValidEmail(emailp))
		{
			if(checkUsernameAndEmailAvailable(username, emailp) && username.length()<=20 && username.length()>=1 )
			{
				Admin newadmin = new Admin(username,emailp,emails,fname,lname,paddress, about, pass,url1, url2, url3,"admin","active","90133455198");
				admins.add(newadmin);
				System.out.println("Successfully registered admin");
			}
			else
			{
				System.out.println("Username or email is already in use !!");
			}
		}
		else
		{
			System.out.println("Invalid email !!");
		}



	} 
	public static void createModerator() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter username:"); 
		String username = br.readLine();	
		if(username.length()>20){
			System.out.println("User name should be less than 20 characters");
			return;
		}

		System.out.println("Enter primary email:"); 
		String emailp = br.readLine();
		System.out.println("Enter password:"); 
		String pass = br.readLine();
		System.out.println("Enter firstname:"); 
		String fname = br.readLine();
		System.out.println("Enter lastname:"); 
		String lname = br.readLine();	

		//---------------------------------------
		System.out.println("Enter secondary email:"); 
		String emails = br.readLine();
		System.out.println("Enter address:"); 
		String paddress = br.readLine();
		System.out.println("Enter about you:"); 
		String about =br.readLine();
		System.out.println("Enter url1 for profile pic:"); 
		String url1 = br.readLine();
		System.out.println("Enter url2 for profile pic:"); 
		String url2 = br.readLine();
		System.out.println("Enter url3 for profile pic:"); 
		String url3 = br.readLine();
		//--------------------------------------
		System.out.println("Enter contact number: "); 
		String contact = br.readLine();
		System.out.println("Enter qualifications:"); 
		String qual = br.readLine();
		if(checkValidEmail(emailp))
		{
			if(checkUsernameAndEmailAvailable(username, emailp) && username.length()<=20 && username.length()>=1)
			{
			//	Moderator newmod = new Moderator(username,emailp,emails,fname,lname,paddress, about, pass,url1, url2, url3,"moderator","active",qual,contact);
				//moderators.add(newmod);
				System.out.println("Successfully registered.");
			}
			else
			{
				System.out.println("Username or email is already in use !!");
			}
		}
		else
		{
			System.out.println("Invalid email id !!");
		}



	}

	public static boolean checkUsernameAndEmailAvailable(String username,String email)
	{
		for(int i=0;i<nusers.size();i++)
		{
			if(username.equals(nusers.get(i).getUsername()) || email.equals(nusers.get(i).getEmailp()))
			{
				return false;
			}

		}

		return true;
	}

	public static boolean checkValidEmail(String email)
	{
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(EMAIL_REGEX);
	}

	public static User loginAndGetType(String username,String pass)
	{
		for(int i=0;i<nusers.size();i++)
		{
			if(username.equals(nusers.get(i).getEmailp()) && pass.equals(nusers.get(i).getPass()) && nusers.get(i).getStatus().equals("active")  )
			{
				return nusers.get(i);
			}
		}

		for(int i=0;i<admins.size();i++)
		{
			if(username.equals(admins.get(i).getEmailp()) && pass.equals(admins.get(i).getPass()) && admins.get(i).getStatus().equals("active") )
			{
				return admins.get(i);
			}
		}
		for(int i=0;i<moderators.size();i++)
		{
			//System.out.println(""+ moderators.get(i).getEmailp() + " "+ moderators.get(i).getPass()+ "  "+ moderators.get(i).getStatus());
			if(username.equals(moderators.get(i).getEmailp()) && pass.equals(moderators.get(i).getPass()) && moderators.get(i).getStatus().equals("active") )
			{
				return moderators.get(i);
			}
		}
		return null;
	}

	static public void updateNUser(User u) throws NumberFormatException, IOException
	{
		int ch;
		do
		{
			System.out.println("\n1.First Name \n2.Last Name\n3.Password\n4.Postal Address\n5.About Me\n6.URL1 of profile photo\n7.URL2 of profile photo\n8.URL3 of profile photo\n9.Exit\nEnter your choice: ");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(br.readLine());
			switch(ch)
			{
			case 1: u.updateFirstName();
			break;
			case 2: u.updateLastName();
			break;
			case 3:	u.updatePassword();
			break;
			case 4: u.updatePostalAddress();
			break;
			case 5: u.updateAbout();
			break;
			case 6: u.updateUrl1();
			break;
			case 7: u.updateUrl2();
			break;
			case 8: u.updateUrl3();
			break;
			case 9:break;
			default: break;	
			}

		}while(ch!=9);
	}

	static public void updateModerator(User u) throws NumberFormatException, IOException
	{
		int ch;
		do
		{
			System.out.println("\n1.First Name \n2.Last Name\n3.Password\n4.Postal Address\n5.About Me\n6.URL1 of profile photo\n7.URL2 of profile photo\n8.URL3 of profile photo\n9.Qualifications \n10.Contact\n11.Exit\nEnter your choice: ");
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(br.readLine());
			switch(ch)
			{
			case 1: u.updateFirstName();
			break;
			case 2: u.updateLastName();
			break;
			case 3:	u.updatePassword();
			break;
			case 4: u.updatePostalAddress();
			break;
			case 5: u.updateAbout();
			break;
			case 6: u.updateUrl1();
			break;
			case 7: u.updateUrl2();
			break;
			case 8: u.updateUrl3();
			break;
			case 9: u.updateQual();
			break;
			case 10: u.updateContact();
			break;
			case 11: break;
			default: break;	
			}

		}while(ch!=11);
	}


}
*/