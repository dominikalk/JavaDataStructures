import java.util.HashMap;
import java.util.Map;

public class AlgorithmTimer {
    public static long startTime;
    // Map : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html
    public static Map<String, Boolean> checks = new HashMap<>();
    public static int mergeInverstions = 0;

    /**
     * Function that resets the start time
     * @return start time
     */
    public static long resetStartTime() {
        // Nanotime : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/System.html#nanoTime()
        startTime = System.nanoTime();
        return startTime;
    }

    /**
     * Function to print the time between now and the global variable startTime
     * @param prefix The text to be written before the time value
     */
    public static void printTime(String prefix) {
        System.out.println(prefix + " : " + (System.nanoTime() - startTime) + "ns");
    }
}
