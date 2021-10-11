package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	// 1. connection
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			// 1.드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2.연결하기 : 드라이버 매니저에게 Connection 객체를 달라고 요청
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			// @param getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패" + e);
		}
		return conn;
	}

	// select
	public List<CategoryVo> findAll() {

		// 1. Arraylist 생성
		List<CategoryVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. 연결
			conn = getConnection();

			// 3. sql 준비
			String sql = "select no , name" + " from category";	

			pstmt = conn.prepareStatement(sql);

			// 4. sql 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setName(name);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

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
		return list;
	}

	// insert

	public void insert(CategoryVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 연결
			conn = getConnection();

			// sql 준비
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);

			// binding

			pstmt.setString(1, vo.getName());

			// sql 실행

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
