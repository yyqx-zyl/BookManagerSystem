package dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Orders;

public interface BookOrdersMapper {
	int saveOrder(Orders orders);

	List<Orders> getOrderList(@Param("userId") String userId);

	int  update(@Param("oid") String oid,@Param("count") int count,@Param("curPrice") double curPrice);
}
