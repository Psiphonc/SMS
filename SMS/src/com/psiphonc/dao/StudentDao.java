package com.psiphonc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.psiphonc.model.StuClass;
import com.psiphonc.model.Student;
import com.psiphonc.util.StringUtil;

public class StudentDao extends BaseDao {
	public boolean addStu(Student s){
		String sqlString ="insert into s_student values(null,?,?,?,?)";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setString(1,s.getName());
			preparedStatement.setInt(2,s.getClassId());
			preparedStatement.setString(3,s.getPassword());
			preparedStatement.setString(4,s.getGender());
			if (preparedStatement.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateStu(Student s){
		String sqlString ="update s_student set name=?,classid=?,password=?,gender=? where id=?";
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setString(1,s.getName());
			preparedStatement.setInt(2,s.getClassId());
			preparedStatement.setString(3,s.getPassword());
			preparedStatement.setString(4,s.getGender());
			preparedStatement.setInt(5,s.getId());
			if (preparedStatement.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Student> getStuListByName(Student stu) {
		List<Student> retList = new ArrayList<Student>();
		String sqlString = "select * from s_student";
		if(!StringUtil.isEmpty(stu.getName())){
			sqlString += " where name like '%"+stu.getName()+"%'";
		}
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Student s = new Student(executeQuery.getInt("id"),executeQuery.getString("name"),executeQuery.getInt("classid"),executeQuery.getString("password"),executeQuery.getString("gender"));
				retList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retList;
	}
	public List<Student> getStuListByClass(Student stu){
		List<Student> retList = new ArrayList<Student>();
		String sqlString = "select * from s_student where classid = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setInt(1, stu.getClassId());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Student s = new Student(executeQuery.getInt("id"),executeQuery.getString("name"),executeQuery.getInt("classid"),executeQuery.getString("password"),executeQuery.getString("gender"));
				retList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	public Student getStudent(Student stu){
		Student ret=null;
		List<Student> retList = new ArrayList<Student>();
		String sqlString = "select * from s_student where id = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			preparedStatement.setInt(1, stu.getId());
			ResultSet executeQuery = preparedStatement.executeQuery();
			executeQuery.next();
			ret = new Student(executeQuery.getInt("id"),executeQuery.getString("name"),executeQuery.getInt("classid"),executeQuery.getString("password"),executeQuery.getString("gender"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
		
	}
	
	public boolean removeStudent(Student c){
		String sqlString ="delete from s_student where id=?";
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
}
