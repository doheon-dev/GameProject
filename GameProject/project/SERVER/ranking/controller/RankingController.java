/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  RankingController
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

package com.project.server.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.server.service.RankingService;

@RestController
@RequestMapping("/api/")
public class RankingController {

	Logger logger = LoggerFactory.getLogger(RankingController.class);

	@Autowired
	RankingService rankingService;

	/** 
	 *  @category 유저 랭킹 리스트  
	 *	@title rankList
	 *	@param paramMap
	 * 	@return
	 */
	@RequestMapping(value="/rankList", method=RequestMethod.GET)
	public ResponseEntity<?> rankList(
		@RequestParam HashMap<String,Object> paramMap
		){

		List rankList = rankingService.rankList(paramMap);		

		HashMap<String, Object> retrunMap = new HashMap<String, Object>();
		retrunMap.put("rankList", rankList);

		if(retrunMap.isEmpty()) {
			return new ResponseEntity <HashMap>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <HashMap>(retrunMap, HttpStatus.OK);
	}
	
}