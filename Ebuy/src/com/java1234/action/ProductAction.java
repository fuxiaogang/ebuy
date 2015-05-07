package com.java1234.action;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.java1234.entity.PageBean;
import com.java1234.entity.Product;
import com.java1234.service.ProductService;
import com.java1234.util.NavUtil;
import com.java1234.util.PageUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ProductAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ProductService productService;
	
	private HttpServletRequest request;
	
	private List<Product> productList;
	private Product s_product;
	
	private String page;
	private Long total;
	private String pageCode;
	private String mainPage;
	private String navCode;
	private int bigTypeId;
	
	private int productId;
	private Product product;
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Product getS_product() {
		return s_product;
	}
	public void setS_product(Product s_product) {
		this.s_product = s_product;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	} 
	
	public int getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(int bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	@Override
	public String execute() throws Exception {
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),8);
		productList=productService.findProductList(s_product, pageBean);
		
		if(productList.size()>0){
			bigTypeId = productList.get(0).getBigType().getId();
		}
		
		
		total=productService.getProductCount(s_product);
		StringBuffer param=new StringBuffer();
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				param.append("s_product.bigType.id="+s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				param.append("s_product.smallType.id="+s_product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(s_product.getName())){
				param.append("s_product.name="+s_product.getName());
			}
		}
		pageCode=PageUtil.genPagination(request.getContextPath()+"/product.action", total, Integer.parseInt(page), 8, param.toString());
		navCode=NavUtil.genNavCode("商品列表");
		mainPage="product/productList.jsp";
		return super.execute();
	}
	
	public String showProduct()throws Exception{
		product = productService.getProductById(productId);
		saveCurrentBrowse(product);
		navCode=NavUtil.genNavCode("商品详情");
		mainPage="product/productDetails.jsp";
		return SUCCESS;
	}
	
	private void saveCurrentBrowse(Product product)throws Exception{
		HttpSession session = request.getSession();
		List<Product> currentBrowseProduct = (List<Product>) session.getAttribute("currentBrowse");
		if(currentBrowseProduct==null){
			currentBrowseProduct = new LinkedList<Product>();		
		}
		boolean flag=true;
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag=false;
				break;
			}
		}
		if(flag){
			currentBrowseProduct.add(0,product);
		}
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	
	
}
