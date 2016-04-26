package DAO;

import test.LoginInfo;

public interface LoginDAO extends DAO
{
	public boolean login(LoginInfo LoginInfo);
}
