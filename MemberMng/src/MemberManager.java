import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
	final static int id_length = 7;
	final static int phNum_length = 13;

	private MemberDAO memberDAO;
	private Scanner keyboard;

	public MemberManager() {
		memberDAO = new MemberDAO();
		keyboard = new Scanner(System.in);
	}

	public void startProgram() throws IOException {
		boolean isRun = true;
		while (isRun) {
			readMenu();
			int cmd = Integer.parseInt(keyboard.nextLine());

			switch (cmd) {
			case 1:
				getMemberList();
				break;
			case 2:
				insertMember();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				deleteMember();
				break;
			case 0:
				isRun = false;
				break;
			}
		}
	}

	public void readMenu() {
		System.out.println("목록을 원하시면 1번을 입력하세요.");
		System.out.println("등록을 원하시면 2번을 입력하세요.");
		System.out.println("수정을 원하시면 3번을 입력하세요.");
		System.out.println("삭제를 원하시면 4번을 입력하세요.");
		System.out.println("종료를 원하시면 0번을 입력하세요.");
	}

	public void getMemberList() {

		// 2. 목록을 조회한다.
		List<Member> memberList = memberDAO.getMemberList();
		if (memberList.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			for (Member member : memberList) {
				System.out.println(member);
			}
		}
	}

	public void insertMember() {
		System.out.print("아이디를 입력하세요 (형식 M-00001): ");
		String id = keyboard.nextLine();
		if (idVarify(id) == false)
			return;

		System.out.print("이름을 입력하세요 : ");
		String name = keyboard.nextLine();
		if (nameVarify(name) == false)
			return;

		System.out.print("전화번호를 입력하세요 : ");
		String phone = keyboard.nextLine();
		if (phoneNumberVarify(phone) == false)
			return;

		if (memberDAO.getMember(id)) {
			System.out.println(id + "가 이미 존재합니다.");
			return;
		}

		// 2. 멤버 정보를 등록한다.
//		Member member = new Member();
//
//		member.setMemberId(id);
//		member.setName(name);
//		member.setPhoneNumber(phone);
		memberDAO.insertMember(id, name, phone);

		System.out.println("---> 회원가입에 성공하셨습니다.");
	}

	public void updateMember() {
		System.out.print("수정할 아이디를 입력하세요 (형식 M-00001): ");
		String id = keyboard.nextLine();
		if (idVarify(id) == false)
			return;

		System.out.print("수정할 전화번호를 입력하세요 : ");
		String phone = keyboard.nextLine();
		if (phoneNumberVarify(phone) == false)
			return;

		if (memberDAO.getMember(id) == false) {
			System.out.println("수정할" + id + "회원 정보가 존재하지 않습니다.");
			return;
		}

		// 2. 멤버 정보를 수정한다.
//		Member member = new Member();
//		member.setMemberId(id);
//		member.setName("");
//		member.setPhoneNumber(phone);
		memberDAO.updateMember(id, phone);

		System.out.println("---> 회원수정에 성공하셨습니다.");
	}

	public void deleteMember() {
		System.out.print("삭제할 아이디를 입력하세요 : ");
		String id = keyboard.nextLine();
		if (idVarify(id) == false)
			return;

		if (memberDAO.getMember(id) == false) {
			System.out.println("삭제할" + id + "회원 정보가 존재하지 않습니다.");
			return;
		}

		// 2. 멤버 정보를 삭제한다.
		memberDAO.deleteMember(id);

		System.out.println("---> " + id + "회원 삭제에 성공하셨습니다.");
	}

	public boolean idVarify(String id) {
//		if (id == "") {
		if (id == null || id.length() == 0) {
			System.out.println("아이디는 필수 입력항목입니다.");
			return false;
		}

//		if(Pattern.matches("^(M)-\\d{5}$", id)==false) {
//		if(!memberId.startsWith("M-") || !(memberId.length() == 7))  {
		if (id.length() != id_length || id.substring(0, 2).equals("M-") != true) {
			System.out.println("아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.");
			return false;
		}
		return true;
	}

	public boolean nameVarify(String name) {
//		if (name == "") {
		if (name == null || name.length() == 0) {
			System.out.println("이름은 필수 입력항목입니다.");
			return false;
		}
		return true;
	}

	public boolean phoneNumberVarify(String phone) {
//		if (phone == "") {
		if (phone == null || phone.length() == 0) {
			System.out.println("전화번호는 필수 입력항목입니다.");
			return false;
		}
//		if(Pattern.matches("^\\d{3}-\\d{4}-\\d{4}$", phone)== false)
		if (phone.length() != phNum_length || phone.charAt(3) != '-' || phone.charAt(8) != '-') {
			System.out.println("전화번호는 두 개의 '-'를 포함하여 총 13개의 문자로 구성해야 합니다.");
			return false;
		}
		return true;
	}

}
