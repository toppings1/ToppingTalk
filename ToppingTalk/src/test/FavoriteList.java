package test;
import java.util.List;

public class FavoriteList
{
	private List<FriendProfileInfo> favoriteList;
	public FavoriteList() {
	}
	
	public FavoriteList(List<FriendProfileInfo> favoriteList)
	{
		this.favoriteList=favoriteList;
	}
	
	public List<FriendProfileInfo> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(List<FriendProfileInfo> favoriteList) {
		this.favoriteList = favoriteList;
	}
	
	public void add()
	{
	
	}
	
	public void search()
	{
	
	}
	
	public void delete()
	{
	
	}
	
	public void update()
	{
	
	}

	public String toString() {
		return "FavoriteList [favoriteList=" + favoriteList + "]";
	}
	
	
}
