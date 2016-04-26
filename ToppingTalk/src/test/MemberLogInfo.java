package test;
import java.util.Date;

public class MemberLogInfo
{
	private String memberId;
	private Date memberLogDate;
	private boolean logStatus;
	
	public MemberLogInfo()
	{
		this("null", null, false);
	}
	public MemberLogInfo(String memberId, Date memberLogDate, boolean logStatus) {
		super();
		this.memberId = memberId;
		this.memberLogDate = memberLogDate;
		this.logStatus = logStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getMemberLogDate() {
		return memberLogDate;
	}
	public void setMemberLogDate(Date memberLogDate) {
		this.memberLogDate = memberLogDate;
	}
	public boolean getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}
	@Override
	public String toString() {
		return "MemberLogInfo [memberId=" + memberId + ", memberLogDate="
				+ memberLogDate + ", logStatus=" + logStatus + "]";
	}
	
	
}
