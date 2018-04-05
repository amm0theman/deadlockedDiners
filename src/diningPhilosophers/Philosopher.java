package diningPhilosophers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Philosopher implements Runnable {
	private Fork leftFork = null;
	private Fork rightFork = null;
	private Table diningTable;
	private int sitPosition;
	final Logger logger = LogManager.getLogger(Driver.class);
	
	public Philosopher(Table _diningTable, int _sitPosition) {
		diningTable = _diningTable;
		sitPosition = _sitPosition;
	}
	
	public void getForks() {
		ForkForm theFork = diningTable.getFork(sitPosition);
		if (theFork != null) {
			if(theFork.getForkSide() == 0) {
				leftFork = theFork.getFork();
			}
			else {
				rightFork = theFork.getFork();
			}	
		}
	}
	
	public boolean haveForks() {
		if(leftFork != null && rightFork != null)
			return true;
		else
			return false;
	}
	
	public void eat() {
		System.out.println("Philosopher " + sitPosition + ": Eating");
		logger.debug("Philosopher " + sitPosition + ": Eating");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Philosopher " + sitPosition + ": Done Eating");
		logger.debug("Philosopher " + sitPosition + ": Done Eating");
	}
	
	public void dropForks() {
		System.out.println("Philosopher " + sitPosition + ": Dropping Forks");
		logger.debug("Philosopher " + sitPosition + ": Dropping Forks");
		synchronized(leftFork) {
			leftFork.setInUse(false);
			leftFork = null;
		}
		synchronized(rightFork) {
			rightFork.setInUse(false);
			rightFork = null;
		}
		System.out.println("Philosopher " + sitPosition + ": Forks Dropped");
		logger.debug("Philosopher " + sitPosition + ": Forks Dropped");
	}
	
	public void ponder() {
		System.out.println("Philosopher " + sitPosition + ": Pondering life");
		logger.debug("Philosopher " + sitPosition + ": Pondering life");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Philosopher " + sitPosition + ": Done pondering");
		logger.debug("Philosopher " + sitPosition + ": Done pondering");
	}
	
	@Override
	public void run() {
		System.out.println("Philosopher " + sitPosition + ": Started");
		logger.debug("Philosopher " + sitPosition + ": Started");
		while(true) {
			//if don't have forks get forks
			System.out.println("Philosopher " + sitPosition + ": Trying to get forks");
			logger.debug("Philosopher " + sitPosition + ": Trying to get forks");
			while(!haveForks()) {
				getForks();
			}
			System.out.println("Philosopher " + sitPosition + ": Got forks");
			logger.debug("Philosopher " + sitPosition + ": Got forks");
			//eat
			eat();
			//release forks
			dropForks();
			//Ponder life
			ponder();
		}
	}
	
}
