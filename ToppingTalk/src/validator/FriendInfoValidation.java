package validator;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

public class FriendInfoValidation {
	private String memberId;
	private String friendId;
	
	public FriendInfoValidation()
	{
		this(null, null);
	}

	public FriendInfoValidation(String memberId, String friendId) {
		this.memberId = memberId;
		this.friendId = friendId;
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
	
	public boolean validate()
	{
		return true;
	}
	
	private boolean validateId(String id)
	{
		return MemberInfoValidation.validateMemberId(memberId);
	}
	private boolean isNullId(String id)
	{
		return MemberInfoValidation.isNullMemberId(id);
	}
}
