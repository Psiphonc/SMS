package com.psiphonc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import com.psiphonc.model.StuClass;
import com.psiphonc.util.StringUtil;

public class ClassDao extends BaseDao {
	public boolean addClass(StuClass c){
		String sqlString ="insert into s_class values(null,?,?)";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setString(1,c.getName());
			preparedStatement.setString(2,c.getInfo());
			if (preparedStatement.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<StuClass> getClassList(StuClass c){
		List<StuClass> retList = new ArrayList<StuClass>();
		String sqlString = "select * from s_class";
		if(!StringUtil.isEmpty(c.getName())){
			sqlString += " where name like '%"+c.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				StuClass sc = new StuClass(executeQuery.getInt("id"),executeQuery.getString("name"),executeQuery.getString("info"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;
	}
	public boolean removeClass(StuClass c){
		String sqlString ="delete from s_class where id=?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setInt(1,c.getId());
			if (preparedStatement.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateClass(StuClass c){
		String sql = "update s_class set name=?, info=? where id=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getInfo());
			preparedStatement.setInt(3, c.getId());
			if(preparedStatement.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public StuClass getStuClass(int classId){
		String sqlString = "select * from s_class where id=?";
		StuClass sc=null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setInt(1, classId);
			ResultSet executeQuery = preparedStatement.executeQuery();
			executeQuery.next();
			sc = new StuClass(executeQuery.getInt("id"),executeQuery.getString("name"),executeQuery.getString("info"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sc;
	}
}
