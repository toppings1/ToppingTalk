package controller;


import javax.servlet.http.HttpServletRequest;

import test.Injector;

import action.SearchAction;

public class JoinFriendController {

	private HttpServletRequest request;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public boolean searchFriend(String id, String userId)
	{
		SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		return search.searchFriend(id, userId);
	}
	
}
