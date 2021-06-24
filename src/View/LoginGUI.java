package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import Helper.*;
import Model.Bashekim;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn =new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 495);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("hospi.png")));
		lbl_logo.setBounds(236, 23, 86, 71);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(78, 106, 425, 27);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 155, 531, 284);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_tabpane.addTab("Hasta Giri�i", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaran\u0131z :");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblTcNumaranz.setBounds(10, 25, 176, 27);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblifreniz = new JLabel("\u015Eifreniz  :");
		lblifreniz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblifreniz.setBounds(10, 84, 170, 27);
		w_hastaLogin.add(lblifreniz);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaTc.setBounds(196, 25, 309, 27);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_register.setBounds(10, 163, 226, 52);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_hastaLogin.setBounds(279, 163, 226, 52);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(190, 89, 309, 27);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel doktor_Login = new JPanel();
		w_tabpane.addTab("Doktor Giri�i", null, doktor_Login, null);
		doktor_Login.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaran\u0131z :");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblTcNumaranz_1.setBounds(10, 20, 176, 27);
		doktor_Login.add(lblTcNumaranz_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(196, 20, 309, 27);
		doktor_Login.add(fld_doctorTc);
		
		JLabel lblifreniz_1 = new JLabel("\u015Eifreniz  :");
		lblifreniz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblifreniz_1.setBounds(10, 79, 170, 27);
		doktor_Login.add(lblifreniz_1);
		
		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length()==0 || fld_doctorPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();	
						Statement  st=con.createStatement();//varm� yokmu diye bak�yor
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno"))&&fld_doctorPass.getText().equals(rs.getString("password"))) {
								Bashekim bhekim=new Bashekim();
							     bhekim.setId(rs.getInt("id"));
							     bhekim.setPassword(rs.getString("password"));
							     bhekim.setTcno(rs.getString("tcno"));
							     bhekim.setName(rs.getString("name"));
							     bhekim.setType(rs.getString("type"));
							     
							     BashekimGUI bGUI=new BashekimGUI(bhekim);
							     bGUI.setVisible(true);
							     dispose();//varolan JFrame i �ld�r�r
							     
;
							     }
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_doktorLogin.setBounds(10, 158, 495, 52);
		doktor_Login.add(btn_doktorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(196, 79, 309, 27);
		doktor_Login.add(fld_doctorPass);
	}
}
