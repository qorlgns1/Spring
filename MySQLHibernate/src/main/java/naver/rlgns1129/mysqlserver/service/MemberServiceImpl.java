package naver.rlgns1129.mysqlserver.service;

import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.rlgns1129.mysqlserver.dao.MemberDAO;
import naver.rlgns1129.mysqlserver.domain.Member;
import naver.rlgns1129.mysqlserver.util.CryptoUtil;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	@Transactional
	public void join(MultipartHttpServletRequest request) {
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String pw = request.getParameter("pw");
		MultipartFile image = request.getFile("profile");
		
		System.out.println("MemberServiceImpl.join.email : " + email);
		System.out.println("MemberServiceImpl.join.nickname : " + nickname);
		System.out.println("MemberServiceImpl.join.pw : " + pw);
		System.out.println("MemberServiceImpl.join.image : " + image);

		
		//결과를 저장할 Map을 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		map.put("emailcheck", false);
		map.put("nicknamecheck", false);
		
		String key = "itggangpae";
		
		//email 중복 검사
		//복호화가 가능한 암호화로 되어 있음
		List<String> emaillist = memberDao.emailCheck();
		for(String temp : emaillist) {
			try {
			//복호화 해서 비교
				if(CryptoUtil.decryptAES256(temp, key).equals(email)) {
					map.put("emailcheck", false);
					request.setAttribute("request", map);
					return;
				}	
			}catch(Exception e) {}
		}
		
		//nickname 중복검사
		List<String> nicknamelist = memberDao.nicknameCheck(nickname);
		if(nicknamelist != null && nicknamelist.size()>0) {
			map.put("nicknamecheck", false);
			request.setAttribute("request", map);
			return;
		}
		
		//파일 업로드
		String profile = "default.jpg";
		//이미지가 존재하면 업로드
		if(image != null && image.isEmpty() == false) {
			//업로드할 디렉토리 경로를 생성
			String filePath = request.getRealPath("/profile");
			//파일 이름 생성
			profile = UUID.randomUUID() + image.getOriginalFilename();
			//업로드할 파일 경로를 생성
			filePath = filePath + "/" + profile;
			
			//파일 업로드
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(filePath);
				fos.write(image.getBytes());
				
			}catch(Exception e) {
				System.out.println("파일 업로드 실패 : " + e.getMessage());
			}
		}
		
			//Dao의 파라미터 만들기
			Member member = new Member();
			try {
				member.setEmail(CryptoUtil.encryptAES256(email, key));
			}catch(Exception e) {
				
			}
			
			member.setNickname(nickname);
			member.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
			member.setProfile(profile);
			
			//회원가입 메소드 호출
			memberDao.join(member);
			map.put("result", true);
			request.setAttribute("result", map);
			//map에서 result가 회원가입 성공여부
			//emailcheck는 이메일 중복 검사 실패
			//nicknamecheck는 닉네임 중복 검사 여부
			
			
			
		
		
		
	}

	@Override
	@Transactional
	public void login(HttpServletRequest request) {
		//파라미터 읽기
		String nickname = request.getParameter("nickname");
		String pw = request.getParameter("pw");
		
		System.out.println("MemberServiceImpl.login.nickname : " + nickname);
		System.out.println("MemberServiceImpl.login.pw : " + pw);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		
		//nickname을 가지고 데이터 찾아오기
		List<Member> list = memberDao.login(nickname);
		System.out.println("MemberServiceImpl.login.list : " + list);

		//nickname에 해당하는 데이터가 없다면 더이상 진행할 필요가 없음
		if(list == null || list.size() < 1) {
			request.setAttribute("result", map);
			return;
		}
		
		//찾아온 데이터와 비밀번호를 비교
		for(Member member : list) {
			if(BCrypt.checkpw(pw, member.getPw())) {
				map.put("nickname", nickname);
				map.put("profile", member.getProfile());
				try {
					map.put("email", CryptoUtil.decryptAES256(member.getEmail(), "itggangpae"));
				}catch(Exception e) {}
				map.put("result", true);
				request.setAttribute("result", map);
				return;
			}
		}
		
	}

	@Override
	@Transactional
	//Hibernate는 기본키를 조건으로 해서 수정이나 삭제를 수행
	public void update(MultipartHttpServletRequest request) {
		//필요한 파라미터 읽기
		String nickname = request.getParameter("nickname");
		String pw = request.getParameter("pw");
		//이미지는 반드시 입력되는 항목이 아니라면
		//이전값을 파라미터로 보내고 새로운 값이 null이 아니라면 새로운 값으로 대체
		String profile = request.getParameter("oldprofile");
		//email 찾아오기
		
		//이미 로그인이 되어있는거라 닉네임은 반드시 존재해서 따로 없는지 검사할 필요가 없다.
		List<Member>list = memberDao.login(nickname);
		String email = list.get(0).getEmail();
		
		//이미지 파일 업로드
		MultipartFile image = request.getFile("profile");
		if(image != null && image.isEmpty() == false) {
			//업로드 할 파일 경로 생성
			String filePath = request.getRealPath("/profile");
			profile = UUID.randomUUID() + image.getOriginalFilename();
			//이걸 안해주면 이상한 이름으로 루트에 생성됨 
			filePath = filePath + "/" + profile;
			//파일 업로드
			try {
				FileOutputStream fos = new FileOutputStream(filePath);
				fos.write(image.getBytes());
			}catch(Exception  e) {
				System.out.println("파일 업로드 예외 : " + e.getMessage());
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		
		//DAO 파라미터 만들기
		Member member = new Member();
		member.setEmail(email);
		member.setPw(pw);
		member.setNickname(nickname);
		member.setProfile(profile);
		
		memberDao.update(member);
		
		map.put("result", true);
		
		request.setAttribute("result", map);
		
		
	}

	@Override
	@Transactional
	public void delete(HttpServletRequest request) {
		String nickname = request.getParameter("nickname");
		List<Member> list = memberDao.login(nickname);
		String email = list.get(0).getEmail();
		
		Member member = new Member();
		member.setEmail(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		
		memberDao.delete(member);
		
		map.put("result", true);
		
		request.setAttribute("result", map);
	}



}
