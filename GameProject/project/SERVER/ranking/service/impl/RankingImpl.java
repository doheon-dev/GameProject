/*
 * 프로그램명 : 개인프로젝트
 * 클래스명 :  RankingImpl
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

package com.project.server.impl;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.server.mapper.RankingMapper;
import com.project.server.service.RankingService;

@Transactional
@Service("RankingService")
public class RankingImpl implements RankingService{
	
	@Autowired
	private RankingMapper rankingMapper;

	/** 
	 *  @category 유저 랭킹 리스트  
	 *	@title rankList
	 *	@param paramMap
	 * 	@return
	 */
	@Override
	public List rankList(HashMap<String, Object> paramMap) {
		return rankingMapper.rankList(paramMap);
	}
	
}