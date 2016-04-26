package DAO;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

import test.FriendInfo;
import test.FriendProfileInfo;
import test.ProfileInfo;

public class FriendProfileInfoDAOImpl extends BaseDAO implements FriendProfileInfoDAO{

	public List<FriendProfileInfo> select() {
		
		FriendProfileInfo info = null;
		FriendInfo friend = null;
		ProfileInfo profile = null;
		DB db = null;
		List<FriendProfileInfo> list = new ArrayList<FriendProfileInfo>();
		
		try
		{
			db = getConnection();
			DBCollection friendTable = db.getCollection("friendTable");
			DBCursor cursor = friendTable.find();
			
			while(cursor.hasNext())
			{
				BasicDBObject temp = (BasicDBObject) cursor.next();
				
				friend = new FriendInfo(temp.getString("member_id"), temp.getString("friend_id"),
						temp.getBoolean("favoriteFlag"), temp.getBoolean("hideFlag"), temp.getBoolean("rejectionFlag"));
				
				profile = new ProfileInfo(temp.getString("nickName"), temp.getString("profilePicture"),
												temp.getString("statusMsg"));
				info = new FriendProfileInfo(friend, profile);
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

	public List<FriendProfileInfo> select(String id) {

		FriendProfileInfo info = null;
		FriendInfo friend = null;
		ProfileInfo profile = null;
		DB db = null;
		List<FriendProfileInfo> list = new ArrayList<FriendProfileInfo>();
		
		try
		{
			db = getConnection();
			DBCollection friendTable = db.getCollection("friendTable");
			BasicDBObject search = new BasicDBObject();
			search.put("member_id", id);
			DBCursor cursor =  friendTable.find(search);
			 while (cursor.hasNext()) {
	      		 BasicDBObject temp = (BasicDBObject) cursor.next();
	      		 
	      		friend = new FriendInfo(temp.getString("member_id"), temp.getString("friend_id"),
						temp.getBoolean("favoriteFlag"), temp.getBoolean("hideFlag"), temp.getBoolean("rejectionFlag"));
				
				profile = new ProfileInfo(temp.getString("nickName"), temp.getString("profilePicture"),
												temp.getString("statusMsg"));
				info = new FriendProfileInfo(friend, profile);
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
	
	public boolean select(String id, String userId) {

		boolean flag = false;
		DB db = null;		
		try
		{
			db = getConnection();
			DBCollection friendTable = db.getCollection("friendTable");
			BasicDBObject search = new BasicDBObject();
			search.put("member_id", id);
			search.put("friend_id", userId);
			BasicDBObject obj =  (BasicDBObject) friendTable.findOne(search);
			if(obj != null)
			{
				flag = true;
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

	public int update(FriendProfileInfo info) {
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection friendTable = db.getCollection("friendTable");
			
			BasicDBObject before = new BasicDBObject("member_id", info.getFriendInfo().getMemberId());	
		
			BasicDBObject update = new BasicDBObject();
			update.put("friend_id", info.getFriendInfo().getFriendId());
			update.put("favoriteFlag", info.getFriendInfo().getFavoriteFlag());
			update.put("hideFlag", info.getFriendInfo().getHideFlag());
			update.put("rejectionFlag", info.getFriendInfo().getRejectionFlag());
			update.put("nickName", info.getProfileInfo().getNickName());
			update.put("profilePicture", info.getProfileInfo().getProfilePicture());
			update.put("statusMsg", info.getProfileInfo().getStatusMsg());
			
			BasicDBObject obj = new BasicDBObject("$set", update);
			
			WriteResult result =  friendTable.update(before, obj);
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

	public int delete(String memberId, String friendId) {
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection friendTable = db.getCollection("friendTable");
			BasicDBObject input = new BasicDBObject();
			input.put("member_id", memberId);
			input.put("friend_id", friendId);
			WriteResult result =  friendTable.remove(input);
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
	public int delete(String friendId) {
		DB db = null;
		int num = 0;
		
		try
		{
			db = getConnection();
			
			DBCollection friendTable = db.getCollection("friendTable");
			BasicDBObject input = new BasicDBObject();
			System.out.println(friendId);
			input.put("friend_id", friendId);
			BasicDBList list = new BasicDBList();
			list.add(input);
			input.remove("friend_id");
			input.put("member_id", friendId);
			list.add(input);
			
			BasicDBObject or = new BasicDBObject();
			or.put("$or", list);
			System.out.println(or.toString());
			WriteResult result =  friendTable.remove(or);
			System.out.println(result);
			num = result.getN(); // 성공 : 1, 실패 : 0
			
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

	public int insert(FriendProfileInfo info) {
		DB db = null;
		int num = 1;
		
		try
		{
			db = getConnection();
			
			DBCollection friendTable = db.getCollection("friendTable");
			BasicDBObject input = new BasicDBObject();
			input.put("member_id", info.getFriendInfo().getMemberId());
			input.put("friend_id", info.getFriendInfo().getFriendId());
			input.put("favoriteFlag", info.getFriendInfo().getFavoriteFlag());
			input.put("hideFlag", info.getFriendInfo().getHideFlag());
			input.put("rejectionFlag", info.getFriendInfo().getRejectionFlag());
			input.put("nickName", info.getProfileInfo().getNickName());
			input.put("profilePicture", info.getProfileInfo().getProfilePicture());
			input.put("statusMsg", info.getProfileInfo().getStatusMsg());
			WriteResult result =  friendTable.insert(input);
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
