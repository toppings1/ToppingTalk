package test;
public class LoginInfo
{
	private String memberId;
	private String pw;
	
	public LoginInfo()
	{
		this("null", "null");
	}
	public LoginInfo(String memberId, String pw) {
		super();
		this.memberId = memberId;
		this.pw = pw;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "LoginInfo [memberId=" + memberId + ", pw=" + pw + "]";
	}
	
	
}
