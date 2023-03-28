package system.pos.member;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberRegistService {
	private MemberDao memberDao;

	public MemberRegistService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public MemberRegistService() {}

	/**
	 * 계정 등록
	 */
	public void regist(MemberRegistRequest req) {
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		
		Member newMember = new Member(req.getRank(), req.getName(), req.getId(),
				req.getPassword(), date.format(d));
		
		System.out.println("service : "+ newMember.getRank()+ newMember.getName()+ newMember.getId() +
				newMember.getPassword()+ newMember.getRegDate());
		memberDao.insert(newMember);
	}

	/**
	 * 계정 삭제
	 */
	public void delete(MemberRegistRequest req) {
		Member member = memberDao.selectById(req.getId());
		memberDao.delete(member);
	}
}
