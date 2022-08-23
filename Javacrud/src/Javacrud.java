import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.commons.dbutils.DbUtils;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Javacrud {

	private JFrame frame;
	private JTextField txtedition;
	private JTextField txtbname;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Javacrud window = new Javacrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Javacrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/JavaCrud ", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	
	public void table_load()
	{
		
		try
		{
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel (DbUtils.resultSetToTableModel(rs));
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(157, 11, 196, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 58, 236, 147);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 86, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 50, 70, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 89, 70, 28);
		panel.add(lblNewLabel_1_1_1);
		
		txtedition = new JTextField();
		txtedition.setBounds(90, 56, 131, 20);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(95, 20, 131, 20);
		panel.add(txtbname);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(90, 95, 131, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				
				try {
					
					pst = con.prepareStatement("insert into book (name,edition,price)value(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Addedd!!!");
					//table.load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		btnNewButton.setBounds(10, 205, 70, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(106, 205, 60, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
			}
		});
		btnClear.setBounds(176, 205, 70, 23);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 65, 238, 140);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 239, 241, 52);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(10, 13, 70, 28);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String id = txtbid.getText();
					
					  pst = con.prepareStatement ("select name,edition,price from book where id = ?");
					  pst.setString(1, id);
					  ResultSet rs = pst.executeQuery();
					 
					if(rs.next()==true )
					{
						
						  String name = rs.getString(1);
						  String edition = rs.getString(2);
						  String price = rs.getString(3);
						  
						  txtbname.setText(name);
						  txtedition.setText(edition);
						  txtprice.setText(price);
					}
					
					else 
					{
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");

           }
					
				}
				
		catch (SQLException ex) {
			
		}
				
				
				
				
			}
			
		});
		txtbid.setColumns(10);
		txtbid.setBounds(90, 19, 131, 20);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
           String bname,edition,price,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
				
				
				try {
					
					pst = con.prepareStatement("update book set name= ?,edition= ?,price= ?where id= ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Updated!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				
				
				
				
			}
		});
		btnUpdate.setBounds(284, 216, 82, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("Delete");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 String bid;
				bid = txtbid.getText();
					
					
					try {
						
						pst = con.prepareStatement("delete from book where id= ?");
						pst.setString(1, bid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null,  "Record Deleted!!");
						table_load();
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
					}
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
						
					}
					
					
			}
		});
		btnClear_1_1.setBounds(396, 216, 72, 23);
		frame.getContentPane().add(btnClear_1_1);
	}
}
