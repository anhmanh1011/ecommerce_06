package app.Utils;

import app.bean.Account;
import app.bean.Role;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    private static final Logger logger = Logger.getLogger(AccountRowMapper.class);


    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setEmail(resultSet.getNString("Email"));
        account.setPassWord(resultSet.getNString("PassWord"));
        account.setPhoneNumber(resultSet.getNString("Phone_Number"));
        return account;
    }
}
