package DAO;

import java.util.ArrayList;
import java.util.List;

import test.MemberInfo;
import test.ProfileInfo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MemberInfoDAOImpl extends BaseDAO implements MemberInfoDAO
{
	public List<MemberInfo> select()
	{
		MemberInfo info = null;
		ProfileInfo profile = null;
		DB db = null;
		List<MemberInfo> list = new ArrayList<MemberInfo>();
		
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			DBCursor cursor =  userTable.find();
			 while (cursor.hasNext()) {
	      		 BasicDBObject temp = (BasicDBObject) cursor.next();
	      		 BasicDBObject tempProfile = (BasicDBObject) temp.get("profile");
	      		profile = new ProfileInfo(tempProfile.getString("nickName"), tempProfile.getString("profilePicture"),
	      									tempProfile.getString("statusMsg"));
	      		
	      		 info = new MemberInfo(temp.getString("_id"), temp.getString("pw"),
	      				 	temp.getString("name"), temp.getString("email"),
	      				 	temp.getString("phoneNo"), temp.getString("gender"),
	      				 	temp.getString("birth"), profile);
	      		 list.add(info);
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
		return list;
	}
	
	public MemberInfo select(String id)
	{
		MemberInfo info = null;
		ProfileInfo profile = null;
		DB db = null;		
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			BasicDBObject search = new BasicDBObject();
			search.put("_id", id);
			DBCursor cursor =  userTable.find(search);
			 while (cursor.hasNext()) {
	      		 BasicDBObject temp = (BasicDBObject) cursor.next();
	      		 BasicDBObject tempProfile = (BasicDBObject) temp.get("profile");
	      		profile = new ProfileInfo(tempProfile.getString("nickName"), tempProfile.getString("profilePicture"),
	      									tempProfile.getString("statusMsg"));
	      		
	      		 info = new MemberInfo(temp.getString("_id"), temp.getString("pw"),
	      				 	temp.getString("name"), temp.getString("email"),
	      				 	temp.getString("phoneNo"), temp.getString("gender"),
	      				 	temp.getString("birth"), profile);
	      		 
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
		return info;
	}
	
	public List<MemberInfo> selectName(String name)
	{
		MemberInfo info = null;
		ProfileInfo profile = null;
		DB db = null;
		List<MemberInfo> list = new ArrayList<MemberInfo>();
		
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			BasicDBObject search = new BasicDBObject();
			search.put("name", name);
			DBCursor cursor =  userTable.find(search);
			 while (cursor.hasNext()) {
	      		 BasicDBObject temp = (BasicDBObject) cursor.next();
	      		 BasicDBObject tempProfile = (BasicDBObject) temp.get("profile");
	      		profile = new ProfileInfo(tempProfile.getString("nickName"), tempProfile.getString("profilePicture"),
	      									tempProfile.getString("statusMsg"));
	      		
	      		 info = new MemberInfo(temp.getString("_id"), temp.getString("pw"),
	      				 	temp.getString("name"), temp.getString("email"),
	      				 	temp.getString("phoneNo"), temp.getString("gender"),
	      				 	temp.getString("birth"), profile);
	      		 list.add(info);
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
		return list;
	}
	public int update(MemberInfo memberInfo)
	{
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			
			BasicDBObject before = new BasicDBObject("_id", memberInfo.getMemberId());	
		
			BasicDBObject update = new BasicDBObject();
			update.put("pw", memberInfo.getPw());
			update.put("name", memberInfo.getName());
			update.put("email", memberInfo.getEmail());
			update.put("phoneNo", memberInfo.getPhoneNo());
			update.put("gender", memberInfo.getGender());
			update.put("birth", memberInfo.getBirth());
			
			BasicDBObject obj = new BasicDBObject("$set", update);
			
			WriteResult result =  userTable.update(before, obj);
			if((boolean) result.getField("updatedExisting")) // 성공 : true, 실패 : false
			{
				num = 1;
			}
			else
			{
				num = 0;
			}
		}
		catch(MongoException e)
		{
			System.out.println("에러코드 : " + e.getCode());
			System.out.println("에러메시지 : " + e.getMessage());
			num = e.getCode();
		}
		finally
		{
			if(db != null)
			{
				close(db);
			}
		}
		return num;
	}
	
	public int updateProfile(MemberInfo memberInfo)
	{
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			
			BasicDBObject before = new BasicDBObject("_id", memberInfo.getMemberId());	
		
			BasicDBObject update = new BasicDBObject();
			update.put("nickName", memberInfo.getPw());
			update.put("profilePicture", memberInfo.getName());
			update.put("statusMsg", memberInfo.getEmail());
			
			BasicDBObject obj = new BasicDBObject("$set", update);
			
			WriteResult result =  userTable.update(before, obj);
			if((boolean) result.getField("updatedExisting")) // 성공 : true, 실패 : false
			{
				num = 1;
			}
			else
			{
				num = 0;
			}
		}
		catch(MongoException e)
		{
			System.out.println("에러코드 : " + e.getCode());
			System.out.println("에러메시지 : " + e.getMessage());
			num = e.getCode();
		}
		finally
		{
			if(db != null)
			{
				close(db);
			}
		}
		return num;
	}
	
	public int delete(String id)
	{
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			BasicDBObject input = new BasicDBObject();
			input.put("_id", id);
			WriteResult result =  userTable.remove(input);
			num = result.getN(); // 성공 : 1, 실패 : 0
			
		}
		catch(MongoException e)
		{
			System.out.println("에러코드 : " + e.getCode());
			num = e.getCode();
		}
		finally
		{
			if(db != null)
			{
				close(db);
			}
		}
		return num;
	}
	
	public int insert(MemberInfo memberInfo)
	{
		DB db = null;
		int num = 1;
		
		try
		{
			db = getConnection();
			
			DBCollection userTable = db.getCollection("userTable");
			BasicDBObject input = new BasicDBObject();
			input.put("_id", memberInfo.getMemberId());
			input.put("pw", memberInfo.getPw());
			input.put("name", memberInfo.getName());
			input.put("email", memberInfo.getEmail());
			input.put("phoneNo", memberInfo.getPhoneNo());
			input.put("gender", memberInfo.getGender());
			input.put("birth", memberInfo.getBirth());
			BasicDBObject profile = new BasicDBObject();
			profile.put("nickName", memberInfo.getName());
			if(memberInfo.getGender().equals("남"))
			{
				profile.put("profilePicture", "topang.PNG");
			}
			else
			{
				profile.put("profilePicture", "tosoon.PNG");
			}
			profile.put("statusMsg", "");
			input.put("profile", profile);
			WriteResult result =  userTable.insert(input);
			System.out.println("@@"+result.getN());
			System.out.println(result);
			num = result.getN(); // 성공하는 경우 리턴값 : 0, 길이에 따른 체크는 하지 못함.
			
		}
		catch(MongoException e)
		{
			System.out.println("에러코드 : " + e.getCode());
			num = e.getCode(); // 에러코드 11000 : 아이디 중복
		}
		finally
		{
			if(db != null)
			{
				close(db);
			}
		}
		return num;
	}
}
