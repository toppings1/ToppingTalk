package test;
public class FriendProfileInfo
{
	private FriendInfo friendInfo;
	private ProfileInfo profileInfo;
	public FriendProfileInfo()
	{
	
	}
	public FriendProfileInfo(FriendInfo friendInfo, ProfileInfo profileInfo) {
		super();
		this.friendInfo = friendInfo;
		this.profileInfo = profileInfo;
	}
	public FriendInfo getFriendInfo() {
		return friendInfo;
	}
	public void setFriendInfo(FriendInfo friendInfo) {
		this.friendInfo = friendInfo;
	}
	public ProfileInfo getProfileInfo() {
		return profileInfo;
	}
	public void setProfileInfo(ProfileInfo profileInfo) {
		this.profileInfo = profileInfo;
	}
	public String toString() {
		return "FriendProfileInfo [friendInfo=" + friendInfo + "]";
	}
}
