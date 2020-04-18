package com.shop.dao;

import java.util.List;

import com.shop.product.dto.ProductOrder;

public interface OrderDAO {

	public int insertNewOrder(ProductOrder order);
	
	public int getOrderCount(int p_id);
	
	public List<ProductOrder> getOrder(int p_id, int u_id);
	
	public int updateOrderState(ProductOrder order);	
	
}
