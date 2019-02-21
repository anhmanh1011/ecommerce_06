package app.dao;

import app.model.AccountEntity;

import java.util.List;

public interface AccountDao extends BaseDAO<Integer, AccountEntity> {

    AccountEntity findByEmail(String userName);
    List<String> findAllEmailUser();
}
