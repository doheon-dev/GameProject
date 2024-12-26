/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  MenuImpl
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

package com.project.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.server.mapper.MenuMapper;
import com.project.server.service.MenuService;

@Transactional
@Service("MenuService")
public class MenuImpl implements MenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	/** 
	 * 	@category 메뉴 리스트  
	 *	@title menuList
	 *	@param paramMap
	 * 	@return
	 */
	@Override
	public ArrayList menuList(HashMap<String, Object> paramMap) {
		return menuMapper.menuList(paramMap);
	}

	/** 
	 * 	@category 메뉴 검색 
	 *	@title menuSearch
	 *	@param paramMap
	 * 	@return
	 */
	@Override
	public ArrayList menuSearch(HashMap<String, Object> paramMap) {
		return menuMapper.menuSearch(paramMap);
	}
	
	/** 
	 * 	@category 메뉴 수정 
	 *	@title updateMenu
	 *	@param paramMap
	 *	@param jsonData
	 */
	@Override
	public void updateMenu(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		menuMapper.updateMenu(jsonObject);
	}
	
	/** 
	 * 	@category 메뉴 삭제 
	 *	@title deleteMenu
	 *	@param paramMap
	 * 	@return
	 */
	@Override
	public int deleteMenu(HashMap<String, Object> paramMap) {
		return menuMapper.deleteMenu(paramMap);
	}
	
	/** 
	 * 	@category 메뉴 생성 
	 *	@title addMenu
	 *	@param paramMap
	 *	@param jsonData
	 */
	@Override
	public void addMenu(HashMap<String, Object> paramMap) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(String.valueOf(paramMap.get("jsonData")));
		menuMapper.addMenu(jsonObject);
	}	
	
	
}