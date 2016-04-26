package controller;

import javax.servlet.http.HttpServletRequest;

import test.FriendInfo;
import test.FriendProfileInfo;
import test.Injector;
import test.MemberInfo;
import test.ProfileInfo;
import validator.Validation;
import validator.Validator;

import action.AddAction;

public class AddFriendController
{
	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	public int addFriend()
	{
//		String userId = request.getParameter("userId");
//		String addUserId = request.getParameter("addUserId");
		String memberId = (String) request.getAttribute("memberId");
		String friendId = (String) request.getAttribute("friendId");
		FriendInfo friend = validate(memberId, friendId);
	
		return add(friend);
		
	}
	
	private FriendInfo validate(String memberId, String friendId)
	{
		Validator addValidator = (Validator) Injector.getInstance().getObject(Validation.class);
		FriendInfo info = new FriendInfo(memberId, friendId, false, false, false);
		if(addValidator.validate(info))
		{
			return info;
		}
		else
		{
			return null;
		}
	}
	
	private int add(FriendInfo friendInfo)
	{
		AddAction add = (AddAction) Injector.getInstance().getObject(AddAction.class);
		
		System.out.println();
		
		MemberInfo friend = (MemberInfo) request.getAttribute("memberInfo");
		/**/System.out.println("&&&&&&&"+friend);
		ProfileInfo profile = friend.getProfile();
		/**/System.out.println(profile);
		FriendProfileInfo info = new FriendProfileInfo(friendInfo, profile);
		return add.addFriendProfileInfo(info);

	}
}
