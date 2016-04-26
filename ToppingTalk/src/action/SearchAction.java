package action;

import java.util.List;

import test.FriendProfileInfo;
import test.Injector;
import test.LoginInfo;
import test.MemberInfo;
import DAO.FriendProfileInfoDAO;
import DAO.FriendProfileInfoDAOImpl;
import DAO.LoginDAO;
import DAO.LoginDAOImpl;
import DAO.MemberInfoDAO;
import DAO.MemberInfoDAOImpl;

public class SearchAction
{
	public MemberInfo searchMemberInfoById(String id)
	{
		MemberInfoDAO dao = (MemberInfoDAO) Injector.getInstance().getObject(MemberInfoDAOImpl.class);
		
		return dao.select(id);
	}
	
	public List<MemberInfo> searchMemberInfoByName(String name)
	{	
		MemberInfoDAO dao = (MemberInfoDAO) Injector.getInstance().getObject(MemberInfoDAOImpl.class);
		return dao.selectName(name);
	}
//	
	public boolean searchMemberInfoByLoginInfo(LoginInfo loginInfo)
	{
		System.out.println("@@"+loginInfo); 
		LoginDAO dao = (LoginDAO) Injector.getInstance().getObject(LoginDAOImpl.class);
		
		boolean ch = dao.login(loginInfo);
		
		if(ch)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<FriendProfileInfo> searchFriends(String id)
	{
		FriendProfileInfoDAO dao = (FriendProfileInfoDAO) Injector.getInstance().getObject(FriendProfileInfoDAOImpl.class);
		
		return dao.select(id);
	}
	
	public boolean searchFriend(String id, String userId)
	{
		FriendProfileInfoDAO dao = (FriendProfileInfoDAO) Injector.getInstance().getObject(FriendProfileInfoDAOImpl.class);
		
		return dao.select(id, userId);
	}
	
//	
//	public Notice searchNoticeById(String noticeId)
//	{
//	
//	}
//	
//	public List<Notice> searchNoticeByName(String name)
//	{
//	
//	}
//	
//	public ProfileInfo searchProfileInfo(String id)
//	{
//	
//	}
}
