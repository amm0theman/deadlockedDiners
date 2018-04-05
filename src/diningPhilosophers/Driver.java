package diningPhilosophers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {

	public static void main(String[] args) {
		Table myTable = new Table();
		/* Get actual class name to be printed on */
		final Logger logger = LogManager.getLogger(Driver.class);
		logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
	}
}
