package app.service.impl;

import app.Utils.ObjectMapperUtils;
import app.bean.Product;
import app.bean.ProductDetail;
import app.model.ProductDetailEntity;
import app.model.ProductEntity;
import app.model.SupplierEntity;
import app.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> getProductList() {
        try {
            return ObjectMapperUtils.mapAll(productDao.getProductList(), Product.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getProductListByPage(int pageId, int total) {
        try {
            List<ProductEntity> productEntities = productDao.getProductListByPage(pageId, total);
            List<Product> products = productEntities.stream().map(new Function<ProductEntity, Product>() {
                @Override
                public Product apply(ProductEntity productEntity) {
                    return ObjectMapperUtils.productMap(productEntity);
                }
            }).collect(Collectors.toList());
            return products;
        } catch (Exception e) {
           e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getPageCount() {
        try {
            return productDao.getPageCount();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public Product getProduct(Integer id) {
        try {
            return ObjectMapperUtils.productMap(productDao.getProduct(id));
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<Product> getProductByNameAndSuppilerId(String name, int supplierId) {

        try {
            return ObjectMapperUtils.mapAll(productDao.getProductByNameAndSuppilerId(name, supplierId), Product.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean saveProducts(List<Product> products) {

        try {
            List<ProductEntity> productEntities = products.stream().map(new Function<Product, ProductEntity>() {
                @Override
                public ProductEntity apply(Product product) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setName(product.getName());
                    productEntity.setPrice(product.getPrice());
                    productEntity.setMainPhoto(product.getMainPhoto());
                    SupplierEntity supplierEntity = new SupplierEntity();
                    supplierEntity.setId(product.getSupplier().getId());
                    productEntity.setSupplierEntity(supplierEntity);
                    return productEntity;
                }
            }).collect(Collectors.toList());
            productEntities.forEach(new Consumer<ProductEntity>() {
                @Override
                public void accept(ProductEntity productEntity) {
                    saveOrUpdate(productEntity);
                }
            });
            return true;

        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

    }

    @Override
    public boolean saveProduct(Product product, ProductDetail productDetail) {
        try {
            ProductEntity productEntity = productDao.findById(product.getId());
            ProductDetailEntity productDetailEntity = productDetailsDao.getProductDetailsByProductId(product.getId());
            BeanUtils.copyProperties(productEntity, product);
            SupplierEntity supplierEntity = suppilerDao.findById(product.getSupplier().getId());
            productEntity.setSupplierEntity(supplierEntity);
            BeanUtils.copyProperties(productDetailEntity, productDetail);
            productDetailEntity.setProductEntity(productEntity);
            logger.info(productEntity);
            logger.info(productDetailEntity);
            productDao.saveOrUpdate(productEntity);
            productDetailsDao.saveOrUpdate(productDetailEntity);
            return true;
        } catch (Exception e) {
            logger.error(e);
            try {
                throw e;
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public ProductEntity findById(Serializable key) {
        try {
            return productDao.findById(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ProductEntity saveOrUpdate(ProductEntity entity) {
        try {
            return productDao.saveOrUpdate(entity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean delete(ProductEntity entity) {
        try {
            ProductEntity productEntity = productDao.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
            if (productEntity == null) {
                return false;
            } else {
                productDao.delete(entity);
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
