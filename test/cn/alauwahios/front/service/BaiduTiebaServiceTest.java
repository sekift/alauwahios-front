package cn.alauwahios.front.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.alauwahios.front.impl.AlauwahiosSpringContext;
import cn.alauwahios.front.vo.PageInfo;

public class BaiduTiebaServiceTest {

	private static BaiduTiebaService baiduTiebaService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		baiduTiebaService = (BaiduTiebaService) AlauwahiosSpringContext.getBean("baiduTiebaService");
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
		baiduTiebaService.saveStar(2);
	}
	@Test
	public void testSaveVisits() {
		baiduTiebaService.saveVisits(1);
	}
	@Test
	public void testListBaiduTieba() {
		PageInfo pageInfo = new PageInfo();
		baiduTiebaService.listBaiduTieba(pageInfo);
	}
}
