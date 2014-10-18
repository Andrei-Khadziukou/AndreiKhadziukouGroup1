import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Aliasksandr Shynkevich
 * Date: 10/17/14
 */
public class FixedMemoryEater {
    static final Logger logger = Logger.getLogger(FixedMemoryEater.class);

    static final int ACTUAL_ARRAY_NUMBER = 200;

    public static void main(String[] args) {
        List v = new ArrayList();
        while (v.size() < ACTUAL_ARRAY_NUMBER) {
            byte b[] = new byte[1048576];
            v.add(b);
            Runtime rt = Runtime.getRuntime();

            System.out.println("free memory: " + rt.freeMemory());
		    try {
		       Thread.sleep(250);
		    } catch (InterruptedException e) {
			   logger.info("Sleep has been interrupted");
            }
        }
		logger.info("Arrays have been created successfully...");
    }
}
