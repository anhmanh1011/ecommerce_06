package app.config;

import app.bean.Account;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AccountItemProcessor implements ItemProcessor<Account, Account> {
	private static final Logger logger = Logger.getLogger(AccountItemProcessor.class);

	@Override
	public Account process(Account account) throws Exception {
		logger.info(account);
		return account;
	}
}
