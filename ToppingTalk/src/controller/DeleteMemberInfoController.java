package controller;

import javax.servlet.http.HttpServletRequest;

import action.DeleteAction;

import test.Injector;
import validator.MemberInfoValidation;

public class DeleteMemberInfoController
{
	private HttpServletRequest request;

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	public boolean deleteMemberInfo()
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(validate(id,pw))
		{
			return delete(id);
		}
		else
		{
			return false;
		}
	}
	
	private boolean validate(String memberId, String pw)
	{
		if(!MemberInfoValidation.isNullMemberId(memberId))
		{
			if(!MemberInfoValidation.isNullPw(pw))
			{
				if(MemberInfoValidation.validateMemberId(memberId))
				{
					if(MemberInfoValidation.validatePw(pw))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean delete(String memberId)
	{
		DeleteAction deleteAction = (DeleteAction) Injector.getInstance().getObject(DeleteAction.class);
		return deleteAction.deleteMemberInfo(memberId);
	}
}
