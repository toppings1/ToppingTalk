package validator;

import test.MemberInfoForm;

public class MemberInfoValidation 
{
	private MemberInfoForm memberInfoForm;
	public MemberInfoValidation()
	{
		this(null);
	}
	
	public MemberInfoValidation(MemberInfoForm memberInfoForm)
	{
		this.memberInfoForm = memberInfoForm;
	}
	
	public MemberInfoForm getMemberInfoForm()
	{
		return memberInfoForm;
	}
	
	public void setMemberInfoForm(MemberInfoForm memberInfoForm)
	{
		this.memberInfoForm = memberInfoForm;
	}
	
	public boolean validate()
	{
		// 나중에
		
		return true;
	}
	
	public static boolean validateMemberId(String memberId)
	{
		//한글, 공백관련 아이디 검사 안함
		
		if(memberId.length() >= 4 && memberId.length() <= 20)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validatePw(String pw)
	{
		//연속된 숫자 또는 알파벳 유효서 검사 안함
		
		if(pw.length() >= 6 && pw.length() <= 20)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean validateName(String name)
	{
		//영문 및 한글만 되는 유효성 안함
		if(name.length() <= 30)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validateEmail(String email)
	{
		//한글, 공백관련 아이디 검사 안함
		
		if(email.length() >= 4 && email.length() <= 20)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validatePhoneNo(String phoneNo)
	{
		//숫자만 허용 검사 안함
		if(phoneNo.length() >= 10 && phoneNo.length() <= 11)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validateGender(String gender)
	{
		if(gender.equals("남") || gender.equals("여"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validateBirth(String birth)
	{
		// 미실시
		return true;
	}
	
	public static boolean isNullMemberId(String memberId)
	{
		if(memberId == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isNullPw(String pw)
	{
		if(pw == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isNullName(String name)
	{
		if(name == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullEmail(String email)
	{
		if(email == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullPhoneNo(String phoneNo)
	{
		if(phoneNo == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullGender(String gender)
	{
		if(gender == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullBirth(String birth)
	{
		if(birth == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
