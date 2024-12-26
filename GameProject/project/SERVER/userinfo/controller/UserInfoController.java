/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  UserinfoController
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

package com.project.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.server.service.UserInfoService;
import com.project.server.service.MenuService;

@RestController
@RequestMapping("/api/")
public class UserInfoController {

	Logger logger = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	UserInfoService userinfoService;
	
	@Autowired
	MenuService menuService;

	/** 
	 *  @category 로그인 아이디, 비밀번호 확인
	 *	@title loginCheck
	 *	@param paramMap
	 *	@param userid
	 *	@param password
	 * 	@return
	 */
	@RequestMapping(value="/loginCheck", method=RequestMethod.GET)
	public ResponseEntity<?> loginCheck(
		@RequestParam (value = "userid") String userid,
		@RequestParam (value = "password") String password
		){

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
		paramMap.put("userid", userid);
		paramMap.put("password", password);
		
		int result = userinfoService.loginCheck(paramMap);
		HashMap<String, Object> dataMap = userinfoService.userInfo(paramMap);

		ArrayList menuList = menuService.menuList(paramMap);

		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("result", result);
		retrunMap.put("userinfo", dataMap);
		retrunMap.put("menuList", menuList);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}
	
	
	/** 
	 *  @category 유저 메뉴 권한 리스트 
	 *	@title loginCheck
	 *	@param paramMap
	 *	@param userid
	 *	@param password
	 * 	@return
	 */
	@RequestMapping(value="/userMenu", method=RequestMethod.GET)
	public ResponseEntity<?> userMenu(
		@RequestParam (value = "userid") String userid
		){

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
		paramMap.put("userid", userid);

		ArrayList menuList = menuService.menuList(paramMap);		

		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("menuList", menuList);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}

	
	/** 
	 * 	@category 회원가입 생성 
	 *	@title regster
	 *	@param paramMap
	 *	@param jsonData
	 * 	@return
	 */
	@RequestMapping(value="/signUp", method=RequestMethod.POST, produces = "applicaion/text")
	public ResponseEntity<?> signUp(
		@RequestParam HashMap<String,Object> paramMap,
		HttpServletRequest request,
		@RequestBody String jsonData){
	try {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonData);
		
		paramMap.put("jsonData", jsonObject);
		
		userinfoService.signUp(paramMap);
		userinfoService.regRank(paramMap);
		userinfoService.regMenuAuth(paramMap);
		
		return new ResponseEntity <String>("success",HttpStatus.OK);
		
		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/** 
	 *  @category 회원정보 수정 
	 *	@title updateUser
	 *	@param paramMap
	 *	@param jsonData
	 * 	@return
	 */
	@RequestMapping(value="/updateUser", method=RequestMethod.PUT, produces = "applicaion/text")
	public ResponseEntity<?> updateUser(
		@RequestParam HashMap<String,Object> paramMap,
		@RequestBody String jsonData){
	try {
			
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonData);

		paramMap.put("jsonData", jsonObject);
		userinfoService.updateUser(paramMap);
			
		return new ResponseEntity <String>("success",HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/** 
	 *  @category 회원가입 탈퇴 
	 *	@title leaveUser
	 *	@param paramMap
	 *	@param userid
	 * 	@return
	 */
	@RequestMapping(value="/leaveUser", method=RequestMethod.DELETE, produces = "applicaion/text")
	public ResponseEntity<?> leaveUser(
		@RequestParam HashMap<String,Object> paramMap,
		@RequestParam (value = "userid") String userid){
	try {

		userinfoService.deleteUser(paramMap);
		
		return new ResponseEntity <String>("success",HttpStatus.OK);
		
		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/** 
	 *  @category 아이디 중복확인 
	 *	@title idCheck 
	 *	@param paramMap
	 *	@param userid
	 * 	@return
	 */
	@RequestMapping(value="/idCheck", method=RequestMethod.GET)
	public ResponseEntity<?> idCheck(
		@RequestParam HashMap<String,Object> paramMap,
		@RequestParam (value = "userid") String userid){
		
		int totalCnt = userinfoService.idCheck(paramMap);
			
		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("totalCnt", totalCnt);
			
		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}
	
	
	/** 
	 *  @category 유저 정보 확인
	 *	@title userInfo
	 *	@param paramMap
	 *	@param userid
	 * 	@return 
	 */
	@RequestMapping(value="/userInfo", method=RequestMethod.GET)
	public ResponseEntity<?> userInfo(
//		@RequestParam HashMap<String,Object> paramMap,
		@RequestParam (value = "userid") String userid){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);

		HashMap<String, Object> dataMap = userinfoService.userInfo(paramMap);
		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
			
		retrunMap.put("result", dataMap);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}

}