import javax.swing.*;//for components
import java.awt.*;//for layout container
import java.awt.event.*;//for actionPerformed n all
import java.sql.*;//for connecting to oracle database

class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete;
	
	MainFrame()
	{
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		btnAdd = new JButton("Add Student");
		btnView = new JButton("View Student");
		btnUpdate = new JButton("Update Student");
		btnDelete = new JButton("Delete Student");

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);

		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				AddFrame a = new AddFrame();
				dispose();
			}
		});

		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				ViewFrame v = new ViewFrame();
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				UpdateFrame u = new UpdateFrame();
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				DeleteFrame d = new DeleteFrame();
				dispose();
			}
		});
		
		setTitle("Student Management System");
		setSize(350,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		MainFrame m = new MainFrame();
	}		
	
}
class DbHandler
{
	public void addStudent(int rollNo,String name)
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			
			String s1 = "insert into student values(?,?)";	
			PreparedStatement pst = conn.prepareStatement(s1);
			pst.setInt(1,rollNo);
			pst.setString(2,name);
			int result = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),result+" records inserted");
			conn.close();
	
		}
		catch(SQLException se)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Insert Issue "+se);
		}				
	}

	public String viewStudent()
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String s1 = "select * from student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s1);
			while(rs.next())
			{
				int rollNo = rs.getInt(1);
				String name = rs.getString(2);
				sb.append("Roll No:"+rollNo+"\nName:"+name+"\n\n");			
			}
			rs.close();
			conn.close();
		}
		catch(SQLException se)
		{
			JOptionPane.showMessageDialog(new JDialog(),"View Issue "+se);
		}				
		return sb.toString();
	}

	public void updateStudent(int rollNo,String name)
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			String s = "update student set name=(?) where rno=(?)";
			PreparedStatement pst = conn.prepareStatement(s);
			pst.setInt(1,rollNo);
			pst.setString(2,name);
			int result = pst.executeUpdate();
			if(result==1)
				JOptionPane.showMessageDialog(new JDialog(),result + " row updated");		
			else 
				JOptionPane.showMessageDialog(new JDialog(),result + " rows affected");		
			pst.close();
			conn.close();
		}
		catch(SQLException se)
		{
		JOptionPane.showMessageDialog(new JDialog(),"Update Issue "+se);
		}
	}
	
	public void deleteStudent(int rollNo)
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			Statement sql = conn.createStatement();
			String s = "delete from student where rno="+rollNo;
			int result = sql.executeUpdate(s);
			if(result==1)
				JOptionPane.showMessageDialog(new JDialog(),result + " rows deleted");		
			else 
				JOptionPane.showMessageDialog(new JDialog(),result + " rows affected");			
			conn.close();
		}
		catch(SQLException se)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Delete Issue "+se);
		}
	}

}