package DAO;

import java.util.List;

import test.FriendInfo;
import test.FriendProfileInfo;
import test.ProfileInfo;

public interface FriendProfileInfoDAO extends DAO{
	public List<FriendProfileInfo> select();
	public List<FriendProfileInfo> select(String id);
	public boolean select(String id, String userId);
	public int update(FriendProfileInfo info);
	public int delete(String memberId, String friendId);
	public int delete(String friendId);
	public int insert(FriendProfileInfo info);
}
