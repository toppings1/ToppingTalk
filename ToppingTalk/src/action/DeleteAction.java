package action;

import test.Injector;
import DAO.FriendProfileInfoDAO;
import DAO.FriendProfileInfoDAOImpl;
import DAO.MemberInfoDAO;
import DAO.MemberInfoDAOImpl;

public class DeleteAction
{
	public boolean deleteMemberInfo(String memberId)
	{
		MemberInfoDAO dao = (MemberInfoDAO) Injector.getInstance().getObject(MemberInfoDAOImpl.class);
		int num = dao.delete(memberId);
		
		//insert시 어떤 오류로 인해 실패했는지 리턴 받지만
		// 최종결과는 boolean으로 리턴하여 오류를 알 수 없다.
		if(num == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean deleteFriendProfile(String friendId)
	{
		FriendProfileInfoDAO dao = (FriendProfileInfoDAO) Injector.getInstance().getObject(FriendProfileInfoDAOImpl.class);
		int num = dao.delete(friendId);
		
		//insert시 어떤 오류로 인해 실패했는지 리턴 받지만
		// 최종결과는 boolean으로 리턴하여 오류를 알 수 없다.
		if(num == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
//	public boolean deleteNotice(String noticeId)
//	{
//	
//	}
}
