package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderBookDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		inserAll();
		findAll();

	}

	private static void inserAll() {

		// category
		CategoryVo ctvo = new CategoryVo();
		CategoryDao ctdao = new CategoryDao();

		ctvo.setName("국내도서");
		ctdao.insert(ctvo);

		ctvo.setName("해외도서");
		ctdao.insert(ctvo);

		ctvo.setName("웹소설");
		ctdao.insert(ctvo);

		// member

		MemberVo mvo = null;
		MemberDao mdao = new MemberDao();

		mvo = new MemberVo();
		mvo.setName("다슬");
		mvo.setEmail("fff@naver.com");
		mvo.setPwd("1234");
		mvo.setPhone("010-0000-0000");
		mdao.insert(mvo);

		mvo = new MemberVo();
		mvo.setName("빈츠");
		mvo.setEmail("bbb@hanmial.net");
		mvo.setPwd("0000");
		mvo.setPhone("010-1111-1111");
		mdao.insert(mvo);

		// book

		BookVo bvo = null;
		BookDao bdao = new BookDao();

		bvo = new BookVo();
		bvo.setCategoryNo(1L);
		bvo.setPrice(16900L);
		bvo.setTitle("아리랑");
		bdao.insert(bvo);

		bvo = new BookVo();
		bvo.setCategoryNo(2L);
		bvo.setPrice(23000L);
		bvo.setTitle("뉴문");
		bdao.insert(bvo);

		bvo = new BookVo();
		bvo.setCategoryNo(3L);
		bvo.setPrice(30000L);
		bvo.setTitle("전지적 독자 시점");
		bdao.insert(bvo);

		// order
		OrderVo ovo = null;
		OrderDao odao = new OrderDao();

		ovo = new OrderVo();
		ovo.setMemberNo(1L);
		ovo.setShip("부산광역시 해운대구 우동 센텀중앙로 55");
		ovo.setPayAmt(0L);
		ovo.setOrderNo(123456789L);
		odao.insert(ovo);

		// orderbook
		OrderBookVo obvo = null;
		OrderBookDao obdao = new OrderBookDao();

		obvo = new OrderBookVo();
		obvo.setOrderNo(1L);
		obvo.setBookNo(1L);
		obvo.setPrice(20000L);
		obvo.setCount(2L);
		obdao.insert(obvo);

		obvo = new OrderBookVo();
		obvo.setOrderNo(1L);
		obvo.setBookNo(2L);
		obvo.setPrice(50000L);
		obvo.setCount(3L);
		obdao.insert(obvo);

		// cart
		CartVo cvo = null;
		CartDao cdao = new CartDao();

		cvo = new CartVo();
		cvo.setCount(3L);
		cvo.setBookNo(2L);
		cvo.setMemberNo(1L);
		cdao.insert(cvo);

		cvo = new CartVo();
		cvo.setCount(2L);
		cvo.setBookNo(1L);
		cvo.setMemberNo(2L);
		cdao.insert(cvo);

	}

	private static void findAll() {
		// category
		List<CategoryVo> ctlist = new CategoryDao().findAll();

		for (CategoryVo vo : ctlist) {
			System.out.println(vo);
		}

		// member
		List<MemberVo> mlist = new MemberDao().findAll();

		for (MemberVo vo : mlist) {
			System.out.println(vo);
		}

		// book
		List<BookVo> blist = new BookDao().findAll();

		for (BookVo vo : blist) {
			System.out.println(vo);
		}
		
		//order
		List<OrderVo> olist = new OrderDao().findAll();
		for(OrderVo vo :olist) {
			System.out.println(vo);
		}
		
		//orderbook
		List<OrderBookVo> odlist = new OrderBookDao().findAll();
		for(OrderBookVo vo :odlist) {
			System.out.println(vo);
		}
		
		//cart
		List<CartVo> clist = new CartDao().findAll();
		for(CartVo vo :clist) {
			System.out.println(vo);
		}
		

	}

}
