package validator;

import test.FriendInfo;
import test.Injector;
import test.LoginInfoForm;
import test.MemberInfoForm;

public class Validation implements Validator
{
	public boolean validate(Object obj)
	{
		if(obj.getClass() == MemberInfoForm.class)
		{
			MemberInfoValidation validate = (MemberInfoValidation)Injector.getInstance().getObject(MemberInfoValidation.class);
			if(validate.validate())
			{
				System.out.println("가입검사성공");
				return true;
			}
			else
			{
				System.out.println("가입검사실패");
				return false;
			}
		}
		else if(obj.getClass() == LoginInfoForm.class)
		{
			LoginInfoValidation validate = (LoginInfoValidation)Injector.getInstance().getObject(LoginInfoValidation.class);
			if(validate.validate())
			{
				System.out.println("로그인검사성공");
				return true;
			}
			else
			{
				System.out.println("로그인검사실패");
				return false;
			}
		}
		else if(obj.getClass() == FriendInfo.class)
		{
			FriendInfoValidation validate = (FriendInfoValidation)Injector.getInstance().getObject(FriendInfoValidation.class);
			if(validate.validate())
			{
				System.out.println("아이디검사성공");
				return true;
			}
			else
			{
				System.out.println("아이디검사실패");
				return false;
			}
		}
		
		return false;
	}
}
