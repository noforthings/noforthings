package com.java_web.service.impl;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java_web.dto.reuqest.SubjectDTO;
import com.java_web.model.Score;
import com.java_web.model.StudyCredit;
import com.java_web.model.Subject;
import com.java_web.repository.ScoreRepository;
import com.java_web.repository.StudyCreditRepository;
import com.java_web.repository.SubjectRepository;
import com.java_web.service.SubjectService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService{
	
	private SubjectRepository sr;
	private ScoreRepository scr;
	private StudyCreditRepository stcr;
	
	public Subject chooseBest(List<Subject> rawList, int userId) {
		Subject sub = new Subject();
		
		StudyCredit stc = stcr.findById(userId).get();
		float temp = 0;
		for (Subject item : rawList) {
			float score = item.getCoding()*stc.getCoding() + item.getInteract()*stc.getInteract()
					+ item.getDesigning()*stc.getDesigning() + item.getLogicalThinking()*stc.getLogicalThinking();
			
			if (score > temp) {
				temp = score;
				sub = item;
			}
		}
		
		return sub;
	}

	public List<Subject> checkExpected(List<Subject> rawList) {
		/*
		 * Check expected through not study subject, compare to all in one group to remove
		 * if size of that group < default
		 * result is group not done and null-obligated group *
		 */
		List<Subject> list = new ArrayList<>();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		rawList.forEach(item -> {
			String note = item.getNote();
			if (note != null) {
				map.put(note, map.getOrDefault(note, 0) + 1);
			}
		});
		System.out.println(" > Before " + map.toString());
		try {
			Map<String, Integer> keysToRemove = new HashMap<>();
			// Must use iterator not foreach when remove element inside
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
	            String note = entry.getKey();
	            Integer count = entry.getValue();
	            if (note == null) {
	            	note = " ";
	            }
				// Nếu đã học, tổng số sẽ nhỏ hơn ban đầu. Nếu học đủ trong nhóm, xóa khỏi map
				switch (note) {
				case "TcNN1":
					if (count < 8) {//8
						keysToRemove.put(note, count);
					}
					break;
				case "TcNN2":
					if (count < 8) {//8
						keysToRemove.put(note, count);
					}
					break;
				case "TcNN3":
					if (count < 8) {//8
						keysToRemove.put(note, count);
					}
					break;
				case "TcNN4":
					if (count < 8) {//8
						keysToRemove.put(note, count);
					}
					break;
				case "TcNN5":
					if (count < 4) {//4
						keysToRemove.put(note, count);
					}
					break;
				case "TcNN6":
					if (count < 4) {//4
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT1":
					if (count < 4) {//4
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT2":
					if (count < 3) {//3
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT3":
					if (count < 3) {//3
						keysToRemove.put(note, count);
					}
					break;
				case "TcGDTC":
					if (count < 33) {//34
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT4":
					if (count < 5) { //5
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT6":
					if (count < 8) { // 9
						keysToRemove.put(note, count);
					}
					break;
				case "TcCNTT5":
					if (count < 7) { // 8
						keysToRemove.put(note, count);
					}
					break;
				}
			}
	        // Remove here
	        for (String key : keysToRemove.keySet()) {
	            map.remove(key);
	        }
			 System.out.println("> After "+map.toString());
			// For each second times to get expected
			rawList.forEach(item -> {
				String note = item.getNote();
				if (note == null) { // Map does not put null note, add manual
					list.add(item);
				} else if (map.get(note)!=null){
					list.add(item);
				}
			});
		} catch (ConcurrentModificationException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<SubjectDTO> getStudy(int userId, int semester) {
		// Find list of subject with name, duration, skillplus
		List<Subject> rawList = sr.findStudy(userId, semester);
		List<SubjectDTO> list = new ArrayList<>();
		
		rawList.forEach(item -> {
			Score sc = scr.findBySub(userId, item.getId()); // Get score to get type, status
			SubjectDTO i = new SubjectDTO();
			i.setName(item.getName());
			i.setDuration(item.getDuration());
			if ("done".equals(sc.getStatus())) {
				i.setStatus("Đã xong");
			} else {
				i.setStatus("Đang học");
			}
			if ("1".equals(sc.getType())) {
				i.setType("Lý thuyết");
			} else if ("2".equals(sc.getType())) {
				i.setType("Thực hành");
			} else {
				i.setType("Bài tập lớn");
			}
			i.setNote(sc.getNote());
			
			list.add(i);
		});
		
		return list;
	}

	@Override
	public List<SubjectDTO> getExpected(int userId) {
		List<Subject> rawList = checkExpected(sr.findNotStudy(userId));
		List<SubjectDTO> list = new ArrayList<>();
		
		// Handle the most suitable in a group, map for separate group, then add the most suitable
		Map<String, List<Subject>> smap = new HashMap<>(); //Save each group of subject
		
		rawList.forEach(item -> {
			String note = item.getNote();
			if (note != null) {
				smap.computeIfAbsent(note, k -> new ArrayList<>()).add(item);
			}
		});
		smap.values().forEach(subjectList -> {
			Subject item = chooseBest(subjectList, userId);
			subjectList.remove(item);
			
			SubjectDTO i = new SubjectDTO();
			
			i.setName(item.getName());
			i.setDuration(item.getDuration());
			i.setStatus("Dự kiến (Phù hợp)");
			//i.setType();
			i.setNote(item.getNote());
			
			list.add(i);
		});

		rawList.forEach(item -> {
			if (item.getNote() == null) {
			SubjectDTO i = new SubjectDTO();
			
			i.setName(item.getName());
			i.setDuration(item.getDuration());
			i.setStatus("Dự kiến");
			//i.setType();
			//i.setNote(item.getNote());
			
			list.add(i);
			}
		});
		
		return list;
	}
}
