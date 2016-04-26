package validator;

import test.LoginInfoForm;

public class LoginInfoValidation
{
	private LoginInfoForm loginInfoForm;
	public LoginInfoValidation()
	{
		this(null);
	}
	
	public LoginInfoValidation(LoginInfoForm loginInfoForm)
	{
		this.loginInfoForm = loginInfoForm;
	}
	
	public LoginInfoForm getLoginInfoForm()
	{
		return loginInfoForm;
	}
	
	public void setLoginInfoForm(LoginInfoForm loginInfoForm)
	{
		this.loginInfoForm = loginInfoForm;
	}
	
	public boolean validate()
	{
		return true;
	}
	
	private boolean validateMemberId(String memberId)
	{
		return MemberInfoValidation.validateMemberId(memberId);
	}
	
	private boolean validatePw(String pw)
	{
		return MemberInfoValidation.validatePw(pw);
	}
	
	private boolean isNullMemberId(String memberId)
	{
		return MemberInfoValidation.isNullMemberId(memberId);
	}
	
	private boolean isNullPw(String pw)
	{
		return MemberInfoValidation.isNullPw(pw);
	}
}
