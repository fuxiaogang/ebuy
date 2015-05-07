package com.java1234.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.java1234.entity.Order;
import com.java1234.entity.OrderProduct;
import com.java1234.entity.Product;
import com.java1234.entity.ShoppingCart;
import com.java1234.entity.ShoppingCartItem;
import com.java1234.entity.User;
import com.java1234.service.OrderService;
import com.java1234.util.DateUtil;
import com.java1234.util.NavUtil;
import com.java1234.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class OrderAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	@Resource
	private OrderService orderService;
	
	private String mainPage;
	private String navCode;
	
	private Order s_order;
	private List<Order> orderList;
	
	private int status;
	private String orderNo;
	
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Order getS_order() {
		return s_order;
	}

	public void setS_order(Order s_order) {
		this.s_order = s_order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
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

	public String save()throws Exception{
		HttpSession session=request.getSession();
		Order order=new Order();
		User currentUsre=(User)session.getAttribute("currentUser");
		order.setUser(currentUsre);
		order.setCreateTime(new Date());
		order.setOrderNo(DateUtil.getCurrentDateStr());
		order.setStatus(1);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		float cost=0;
		List<OrderProduct> orderProductList=new LinkedList<OrderProduct>();
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			Product product=shoppingCartItem.getProduct();
			OrderProduct orderProduct=new OrderProduct();
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			cost+=product.getPrice()*shoppingCartItem.getCount();
			orderProductList.add(orderProduct);
		}
		order.setOrderProductList(orderProductList);
		order.setCost(cost);
		
		orderService.saveOrder(order);
		navCode=NavUtil.genNavCode("购物");
		mainPage="shopping/shopping-result.jsp";
		
		session.removeAttribute("shoppingCart");
		return SUCCESS;
	}
	
	public String findOrder()throws Exception{
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		if(s_order==null){
			s_order=new Order();
		}
		s_order.setUser(currentUser);
		orderList=orderService.findOrder(s_order, null);
		navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/orderList.jsp";
		return "orderList";
	}
	
	public String confirmReceive()throws Exception{
		orderService.updateOrderStatus(status, orderNo);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	
}
