package action;

import DAO.MemberInfoDAO;
import DAO.MemberInfoDAOImpl;
import test.Injector;
import test.MemberInfo;

public class UpdateAction
{
	public boolean updateMemberInfo(MemberInfo memberInfo)
	{
		MemberInfoDAO dao = (MemberInfoDAO) Injector.getInstance().getObject(MemberInfoDAOImpl.class);
		System.out.println(memberInfo);
		int num = dao.update(memberInfo);
		
		if(num == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
//	public boolean updateNotice(Notice notice)
//	{
//	
//	}
}
