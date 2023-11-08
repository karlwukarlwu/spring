package service;

import dao.MemberDAOImpl;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class MemberServiceImpl {
    private MemberDAOImpl memberDAO;

    public MemberServiceImpl() {
//        System.out.println("MemberServiceImpl 构造器被执行...");
    }

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }
    public void add() {
        System.out.println("MemberServiceImpl add() 被调用..");
        memberDAO.add();
    }
}
