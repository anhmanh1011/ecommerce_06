package app.service;

import app.bean.Account;
import app.model.AccountEntity;

public interface AccountService extends BaseService<Integer, AccountEntity> {
    boolean createAccount(String email);
    Account getAccount(String email);
}
