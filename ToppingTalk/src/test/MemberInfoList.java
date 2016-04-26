package test;
import java.util.List;

public class MemberInfoList
{
	private List<MemberInfo> memberList;
	public MemberInfoList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberInfoList(List<MemberInfo> memberList) {
		super();
		this.memberList = memberList;
	}


	public void setMemberList(List<MemberInfo> memberList) {
		this.memberList = memberList;
	}
	public List<MemberInfo> getMemberList() {
		return memberList;
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


	@Override
	public String toString() {
		return "MemberInfoList [memberList=" + memberList + "]";
	}


	
}
