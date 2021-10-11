package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrederDaoTest {

	public static void main(String[] args) {

		insertTest();
		findAllTest();
	}

	private static void insertTest() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setMemberNo(1L);
		vo.setShip("부산광역시 해운대구 우동 센텀중앙로 55");
		vo.setPayAmt(50000L);
		vo.setOrderNo(123456789L);
		dao.insert(vo);
		
	}
	
	private static void findAllTest() {
		
		List<OrderVo> olist = new OrderDao().findAll();
		for(OrderVo vo :olist) {
			System.out.println(vo);
		}
	}

}
