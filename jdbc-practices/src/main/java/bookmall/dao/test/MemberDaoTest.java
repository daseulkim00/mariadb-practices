package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {

		insertTest();
		findAllTest();
	}



	private static void insertTest() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setName("다슬");
		vo.setEmail("fff@naver.com");
		vo.setPwd("1234");
		vo.setPhone("010-0000-0000");
		dao.insert(vo);
		
		vo = new MemberVo();
		vo.setName("빈츠");
		vo.setEmail("bbb@hanmial.net");
		vo.setPwd("0000");
		vo.setPhone("010-1111-1111");
		dao.insert(vo);

	}
	
	private static void findAllTest() {
		List<MemberVo> mlist = new MemberDao().findAll();
		
		for(MemberVo vo :mlist) {
			System.out.println(vo);
		}
	}

}
