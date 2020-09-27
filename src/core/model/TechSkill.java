package core.model;

public enum TechSkill{
		PROGRAMMING("P"),NETWORKING("N"),ANALYTICS("A"),WEB("W");
		private String skill;
		private TechSkill(String skill) {
			this.skill=skill;
		}
		public String getskill() {
			return skill;
		}
}
