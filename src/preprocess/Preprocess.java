package preprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseHandler;
import entities.User;

public class Preprocess {


	/*
	 * Fills Usertype table with details
	 */
	public static void createUserType() throws ClassNotFoundException, SQLException{
		int uid[] = { 101,102,103,200,300};
		String des [] = {"new" , "middle" , "old" , "moderator" ,"administrator" };
		Connection con = DatabaseHandler.createConnection();

		for ( int i = 0 ; i < 5 ; i++){
			String createEntryUserType  = "insert into usertype values(?,?)";
			PreparedStatement stmt = con.prepareStatement(createEntryUserType);
			try{
				stmt.setInt(1,uid[i]);
				stmt.setString(2,des[i]);
				int res = stmt.executeUpdate();
				if(res > 0){
					System.out.println(uid[i]+ " added");
				}
				else{
					System.out.println(uid[i]+" failed");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Creates Admin with default details
	 */
	public static void createAdmin() throws ClassNotFoundException, SQLException{
		String username ="n";
		String pass = "1234";
		String emailp = "n@n.com";
		String emails = "n@n.com";
		String fname = "n";
		String lname = "n";	
		String  about = "hello i am admin";
		String  url1 = "www.facebook.com";
		String  url2 = "google.com";
		String  url3 = "yahoo.com";
		String  sno = "A-1";
		String  snm = "Patel chowk";
		String  mun = "centre";
		String  dist = "Patel nagar";
		String  area = "delhi";
		int uid = 300;
		boolean status = true ;
		String phone = "997876865";

		Connection con = DatabaseHandler.createConnection();
		String createAdminInUserSql  = "insert into User values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(createAdminInUserSql);
		stmt.setString(1,username);
		stmt.setString(2,pass);
		stmt.setString(3,emailp);
		stmt.setString(4,emails);
		stmt.setString(5,fname);
		stmt.setString(6,lname);
		stmt.setString(7,about);
		stmt.setString(8,url1);
		stmt.setString(9,url2);
		stmt.setString(10,url3);
		stmt.setString(11,sno);
		stmt.setString(12,snm);
		stmt.setString(13,mun);
		stmt.setString(14,dist);
		stmt.setString(15,area);
		stmt.setInt(16,uid);
		stmt.setBoolean(17,status);
		//System.out.println(stmt);
		String createAdminInAdminSql  = "insert into Administrator values(?,?)";
		PreparedStatement stmt2 = con.prepareStatement(createAdminInAdminSql);
		stmt2.setString(1,username);
		stmt2.setString(2,phone);


		//%------------- Moderator
		String createModeratorSql  = "insert into Moderator values(?,?)";
		PreparedStatement stmt3 = con.prepareStatement(createModeratorSql);
		stmt3.setString(1,username);
		stmt3.setString(2,phone);

		try
		{

			//stmt3.executeUpdate();
			int rs = stmt2.executeUpdate();
			stmt.executeUpdate();
			if(rs > 0){
				System.out.println("Admin added");
			}
			else{
				System.out.println("Admin addition failure");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in Adding to Database");
			e.printStackTrace();
		}



	}

}
