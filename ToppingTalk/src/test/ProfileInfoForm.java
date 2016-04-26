package test;
public class ProfileInfoForm
{
	private String nickName;
	private String profilePicture;
	private String statusMsg;
	public ProfileInfoForm()
	{
		this("null","null","null");
	}
	public ProfileInfoForm(String nickName,
			String profilePicture, String statusMsg) {
		super();
		this.nickName = nickName;
		this.profilePicture = profilePicture;
		this.statusMsg = statusMsg;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	@Override
	public String toString() {
		return "ProfileInfoForm [nickName=" + nickName + ", profilePicture="
				+ profilePicture + ", statusMsg=" + statusMsg + "]";
	}

}
