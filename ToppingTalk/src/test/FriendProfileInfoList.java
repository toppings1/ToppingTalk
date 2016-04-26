package test;
import java.util.List;

public class FriendProfileInfoList
{
	private List<FriendProfileInfo> friendProfileInfoList;
	public  FriendProfileInfoList()
	{
	
	}
	public FriendProfileInfoList(List<FriendProfileInfo> friendProfileInfoList) {
		super();
		this.friendProfileInfoList = friendProfileInfoList;
	}
	public List<FriendProfileInfo> getFriendProfileInfoList() {
		return friendProfileInfoList;
	}
	public void setFriendProfileInfoList(
			List<FriendProfileInfo> friendProfileInfoList) {
		this.friendProfileInfoList = friendProfileInfoList;
	}
	public String toString() {
		return "FriendProfileInfoList [getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
