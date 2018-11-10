package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;

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
									"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setFileName("fileName");
		product.setManuDate("20181131");
		product.setPrice(12345);
		product.setProdDetail("prodDetail");
		product.setProdName("�׽�Ʈ");

		Assert.assertTrue(productService.addProduct(product)==1);

		product = productService.getProduct(10070);
		System.out.println(product);
		// ==> API Ȯ��
		Assert.assertTrue(10070 == product.getProdNo());
		Assert.assertEquals("fileName", product.getFileName());
		Assert.assertEquals("20181131", product.getManuDate());
		Assert.assertTrue(12345 == product.getPrice());
		Assert.assertEquals("prodDetail", product.getProdDetail());
		Assert.assertEquals("�׽�Ʈ", product.getProdName());
	}

	//@Test
	public void testGetUser() throws Exception {

		Product product = productService.getProduct(10070);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertNotNull(product);

		Assert.assertTrue(10070 == product.getProdNo());
		Assert.assertEquals("fileName", product.getFileName());
		Assert.assertEquals("20181131", product.getManuDate());
		Assert.assertTrue(12345 == product.getPrice());
		Assert.assertEquals("prodDetail", product.getProdDetail());
		Assert.assertEquals("�׽�Ʈ", product.getProdName());
	}

	//@Test
	public void testUpdateUser() throws Exception {

		Product product = productService.getProduct(10070);
		Assert.assertNotNull(product);

		Assert.assertEquals(10070, product.getProdNo());
		Assert.assertEquals("fileName", product.getFileName());
		Assert.assertEquals("20181131", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("prodDetail", product.getProdDetail());
		Assert.assertEquals("�׽�Ʈ", product.getProdName());

		product.setFileName("fileName2");
		product.setManuDate("20181132");
		product.setPrice(123456);
		product.setProdDetail("prodDetail2");
		product.setProdName("�׽�Ʈ2");

		productService.updateProduct(product);

		product = productService.getProduct(10070);
		Assert.assertNotNull(product);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
		Assert.assertEquals(10070, product.getProdNo());
		Assert.assertEquals("fileName2", product.getFileName());
		Assert.assertEquals("20181132", product.getManuDate());
		Assert.assertEquals(123456, product.getPrice());
		Assert.assertEquals("prodDetail2", product.getProdDetail());
		Assert.assertEquals("�׽�Ʈ2", product.getProdName());
	}

	// ==> �ּ��� Ǯ�� �����ϸ�....
	@Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		Integer totalCount = (Integer) map.get("totalCount");
		Assert.assertTrue(65 == totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSortCondition("2");
		search.setFilterCondition("2");
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		
		Assert.assertEquals(3, list.size());
		Assert.assertEquals(10002,((Product)list.get(0)).getProdNo());
		Assert.assertEquals(10005,((Product)list.get(1)).getProdNo());
		Assert.assertEquals(10007,((Product)list.get(2)).getProdNo());
		
		

		totalCount = (Integer) map.get("totalCount");
		Assert.assertTrue(64 == totalCount);
		
	}
	
	//@Test
	public void testGetRandomList() throws Exception {
		List<Product> list = productService.getRandomList(5);
		
		Assert.assertEquals(5, list.size());
	}

}