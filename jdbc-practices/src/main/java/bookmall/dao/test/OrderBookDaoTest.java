package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderBookDao;
import bookmall.vo.OrderBookVo;

public class OrderBookDaoTest {

	public static void main(String[] args) {
		insertTest();
		findAllTest();
	}

	private static void insertTest() {
		OrderBookVo vo = null;
		OrderBookDao dao = new OrderBookDao();
		
		vo = new OrderBookVo();
		vo.setOrderNo(1L);
		vo.setBookNo(1L);
		vo.setPrice(20000L);
		vo.setCount(2L);
		dao.insert(vo);
		
		

		vo = new OrderBookVo();
		vo.setOrderNo(1L);
		vo.setBookNo(2L);
		vo.setPrice(50000L);
		vo.setCount(3L);
		dao.insert(vo);
	}

	private static void findAllTest() {
		
		List<OrderBookVo> odlist = new OrderBookDao().findAll();
		for(OrderBookVo vo :odlist) {
			System.out.println(vo);
		}
	}

}
