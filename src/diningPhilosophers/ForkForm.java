package diningPhilosophers;

public class ForkForm {
	private int forkSide;
	private Fork fork;
	
	public ForkForm(int _forkSide, Fork _fork) {
		forkSide = _forkSide;
		fork = _fork;
	}
	
	public int getForkSide() {
		return forkSide;
	}
	public void setForkSide(int forkSide) {
		this.forkSide = forkSide;
	}
	public Fork getFork() {
		return fork;
	}
	public void setFork(Fork fork) {
		this.fork = fork;
	}
	
}
