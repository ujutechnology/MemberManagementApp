public class Member {

	private String memberId;
	private String name;
	private String phoneNumber;

	public Member() {
		this.memberId = "";
		this.name = "";
		this.phoneNumber = "";
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}

}
