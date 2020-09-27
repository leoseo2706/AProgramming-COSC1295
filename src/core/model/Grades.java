package core.model;

public enum Grades {
	
		HD(4),DI(3),CR(2),PA(1),NN(0);
		private int grd;
		private Grades(int grd) {
		this.grd =grd;	
		}
		public int getGrd() {
			return grd;
		}
	}

