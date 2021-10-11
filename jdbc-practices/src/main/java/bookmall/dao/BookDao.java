package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {

	// Connection
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}

		return conn;
	}

	// select

	public List<BookVo> findAll() {

		// 1. arraylist 생성
		List<BookVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. 연결
			conn = getConnection();

			// 3. sql 준비
			String sql =
					      " select a.no, a.title, a.price, b.no as categoryNo" + " from book a, category b"
					    + " where a.category_no = b.no";

			pstmt = conn.prepareStatement(sql);

			// 4. sql 실행

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				Long categoryNo = rs.getLong(4);

				BookVo vo = new BookVo();

				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryNo(categoryNo);

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

	
	//insert
	
	public void insert(BookVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = getConnection();
			
			String sql = "insert into book values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2,vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("error:"+ e);
		}finally {
			
			
				try {
					if(pstmt != null) {
					pstmt.close();
					}
					if(conn !=null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	


}
