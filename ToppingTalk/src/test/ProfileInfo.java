package test;
public class ProfileInfo
{
	private String nickName;
	private String profilePicture;
	private String statusMsg;
	public ProfileInfo()
	{
		this(" ", " "," ");
	}
	public ProfileInfo(String nickName, String profilePicture,
			String statusMsg) {
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
	public String toString() {
		return "ProfileInfo [nickName=" + nickName + ", profilePicture="
				+ profilePicture + ", statusMsg=" + statusMsg + "]";
	}
	
	
	
}
