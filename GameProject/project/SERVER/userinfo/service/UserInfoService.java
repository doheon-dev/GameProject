/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  UserInfoService
 * 버전 : 1.0v
 * 업무명 (기능명) : UseInfo
 * 설명 : 사용자의 계정 관리
 * 작성일 : 2020.03.04
 * 작성자 : 김도헌 
 * 수정 내역 : 
 * 수정자    수정일자       수정내역  
 * -----   ------       ------
 * 
 */

package com.project.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
 
@Service
public interface UserInfoService {
	
	/** 
	 *  @category 로그인 
	 *	@title loginCheck
	 *	@param paramMap
	 */
	int loginCheck(HashMap<String, Object> paramMap);
	
	/**	
	 *  @category 회원가입 
	 *	@title signUp
	 *	@param paramMap
	 *  @throws Exception
	 */
	void signUp(HashMap<String, Object> paramMap) throws Exception;
	
	/** 
	 *  @category 사용자 초기 랭킹 
	 *	@title regRank
	 *	@param paramMap
	 *  @throws Exception
	 */
	void regRank(HashMap<String, Object> paramMap) throws Exception;
	
	/** 
	 *  @category 사용자 초기 메뉴 권한 
	 *	@title regMenuAuth
	 *	@param paramMap
	 *	@throws Exception
	 */
	void regMenuAuth(HashMap<String, Object> paramMap) throws Exception;
	
	/** 
	 *  @category 아이디 중복 확인 
	 *	@title idCheck
	 *	@param paramMap
	 */
	int idCheck(HashMap<String, Object> paramMap);
	
	/** 
	 *  @category 유저 정보 확인 
	 *	@title userInfo
	 *	@param paramMap
	 */
	HashMap<String, Object> userInfo(HashMap<String, Object> paramMap);
	
	/** 
	 *  @category 사용자 메뉴리스트 
	 *	@title userMenuList
	 *	@param paramMap
	 */
	ArrayList userMenuList(HashMap<String, Object> paramMap);
	
	/** 
	 *  @category 유저 삭제 
	 *	@title deleteUser
	 *	@param paramMap
	 */
	int deleteUser(HashMap<String, Object> paramMap);
	
	/** 
	 *  @category 회원정보 수정 
	 *	@title updateUser
	 *	@param paramMap
	 *	@throws Exception
	 */
	void updateUser(HashMap<String, Object> paramMap) throws Exception;
	
}