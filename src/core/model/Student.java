package core.model;

import java.util.List;

public class Student {

	private String id;
	private int gradeP;
	private int gradeN;
	private int gradeA;
	private int gradeW;
	private String personality;
	private String cStudent1;
	private String cStudent2;
	private List<StudentPreference> preferenceList;

	public Student(String id, String personality, String cStudent1, String cStudent2) {
		this.id = id;
		this.personality = personality;
		this.cStudent1 = cStudent1;
		this.cStudent2 = cStudent2;
	}

	public Student(String id, int gradeP, int gradeN, int gradeA, int gradeW) {
		this.id = id;
		this.gradeP = gradeP;
		this.gradeN = gradeN;
		this.gradeA = gradeA;
		this.gradeW = gradeW;
	}

	public Student(String id, int gradeP, int gradeN, int gradeA, int gradeW,
				   String personality, String cStudent1, String cStudent2,
				   List<StudentPreference> preferenceList) {
		this.id = id;
		this.gradeP = gradeP;
		this.gradeN = gradeN;
		this.gradeA = gradeA;
		this.gradeW = gradeW;
		this.personality = personality;
		this.cStudent1 = cStudent1;
		this.cStudent2 = cStudent2;
		this.preferenceList = preferenceList;
	}

	public Student(String id, int gradeP, int gradeN, int gradeA, int gradeW,
				   String personality, String cStudent1, String cStudent2) {
		this.id = id;
		this.gradeP = gradeP;
		this.gradeN = gradeN;
		this.gradeA = gradeA;
		this.gradeW = gradeW;
		this.personality = personality;
		this.cStudent1 = cStudent1;
		this.cStudent2 = cStudent2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGradeP() {
		return gradeP;
	}

	public void setGradeP(int gradeP) {
		this.gradeP = gradeP;
	}

	public int getGradeN() {
		return gradeN;
	}

	public void setGradeN(int gradeN) {
		this.gradeN = gradeN;
	}

	public int getGradeA() {
		return gradeA;
	}

	public void setGradeA(int gradeA) {
		this.gradeA = gradeA;
	}

	public int getGradeW() {
		return gradeW;
	}

	public void setGradeW(int gradeW) {
		this.gradeW = gradeW;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getcStudent1() {
		return cStudent1;
	}

	public void setcStudent1(String cStudent1) {
		this.cStudent1 = cStudent1;
	}

	public String getcStudent2() {
		return cStudent2;
	}

	public void setcStudent2(String cStudent2) {
		this.cStudent2 = cStudent2;
	}

	public List<StudentPreference> getPreferenceList() {
		return preferenceList;
	}

	public void setPreferenceList(List<StudentPreference> preferenceList) {
		this.preferenceList = preferenceList;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", gradeP=" + gradeP +
				", gradeN=" + gradeN +
				", gradeA=" + gradeA +
				", gradeW=" + gradeW +
				", personality='" + personality + '\'' +
				", cStudent1='" + cStudent1 + '\'' +
				", cStudent2='" + cStudent2 + '\'' +
				", preferenceList=" + preferenceList +
				'}';
	}
}