package com.psiphonc.dao;

import java.sql.SQLException;

import java.sql.Connection;
import com.psiphonc.util.DbUtil;

public class BaseDao {
	public Connection con = new DbUtil().getCon();

	public void closeDao() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
