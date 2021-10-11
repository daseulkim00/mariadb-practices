package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CatagoryDaoTest {

	public static void main(String[] args) {
		insertTest();
		findAllTest();
	}

	private static void insertTest() {
		CategoryVo vo = new CategoryVo();
		CategoryDao dao = new CategoryDao();

		vo.setName("국내도서");
		dao.insert(vo);
		
		vo.setName("해외도서");
		dao.insert(vo);
		
		vo.setName("웹소설");
		dao.insert(vo);
	}

	private static void findAllTest() {
		List<CategoryVo> ctlist = new CategoryDao().findAll();
		
		for (CategoryVo vo : ctlist) {
			System.out.println(vo);

		}
		
		
		

		
	}
}
