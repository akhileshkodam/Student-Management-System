package StudentsManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Students{
	String Name="Akhil";
	int Roll_No=1;
	String Grade="B";
}

public class StudentManagementSystem {
		static Scanner sc = new Scanner(System.in);
	
	
		static void add(Connection con) throws SQLException {
			System.out.println("-----------------------------------------------");
			System.out.println("|------========>> Adding page <<========------|");
			System.out.println("-----------------------------------------------");
			
			System.out.println();
			
			PreparedStatement ps=con.prepareStatement("insert into students values(?,?,?)");
			
			System.out.print("Enter Student Name:");
			ps.setString(1, sc.next());
			System.out.println();
			
			System.out.print("Enter Student Roll NO:");
			ps.setInt(2, sc.nextInt());
			System.out.println();
			
			System.out.print("Enter Student Grade:");
			ps.setString(3, sc.next());
			System.out.println();
			
			
			ps.executeUpdate();
			ps.close();
			
			System.out.println("Students Details Added Successfulluy"); 
			
			
			
		}
		static void remove(Connection con) throws SQLException {
			
		System.out.println("------------------------------------------------------");			
		System.out.println("|------========>>Remove Students page<<========------|");
		System.out.println("------------------------------------------------------");
		System.out.println();
			
			
			PreparedStatement ps=con.prepareStatement("delete from students where Roll=?");
		
			
			System.out.print("Enter Student Roll NO:");
			ps.setInt(1, sc.nextInt());
			System.out.println();
		
			
			ps.executeUpdate();
			ps.close();
			
			System.out.println("Students Details Removed Successfulluy"); 
			
					
				}
		static void search(Statement st) throws SQLException {
			
			System.out.println("------------------------------------------------------");
			System.out.println("|------========>>Search Students page<<========------|");
			System.out.println("------------------------------------------------------");
			System.out.println();
			ResultSet rs=st.executeQuery("select * from students");
			
			int d=0;
			
			boolean b=true;
			while(b) {
				System.out.print("Enter student Name: ");
				String name=sc.next();
				name=name.toLowerCase();
				while(rs.next())
				{
					String name1=rs.getString(1);
					name1=name1.toLowerCase();
					if(name1.equals(name))
					{
						b=false;
						d=1;
						System.out.println("Student Found");
						System.out.println();
						
						System.out.println("  Students Name  |   Roll No  |   Grade   |");
						int roll=rs.getInt(2);
						String grade=rs.getString(3);
						System.out.println("      "+name+"            "+roll+"           "+grade);
						
					}
				}
				if(d==0)
				{
					System.out.println("There is No Student with these Name");
					System.out.println("Enter Correct Details or you want To Exit enter 1");
					int t=sc.nextInt();
					if(t==1) {b=false;}
				}
			}
			rs.close();
					
		}
		static void display(Statement st) throws SQLException {
			
			System.out.println("-------------------------------------------------------");
			System.out.println("|------========>>Display Students page<<========------|");
			System.out.println("-------------------------------------------------------");
			System.out.println();
					
			System.out.println("  Students Name  |   Roll No  |   Grade   |");
			
			ResultSet rs=st.executeQuery("select * from students");
			while(rs.next()) {
				String name=rs.getString(1);
				int roll=rs.getInt(2);
				String grade=rs.getString(3);
				System.out.println("      "+name+"            "+roll+"           "+grade);
			}
			System.out.println();
			rs.close();
					
					
		}
		
		static void update(Connection con) throws SQLException {
			System.out.println("--------------------------------------------------------");
			System.out.println("|------========>> Update Students page <<========------|");
			System.out.println("--------------------------------------------------------");
			
			
			System.out.println();
			System.out.println("If you Want to update Name --->Enter 1");
			System.out.println("If you Want to update Roll --->Enter 2");
			System.out.println("If you Want to update Grade --->Enter 3");
			
			int n=sc.nextInt();
			if(n==1)
			{
				PreparedStatement ps=con.prepareStatement("update students set name=? where name=?");
				
				System.out.print("Enter New Student Name: ");
				ps.setString(1, sc.next());
				System.out.println();
				System.out.print("Enter Old Student Name: ");
				ps.setString(2, sc.next());
				ps.executeUpdate();
				ps.close();
				
			}
			else if(n==2)
			{
				PreparedStatement ps=con.prepareStatement("update students set roll=? where name=?");
				
				System.out.print("Enter New Student Roll: ");
				ps.setInt(1, sc.nextInt());
				System.out.println();
				System.out.print("Enter Student Name: ");
				ps.setString(2, sc.next());
				ps.executeUpdate();
				ps.close();
			}
			else if(n==3)
			{
				PreparedStatement ps=con.prepareStatement("update students set grade=? where name=?");
				
				System.out.print("Enter New Student grade: ");
				ps.setString(1, sc.next());
				System.out.println();
				System.out.print("Enter Student Name: ");
				ps.setString(2, sc.next());
				ps.executeUpdate();
				ps.close();
			}
			
			
			System.out.println("Students Details Updated Successfulluy"); 
			
			
		}

	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			
			Statement st=con.createStatement();
			
			System.out.println("                    -------------------------------");
			System.out.println("                    |   StudentManagementSystem   |");
			System.out.println("                    -------------------------------");
			
			System.out.println();
			
			
			
			boolean b=true;
			int l=0;
			while(b) {
				l=0;
				System.out.println("--> 1.Adding a Student\n--> 2.Remove a Student\n--> 3.Search for a Student\n--> 4.Display All Students\n--> 5.Update Student Details");
				int n=sc.nextInt();
				switch(n) {
						case 1:
							add(con);
							break;
						case 2:
							remove(con);
							break;
						case 3:
							search(st);
							break;
						case 4:
							display(st);
							break;
						case 5:
							update(con);
							break;
						default:
							System.out.println("Select Numbers between 1 to 5 Only");
							System.out.println();
							l=1;
							break;
				}
				if(l==0) {
					
					System.out.println();
					System.out.println("If You Want to Continue ---> Enter 1 or\nIf You Want to Exit ---> Enter 2");
					System.out.println();
					int k=sc.nextInt();
					if(k==2)
					{
						b=false;
					}
				}
			}
			System.out.println("                    -------------------------------");
			System.out.println("                    |    Thank You Visit Again    |");
			System.out.println("                    -------------------------------");
			
			st.close();
			con.close();
			sc.close();
		
		}
		catch(Exception e) {}
		

	}

}
