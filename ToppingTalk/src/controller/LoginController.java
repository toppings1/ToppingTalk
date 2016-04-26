package controller;

import javax.servlet.http.HttpServletRequest;

import test.Injector;
import test.LoginInfo;
import test.LoginInfoForm;
import validator.Validation;
import validator.Validator;

import action.SearchAction;

public class LoginController
{
	private HttpServletRequest request;

	
	public boolean login()
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		LoginInfoForm loginInfoForm = new LoginInfoForm(id, pw);
		LoginInfo loginInfo = validate(loginInfoForm);
		
		if(isLoginInfo(loginInfo))
		{
			request.setAttribute("loginInfo", loginInfo);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setReqeust(HttpServletRequest request)
	{
		this.request = request;
	}
	private LoginInfo validate(LoginInfoForm loginInfoForm)
	{
		Validator loginValidate = (Validator)Injector.getInstance().getObject(Validation.class);
		
		if(loginValidate.validate(loginInfoForm))
		{
			System.out.println("생성인데?");
			System.out.println(loginInfoForm);
			return new LoginInfo(loginInfoForm.getMemberId(), loginInfoForm.getPw());
		}
		else
		{
			return null;
		}
	}
	
	private boolean isLoginInfo(LoginInfo loginInfo)
	{
		SearchAction SearchAction = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
		if(SearchAction.searchMemberInfoByLoginInfo(loginInfo))
		{
			System.out.println("로그인 성공");
			return true;
		}
		else
		{
			System.out.println("로그인 실패");
			return false;
		}
	}
}
