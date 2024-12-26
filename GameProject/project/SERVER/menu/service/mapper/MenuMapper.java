/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  MenuMapper
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

package com.project.server.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MenuMapper {
   
	/** 
	 * 	@category 메뉴 리스트 
	 *	@title menuList
	 *	@param paramMap
	 */
	ArrayList menuList(HashMap<String, Object> paramMap);

	/** 
	 * 	@category 메뉴 검색  
	 *	@title menuSearch
	 *	@param paramMap
	 */
	ArrayList menuSearch(HashMap<String, Object> paramMap);
	
	/** 
	 * 	@category 메뉴 수정 
	 *	@title updateMenu
	 *	@param paramMap
	 */
	int updateMenu(HashMap<String, Object> paramMap);
	
	/** 
	 * 	@category 메뉴 삭제  
	 *	@title deleteMenu
	 *	@param paramMap
	 */
	int deleteMenu(HashMap<String, Object> paramMap);
	
	/** 
	 * 	@category 메뉴 생성 
	 *	@title addMenu
	 *	@param paramMap
	 */
	int addMenu(HashMap<String, Object> paramMap);
	
}