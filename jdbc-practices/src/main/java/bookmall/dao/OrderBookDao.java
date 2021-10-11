package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;

public class OrderBookDao {

	// Connection

	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			// 1. 드라이버 연결
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

	public List<OrderBookVo> findAll() {
		List<OrderBookVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 연결
			conn = getConnection();

			// sql 준비

			String sql = "select a.count, a.price, b.no as bookNo, c.no as orderNo"
					+ " from order_book a, book b , `order` c" + " where a.book_no = b.no and a.order_no = c.no";

			pstmt = conn.prepareStatement(sql);

			// sql 실행

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long count = rs.getLong(1);
				Long price = rs.getLong(2);
				Long bookNo = rs.getLong(3);
				Long orderNo = rs.getLong(4);

				OrderBookVo vo = new OrderBookVo();
				vo.setCount(count);
				vo.setPrice(price);
				vo.setBookNo(bookNo);
				vo.setOrderNo(orderNo);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
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
	public void insert(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into order_book values(?,?,?,?)";
			String updatePrice = "update `order` set pay_amt = pay_amt + ? where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getCount());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getBookNo());
			pstmt.setLong(4, vo.getOrderNo());

			
			pstmt.executeUpdate();
			pstmt.setLong(1, vo.getPrice());
			pstmt.setLong(2, vo.getOrderNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("errpr" + e);
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
