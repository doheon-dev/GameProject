/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  RankingService
 * 버전 : 1.0v
 * 업무명 (기능명) : Ranking
 * 설명 : 사용자들의 랭킹을 조회
 * 작성일 : 2020.03.04
 * 작성자 : 김도헌 
 * 수정 내역 : 
 * 수정자    수정일자       수정내역  
 * -----   ------       ------
 * 
 */

package com.project.server.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
 
@Service
public interface RankingService {
	
	/** 
	 *  @category 유저 랭킹 리스트  
	 *	@title rankList
	 *	@param paramMap
	 */
	List rankList(HashMap<String, Object> paramMap);
	
}