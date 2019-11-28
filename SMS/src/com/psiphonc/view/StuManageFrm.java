package com.psiphonc.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.psiphonc.dao.ClassDao;
import com.psiphonc.dao.StudentDao;
import com.psiphonc.model.StuClass;
import com.psiphonc.model.Student;
import com.psiphonc.util.StringUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StuManageFrm extends JInternalFrame {
	private JTable stuTable;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JComboBox classComboBox;
	private JButton btnEditStudent;
	private JButton btnRemoveStuden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuManageFrm frame = new StuManageFrm();
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
	public StuManageFrm() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 772, 463);
		setLocation(0, 0);
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblStudentId = new JLabel("ID:");
		lblStudentId.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblStudentId.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/id.png")));
		
		idTextField = new JTextField();
		idTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					searchByIdAction();
				}
			}
		});
		idTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name\uFF1A");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/un.png")));
		
		nameTextField = new JTextField();
		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchByNameAction();
				}
			}
		});
		nameTextField.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JLabel lblClass = new JLabel("Class\uFF1A");
		lblClass.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblClass.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/class.png")));
		
		classComboBox = new JComboBox();
		classComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				searchByClassAction();
			}
		});
		
		JButton nameSearchBtn = new JButton("");
		nameSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByNameAction();
			}
		});
		nameSearchBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		nameSearchBtn.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/Search.png")));
		
		JButton addStuBtn = new JButton("Add Student");
		addStuBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		addStuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddStudentFrm().setVisible(true);
			}
		});
		addStuBtn.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/add.png")));
		
		JButton idSearchBtn = new JButton("");
		idSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchByIdAction();
			}
		});
		idSearchBtn.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/Search.png")));
		
		JButton resetBtn = new JButton("");
		resetBtn.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/reset.png")));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillClassCombo(classComboBox);
				nameTextField.setText("");
				idTextField.setText("");
				stuTable.clearSelection();
				setTable(new Student());
			}
		});
		
		btnEditStudent = new JButton("Edit Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editStudentAction();
			}
		});
		btnEditStudent.setEnabled(false);
		btnEditStudent.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/edit.png")));
		
		btnRemoveStuden = new JButton("Remove Studen");
		btnRemoveStuden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAction();
			}
		});
		btnRemoveStuden.setEnabled(false);
		btnRemoveStuden.setIcon(new ImageIcon(StuManageFrm.class.getResource("/icon/remove.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentId)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(idSearchBtn, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nameSearchBtn)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblClass)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addGap(57))
								.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addComponent(addStuBtn)
							.addGap(37)
							.addComponent(btnEditStudent)
							.addGap(47)
							.addComponent(btnRemoveStuden)
							.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
							.addComponent(resetBtn))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(19))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblStudentId))
									.addComponent(idSearchBtn))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel)
										.addComponent(classComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblClass))
									.addGap(1))
								.addComponent(nameSearchBtn, Alignment.LEADING))
							.addGap(18)))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(resetBtn)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(addStuBtn)
							.addComponent(btnEditStudent)
							.addComponent(btnRemoveStuden)))
					.addGap(75))
		);
		
		stuTable = new JTable(){
			@Override 
			public boolean isCellEditable(int row, int column)
		     {
		                return false;
		     }
		};
		stuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setField();
				btnEditStudent.setEnabled(true);
				btnRemoveStuden.setEnabled(true);
			}
		});
		stuTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Name", "Class", "Gender"
			}
		));
		stuTable.getColumnModel().getColumn(0).setPreferredWidth(121);
		scrollPane.setViewportView(stuTable);
		getContentPane().setLayout(groupLayout);
		fillClassCombo(classComboBox);
		setTable(new Student());
	}
	

	protected void removeAction() {
		// TODO Auto-generated method stub
		DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
		Student temp = new Student();
		temp.setId(Integer.parseInt(stuTable.getValueAt(stuTable.getSelectedRow(), 0).toString()));
		String sn = stuTable.getValueAt(stuTable.getSelectedRow(), 1).toString();
		if(JOptionPane.showConfirmDialog(this, 
				"Remove operation is irrevocable,do you want to remove student ?"+sn)
				!= JOptionPane.OK_OPTION){
			return;
		}
		StudentDao studentDao = new StudentDao();
		if(!studentDao.removeStudent(temp)){
			JOptionPane.showMessageDialog(this, "Failed!");
		}
		setTable(new Student());
		studentDao.closeDao();
		btnEditStudent.setEnabled(false);
		btnRemoveStuden.setEnabled(false);
	}

	protected void editStudentAction() {
		// TODO Auto-generated method stub
		new EditStuFrm().setVisible(true);
		
	}

	protected void searchByNameAction() {
		Student stu=new Student();
		stu.setName(nameTextField.getText());
		setTable(stu);
	}

	protected void searchByIdAction() {
		if(StringUtil.isEmpty(idTextField.getText().toString()))
			return ;
		int parseInt = Integer.parseInt(idTextField.getText().toString());
		DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
		for(int i = 0; i < stuTable.getRowCount(); i++){
			if(((Integer)dft.getValueAt(i, 0)).equals(new Integer(parseInt))){
				stuTable.setRowSelectionInterval(i,i);
			}
		}
	}

	protected void searchByClassAction() {
		if(classComboBox.getItemCount()<=0) return;
		DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
		dft.setRowCount(0);
		StuClass sc = (StuClass)classComboBox.getSelectedItem();
		Student stu = new Student();
		stu.setClassId(sc.getId());
		StudentDao stuDao = new StudentDao();
		List<Student> stuList = stuDao.getStuListByClass(stu);
		ClassDao classDao = new ClassDao();
		for (Student s : stuList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(classDao.getStuClass(s.getClassId()));
			v.add(s.getGender());
			dft.addRow(v);
		}
		classDao.closeDao();
		stuDao.closeDao();
	}

	protected void setField() {
		DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
		String stuId = dft.getValueAt(stuTable.getSelectedRow(), 0).toString();
		String stuName = dft.getValueAt(stuTable.getSelectedRow(), 1).toString();
		nameTextField.setText(stuName);
		idTextField.setText(stuId);
	}


	public class AddStudentFrm extends JFrame {
		protected JTextField stuNameTextField;
		protected JPasswordField passwordField;
		protected JComboBox classComboBox = new JComboBox();
		protected ButtonGroup gendersBtnGroup = new ButtonGroup();
		protected JRadioButton femaleRdbtn;
		protected JRadioButton maleRdbtn;

		/**
		 * Create the frame.
		 */
		public AddStudentFrm() {
			setTitle("AddStudent");
			setBounds(100, 100, 453, 343);
			setLocationRelativeTo(null);
			JLabel lblName = new JLabel("Name");
			lblName.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/un.png")));
			lblName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

			JLabel lblClass = new JLabel("Class");
			lblClass.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/class.png")));
			lblClass.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

			JLabel lblPassword = new JLabel("Password");
			lblPassword.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/pw.png")));
			lblPassword.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

			stuNameTextField = new JTextField();
			stuNameTextField.setColumns(10);

			passwordField = new JPasswordField();

			JButton submitBtn = new JButton("Submit");
			submitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					submitAction();
				}
			});
			submitBtn.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/confirm.png")));

			JButton resetBtn = new JButton("Reset");
			resetBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetAction();
				}
			});
			resetBtn.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/reset.png")));

			JLabel lblGender = new JLabel("Gender");
			lblGender.setIcon(new ImageIcon(AddStudentFrm.class
					.getResource("/icon/gender.png")));
			lblGender.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

			maleRdbtn = new JRadioButton("Male");
			maleRdbtn.setSelected(true);
			femaleRdbtn = new JRadioButton("Female");
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout
					.setHorizontalGroup(groupLayout
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									groupLayout.createSequentialGroup().addGap(86)
											.addComponent(resetBtn).addGap(43)
											.addComponent(submitBtn)
											.addContainerGap(105, Short.MAX_VALUE))
							.addGroup(
									groupLayout
											.createSequentialGroup()
											.addGap(62)
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.LEADING)
															.addGroup(
																	groupLayout
																			.createSequentialGroup()
																			.addComponent(
																					lblGender)
																			.addContainerGap())
															.addGroup(
																	groupLayout
																			.createSequentialGroup()
																			.addGroup(
																					groupLayout
																							.createParallelGroup(
																									Alignment.LEADING)
																							.addComponent(
																									lblClass)
																							.addComponent(
																									lblName)
																							.addComponent(
																									lblPassword))
																			.addGap(32)
																			.addGroup(
																					groupLayout
																							.createParallelGroup(
																									Alignment.LEADING)
																							.addComponent(
																									stuNameTextField,
																									Alignment.TRAILING,
																									GroupLayout.DEFAULT_SIZE,
																									199,
																									Short.MAX_VALUE)
																							.addComponent(
																									classComboBox,
																									0,
																									199,
																									Short.MAX_VALUE)
																							.addComponent(
																									passwordField,
																									Alignment.TRAILING,
																									GroupLayout.DEFAULT_SIZE,
																									199,
																									Short.MAX_VALUE)
																							.addGroup(
																									groupLayout
																											.createSequentialGroup()
																											.addPreferredGap(
																													ComponentPlacement.RELATED)
																											.addComponent(
																													maleRdbtn)
																											.addGap(18)
																											.addComponent(
																													femaleRdbtn)))
																			.addGap(52)))));
			gendersBtnGroup.add(maleRdbtn);
			gendersBtnGroup.add(femaleRdbtn);
			groupLayout
					.setVerticalGroup(groupLayout
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									groupLayout
											.createSequentialGroup()
											.addContainerGap(39, Short.MAX_VALUE)
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	stuNameTextField,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	lblName,
																	GroupLayout.PREFERRED_SIZE,
																	23,
																	GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	passwordField,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	lblPassword))
											.addGap(18)
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	classComboBox,
																	GroupLayout.PREFERRED_SIZE,
																	18,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(lblClass))
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.LEADING)
															.addGroup(
																	groupLayout
																			.createSequentialGroup()
																			.addGap(18)
																			.addComponent(
																					lblGender)
																			.addGap(29))
															.addGroup(
																	groupLayout
																			.createSequentialGroup()
																			.addGap(18)
																			.addGroup(
																					groupLayout
																							.createParallelGroup(
																									Alignment.BASELINE)
																							.addComponent(
																									maleRdbtn)
																							.addComponent(
																									femaleRdbtn))))
											.addGap(18)
											.addGroup(
													groupLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(resetBtn)
															.addComponent(submitBtn))
											.addContainerGap(27, Short.MAX_VALUE)));
			getContentPane().setLayout(groupLayout);
			fillClassCombo(classComboBox);
		}

		protected void resetAction() {
			stuNameTextField.setText("");
			passwordField.setText("");
			classComboBox.setSelectedIndex(0);
			gendersBtnGroup.clearSelection();
			maleRdbtn.setSelected(true);
		}

		protected void submitAction() {
			String pw = passwordField.getText();
			String sname = stuNameTextField.getText();
			if(StringUtil.isEmpty(pw)||StringUtil.isEmpty(sname)){
				JOptionPane.showMessageDialog(this, "Please fill all of the blanks!");
				return;
			}
			String gender = maleRdbtn.isSelected() ? maleRdbtn.getText()
					: femaleRdbtn.getText();
			StuClass sc = (StuClass) classComboBox.getSelectedItem();
			Student student = new Student();
			student.setName(sname);
			student.setPassword(pw);
			student.setClassId(sc.getId());
			student.setGender(gender);
			StudentDao studentDao = new StudentDao();
			if(!studentDao.addStu(student))
				JOptionPane.showMessageDialog(this, "failed");
			else {
				resetAction();
			}
			dispose();
			setTable(new Student());
		}
		
	}
	public void fillClassCombo(JComboBox<Object> cb) {
		ClassDao classDao = new ClassDao();
		List<StuClass> slist = classDao.getClassList(new StuClass());
		cb.removeAllItems();
		for (StuClass stuClass : slist) {
			cb.addItem(stuClass);
		}
		classDao.closeDao();
	}
	public void setTable(Student stu){
		DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
		dft.setRowCount(0);
		StudentDao stuDao = new StudentDao();
		List<Student> stuList = stuDao.getStuListByName(stu);
		ClassDao classDao = new ClassDao();
		for (Student s : stuList) {
			Vector v = new Vector();
			v.add(s.getId());
			v.add(s.getName());
			v.add(classDao.getStuClass(s.getClassId()));
			v.add(s.getGender());
			dft.addRow(v);
		}
		classDao.closeDao();
		stuDao.closeDao();
	}
	
	public class EditStuFrm extends AddStudentFrm{
		int id;
		String name;
		String password;
		int classId;
		String gender;
		public EditStuFrm() {
			DefaultTableModel dft = (DefaultTableModel) stuTable.getModel();
			id=Integer.parseInt(dft.getValueAt(stuTable.getSelectedRow(), 0).toString());
			name=dft.getValueAt(stuTable.getSelectedRow(), 1).toString();
			classId=((StuClass)(dft.getValueAt(stuTable.getSelectedRow(), 2))).getId();
			gender=dft.getValueAt(stuTable.getSelectedRow(), 3).toString();
			super.setTitle("Edit Student");
			super.stuNameTextField.setText(name);
			if("Male".equals(gender))
				super.maleRdbtn.setSelected(true);
			else {
				super.femaleRdbtn.setSelected(true);
			}
		}
		@Override
		protected void submitAction() {
			// TODO Auto-generated method stub
			String pw = passwordField.getText();
			String sname = stuNameTextField.getText();
			if(StringUtil.isEmpty(pw)||StringUtil.isEmpty(sname)){
				JOptionPane.showMessageDialog(this, "Please fill all of the blanks!");
				return;
			}
			String gender = maleRdbtn.isSelected() ? maleRdbtn.getText()
					: femaleRdbtn.getText();
			StuClass sc = (StuClass) classComboBox.getSelectedItem();
			Student student = new Student();
			student.setId(id);
			student.setName(sname);
			student.setPassword(pw);
			student.setClassId(sc.getId());
			student.setGender(gender);
			StudentDao studentDao = new StudentDao();
			if(!studentDao.updateStu(student))
				JOptionPane.showMessageDialog(this, "failed");
			else {
				resetAction();
			}
			dispose();
			setTable(new Student());
		}
	}
}
