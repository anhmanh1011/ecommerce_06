package app.service.impl;

import app.Utils.ObjectMapperUtils;
import app.bean.Order;
import app.model.AccountEntity;
import app.model.OrderDetailEntity;
import app.model.OrderEntity;
import app.model.ProductEntity;
import app.service.OrderService;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import java.io.Serializable;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public OrderEntity findById(Serializable key) {
        try {
            return orderDao.findById(key);
        } catch (Exception e) {
          e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderEntity saveOrUpdate(OrderEntity entity) {
        try {
            return orderDao.saveOrUpdate(entity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public boolean delete(OrderEntity entity) {
        try {
            OrderEntity orderDaoByIdUsingLock = orderDao.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
            if (orderDaoByIdUsingLock == null) {
                return false;
            } else {
                orderDao.delete(entity);
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public OrderEntity save(Order order) {
        try {
            order.setCreatedAt(new Timestamp(new Date().getTime()));
            order.setStatus("chưa xác nhận");
            AccountEntity accountEntity = null;
            if (order.getAccountByUserId() != null) {
                accountEntity = accountDao.findByEmail(order.getAccountByUserId().getEmail());
            }
            OrderEntity orderEntity = ObjectMapperUtils.orderEntityMap(order);
            orderEntity.setAccountByUserId(accountEntity);
            OrderEntity orderEntity2 = orderDao.saveOrUpdate(orderEntity);
            List<OrderDetailEntity> orderDetailEntities = ObjectMapperUtils.orderDetailsEntityMap(order.getOrderDetails());
            for (int i = 0; i < orderDetailEntities.size(); i++) {
                orderDetailEntities.get(i).getOrderDetailEntityPK().setOrderEntity(orderEntity2);
                logger.info(orderDetailEntities.get(i));
                orderDetailDao.saveOrUpdate(orderDetailEntities.get(i));
            }
            return orderEntity;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

    }

    @Override
    public List<Order> findAll() {
        try {
            List<Order> orders = ObjectMapperUtils.mapAll(orderDao.findAll(), Order.class);
            return orders;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public Order findOrderById(Integer id) {
        try {
            OrderEntity orderEntity = findById(id);
            List<OrderDetailEntity> orderDetailEntities = orderDetailDao.findByOrderId(id);
            List<OrderDetailEntity> orderDetailEntityList = orderDetailEntities.stream().map(orderDetailEntity -> {
                ProductEntity productEntity = productDao.getProduct(orderDetailEntity.getOrderDetailEntityPK().getProductEntity().getId());
                orderDetailEntity.getOrderDetailEntityPK().setProductEntity(productEntity);
                return orderDetailEntity;
            }).collect(Collectors.toList());
            orderEntity.setOrderDetailEntities(orderDetailEntityList);
            Order order = ObjectMapperUtils.orderMap(orderEntity);
            return order;
        } catch (Exception e) {
          e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(Order order) {
        try {
            OrderEntity orderEntity = findById(order.getId());
            orderEntity.setStatus(order.getStatus());
            orderEntity.setUpdatedAt(new Timestamp(new Date().getTime()));
            orderDao.saveOrUpdate(orderEntity);
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

    }

    @Override
    public int[] getRevenue() {
        try {
            return orderDao.getRevenue();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
