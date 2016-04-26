package controller;
import javax.servlet.http.HttpServletRequest;

import test.Injector;
import test.MemberInfo;
import test.MemberInfoForm;
import validator.Validation;
import validator.Validator;

import action.AddAction;
public class JoinMemberController
{
	private HttpServletRequest request;

	public boolean joinMember()
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNo = request.getParameter("phoneNo");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		
		MemberInfoForm memberInfoForm = new MemberInfoForm(id, pw, name, email, phoneNo, gender, birth);
		MemberInfo info = validate(memberInfoForm);
		if(add(info))
		{
			return true;
		}
		else
		{
			return false;	
		}
	}
	
	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	private MemberInfo validate(MemberInfoForm memberInfoForm)
	{
		Validator memberValidate = (Validator)Injector.getInstance().getObject(Validation.class);
		
		if(memberValidate.validate(memberInfoForm))
		{
			System.out.println("멤버 리턴성공");
			return new MemberInfo(memberInfoForm.getMemberId(), memberInfoForm.getPw(), memberInfoForm.getName(),
					memberInfoForm.getEmail(), memberInfoForm.getPhoneNo(), memberInfoForm.getGender(),
					memberInfoForm.getBirth());
		}
		else
		{
			System.out.println("멤버 리턴실패");
			return null;
		}
	}
	
	private boolean add(MemberInfo memberInfo)
	{
		AddAction addAction = (AddAction) Injector.getInstance().getObject(AddAction.class);
		System.out.println(addAction);
		if(addAction.addMemberInfo(memberInfo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
