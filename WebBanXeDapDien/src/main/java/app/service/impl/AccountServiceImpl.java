package app.service.impl;

import app.Utils.ObjectMapperUtils;
import app.bean.Account;
import app.bean.Role;
import app.model.AccountEntity;
import app.model.RoleEntity;
import app.service.AccountService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;

import java.io.Serializable;
import java.util.*;

public class AccountServiceImpl extends BaseServiceImpl implements AccountService {
    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Override
    public AccountEntity findById(Serializable key) {
        try {
            return accountDao.findById(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public AccountEntity saveOrUpdate(AccountEntity entity) {
        try {

            return accountDao.saveOrUpdate(entity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public boolean delete(AccountEntity entity) {
        try {
            AccountEntity byIdUsingLock = accountDao.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
            if (byIdUsingLock != null) {
                accountDao.delete(entity);
                return true;
            }
            return false;
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }


    @Override
    public boolean createAccount(String email) {
        try {
            AccountEntity byEmail = accountDao.findByEmail(email);
            if (byEmail != null)
                return false;
            SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
            crunchifyMsg.setTo(email);
            crunchifyMsg.setSubject("Đăng ký account");
            String random = RandomStringUtils.random(10, true, true);
            crunchifyMsg.setText(random);
            mailSender.send(crunchifyMsg);
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setEmail(email);
            accountEntity.setPassWord(passwordEncoder.encode((random)));
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId((byte) 2);
            accountEntity.setRoleEntity(roleEntity);
            accountDao.saveOrUpdate(accountEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Account getAccount(String email) {
        Account account = ObjectMapperUtils.map(accountDao.findByEmail(email), Account.class);
        return account;
    }


}
