package com.psiphonc.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.psiphonc.model.UserType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import java.awt.Color;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	public static UserType userType;
	public static Object userObject;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public MainFrm(UserType userType, Object userObject) {
		this.userType = userType;
		this.userObject = userObject;
		setTitle("NCWU SMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 558);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPreferences = new JMenu("Preferences");
		mnPreferences.setIcon(new ImageIcon(MainFrm.class
				.getResource("/icon/preferences.png")));
		menuBar.add(mnPreferences);

		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePWAction(e);
			}
		});
		mntmChangePassword.setIcon(new ImageIcon(MainFrm.class
				.getResource("/icon/cPW.png")));
		mnPreferences.add(mntmChangePassword);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmQuit.setIcon(new ImageIcon(MainFrm.class
				.getResource("/icon/quit.png")));
		mnPreferences.add(mntmQuit);
		
		JMenu mnClass = new JMenu("Class");
		mnClass.setIcon(new ImageIcon(MainFrm.class.getResource("/icon/class.png")));
		menuBar.add(mnClass);
		
		JMenuItem mntmManageClass = new JMenuItem("Manage Class");
		mntmManageClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassManageFrm classManageView = new ClassManageFrm();
				classManageView.setVisible(true);
				desktopPane.add(classManageView);
			}
		});
		mntmManageClass.setIcon(new ImageIcon(MainFrm.class.getResource("/icon/edit.png")));
		mnClass.add(mntmManageClass);
				
				JMenu mnClass_1 = new JMenu("Student");
				mnClass_1.setIcon(new ImageIcon(MainFrm.class.getResource("/icon/un.png")));
				menuBar.add(mnClass_1);
				
				JMenuItem mntmEditstudent = new JMenuItem("Edit Student");
				mntmEditstudent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						StuManageFrm stuManageView = new StuManageFrm();
						stuManageView.setVisible(true);
						desktopPane.add(stuManageView);
					}
				});
				mntmEditstudent.setIcon(new ImageIcon(MainFrm.class.getResource("/icon/edit.png")));
				mnClass_1.add(mntmEditstudent);
		
				JMenu mnHelp = new JMenu("Help");
				mnHelp.setIcon(new ImageIcon(MainFrm.class
						.getResource("/icon/help.png")));
				menuBar.add(mnHelp);
				
						JMenuItem mntmAbout = new JMenuItem("About");
						mntmAbout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								aboutUs(arg0);
							}
						});
						mntmAbout.setIcon(new ImageIcon(MainFrm.class
								.getResource("/icon/about.png")));
						mnHelp.add(mntmAbout);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
	}

	protected void ChangePWAction(ActionEvent e) {
		// TODO Auto-generated method stub
		ChangePWFrm changePWFrm = new ChangePWFrm();
		changePWFrm.setVisible(true);
		desktopPane.add(changePWFrm);

	}

	protected void aboutUs(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,
				"A Curriculum Design of DBMS By psiphonc in NOV.2019");
	}
}
