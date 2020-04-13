package service.orders;

import java.util.List;

import pojo.Orders;

public interface BookOrdersService {

	boolean addOrders(Orders orders);

	List<Orders> findOrders(String userId);

	boolean update(String oid, int count, double curPrice);

}
