package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JTextField txtNameSU;
	private JTextField txtUsernameSU;
	private JTextField txtSurnameSU;
	private JPasswordField txtPasswordSU;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
					frame.setResizable(false);
					ImageIcon icon = new ImageIcon("logo.jpg");
					frame.setIconImage(icon.getImage());
					frame.getContentPane().setBackground(new Color(200,255,200));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public LoginPage() {
		getContentPane().setLayout(null);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 14));
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lbwelcoming = new JLabel("Welcome to the Syrian Massacres Memory System");
		lbwelcoming.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		lbwelcoming.setBounds(10, 11, 414, 43);
		getContentPane().add(lbwelcoming);
		
		JLabel lblNewLabel = new JLabel("please log in");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 95, 99, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setBounds(30, 130, 73, 14);
		getContentPane().add(lbUsername);
		
		JLabel lblNewLabel_1 = new JLabel("Explore the history of Syrian massacres to never forget what happened.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 49, 414, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To remember is to resist. To forget is to let it happen again.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(46, 65, 345, 32);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setBounds(238, 130, 81, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Role");
		lblNewLabel_4.setBounds(30, 168, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(102, 127, 86, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();

		txtPassword.setBounds(322, 127, 86, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JComboBox cbRole = new JComboBox();
		cbRole.setModel(new DefaultComboBoxModel(new String [] {"Admin","Researcher"}));
		cbRole.setBounds(75, 164, 57, 22);
		getContentPane().add(cbRole);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserContext ctxUser = new UserContext() ;
				String username = txtUserName.getText() ;
				String password = new String(txtPassword.getPassword());

				String userRole = cbRole.getSelectedItem().toString();
				
		try {
				if(ctxUser.checkUser(username, password, userRole)) {
					if(userRole.equals("Admin")) {
						MainPageAdmin  mpAdmin = new MainPageAdmin() ;
						mpAdmin.setVisible(true) ;
						dispose() ;
					}
					else if (userRole.equals("Researcher")) {
						MainPageResearcher mpResearcher = new MainPageResearcher() ;
						mpResearcher.setVisible(true) ;
						dispose() ;
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Failed!") ;
					txtUserName.setText("") ;
					txtPassword.setText("");
				}
				} catch (SQLException e1) {
						e1.printStackTrace();
				}
				
			}
		});
		btnLogIn.setBounds(168, 181, 89, 23);
		getContentPane().add(btnLogIn);
		
		JLabel lblNewLabel_5 = new JLabel("Create a new account");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(30, 230, 145, 14);
		getContentPane().add(lblNewLabel_5);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(100,100,450,450);
			}
		});
		btnSignUp.setBounds(168, 227, 89, 23);
		getContentPane().add(btnSignUp);
		
		JLabel lblNewLabel_6 = new JLabel("Name");
		lblNewLabel_6.setBounds(30, 270, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Surname");
		lblNewLabel_7.setBounds(217, 270, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setBounds(217, 311, 46, 14);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Role");
		lblNewLabel_9.setBounds(30, 356, 46, 14);
		getContentPane().add(lblNewLabel_9);
		
		txtNameSU = new JTextField();
		txtNameSU.setBounds(86, 267, 86, 20);
		getContentPane().add(txtNameSU);
		txtNameSU.setColumns(10);
		
		txtUsernameSU = new JTextField();
		txtUsernameSU.setBounds(86, 308, 86, 20);
		getContentPane().add(txtUsernameSU);
		txtUsernameSU.setColumns(10);
		
		txtSurnameSU = new JTextField();
		txtSurnameSU.setBounds(286, 267, 86, 20);
		getContentPane().add(txtSurnameSU);
		txtSurnameSU.setColumns(10);
		
		txtPasswordSU = new JPasswordField();
		txtPasswordSU.setBounds(286, 308, 86, 20);
		getContentPane().add(txtPasswordSU);
		txtPasswordSU.setColumns(10);
		
		JComboBox cbRoleSU = new JComboBox();
		cbRoleSU.setModel(new DefaultComboBoxModel(new String [] {"Admin","Researcher"}));
		cbRoleSU.setBounds(75, 352, 57, 22);
		getContentPane().add(cbRoleSU);
		
		
		
		JLabel lblNewLabel_10 = new JLabel("Username");
		lblNewLabel_10.setBounds(30, 311, 57, 14);
		getContentPane().add(lblNewLabel_10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(174, 377, 89, 23);
		
		btnSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String name = txtNameSU.getText();
		        String surname = txtSurnameSU.getText();
		        String username = txtUsernameSU.getText();
		        String password = new String(txtPasswordSU.getPassword());
		        String userRoleSU = cbRoleSU.getSelectedItem().toString();

		        UserContext ctxUser = new UserContext();

		        try {
		            if (!ctxUser.checkUser(username)) {
		            	User u = new User();
		            	u.setName(txtNameSU.getText());
		            	u.setSurname(txtSurnameSU.getText());
		            	u.setUsername(txtUsernameSU.getText());
		            	u.setPassword(txtPasswordSU.getText());
		            	u.setUserRole(cbRoleSU.getSelectedItem().toString());
		                ctxUser.saveUser(u);
		                JOptionPane.showMessageDialog(null, "User Saved");
		                setBounds(100, 100, 450, 300);  
		                txtNameSU.setText("");
		                txtSurnameSU.setText("");
		                txtUsernameSU.setText("");
		                txtPasswordSU.setText("");
		              
		            } else {
		                JOptionPane.showMessageDialog(null, "User already exists");
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		getContentPane().add(btnSave);
		
		
	
		
		

	}
}
