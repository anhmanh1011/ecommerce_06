package app.dao;

import app.model.OrderDetailEntity;
import app.model.OrderDetailEntityPK;

import java.util.*;

public interface OrderDetailDao extends BaseDAO<Integer, OrderDetailEntity> {
    List<OrderDetailEntity> findByOrderId(Integer id);

    OrderDetailEntity loadById(OrderDetailEntityPK orderDetailEntityPK);
}
