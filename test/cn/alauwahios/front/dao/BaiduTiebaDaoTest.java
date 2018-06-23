package cn.alauwahios.front.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.alauwahios.front.impl.AlauwahiosSpringContext;
import cn.alauwahios.front.vo.PageInfo;

public class BaiduTiebaDaoTest {

	private static BaiduTiebaDao baiduTiebaDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		baiduTiebaDao = (BaiduTiebaDao) AlauwahiosSpringContext.getBean("baiduTiebaDao");
	}

	@AfterClass
	public static void setDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveStar() {
		baiduTiebaDao.saveStar(1);
	}
	@Test
	public void testSaveVisits() {
		baiduTiebaDao.saveVisits(1);
	}
	@Test
	public void testListBaiduTieba() {
		PageInfo pageInfo = new PageInfo();
		baiduTiebaDao.listBaiduTieba(pageInfo);
	}

}
