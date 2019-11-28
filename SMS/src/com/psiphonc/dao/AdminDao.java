package com.psiphonc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.psiphonc.model.Admin;

public class AdminDao extends BaseDao {
	public Admin login(Admin admin) {
		Admin ret = null;
		String sql = "select * from s_admin where name=? and password=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, admin.getName());
			preparedStatement.setString(2, admin.getPassword());
			ResultSet executeQuery = preparedStatement.executeQuery();
			if (executeQuery.next()) {
				ret = new Admin();
				ret.setId(executeQuery.getInt("id"));
				ret.setName(executeQuery.getString("name"));
				ret.setPassword(executeQuery.getString("password"));
				ret.setCreateDate(executeQuery.getString("createDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public String changePW(Admin admin, String npw) {
		PreparedStatement preparedStatement = null;
		String retString = "Faild!";
		String sqlString = "update s_admin set password=? where id =?";
		try {
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setString(1, npw);
			preparedStatement.setInt(2, admin.getId());
			int ret = preparedStatement.executeUpdate();
			if (ret > 0) {
				retString = "Password Changed!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return retString;
	}
}
