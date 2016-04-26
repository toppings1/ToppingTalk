package DAO;

import java.util.List;

import test.MemberInfo;

public interface MemberInfoDAO extends DAO
{
	public List<MemberInfo> select();
	public MemberInfo select(String id);
	public List<MemberInfo> selectName(String name);
	public int update(MemberInfo memberInfo);
	public int delete(String id);
	public int insert(MemberInfo memberInfo);
}
