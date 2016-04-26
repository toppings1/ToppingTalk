package test;
public class MemberInfoForm
{
	private String memberId;
	private String pw;
	private String name;
	private String email;
	private String phoneNo;
	private String gender;
	private String birth;
	public MemberInfoForm()
	{
		this("null","null","null","null","null","null","null");

	}
	public MemberInfoForm(String memberId, String pw, String name,
			String email, String phoneNo, String gender, String birth) {
		super();
		this.memberId = memberId;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.birth = birth;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "MemberInfoForm [memberId=" + memberId + ", pw=" + pw
				+ ", name=" + name + ", email=" + email + ", phoneNo="
				+ phoneNo + ", gender=" + gender + ", birth=" + birth + "]";
	}
}
