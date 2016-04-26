package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import test.FriendProfileInfo;
import test.Injector;
import test.LoginInfo;
import test.MemberInfo;

import action.SearchAction;

public class LoginLoadingInfoController
{
	private HttpServletRequest request;
	
	public void setRequest(HttpServletRequest httpServletRequest)
	{
		this.request = httpServletRequest;
	}
	public void loadLoginInfo()
	{
		LoginInfo info = (LoginInfo) request.getAttribute("loginInfo");

		getFriendProfileList(info.getMemberId());
		getMemberInfo(info.getMemberId());
	}
//	private void getProfileInfo()
//	{
//	
//	}
	
	private void getFriendProfileList(String id)
	{
		LoginInfo info = (LoginInfo) request.getAttribute("loginInfo");
		SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		List<FriendProfileInfo> list = search.searchFriends(info.getMemberId());
		request.setAttribute("friends", list);
	}
	
	private void getMemberInfo(String id)
	{
		SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		MemberInfo info = search.searchMemberInfoById(id);
		request.setAttribute("memberInfo", info);
	}
	
//	private void getSystemSetting()
//	{
//	
//	}
//	
//	private void getChatRoomJoinSetting()
//	{
//	
//	}
//	
//	private void getChatRoomList()
//	{
//	
//	}
}
