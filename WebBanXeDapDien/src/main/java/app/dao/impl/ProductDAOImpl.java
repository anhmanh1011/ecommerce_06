package app.dao.impl;

import app.dao.ProductDao;
import app.model.ProductEntity;
import app.model.SupplierEntity;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductDAOImpl extends GenericDAO<Integer, ProductEntity> implements ProductDao {
    @Override
    public List<ProductEntity> getProductList() {
        return getSession().createQuery("from ProductEntity P Order By P.id desc").getResultList();
    }

    @Override
    public List<ProductEntity> getProductListByPage(int pageid, int total) {
        List<ProductEntity> resultList = getSession().createQuery("from ProductEntity P Order By P.id desc")
                .setFirstResult((pageid - 1) * total) // offset
                .setMaxResults(total) // limit
                .getResultList();
        return resultList.stream().map(new Function<ProductEntity, ProductEntity>() {
            @Override
            public ProductEntity apply(ProductEntity productEntity) {
                SupplierEntity supplierEntity = productEntity.getSupplierEntity();
                productEntity.setSupplierEntity(supplierEntity);
                productEntity.getProductColorEntities().removeIf(Objects::isNull);
                productEntity.getOrderDetailEntities().removeIf(Objects::isNull);
                productEntity.getImageEntities().removeIf(Objects::isNull);
                return productEntity;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductEntity> getProductByNameAndSuppilerId(String name, int supplierId) {
        if (supplierId == 0) {
            return getSession().createQuery("From ProductEntity P WHERE P.name LIKE :name ").setParameter("name", "%" + name + "%").getResultList();
        } else {
            return getSession().createQuery("From ProductEntity P WHERE P.name LIKE :name AND P.supplierEntity.id = :supplierId").setParameter("name", "%" + name + "%").setParameter("supplierId", supplierId).getResultList();
        }
    }

    @Override
    public int getPageCount() {
        Query query = getSession().createQuery(" Select count(*)  from ProductEntity");
        return Integer.valueOf(String.valueOf(query.getSingleResult()));
    }

    @Override
    public ProductEntity getProduct(Integer id) {
        ProductEntity productsEntity = findById(id);
        productsEntity.getImageEntities().removeIf(Objects::isNull);
        productsEntity.getProductColorEntities().removeIf(Objects::isNull);
        productsEntity.getOrderDetailEntities().removeIf(Objects::isNull);
        return productsEntity;
    }
}
