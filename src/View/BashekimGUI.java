package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper.*;

public class BashekimGUI extends JFrame {
	
    static Bashekim bashekim =new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JTextField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel=null;   //tabloya veri eklemek için table modellerden ayrarlanýyoruz
	private Object[] doctorData=null;            //datalarýn içine atacaðýmýz veriler

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];//type gerek yok tabloda 4 kolon var
		colDoctorName[0]="ID";
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC NO";
		colDoctorName[3]="Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		
		for(int i=0 ;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		

		
		setTitle("Hastane Yönenetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 598);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn "+bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 10, 345, 30);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnNewButton.setBounds(624, 10, 135, 40);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 60, 766, 490);
		w_pane.add(w_tabpane);
		
		JPanel w_doctor = new JPanel();
		w_tabpane.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel lbl_adsoyad = new JLabel("Ad Soyad :");
		lbl_adsoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_adsoyad.setBounds(524, 10, 187, 30);
		w_doctor.add(lbl_adsoyad);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(524, 50, 187, 30);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel lbl_TcNo = new JLabel("T.C. No:");
		lbl_TcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_TcNo.setBounds(524, 90, 187, 30);
		w_doctor.add(lbl_TcNo);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(524, 130, 187, 30);
		w_doctor.add(fld_dTcno);
		
		JLabel lbl_Pass = new JLabel("\u015Eifre  :");
		lbl_Pass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_Pass.setBounds(524, 170, 187, 30);
		w_doctor.add(lbl_Pass);
		
		fld_dPass = new JTextField();
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(524, 210, 187, 30);
		w_doctor.add(fld_dPass);
		
		JButton btn_addDokctor = new JButton("Ekle");
		btn_addDokctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_dName.getText().length()==0||fld_dPass.getText().length()==0|| fld_dTcno.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
                try {
					boolean control=bashekim.addDoctor(fld_dTcno.getText(),fld_dPass.getText(),fld_dName.getText());
					if(control) {
						Helper.showMsg("Success");
						fld_dTcno.setText(null);
						fld_dPass.setText(null);
						fld_dName.setText(null);
						updateDoctorModel();
;						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		btn_addDokctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_addDokctor.setBounds(525, 250, 186, 43);
		w_doctor.add(btn_addDokctor);
		
		JLabel lbl_kullaniciID = new JLabel("Kullan\u0131c\u0131 ID :");
		lbl_kullaniciID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_kullaniciID.setBounds(524, 303, 187, 30);
		w_doctor.add(lbl_kullaniciID);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setEditable(false);
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(524, 349, 187, 30);
		w_doctor.add(fld_doctorID);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorID.getText().length()==0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz !");
					
				}else {
				if(	Helper.confirm("sure")){
						int selectID=Integer.parseInt(fld_doctorID.getText());
						try {
						
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				
				}
			}
		});
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_delDoctor.setBounds(524, 400, 186, 41);
		w_doctor.add(btn_delDoctor);
		
		JScrollPane w_scroolDoctor = new JScrollPane();
		w_scroolDoctor.setBounds(10, 10, 504, 431);
		w_doctor.add(w_scroolDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scroolDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {//tablodaki satýrý seçildiðinde bir dinleme yapp

			@Override
			public void valueChanged(ListSelectionEvent e) {//birþey seçildise deðeri deðiþtiyse bunu bana getir

				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}
			
		});
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {//tablodaki modeldeki hareketliliðe bak

			@Override
			public void tableChanged(TableModelEvent e) {

				if(e.getType()==TableModelEvent.UPDATE) {//eðer üstünde oynama varsa
					int selectID=Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName=table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTc=table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPassword=table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					
					try {
						boolean control=bashekim.updateDoctor(selectID, selectTc, selectPassword, selectName);
						
				
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
	}
	
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);//bütün rowlarý sildirme
		for(int i=0 ;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
	
}
