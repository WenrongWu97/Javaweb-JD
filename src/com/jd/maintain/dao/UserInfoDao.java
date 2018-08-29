package com.jd.maintain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.jd.common.JDBCUtil;
import com.jd.maintain.dto.UserInfoDto;

public class UserInfoDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserInfoDto queryByUserName(String userName) {
		conn = JDBCUtil.getConnection();
		UserInfoDto userInfoDto = null;
		try {
			String sql = "select * from user_info where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				userInfoDto = new UserInfoDto();
				userInfoDto.setId(rs.getInt("id"));
				userInfoDto.setUserName(rs.getString("user_name"));
				userInfoDto.setPassword(rs.getBytes("password"));
				userInfoDto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, rs);
		}
		return userInfoDto;
	}

	public void saveUserInfo(UserInfoDto userInfoDto) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "insert into user_info(user_name,password,phone) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userInfoDto.getUserName());
			ps.setBytes(2, userInfoDto.getPassword());
			ps.setString(3, userInfoDto.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfoDto UserInfoDto = userInfoDao.queryByUserName("´ó½Å¹Ö");
		if (userInfoDao != null) {
			System.out.println(UserInfoDto.getId());
			System.out.println(UserInfoDto.getUserName());
			System.out.println(Arrays.toString(UserInfoDto.getPassword()));
			System.out.println(UserInfoDto.getPhone());
		}
	}

}
