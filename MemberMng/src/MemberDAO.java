import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// MEMBER 테이블 관련 SQL 명령어
	private final String MEMBER_LIST = "SELECT MEMBER_ID, NAME, PHONE_NUMBER FROM MEMBER";
	private final String MEMBER_GET = "SELECT COUNT(MEMBER_ID) FROM MEMBER WHERE MEMBER_ID = ?";
	private final String MEMBER_INSERT = "INSERT INTO MEMBER VALUES(?, ?, ?)";
	private final String MEMBER_UPDATE = "UPDATE MEMBER SET PHONE_NUMBER = ? WHERE MEMBER_ID = ?";
	private final String MEMBER_DELETE = "DELETE MEMBER WHERE MEMBER_ID = ?";

	// MEMBER 테이블 관련 CRUD 메소드
	// 목록 조회
	public List<Member> getMemberList() {
		List<Member> memberList = new ArrayList<Member>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setName(rs.getString("NAME"));
				member.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return memberList;
	}

	public boolean getMember(String id) {
		int count = 0;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_GET);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				if (count > 0)
					return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return false;
	}

	// 등록
	public void insertMember(String memberId, String name, String phoneNumber) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, phoneNumber);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 수정
	public void updateMember(String memberId, String phoneNumber) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			stmt.setString(1, phoneNumber);
			stmt.setString(2, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 학생 삭제
	public void deleteMember(String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
