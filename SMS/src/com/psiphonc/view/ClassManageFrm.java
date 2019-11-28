package com.psiphonc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.psiphonc.dao.ClassDao;
import com.psiphonc.model.StuClass;
import com.psiphonc.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClassManageFrm extends JInternalFrame {
	private JTextField classSearchField;
	private JTable classTable;
	private JButton removeClassBtn;
	private JButton editClassBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassManageFrm frame = new ClassManageFrm();
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
	public ClassManageFrm() {
		setIconifiable(true);
		setBounds(100, 100, 757, 453);
		setLocation(0, 0);
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setIcon(new ImageIcon(ClassManageFrm.class.getResource("/icon/class.png")));
		lblClass.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		setClosable(true);
		
		classSearchField = new JTextField();
		classSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					searchAction();
				}
			}
		});
		classSearchField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchAction();
			}
		});
		searchBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		searchBtn.setIcon(new ImageIcon(ClassManageFrm.class.getResource("/icon/search.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAddClass = new JButton("Add Class");
		btnAddClass.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClassFrm addClassFrm = new AddClassFrm();
				addClassFrm.setVisible(true);
			}
		});
		btnAddClass.setIcon(new ImageIcon(ClassManageFrm.class.getResource("/icon/add.png")));
		
		editClassBtn = new JButton("Edit Class");
		editClassBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		editClassBtn.setEnabled(false);
		editClassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditClassFrm().setVisible(true);
			}
		});
		editClassBtn.setIcon(new ImageIcon(ClassManageFrm.class.getResource("/icon/edit.png")));
		
		removeClassBtn = new JButton("Remove Class");
		removeClassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				removeAction();
			}
		});
		removeClassBtn.setEnabled(false);
		removeClassBtn.setIcon(new ImageIcon(ClassManageFrm.class.getResource("/icon/remove.png")));
		removeClassBtn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblClass)
									.addGap(18)
									.addComponent(classSearchField)
									.addGap(18)
									.addComponent(searchBtn))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(116)
							.addComponent(btnAddClass)
							.addGap(32)
							.addComponent(editClassBtn)
							.addGap(44)
							.addComponent(removeClassBtn)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClass)
								.addComponent(classSearchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(searchBtn)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddClass)
						.addComponent(editClassBtn)
						.addComponent(removeClassBtn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		classTable = new JTable(){
			@Override 
			public boolean isCellEditable(int row, int column)
		     {
		                return false;
		     }
		};
		classTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editClassBtn.setEnabled(true);
				removeClassBtn.setEnabled(true);
				
			}
		});
		classTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Class ID", "Class Name", "Information"
			}
			
		));
		classTable.getColumnModel().getColumn(0).setPreferredWidth(95);
		classTable.getColumnModel().getColumn(1).setPreferredWidth(130);
		classTable.getColumnModel().getColumn(2).setPreferredWidth(289);
		scrollPane.setViewportView(classTable);
		getContentPane().setLayout(groupLayout);
		setTable(new StuClass());
	}
	protected void searchAction() {
		// TODO Auto-generated method stub
		StuClass sc = new StuClass();
		sc.setName(classSearchField.getText().toString());
		setTable(sc);
	}



	protected void removeAction() {
		// TODO Auto-generated method stub
		DefaultTableModel dft = (DefaultTableModel) classTable.getModel();
		StuClass temp = new StuClass();
		temp.setId(Integer.parseInt(classTable.getValueAt(classTable.getSelectedRow(), 0).toString()));
		String cn = classTable.getValueAt(classTable.getSelectedRow(), 1).toString();
		if(JOptionPane.showConfirmDialog(this, 
				"Remove operation is irrevocable,do you want to remove class ?"+cn)
				!= JOptionPane.OK_OPTION){
			return;
			
		}
		ClassDao classDao = new ClassDao();
		if(!classDao.removeClass(temp)){
			JOptionPane.showMessageDialog(this, "Failed!");
		}
		setTable(new StuClass());
		classDao.closeDao();
		editClassBtn.setEnabled(false);
		removeClassBtn.setEnabled(false);
	}



	public void setTable(StuClass stuClass){
		DefaultTableModel dft = (DefaultTableModel) classTable.getModel();
		dft.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StuClass> classList = classDao.getClassList(stuClass);
		for (StuClass sc : classList) {
			Vector v = new Vector();
			v.add(sc.getId());
			v.add(sc.getName());
			v.add(sc.getInfo());
			dft.addRow(v);
		}
		classDao.closeDao();
	}


	public class AddClassFrm extends JFrame {
		private JTextField classNameTextField;
		private JTextArea classInfoTextField;

		/**
		 * Create the frame.
		 */
		public AddClassFrm() {
			setTitle("Add Class");
			setSize(500, 300);
		
			JLabel lblNewLabel = new JLabel("Class Name:");
			lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
			
			
			classNameTextField = new JTextField();
			classNameTextField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Class Info:");
			lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
			setLocationRelativeTo(null);
			
			classInfoTextField = new JTextArea();
			classInfoTextField.setColumns(10);
			
			JButton resetBtn = new JButton("Reset");
			resetBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					resetAction();
				}
			});
			resetBtn.setIcon(new ImageIcon(AddClassFrm.class.getResource("/icon/reset.png")));
			
			JButton submitBtn = new JButton("Submit");
			submitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					submit();
				}
			});
			submitBtn.setIcon(new ImageIcon(AddClassFrm.class.getResource("/icon/confirm.png")));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel)
									.addComponent(lblNewLabel_1))
								.addGap(27)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(classNameTextField)
									.addComponent(classInfoTextField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(65)
								.addComponent(resetBtn)
								.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
								.addComponent(submitBtn)))
						.addGap(78))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(23)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(classInfoTextField, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(resetBtn)
							.addComponent(submitBtn))
						.addContainerGap(17, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);

		}

		protected void submit() {
			// TODO Auto-generated method stub
			String className=classNameTextField.getText().toString();
			String classInfo=classInfoTextField.getText().toString(); 
			if(StringUtil.isEmpty(className)){
				JOptionPane.showMessageDialog(this, "Class Name must be filled!");
				return;
			}
			ClassDao classDao = new ClassDao();
			if(classDao.addClass(new StuClass(1,className,classInfo))){
				JOptionPane.showMessageDialog(this, "Class Added!");
			}else{
				JOptionPane.showMessageDialog(this, "Failed!");
			}
			classDao.closeDao();
			dispose();
			setTable(new StuClass());
		}

		protected void resetAction() {
			// TODO Auto-generated method stub
			classNameTextField.setText("");
			classInfoTextField.setText("");
		}
	}

	public class EditClassFrm extends AddClassFrm
	{
		String className;
		String classInfo;
		public EditClassFrm() {
			// TODO Auto-generated constructor stub
			setTitle("Edit Class");
			DefaultTableModel dft = (DefaultTableModel) classTable.getModel();
			className = dft.getValueAt(classTable.getSelectedRow(), 1).toString();
			classInfo = dft.getValueAt(classTable.getSelectedRow(), 2).toString();
			super.classNameTextField.setText(className);
			super.classInfoTextField.setText(classInfo);
		}
		
		@Override
		protected void submit() {
			// TODO Auto-generated method stub
			String cn = super.classNameTextField.getText();
			String ci = super.classInfoTextField.getText();
			if(classInfo.equals(ci)&&className.equals(cn)){
				JOptionPane.showMessageDialog(this, "You didn't change anything!");
				return;
			}
			DefaultTableModel dft = (DefaultTableModel) classTable.getModel();
			int id =Integer.parseInt(dft.getValueAt(classTable.getSelectedRow(), 0).toString());
			StuClass temp = new StuClass(id,cn,ci);
			ClassDao classDao = new ClassDao();
			if(!classDao.updateClass(temp))
				JOptionPane.showMessageDialog(this, "Failed");
			else {
				dispose();
				setTable(new StuClass());
			}
		}
	}
}
