package com.java_web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java_web.dto.reuqest.StatDTO;
import com.java_web.repository.StatisticRepository;
import com.java_web.service.StatisticService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService{
	
	private StatisticRepository statisticRepository;
	
	@Override
	public List<StatDTO> getAvgScore() {
		List<StatDTO> list = new ArrayList<StatDTO>();
		
		List<Object[]> rawList = statisticRepository.getAvgScore();
		for (Object[] rawItem : rawList) {
			try {
				int id = (Integer) rawItem[0];
				String name = (String) rawItem[1];
				BigDecimal rscore = (BigDecimal) rawItem[2];
				float score = rscore.floatValue();
				Long rtotal = (Long) rawItem[3];
				int total = rtotal.intValue();
				
				StatDTO item = new StatDTO(id, name, score, total);
			
				list.add(item);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public Map<Integer, Long> getTodoPerMonth() {
		Map<Integer, Long> map = new HashMap<Integer, Long>(); // Map includes <month, total>
		List<Object[]> rawList = statisticRepository.getTodoPerMonth();
		try {
			for (Object[] rawItem : rawList) {
				try {
					Long rtotal = (Long) rawItem[0];
					Integer rmonth = (Integer) rawItem[1];
					
//					Long total = rtotal.longValue();
//					int month = rmonth.intValue();
					
					map.put(rmonth, rtotal);
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
			}
		} catch (IndexOutOfBoundsException | ConcurrentModificationException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@Override
	public Float getAvgTotal() {
		List<Object[]> l = statisticRepository.findAvgTotal();
		Object[] o = l.get(0);
		float value = 0;
		try {
			BigDecimal raw = (BigDecimal) o[0];
			value = raw.floatValue();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return value;
	}
	@Override
	public Long getTotalJob() {
		List<Object[]> l = statisticRepository.findTotalJob();
		Object[] o = l.get(0);
		Long value = (long) 0;
		try {
			Long raw = (Long) o[0];
			value = raw.longValue();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return value;
	}
	@Override
	public Long getTotalUser() {
		List<Object[]> l = statisticRepository.findTotalUser();
		Object[] o = l.get(0);
		Long value = (long) 0;
		try {
			Long raw = (Long) o[0];
			value = raw.longValue();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
