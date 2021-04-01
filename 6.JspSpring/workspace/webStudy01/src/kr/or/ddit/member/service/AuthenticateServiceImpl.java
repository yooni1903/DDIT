package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemeberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService {
	private IMemeberDAO dao = new MemberDAOImpl();

	@Override
	public ServiceResult authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMemberForAuth(member.getMem_id());
		ServiceResult result = null;
		if(savedMember != null) {
			// 아이디는 있는 것을 확인했으니 비밀번호를 인증하는 것
			String inputPass = member.getMem_pass();
			String savedPass = savedMember.getMem_pass();
			if(savedPass.equals(inputPass)) {
				try {
					BeanUtils.copyProperties(member, savedMember);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			// 회원이 존재하지 않는다.
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
