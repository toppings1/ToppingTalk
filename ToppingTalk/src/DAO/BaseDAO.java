package DAO;

import com.mongodb.DB;

public abstract class BaseDAO implements DAO
{
	private DB db;
	public DB getConnection()
	{
		db.requestStart();
		db.requestEnsureConnection();
		return db;
	}
	
	public void close(DB db)
	{
		db.requestDone();
	}
	
	public void setDB(DB db)
	{
		this.db = db;
	}
	
}
