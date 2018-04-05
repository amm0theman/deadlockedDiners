package diningPhilosophers;

public class Table {
	private Philosopher[] manySocrates;
	private Fork[] manyForks;
	private Thread[] manyThreads;
	
	public Table() {
		manySocrates = new Philosopher[5];
		manyForks = new Fork[5];
		manyThreads = new Thread[5];
		
		for(int i = 0; i < manyForks.length; i++) {
			manyForks[i] = new Fork();
		}
		
		for(int i = 0; i < manySocrates.length; i++) {
			manySocrates[i] = new Philosopher(this, i);
		}
		
		for(int i = 0; i < manySocrates.length; i++) {
			manyThreads[i] = new Thread(manySocrates[i]);
		}
		
		for(int i = 0; i < manySocrates.length; i++) {
			manyThreads[i].start();
		}
	}
	
	public ForkForm getFork(int sitPosition) {
		//if not the last philosopher
		if(sitPosition < manyForks.length - 1) {
			//if lower numbered fork relative available grab it
			synchronized(manyForks[sitPosition]) {
				if(!manyForks[sitPosition].isInUse()) {
					manyForks[sitPosition].setInUse(true);
					return new ForkForm(0, manyForks[sitPosition]);
				}
			}
			//else grab higher number fork if available
			synchronized(manyForks[sitPosition+1]) {
				if(!manyForks[sitPosition+1].isInUse()) {
					manyForks[sitPosition+1].setInUse(true);
					return new ForkForm(1, manyForks[sitPosition+1]);
				}
			}
			//if niether available return null
			return null;
		}
		//last philosopher follows same algorithm but numbering is different so he's a special case
		else {
			//if lower numbered fork relative available grab it
			synchronized(manyForks[0]) {
				if(!manyForks[0].isInUse()) {
					manyForks[0].setInUse(true);
					return new ForkForm(1, manyForks[0]);
				}
			}
			//else grab higher number fork if available
			synchronized(manyForks[sitPosition]) {
				if(!manyForks[sitPosition].isInUse()) {
					manyForks[0].setInUse(true);
					return new ForkForm(0, manyForks[sitPosition]);
				}
			}
			//if niether available return null
			return null;
		}
		
	}

}
