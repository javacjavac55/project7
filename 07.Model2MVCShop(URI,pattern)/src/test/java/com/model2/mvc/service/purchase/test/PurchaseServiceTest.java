package com.model2.mvc.service.purchase.test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"  })
public class PurchaseServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {

		User user = new User();
		user.setUserId("user01");
		Product product = new Product();
		product.setProdNo(10060);
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("receiverName");
		purchase.setReceiverPhone("010-5555-5555");
		purchase.setDivyAddr("divyAddr");
		purchase.setDivyRequest("divyRequest");
		purchase.setTranCode("1");
		purchase.setDivyDate("20181111");

		Assert.assertTrue(purchaseService.addPurchase(purchase)==1);

		/*product = productService.getProduct(10070);
		System.out.println(product);
		// ==> API Ȯ��
		Assert.assertTrue(10070 == product.getProdNo());
		Assert.assertEquals("fileName", product.getFileName());
		Assert.assertEquals("20181131", product.getManuDate());
		Assert.assertTrue(12345 == product.getPrice());
		Assert.assertEquals("prodDetail", product.getProdDetail());
		Assert.assertEquals("�׽�Ʈ", product.getProdName());*/
	}

	//@Test
	public void testGetPurchase() throws Exception {

		Purchase purchase = purchaseService.getPurchase(10026);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertNotNull(purchase);
		Assert.assertTrue(10026 == purchase.getTranNo());
		Assert.assertTrue(10060 == purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("receiverName", purchase.getReceiverName());
		Assert.assertEquals("010-5555-5555", purchase.getReceiverPhone());
		Assert.assertEquals("divyAddr", purchase.getDivyAddr());
		Assert.assertEquals("divyRequest", purchase.getDivyRequest());
		Assert.assertEquals("1", purchase.getTranCode().trim());
		Assert.assertEquals("20181111", purchase.getDivyDate().substring(0,10).replace("-", ""));
		Assert.assertEquals("2018-11-01",purchase.getOrderDate().toString());
		
		
		purchase = purchaseService.getPurchase2(10060);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertNotNull(purchase);
		Assert.assertTrue(10026 == purchase.getTranNo());
		Assert.assertTrue(10060 == purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("receiverName", purchase.getReceiverName());
		Assert.assertEquals("010-5555-5555", purchase.getReceiverPhone());
		Assert.assertEquals("divyAddr", purchase.getDivyAddr());
		Assert.assertEquals("divyRequest", purchase.getDivyRequest());
		Assert.assertEquals("1", purchase.getTranCode().trim());
		Assert.assertEquals("20181111", purchase.getDivyDate().substring(0,10).replace("-", ""));
		Assert.assertEquals("2018-11-01",purchase.getOrderDate().toString());
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception {

		Purchase purchase = purchaseService.getPurchase(10026);
		Assert.assertNotNull(purchase);
		Assert.assertTrue(10026 == purchase.getTranNo());
		Assert.assertTrue(10060 == purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals("receiverName", purchase.getReceiverName());
		Assert.assertEquals("010-5555-5555", purchase.getReceiverPhone());
		Assert.assertEquals("divyAddr", purchase.getDivyAddr());
		Assert.assertEquals("divyRequest", purchase.getDivyRequest());
		Assert.assertEquals("1", purchase.getTranCode().trim());
		Assert.assertEquals("20181111", purchase.getDivyDate().substring(0,10).replace("-", ""));
		Assert.assertEquals("2018-11-01",purchase.getOrderDate().toString());

		purchase.setPaymentOption("2");
		purchase.setReceiverName("receiverName2");
		purchase.setReceiverPhone("010-7777-7777");
		purchase.setDivyAddr("divyAddr2");
		purchase.setDivyRequest("divyRequest2");
		purchase.setTranCode("2");
		purchase.setDivyDate("20181112");

		purchaseService.updatePurchase(purchase);

		purchase = purchaseService.getPurchase(10026);
		Assert.assertNotNull(purchase);
		Assert.assertTrue(10026 == purchase.getTranNo());
		Assert.assertTrue(10060 == purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("2", purchase.getPaymentOption().trim());
		Assert.assertEquals("receiverName2", purchase.getReceiverName());
		Assert.assertEquals("010-7777-7777", purchase.getReceiverPhone());
		Assert.assertEquals("divyAddr2", purchase.getDivyAddr());
		Assert.assertEquals("divyRequest2", purchase.getDivyRequest());
		Assert.assertEquals("2", purchase.getTranCode().trim());
		Assert.assertEquals("20181112", purchase.getDivyDate().substring(0,10).replace("-", ""));
		Assert.assertEquals("2018-11-01",purchase.getOrderDate().toString());
	}
	
	// ==> �ּ��� Ǯ�� �����ϸ�....
	@Test
	public void testGetPurchaseListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = purchaseService.getPurchaseList(search, "user01");

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(10060,((Purchase)list.get(0)).getPurchaseProd().getProdNo());
		Assert.assertEquals(10055,((Purchase)list.get(1)).getPurchaseProd().getProdNo());
		Assert.assertEquals(10001,((Purchase)list.get(2)).getPurchaseProd().getProdNo());

		Integer totalCount = (Integer) map.get("totalCount");
		Assert.assertTrue(3 == totalCount);		
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception {
		Purchase purchase = purchaseService.getPurchase(10026);
		Assert.assertNotNull(purchase);
		Assert.assertTrue(10026 == purchase.getTranNo());
		Assert.assertTrue(10060 == purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		Assert.assertEquals("2", purchase.getPaymentOption().trim());
		Assert.assertEquals("receiverName2", purchase.getReceiverName());
		Assert.assertEquals("010-7777-7777", purchase.getReceiverPhone());
		Assert.assertEquals("divyAddr2", purchase.getDivyAddr());
		Assert.assertEquals("divyRequest2", purchase.getDivyRequest());
		Assert.assertEquals("3", purchase.getTranCode().trim());
		Assert.assertEquals("20181112", purchase.getDivyDate().substring(0,10).replace("-", ""));
		Assert.assertEquals("2018-11-01",purchase.getOrderDate().toString());
		
		purchase.setTranCode("4");
		Assert.assertEquals(1, purchaseService.updateTranCode(purchase));
		purchase = purchaseService.getPurchase(10026);
		Assert.assertNotNull(purchase);
		Assert.assertEquals("4", purchase.getTranCode().trim());
	}

}