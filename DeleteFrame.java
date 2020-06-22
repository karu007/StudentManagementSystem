import javax.swing.*;//for components
import java.awt.*;//for layout container
import java.awt.event.*;//for actionPerformed n all
import java.sql.*;//for connecting to oracle database

class DeleteFrame extends JFrame
{
	Container c;
	JLabel lblRno;
	JTextField txtRNo;
	JButton btnSave,btnBack;
	JPanel p1,p2;
	
	DeleteFrame()
	{
		c = getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		lblRno = new JLabel("Roll No:");
		txtRNo = new JTextField(4);
		p1.add(lblRno);
		p1.add(txtRNo);
		c.add(p1);
		
		p2 = new JPanel();
		btnSave = new JButton("Save");
		btnBack = new JButton("Back");		
		p2.add(btnSave);
		p2.add(btnBack);
		c.add(p2);

		
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				DbHandler db = new DbHandler();
				db.deleteStudent(Integer.parseInt(txtRNo.getText()));
			}
		});
	


		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				MainFrame m = new MainFrame();
				dispose();
			}
		});


		setTitle("Delete Student");
		setSize(350,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}