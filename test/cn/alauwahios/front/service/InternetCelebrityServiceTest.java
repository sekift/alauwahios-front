package cn.alauwahios.front.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.alauwahios.front.impl.AlauwahiosSpringContext;
import cn.alauwahios.front.vo.PageInfo;

public class InternetCelebrityServiceTest {
	private static InternetCelebrityService internetCelebrityService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		internetCelebrityService = (InternetCelebrityService) AlauwahiosSpringContext.getBean("internetCelebrityService");
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
		internetCelebrityService.saveStar(2);
	}
	@Test
	public void testSaveVisits() {
		internetCelebrityService.saveVisits(1);
	}
	@Test
	public void testListBaiduTieba() {
		PageInfo pageInfo = new PageInfo();
		System.out.println(internetCelebrityService.listInternetCelebrity(pageInfo));
	}
}
