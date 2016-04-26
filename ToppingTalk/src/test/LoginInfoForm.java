package test;
public class LoginInfoForm
{
	private String memberId;
	private String pw;
	public LoginInfoForm()
	{
		this("null", "null");
	}
	public LoginInfoForm(String memberId, String pw) {
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
		return "LoginInfoForm [memberId=" + memberId + ", pw=" + pw + "]";
	}
	
	

}
