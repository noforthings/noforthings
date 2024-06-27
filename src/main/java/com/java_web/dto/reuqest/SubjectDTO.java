package com.java_web.dto.reuqest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectDTO {
	String name;
	String duration;
	String type;
	String skillPlus;
	String status;
	String note;
	Integer group;
}
