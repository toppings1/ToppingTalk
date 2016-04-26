package DAO;

import test.LoginInfo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public class LoginDAOImpl extends BaseDAO implements LoginDAO
{
//	private LoginInfo loginInfo;
//	public LoginDAOImpl()
//	{
//		this(new LoginInfo("null", "null"));
//	}
//	
//	public LoginDAOImpl(LoginInfo loginInfo)
//	{
//		this.loginInfo = loginInfo;
//	}
//	
//	public void setLoginInfo(LoginInfo loginInfo)
//	{
//		this.loginInfo = loginInfo;
//	}
	
	public boolean login(LoginInfo loginInfo)
	{
		System.out.println(loginInfo);
		DB db = null;
		boolean flag = false;
		try
		{
			db = getConnection();
			DBCollection userTable = db.getCollection("userTable");
			BasicDBObject search = new BasicDBObject("_id", loginInfo.getMemberId()).append("pw", loginInfo.getPw());
			
			BasicDBObject result = (BasicDBObject) userTable.findOne(search); // 아이디와 비밀번호로 검색
			System.out.println(result);
			if(result != null) // 입력한 아이디와 비밀번호가 db와 일치한 경우
			{
				flag = true;
			}
			else // 일치하지 않는 경우
			{
				search.removeField("pw"); // 비밀번호 속성을 해당 객체에서 지운다.
				result = (BasicDBObject) userTable.findOne(search); // 아이디를 통해 재검색하여 아이디가 있는지 확인
				if(result != null)
				{
					// 입력한 아이디가 db와 일치한 경우, 비밀번호가 틀린 것
				}
				System.out.println(result);
			}
		}
		catch(MongoException e)
		{
			System.out.println("에러코드 : " + e.getCode());
		}
		finally
		{
			if(db != null)
			{
				close(db);
			}
		}
		
		return flag;
	}
}
