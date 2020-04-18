package com.shop.product.service;

import java.util.List;

import com.shop.dao.OrderDAO;
import com.shop.product.dto.ProductOrder;


public class OrderServices implements OrderDAO {
	
	private OrderDAO dao;

	public OrderServices(OrderDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public int getOrderCount(int p_id) {
		// TODO Auto-generated method stub
		return dao.getOrderCount(p_id);
	}
	
	@Override
	public int insertNewOrder(ProductOrder order) {
		// TODO Auto-generated method stub
		return dao.insertNewOrder(order);
	}

	@Override
	public List<ProductOrder> getOrder(int p_id, int u_id) {
		// TODO Auto-generated method stub
		return dao.getOrder(p_id, u_id);
	}

	@Override
	public int updateOrderState(ProductOrder order) {
		// TODO Auto-generated method stub
		return dao.updateOrderState(order);
	}

}
