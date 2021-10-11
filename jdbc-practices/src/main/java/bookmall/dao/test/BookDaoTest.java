package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {

		insertTest();
		findAllTest();
	}
	
	private static void insertTest() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setCategoryNo(1L);
		vo.setPrice(16900L);
		vo.setTitle("아리랑");
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setCategoryNo(2L);
		vo.setPrice(23000L);
		vo.setTitle("뉴문");
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setCategoryNo(3L);
		vo.setPrice(30000L);
		vo.setTitle("전지적 독자 시점");
		dao.insert(vo);
		
	}
	
	private static void findAllTest() {
	
		List<BookVo> blist = new BookDao().findAll();
		
		for(BookVo vo :blist) {
			System.out.println(vo);
		}
		
	}
	
	
}
