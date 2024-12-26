/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  MenuController
 * 버전 : 1.0v
 * 업무명 (기능명) : Menu
 * 설명 : 개인 프로젝트의 메뉴를 조회, 수정, 삭제
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

import com.project.server.service.MenuService;

@RestController
@RequestMapping("/api/")
public class MenuController {

	Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	MenuService menuService;

	/** 
	 *  @category 메뉴 리스트  
	 *	@title menuList
	 *	@param paramMap
	 * 	@return
	 */
	@RequestMapping(value="/menuList", method=RequestMethod.GET)
	public ResponseEntity<?> menuList(
		@RequestParam HashMap<String,Object> paramMap
		){

		ArrayList menuList = menuService.menuList(paramMap);		

		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("menuList", menuList);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}

	/** 
	 *  @category 메뉴이름 검색 
	 *	@title updateUser
	 *	@param paramMap
	 * 	@return
	 */
	@RequestMapping(value="/menuSearch", method=RequestMethod.GET)
	public ResponseEntity<?> menuSearch(
		@RequestParam (value = "menuname") String menuname){
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("menuname", menuname);

		ArrayList menuList = menuService.menuSearch(paramMap);		

		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("menuList", menuList);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}
	
	/** 
	 *  @category 메뉴 수정 
	 *	@title updateUser
	 *	@param paramMap
	 *	@param jsonData
	 * 	@return
	 */
	@RequestMapping(value="/updateMenu", method=RequestMethod.PUT, produces = "applicaion/text")
	public ResponseEntity<?> updateMenu(
		@RequestParam HashMap<String,Object> paramMap,
		@RequestBody String jsonData){
	try {
			
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonData);

		paramMap.put("jsonData", jsonObject);
		menuService.updateMenu(paramMap);
		
		return new ResponseEntity <String>("success",HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/** 
	 *  @category 메뉴 삭제 
	 *	@title leaveUser
	 *	@param paramMap
	 *	@param menuid
	 * 	@return
	 */
	@RequestMapping(value="/deleteMenu", method=RequestMethod.DELETE, produces = "applicaion/text")
	public ResponseEntity<?> deleteMenu(
		@RequestParam HashMap<String,Object> paramMap,
		@RequestParam (value = "menuid") String menuid){
	try {

		menuService.deleteMenu(paramMap);
		
		return new ResponseEntity <String>("success",HttpStatus.OK);
		
		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/** 
	 * 	@category 메뉴 생성 
	 *	@title regster
	 *	@param paramMap
	 *	@param jsonData
	 * 	@return
	 */
	@RequestMapping(value="/addMenu", method=RequestMethod.POST, produces = "applicaion/text")
	public ResponseEntity<?> addMenu(
		@RequestParam HashMap<String,Object> paramMap,
		HttpServletRequest request,
		@RequestBody String jsonData){
	try {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonData);
		
		paramMap.put("jsonData", jsonObject);
		
		menuService.addMenu(paramMap);
		
		return new ResponseEntity <String>("success",HttpStatus.OK);
		
		} catch(Exception e) {
			return new ResponseEntity (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
		
}