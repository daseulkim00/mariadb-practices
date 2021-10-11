package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

public class MemberDao {

		//connection
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
		}
		return conn;
	}

	// select
	public List<MemberVo> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<MemberVo> list = new ArrayList<>();

		try {
			conn = getConnection();

			String sql = " select no, name, email, pwd, phone" + " from member";

			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String pwd = rs.getString(4);
				String phone = rs.getString(5);

				MemberVo vo = new MemberVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPwd(pwd);
				vo.setPhone(phone);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// insert

	public void insert(MemberVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into member values(null, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getPhone());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
