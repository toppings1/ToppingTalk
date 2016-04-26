package validator;

import test.ProfileInfoForm;

public class ProfileInfoValidation
{
	private ProfileInfoForm profileInfoForm;
	
	public ProfileInfoValidation()
	{
		this(null);
	}
	
	public ProfileInfoValidation(ProfileInfoForm profileInfoForm)
	{
		this.profileInfoForm = profileInfoForm;
	}
	
	public boolean validate()
	{
		return true;
	}
	
	private boolean validateNickName(String nickName)
	{
		//한글 영문 구분 아직 안함
		
		if(nickName.length() <= 20)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean validateProfilePicture(String profilePciture)
	{
		// 이건 url로 저장되는 형식일텐데 비교할 수 있을까?
		// 아니면 그냥 저장?
		return true;
	}
	
	private boolean validateStatusMsg(String statusMsg)
	{
		if(statusMsg.length() <= 60)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isNullNickName(String nickName)
	{
		if(nickName == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullProfilePicture(String profilePicture)
	{
		if(profilePicture == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isNullStatusMsg(String statusMsg)
	{
		if(statusMsg == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
