package com.pmu.course.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDTO {

	private String name;

	private Date date;

	private int dayUniqueNumber;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public int getDayUniqueNumber() {
		return this.dayUniqueNumber;
	}

	public void setDayUniqueNumber(int dayUniqueNumber) {
		this.dayUniqueNumber = dayUniqueNumber;
	}

	private final List<PartantDTO>  partants = new ArrayList<>();

	public List<PartantDTO> getPartants() {
		return this.partants;
	}
}
