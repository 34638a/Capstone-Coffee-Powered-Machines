package au.com.qut.cpm.capstone.security.service;

import au.com.qut.cpm.capstone.security.entity.UserAccount;
import au.com.qut.cpm.capstone.security.model.UserAccountModel;

public interface UserAccountService {

    UserAccount getIfExists(UserAccountModel userAccountModel);

    UserAccount registerAccount(UserAccountModel userAccountModel);
}
