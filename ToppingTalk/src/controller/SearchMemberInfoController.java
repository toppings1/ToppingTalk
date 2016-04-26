package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import action.SearchAction;
import test.Injector;
import test.MemberInfo;
import validator.MemberInfoValidation;

public class SearchMemberInfoController
{
	private HttpServletRequest request;

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	private boolean isNullId(String memberId)
	{
		return MemberInfoValidation.isNullMemberId(memberId);
	}
	private boolean validationId(String memberId)
	{
		return MemberInfoValidation.validateMemberId(memberId);
	}
	
	private boolean isNullName(String name)
	{
		return MemberInfoValidation.isNullName(name);
	}
	private boolean validationName(String name)
	{
		return MemberInfoValidation.validateName(name);
	}
	
	public MemberInfo searchId(String id)
	{
		SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		if(!isNullId(id))
		{
			if(validationId(id))
			{
				return search.searchMemberInfoById(id);
			}
		}
		return null;
	}
	
	public List<MemberInfo> searchName(String name)
	{
		SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		if(!isNullName(name))
		{
			if(validationName(name))
			{
				return search.searchMemberInfoByName(name);
			}
		}
		return null;
	}
}
