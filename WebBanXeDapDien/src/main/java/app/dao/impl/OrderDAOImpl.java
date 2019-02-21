package app.dao.impl;

import app.dao.OrderDao;
import app.model.OrderEntity;
import org.hibernate.query.NativeQuery;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class OrderDAOImpl extends GenericDAO<Integer, OrderEntity> implements OrderDao {

    @Override
    public List<OrderEntity> findAll() {
        return getSession().createQuery("From OrderEntity O order by O.createdAt desc").getResultList();
    }

    @Override
    public int[] getRevenue() {
        NativeQuery nativeQuery = getSession().createNativeQuery("select MONTH(Orders.Created_At) as \"mounth\", CAST(Sum(OD.Amount * OD.Price) AS CHAR) as \"total\" " +
                "from Orders " +
                "inner join Orders_Details OD on Orders.Id = OD.Order_id " +
                "where Orders.Created_At " +
                "group by MONTH(Orders.Created_At)");
        List<Object[]> resultList = nativeQuery.getResultList();
        int[] revenue = new int[12];
        for (Object[] record : resultList) {
            int mounth = (int) record[0];
            revenue[mounth - 1] = Integer.valueOf(record[1].toString());
        }
        return revenue;
    }
}
