package 테스트2;

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
		JFrame f = new JFrame("도서관리");
		

//-----------------------------------------------------------	
		
		f.add(panel, BorderLayout.CENTER);
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
		
	} // end public static void frame()
	
	
	public static void Books()  {
		// 1. JDBC 드라이버를 적재
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이버 적재 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버를 찾을 수 없습니다.");
			}
		
		// 2. DB 연결
			String url		= "jdnc:oracle:thin:@net.yju.ac.kr:1521:orcl";
			String id 		= "s1701052";									
			String password = "p1701052";									
			Connection con 	= null;
		
			try {
				con = DriverManager.getConnection(url , id, password);
				System.out.println("연결 성공");
			} catch (SQLException e) {
				System.out.println("연결 오류");
			}

//-----------------------------------------------------------------------------------------------------
		
		// 3. SQL문장 작성 및 전송
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
				
				panel.add(new JLabel("책이름"));
				title = new JTextField(10);
				panel.add(title);
				if(title != null) title.setText(tt);
				
				
				panel.add(new JLabel("가격"));
				price = new JTextField(10);
				panel.add(price);
				if(price != null) price.setText(Integer.toString(pc));
				
				
				panel.add(new JLabel("출판사"));
				publisher = new JTextField(10);
				panel.add(publisher);
				if(publisher != null) publisher.setText(ps);
				
	         }// end while

			
		stmt.close();
		con.close();
		
		} catch (SQLException e) {
			System.out.println("예외가 발생하였습니다.");
		}
		
		
	}// end Books()
	

	public static void main(String[] args) {
		Books();
		frame();
	}

}// end public class Main extends JFrame
