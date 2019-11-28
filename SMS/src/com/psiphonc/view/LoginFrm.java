package com.psiphonc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.psiphonc.dao.AdminDao;
import com.psiphonc.model.Admin;
import com.psiphonc.model.UserType;
import com.psiphonc.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.IllegalFormatCodePointException;
import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTexField;
	private JComboBox userTypeComboBox;
	private Object selectedItem;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
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
	public LoginFrm() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JLabel label = new JLabel("");

		JLabel lblNcwuSmsLogin = new JLabel("NCWU SMS Login");
		lblNcwuSmsLogin.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/stu.png")));
		lblNcwuSmsLogin.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblUserName.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/un.png")));

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblPassword.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/pw.png")));

		usernameTexField = new JTextField();
		usernameTexField.setColumns(10);

		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/ut.png")));
		lblUserType.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

		userTypeComboBox = new JComboBox();
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] {
				UserType.ADMIN, UserType.TEACHER, UserType.STUDENT }));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAction(ae);
			}
		});
		btnLogin.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/login.png")));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetBtmAct(ae);
			}
		});
		btnReset.setIcon(new ImageIcon(LoginFrm.class
				.getResource("/icon/reset.png")));

		passwordTextField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(58)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblNcwuSmsLogin,
																GroupLayout.PREFERRED_SIZE,
																256,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(556)
																		.addComponent(
																				label))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				btnReset,
																				GroupLayout.PREFERRED_SIZE,
																				95,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(46)
																		.addComponent(
																				btnLogin,
																				GroupLayout.PREFERRED_SIZE,
																				100,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblUserName)
																						.addComponent(
																								lblPassword)
																						.addComponent(
																								lblUserType))
																		.addGap(14)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								passwordTextField)
																						.addComponent(
																								userTypeComboBox,
																								0,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								usernameTexField))))));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(32)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(label)
														.addComponent(
																lblNcwuSmsLogin,
																GroupLayout.PREFERRED_SIZE,
																64,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblUserName)
														.addComponent(
																usernameTexField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(11)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPassword)
														.addComponent(
																passwordTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																userTypeComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblUserType))
										.addGap(32)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnReset,
																GroupLayout.DEFAULT_SIZE,
																27,
																Short.MAX_VALUE)
														.addComponent(
																btnLogin,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(32)));
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginAction(ActionEvent ae) {
		String un = usernameTexField.getText().toString();
		String pw = passwordTextField.getText().toString();
		UserType selectedItem = (UserType) userTypeComboBox.getSelectedItem();
		if (StringUtil.isEmpty(un)) {
			JOptionPane.showMessageDialog(this, "Empty username!");
			return;
		}
		if (StringUtil.isEmpty(pw)) {
			JOptionPane.showMessageDialog(this, "Empty password!");
			return;
		}
		Admin admin;
		if (UserType.ADMIN.getName().equals(selectedItem.getName())) {
			// Administrator login
			AdminDao adminDao = new AdminDao();
			Admin temp = new Admin();
			temp.setName(un);
			temp.setPassword(pw);
			admin = adminDao.login(temp);
			if (admin == null) {
				JOptionPane.showMessageDialog(this,
						"Wrong username or password!");
				passwordTextField.setText("");
				return;
			}
			this.dispose();
			new MainFrm(selectedItem, admin).setVisible(true);
		} else if (UserType.TEACHER.getName().equals(selectedItem.getName())) {
			// Teacher login
		} else {
			// Student login
		}
	}

	protected void resetBtmAct(ActionEvent ae) {
		usernameTexField.setText("");
		passwordTextField.setText("");
		userTypeComboBox.setSelectedIndex(0);
	}
}
