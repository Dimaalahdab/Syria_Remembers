package pack1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainPageResearcher extends JFrame {

	private static final long serialVersionUID = 1L;
	MassacreContext ctxMassacre = new MassacreContext();
	 DefaultTableModel model= new DefaultTableModel();
	 private JTable table;
	 
	 
	 
	 public  void fillTable() throws SQLException {
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
	 
	 
	 
	 public void massacresByRegion(String query, String location) {
		    model.setRowCount(0); 

		    try {
		    	 PreparedStatement ps = ctxMassacre.getConnected().prepareStatement(query);
		        if (!"All Regions".equals(location)) {
		            ps.setString(1, location);
		        }

		        ResultSet rs = ps.executeQuery();

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

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	 public void massacresByType(String query, String type) {
		    model.setRowCount(0); 

		    try {
		    	 PreparedStatement ps = ctxMassacre.getConnected().prepareStatement(query);
		        if (!"All Types".equals(type)) {
		            ps.setString(1, type);
		        }

		        ResultSet rs = ps.executeQuery();

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

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	 public void sortMassacres(String orderBy) {
		    model.setRowCount(0);

		    String query = "SELECT * FROM massacres " + orderBy;

		    try {
		        PreparedStatement ps = ctxMassacre.getConnected().prepareStatement(query);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            model.addRow(new Object[]{
		                rs.getInt("id"),
		                rs.getString("title"),
		                rs.getString("date"), 
		                rs.getString("location"),
		                rs.getString("type"),
		                rs.getInt("victims")
		            });
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}	 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageResearcher frame = new MainPageResearcher();
					frame.setVisible(true);
					frame.fillTable();
					frame.setResizable(false);
					ImageIcon logo = new ImageIcon("logo.jpg");
					frame.getContentPane().setBackground(new Color(200,255,200));
					frame.setIconImage(logo.getImage());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainPageResearcher() {
		setBounds(100, 100, 831, 430);
		setTitle("Researcher Dashboard  – Syrian Massacres Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		 contentPane.setLayout(null);
		 
		
		 setContentPane(contentPane);
		 
		  JLabel lblNewLabel_1 = new JLabel("Welcome to the Dashboard Researcher");
		    lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		    lblNewLabel_1.setBounds(140, 11, 366, 30);
		    contentPane.add(lblNewLabel_1);
		 
		    JLabel lblNewLabel = new JLabel("Massacres Table");
		    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblNewLabel.setBounds(24, 62, 146, 14);
		    contentPane.add(lblNewLabel);
		    
	
		    
		    
		    
		    JComboBox cbRegionFilter = new JComboBox();
		    cbRegionFilter.addItem("All Regions");
		    cbRegionFilter.addItem("Damascus");
		    cbRegionFilter.addItem("Aleppo");
		    cbRegionFilter.addItem("Homs");
		    cbRegionFilter.addItem("Hama");
		    cbRegionFilter.addItem("Deir ez-Zor");
		    cbRegionFilter.addItem("Idlib");
		    cbRegionFilter.addItem("Raqqa");
		    cbRegionFilter.addItem("Daraa");
		    cbRegionFilter.addItem("Hasakah");
		    cbRegionFilter.addItem("Latakia");
		    cbRegionFilter.addItem("Tartus");
		    cbRegionFilter.addItem("Quneitra");
		    cbRegionFilter.addItem("As-Suwayda");
		    contentPane.add(cbRegionFilter);
		    cbRegionFilter.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		 String selectedRegion =  cbRegionFilter.getSelectedItem().toString();

		    	        String query;
		    	        if ("All Regions".equals(selectedRegion)) {
		    	            query = "SELECT * FROM massacres";
		    	        } else {
		    	            query = "SELECT * FROM massacres WHERE location = ?";
		    	        }

		    	     massacresByRegion(query, selectedRegion);
		    		
		    		
		    		
		    	}
		    });
		    cbRegionFilter.setBounds(643, 254, 153, 22);
		
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(20, 88, 554, 246);
		    contentPane.add(scrollPane);
		    
		    table = new JTable();
		    model.setColumnIdentifiers(new Object[] {"ID","Title","Date","Location","Type","Victims"});
		    scrollPane.setViewportView(table);
		    table.setModel(model);
		    
		    JLabel lblNewLabel_2 = new JLabel("Location :");
		    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblNewLabel_2.setBounds(584, 257, 69, 14);
		    contentPane.add(lblNewLabel_2);
		    
		    JLabel lblNewLabel_3 = new JLabel("Type :");
		    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblNewLabel_3.setBounds(584, 196, 46, 14);
		    contentPane.add(lblNewLabel_3);
		    
		    JComboBox cbType = new JComboBox();
		    cbType.addItem("All Types"); 

		    cbType.addItem("Airstrike");
		    cbType.addItem("Chemical Attack");
		    cbType.addItem("Barrel Bombing");
		    cbType.addItem("Artillery Shelling");
		    cbType.addItem("Mass Shooting");
		    cbType.addItem("Torture and Execution");
		    cbType.addItem("Siege Starvation");
		    cbType.addItem("Hospital Bombing");
		    cbType.addItem("Mass Arrest and Killing");
		    cbType.addItem("Other");
		    cbType.setBounds(643, 193, 153, 22);
		    contentPane.add(cbType);
		    cbType.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		String selectedType =  cbType.getSelectedItem().toString();

	    	        String query;
	    	        if ("All Types".equals(selectedType)) {
	    	            query = "SELECT * FROM massacres";
	    	        } else {
	    	            query = "SELECT * FROM massacres WHERE type = ?";
	    	        }

	    	     massacresByType(query, selectedType);
		    		
		    	}
		    });
		 
		   
		    
		    JComboBox cbSort = new JComboBox();
		    
		   
		    cbSort.addItem("Date: Newest to Oldest");
		    cbSort.addItem("Date: Oldest to Newest");
		    cbSort.addItem("Victims: Most to Least");
		    cbSort.addItem("Victims: Least to Most");
		    cbSort.setBounds(643, 134, 153, 22);
		    contentPane.add(cbSort);
		    
		    JLabel lblNewLabel_4 = new JLabel("Sort By :");
		    lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    lblNewLabel_4.setBounds(584, 137, 69, 14);
		    contentPane.add(lblNewLabel_4);
		    
		    JLabel lblNewLabel_5 = new JLabel("Use the filters below to view");
		    lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblNewLabel_5.setBounds(603, 58, 187, 22);
		    contentPane.add(lblNewLabel_5);
		    
		    JLabel lblNewLabel_6 = new JLabel("specific massacre records :");
		    lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblNewLabel_6.setBounds(603, 85, 179, 19);
		    contentPane.add(lblNewLabel_6);
		    cbSort.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String selectedSort = cbSort.getSelectedItem().toString();
		            String orderBy = "";

		            switch (selectedSort) {
		                case "Date: Newest to Oldest":
		                    orderBy = "ORDER BY date DESC";
		                    break;
		                case "Date: Oldest to Newest":
		                    orderBy = "ORDER BY date ASC";
		                    break;
		                case "Victims: Most to Least":
		                    orderBy = "ORDER BY victims DESC";
		                    break;
		                case "Victims: Least to Most":
		                    orderBy = "ORDER BY victims ASC";
		                    break;
		            }

		            sortMassacres(orderBy);
		        }
		    });

		    JButton btnLogout = new JButton("Logout");
		    btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    btnLogout.setBounds(294, 355, 153, 25); 
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
