package app.dao.impl;

import app.dao.AccountDao;
import app.model.AccountEntity;

import java.util.List;

public class AccountDAOImpl extends GenericDAO<Integer, AccountEntity> implements AccountDao {
    @Override
    public AccountEntity findByEmail(String email) {
        return (AccountEntity) getSession().createQuery("from AccountEntity A Where A.email = :email").setParameter("email", email).uniqueResult();
    }

    @Override
    public List<String> findAllEmailUser() {
        return getSession().createQuery("select A.email From AccountEntity A where A.roleEntity.id = 2").getResultList();
    }

}
