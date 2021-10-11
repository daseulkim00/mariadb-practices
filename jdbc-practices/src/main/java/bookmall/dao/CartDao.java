package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bookmall.vo.CartVo;

public class CartDao {

	//conncetion
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}

	// select
	public List<CartVo> findAll() {
		List<CartVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 연결
			conn = getConnection();

			// 3. sql준비
			String sql = "select a.count, b.no as bookNo, c.no as memberNo" + 
			             " from cart a, book b, member c" + 
					     " where a.book_no = b.no"+ " and a.member_no = c.no";

			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			// 5. sql 실행

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long count = rs.getLong(1);
				Long bookNo = rs.getLong(2);
				Long memberNo = rs.getLong(3);

				CartVo vo = new CartVo();
				vo.setCount(count);
				vo.setBookNo(bookNo);
				vo.setMemberNo(memberNo);

				list.add(vo);

			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// clean up
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
	public boolean insert(CartVo vo) {  //boolean은 확인용이고 void로 해도된다.
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql 준비
			String sql = "insert into cart values(?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. binding

			pstmt.setLong(1, vo.getCount());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getMemberNo());

			// 5. sql 실행
			int count = pstmt.executeUpdate();
			// 이게실행되면 1이 반환된다 
			result = count == 1; //countr가 ==1 이면 true를 반환한다.

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
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
		return result; // result를 true로 반환해주는것
	}
	
}
