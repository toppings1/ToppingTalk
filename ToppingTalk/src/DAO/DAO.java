package DAO;

import com.mongodb.DB;

public interface DAO
{
	public DB getConnection();
	public void close(DB db);
	public void setDB(DB db);
}
