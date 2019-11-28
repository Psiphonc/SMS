package com.psiphonc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.security.auth.login.Configuration;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.psiphonc.dao.AdminDao;
import com.psiphonc.model.Admin;
import com.psiphonc.model.UserType;
import com.psiphonc.util.StringUtil;

public class ChangePWFrm extends JInternalFrame {

	private JPanel contentPane;
	private JPasswordField originPWField;
	private JPasswordField newPWField;
	private JPasswordField confirmPWField;


	/**
	 * Create the frame.
	 */
	public ChangePWFrm() {
		setTitle("Change Password");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);

		JLabel lblNewLabel = new JLabel("Origin Password:");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

		JLabel lblConfirmNewPassword = new JLabel("Confirm Password:");
		lblConfirmNewPassword.setFont(new Font("Microsoft YaHei UI",
				Font.PLAIN, 15));

		originPWField = new JPasswordField();

		newPWField = new JPasswordField();

		confirmPWField = new JPasswordField();

		JButton submitButton = new JButton("Submit");
		submitButton.setIcon(new ImageIcon(ChangePWFrm.class
				.getResource("/icon/confirm.png")));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit(arg0);
			}
		});

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetField(e);
			}
		});
		resetButton.setIcon(new ImageIcon(ChangePWFrm.class
				.getResource("/icon/reset.png")));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setIcon(new ImageIcon(ChangePWFrm.class
				.getResource("/icon/un.png")));
		lblUsername.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));

		JLabel usernameLabel = new JLabel("New label");
		usernameLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		usernameLabel.setEnabled(false);
		usernameLabel.setText("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblConfirmNewPassword)
																						.addComponent(
																								lblNewPassword)
																						.addComponent(
																								lblNewLabel)
																						.addComponent(
																								lblUsername)))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap(
																				78,
																				Short.MAX_VALUE)
																		.addComponent(
																				resetButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								usernameLabel)
																						.addComponent(
																								originPWField,
																								202,
																								202,
																								202)
																						.addGroup(
																								gl_contentPane
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												confirmPWField,
																												Alignment.TRAILING,
																												GroupLayout.DEFAULT_SIZE,
																												184,
																												Short.MAX_VALUE)
																										.addComponent(
																												newPWField))))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(76)
																		.addComponent(
																				submitButton)))
										.addContainerGap(53, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(42)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																usernameLabel)
														.addComponent(
																lblUsername))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																originPWField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																newPWField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNewPassword))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																confirmPWField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblConfirmNewPassword))
										.addGap(27)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																submitButton)
														.addComponent(
																resetButton))
										.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
		if (UserType.ADMIN.getName().equals(MainFrm.userType.getName())) {
			Admin admin = (Admin) MainFrm.userObject;
			usernameLabel.setText("[Administrator] " + admin.getName());
		}
	}

	protected void submit(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object uo = MainFrm.userObject;
		String opw = originPWField.getText().toString();
		String npw = newPWField.getText().toString();
		String cpw = confirmPWField.getText().toString();
		if (StringUtil.isEmpty(opw) || StringUtil.isEmpty(npw)
				|| StringUtil.isEmpty(cpw)) {
			JOptionPane
					.showMessageDialog(this, "Please fill all of the filed!");
			return;
		}
		if (!npw.equals(cpw)) {
			JOptionPane.showMessageDialog(this,
					"Two passwords are inconsistent!");
			return;
		}

		if (UserType.ADMIN.getName().equals(MainFrm.userType.getName())) {
			Admin a = (Admin) uo;
			if (!opw.equals(a.getPassword())) {
				JOptionPane.showMessageDialog(this, "Wrong Origin Password!");
				return;
			}
			AdminDao adminDao = new AdminDao();
			JOptionPane.showMessageDialog(this, adminDao.changePW(a, npw));
			adminDao.closeDao();
			return;
		}
	}

	protected void resetField(ActionEvent e) {
		// TODO Auto-generated method stub
		originPWField.setText("");
		newPWField.setText("");
		confirmPWField.setText("");
	}
}
