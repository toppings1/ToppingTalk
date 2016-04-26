package test;
public class FriendInfo
{
	private String memberId;
	private String friendId;
	private boolean favoriteFlag;
	private boolean hideFlag;
	private boolean rejectionFlag;
	public FriendInfo()
	{
		this("null", "null", false, false, false);
	}
	public FriendInfo(String memberId, String friendId, boolean favoriteFlag,
			boolean hideFlag, boolean rejectionFlag) {
		super();
		this.memberId = memberId;
		this.friendId = friendId;
		this.favoriteFlag = favoriteFlag;
		this.hideFlag = hideFlag;
		this.rejectionFlag = rejectionFlag;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public boolean getFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	public boolean getHideFlag() {
		return hideFlag;
	}
	public void setHideFlag(boolean hideFlag) {
		this.hideFlag = hideFlag;
	}
	public boolean getRejectionFlag() {
		return rejectionFlag;
	}
	public void setRejectionFlag(boolean rejectionFlag) {
		this.rejectionFlag = rejectionFlag;
	}
	public String toString() {
		return "FriendInfo [memberId=" + memberId + ", friendId=" + friendId
				+ ", favoriteFlag=" + favoriteFlag + ", hideFlag=" + hideFlag
				+ ", rejectionFlag=" + rejectionFlag + "]";
	}
	
	
}
