package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderVo;

public class OrderDao {

	// connection
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패" + e);
		}
		return conn;

	}

	// select
	public List<OrderVo> findAll() {
		List<OrderVo> list = new ArrayList<>();

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = " select a.no , a.order_no, a.pay_amt, a.ship, b.no as memberNo" + "  from `order` a, member b"
					+ "  where a.member_no = b.no";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				Long orderNo = rs.getLong(2);
				Long payAmt = rs.getLong(3);
				String ship = rs.getString(4);
				Long memberNo = rs.getLong(5);

				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setOrderNo(orderNo);
				vo.setPayAmt(payAmt);
				vo.setShip(ship);
				vo.setMemberNo(memberNo);

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

	public void insert(OrderVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " insert into `order` value(null,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getOrderNo());
			pstmt.setLong(2, vo.getPayAmt());
			pstmt.setString(3, vo.getShip());
			pstmt.setLong(4, vo.getMemberNo());

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
