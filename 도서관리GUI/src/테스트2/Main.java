package �׽�Ʈ2;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Main extends JFrame {
	
	static JTextField title, price, publisher;
	
	 static String tt;	// title
	 static int    pc;	// price
	 static String ps;	// publisher
	 
	 static JPanel panel = new JPanel();
	
	public static void frame() {
		JFrame f = new JFrame("��������");
		

//-----------------------------------------------------------	
		
		f.add(panel, BorderLayout.CENTER);
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
		
	} // end public static void frame()
	
	
	public static void Books()  {
		// 1. JDBC ����̹��� ����
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("����̹� ���� ����");
			} catch (ClassNotFoundException e) {
				System.out.println("����̹��� ã�� �� �����ϴ�.");
			}
		
		// 2. DB ����
			String url		= "jdnc:oracle:thin:@net.yju.ac.kr:1521:orcl";
			String id 		= "s1701052";									
			String password = "p1701052";									
			Connection con 	= null;
		
			try {
				con = DriverManager.getConnection(url , id, password);
				System.out.println("���� ����");
			} catch (SQLException e) {
				System.out.println("���� ����");
			}

//-----------------------------------------------------------------------------------------------------
		
		// 3. SQL���� �ۼ� �� ����
		try {
			Statement stmt = con.createStatement();
			String	  sql = null;
			
			sql = "select * from Books order by BOOK_ID";
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	         // test 
	         // System.out.println(rs.getInt("BOOK_ID") + " : " + rs.getString("TITLE") + " : " + rs.getInt("PRICE"));

// ----------------------------------------------------------------------------------
	        	 
	        	 tt = rs.getString("TITLE");
	        	 pc = rs.getInt("PRICE");
	        	 ps = rs.getString("PUBLISHER");
				
				panel.add(new JLabel("å�̸�"));
				title = new JTextField(10);
				panel.add(title);
				if(title != null) title.setText(tt);
				
				
				panel.add(new JLabel("����"));
				price = new JTextField(10);
				panel.add(price);
				if(price != null) price.setText(Integer.toString(pc));
				
				
				panel.add(new JLabel("���ǻ�"));
				publisher = new JTextField(10);
				panel.add(publisher);
				if(publisher != null) publisher.setText(ps);
				
	         }// end while

			
		stmt.close();
		con.close();
		
		} catch (SQLException e) {
			System.out.println("���ܰ� �߻��Ͽ����ϴ�.");
		}
		
		
	}// end Books()
	

	public static void main(String[] args) {
		Books();
		frame();
	}

}// end public class Main extends JFrame
