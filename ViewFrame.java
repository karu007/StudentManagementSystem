import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class ViewFrame extends JFrame
{
	Container c;
	TextArea ta;
	JButton btnBack;
	JPanel p1,p2;
	
	ViewFrame()
	{
		c = getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		ta = new TextArea(5,20);
		p1.add(ta);
		c.add(p1);
		
		p2 = new JPanel();
		btnBack = new JButton("Back");		
		p2.add(btnBack);
		c.add(p2);

		DbHandler db = new DbHandler();
		String data = db.viewStudent();
		ta.setText(data);

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				MainFrame m = new MainFrame();
				dispose();
			}
		});

		setTitle("View Student");
		setSize(350,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}