package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;

public class MainPageAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tableMassacres;
	private JTextField txtTitle;
	private JTextField txtLocation;
	private JTextField txtVictims;
	MassacreContext ctxMassacre = new MassacreContext();
	 DefaultTableModel model= new DefaultTableModel();
	
	public void fillTable() throws SQLException {
		model.setRowCount(0);
		ArrayList<Massacre> massacres = ctxMassacre.getMassacres();	
		massacres.forEach(m -> {
			model.addRow(new Object[] {
				   
					m.getId(),
					m.getTitle(),
					m.getDate(),				
					m.getLocation(),				   
					 m.getType(),				  
					  m.getVictims()} );				 
		});
	}
	
	public void fillTable(List<Massacre> massacres) {
		model.setRowCount(0);
		massacres.forEach(m -> {
			model.addRow(new Object[] {
					m.getId(),
					m.getTitle(),
					   m.getDate(),
					   m.getLocation(),
					   m.getType(),
					   m.getVictims()} );
		});
	}
	
	
	public void selectedSort(String order) {
	    try {
	        
	        String query = "SELECT * FROM massacres ORDER BY date " + order;
	        PreparedStatement pst = ctxMassacre.getConnected().prepareStatement(query);
	        ResultSet rs = pst.executeQuery();
	        model.setRowCount(0); 

	        while (rs.next()) {
	            model.addRow(new Object[]{
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getInt("date"),
	                rs.getString("location"),
	                rs.getString("type"),
	                rs.getInt("victims"),
	              
	            });
	        }

	       

	    } catch (Exception ex) {
	        ex.printStackTrace();
	      
	    }
	}

	
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageAdmin frame = new MainPageAdmin();
					frame.fillTable();
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

	/**
	 * Create the frame.
	 */
	public MainPageAdmin() {
		setBounds(100, 100, 504, 346);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Researcher Dashboard – Syrian Massacres Information");
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
	    contentPane.setLayout(null); 
	    setContentPane(contentPane);
	    
	    JLabel lblNewLabel = new JLabel("Massacres Table");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblNewLabel.setBounds(10, 63, 92, 14);
	    contentPane.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("Welcome to the Admin Dashboard ");
	    lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
	    lblNewLabel_1.setBounds(48, 0, 313, 30);
	    contentPane.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("Manage and maintain massacre records with full control");
	    lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
	    lblNewLabel_2.setBounds(30, 28, 378, 24);
	    contentPane.add(lblNewLabel_2);
	    
	    JButton btnManageMassacres = new JButton("Manage Massacres");
	    btnManageMassacres.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		setBounds(100, 100, 805, 358);
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnManageMassacres.setBounds(308, 73, 162, 30);
	    contentPane.add(btnManageMassacres);
	    
	    JComboBox cbSort = new JComboBox();
	    cbSort.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		   String selected =  cbSort.getSelectedItem().toString();
	    	        if (selected.equals("Oldest → Newest")) {
	    	           selectedSort("ASC");
	    	        } else if (selected.equals("Newest → Oldest")) {
	    	        	selectedSort("DESC");
	    	        }
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    cbSort.setBounds(181, 77, 117, 22);
	    cbSort.addItem("Oldest → Newest");
	    cbSort.addItem("Newest → Oldest");
	    contentPane.add(cbSort);
	    
	    
	    JLabel lblNewLabel_3 = new JLabel("Sort By Date");
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblNewLabel_3.setBounds(106, 80, 70, 14);
	    contentPane.add(lblNewLabel_3);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 107, 471, 143);
	    contentPane.add(scrollPane);
	    
	    tableMassacres = new JTable();
	    scrollPane.setViewportView(tableMassacres);
	   
	    tableMassacres.setModel(model);
	    model.setColumnIdentifiers(new Object[] {"ID","Title","Date","Location","Type","Victims"});
	    JLabel lblNewLabel_4 = new JLabel("Tiltle");
	    lblNewLabel_4.setBounds(504, 89, 46, 14);
	    contentPane.add(lblNewLabel_4);
	    
	    txtTitle = new JTextField();
	    txtTitle.setBounds(613, 83, 133, 20);
	    contentPane.add(txtTitle);
	    txtTitle.setColumns(10);
	    
	    JLabel lblNewLabel_5 = new JLabel("Location");
	    lblNewLabel_5.setBounds(504, 122, 46, 14);
	    contentPane.add(lblNewLabel_5);
	    
	    txtLocation = new JTextField();
	    txtLocation.setBounds(613, 119, 133, 20);
	    contentPane.add(txtLocation);
	    txtLocation.setColumns(10);
	    
	    JLabel lblNewLabel_6 = new JLabel("Estimated Victims");
	    lblNewLabel_6.setBounds(504, 158, 93, 14);
	    contentPane.add(lblNewLabel_6);
	    
	    txtVictims = new JTextField();
	    txtVictims.setBounds(613, 155, 133, 20);
	    contentPane.add(txtVictims);
	    txtVictims.setColumns(10);
	    
	    JLabel lblNewLabel_7 = new JLabel("Type");
	    lblNewLabel_7.setBounds(518, 238, 34, 24);
	    contentPane.add(lblNewLabel_7);
	    
	    JComboBox cbType = new JComboBox();
	    
	    
	    cbType.setModel(new DefaultComboBoxModel<>(new String[] {
	    	    "Airstrike",
	    	    "Chemical Attack",
	    	    "Barrel Bombing",
	    	    "Artillery Shelling",
	    	    "Mass Shooting",
	    	    "Torture and Execution",
	    	    "Siege Starvation",
	    	    "Hospital Bombing",
	    	    "Mass Arrest and Killing",
	    	    "Other"
	    	}));
	    
	    
	   
	    cbType.setBounds(613, 239, 133, 22);
	    contentPane.add(cbType);
	    
	    JLabel lblNewLabel_8 = new JLabel("Date");
	    lblNewLabel_8.setBounds(518, 203, 46, 14);
	    contentPane.add(lblNewLabel_8);
	    
	    JComboBox cbDate = new JComboBox();
	    cbDate.setBounds(613, 199, 133, 22);
	    contentPane.add(cbDate);

		for (int i = 2011; i < 2024; i++) {
			cbDate.addItem(i);
		}
	    JButton btnSave = new JButton("Save");
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 String title = txtTitle.getText();
			        String location = txtLocation.getText();
			        int victims =Integer.parseInt(txtVictims.getText());
			        int date = Integer.parseInt (cbDate.getSelectedItem().toString());
			        String type = cbType.getSelectedItem().toString();

			        MassacreContext ctxMassacre = new MassacreContext();
			        try {
			            if (!ctxMassacre.checkMassacre(title)) {
			            	Massacre m = new Massacre();
			            	m.setTitle(txtTitle.getText());
			            	m.setLocation(txtLocation.getText());
			            	m.setVictims(Integer.parseInt(txtVictims.getText()));
			            	m.setDate(  Integer.parseInt(cbDate.getSelectedItem().toString()));
			            	m.setType(cbType.getSelectedItem().toString());
			            	ctxMassacre.saveMassacre(m);
			                JOptionPane.showMessageDialog(null, "Massacre Saved");
			                fillTable();
			                txtTitle.setText("");
			                txtLocation.setText("");
			                txtVictims.setText("");
			                setBounds(100, 100, 513, 306);
			              
			            } else {
			                JOptionPane.showMessageDialog(null, "Massacre already exists");
			            }
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
			    }
			});
	    btnSave.setBounds(546, 272, 89, 23);
	    contentPane.add(btnSave);
	    
	    JButton btnLogout = new JButton("Logout");
	    btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnLogout.setBounds(163, 269, 153, 25); 
	    contentPane.add(btnLogout);
	    btnLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose(); 
	            LoginPage loginPage = new LoginPage(); 
	            loginPage.setVisible(true);
	        }
	    });
	    
	   
	    
	    
	    
	    
	}
}
