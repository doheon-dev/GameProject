/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  UserInfoImpl
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

package com.project.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.server.mapper.UserInfoMapper;
import com.project.server.service.UserInfoService;

@Transactional
@Service("userinfoService")
public class UserInfoImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userinfoMapper;

	/** 
	 *  @category 로그인 
	 *	@title loginCheck
	 *	@param paramMap
	 * 	@return 
	 */
	@Override
	public int loginCheck(HashMap<String, Object> paramMap){
		return userinfoMapper.loginCheck(paramMap);
	}
	
	/** 
	 *  @category 회원가입 
	 *	@title signUp
	 *	@param paramMap
	 *	@param jsonData
	 *	@throws Exception
	 */
	@Override
	public void signUp(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		userinfoMapper.signUp(jsonObject);
	}	

	/** 
	 *  @category 사용자 초기 랭킹 
	 *	@title regRank
	 *	@param paramMap
	 *	@param jsonData
	 *	@throws Exception
	 */
	@Override
	public void regRank(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		userinfoMapper.regRank(jsonObject);
	}			

	/** 
	 *  @category 사용자 초기 메뉴 권한 
	 *	@title regMenuAuth
	 *	@param paramMap
	 *	@param jsonData
	 *	@throws Exception 
	 */
	@Override
	public void regMenuAuth(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		userinfoMapper.regMenuAuth(jsonObject);
	}		
	
	
	/** 
	 *  @category 유저 정보 확인 
	 *	@title userInfo
	 *	@param paramMap
	 *	@param userid
	 * 	@return 
	 */
	@Override
	public HashMap<String, Object> userInfo(HashMap<String, Object> paramMap) {
		return userinfoMapper.userInfo(paramMap);
	}
	
	/** 
	 *  @category 사용자 메뉴 리스트 
	 *	@title userMenuList
	 *	@param paramMap
	 *	@param userid
	 * 	@return 
	 */
	@Override
	public ArrayList userMenuList(HashMap<String, Object> paramMap) {
		return userinfoMapper.userMenuList(paramMap);
	}
	
	
	/** 
	 *  @category 아이디 중복 확인 
	 *	@title idCheck
	 *	@param paramMap
	 *	@param userid
	 * 	@return 
	 */
	@Override
	public int idCheck(HashMap<String, Object> paramMap){
		return userinfoMapper.idCheck(paramMap);
	}
	
	/** 
	 *  @category 회원정보 수정 
	 *	@title updateUser
	 *	@param paramMap
	 *	@param jsonData
	 *	@throws Exception
	 */
	@Override
	public void updateUser(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		userinfoMapper.updateUser(jsonObject);
	}
	
	/** 
	 *  @category 유저 삭제 
	 *	@title deleteUser
	 *	@param paramMap
	 * 	@return 
	 */
	@Override
	public int deleteUser(HashMap<String, Object> paramMap) {
		return userinfoMapper.deleteUser(paramMap);
	}
	
}