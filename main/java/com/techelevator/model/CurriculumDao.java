package com.techelevator.model;

import java.util.List;

public interface CurriculumDao {
	
	Curriculum getCurriculumByTopic(String topic);
	
	List<Curriculum> getCurriculums();

}
