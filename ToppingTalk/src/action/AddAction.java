package action;

import test.FriendProfileInfo;
import test.Injector;
import test.MemberInfo;
import DAO.FriendProfileInfoDAO;
import DAO.FriendProfileInfoDAOImpl;
import DAO.MemberInfoDAO;
import DAO.MemberInfoDAOImpl;

public class AddAction
{
	public boolean addMemberInfo(MemberInfo memberInfo)
	{
		MemberInfoDAO dao = (MemberInfoDAO) Injector.getInstance().getObject(MemberInfoDAOImpl.class);
		System.out.println(memberInfo);
		int num = dao.insert(memberInfo);
		
		//insert시 어떤 오류로 인해 실패했는지 리턴 받지만
		// 최종결과는 boolean으로 리턴하여 오류를 알 수 없다.
		if(num == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
//	public boolean addNotice(Notice notice)
//	{
//		return false;
//	}
//	
	public int addFriendProfileInfo(FriendProfileInfo friendProfileInfo)
	{
		FriendProfileInfoDAO dao = (FriendProfileInfoDAO) Injector.getInstance().getObject(FriendProfileInfoDAOImpl.class);
		return dao.insert(friendProfileInfo);

	}
}
