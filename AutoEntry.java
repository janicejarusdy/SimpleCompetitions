import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * name: Janice Teguh
 * Student Id: 860294
 * username: jteguh
 *
 * Generates entry numbers automatically
 */
public class AutoEntry extends Entry {
    
    /**
     * create array of 7 numbers automatically
     * @param seed = competition ID, entry ID, or random number
     * @return array of 7 numbers
     */
    public int[] createNumbers (int seed) {
        ArrayList<Integer> validList = new ArrayList<Integer>();
	int[] tempNumbers = new int[7];
        for (int i = 1; i <= 35; i++) {
    	    validList.add(i);
        }
        Collections.shuffle(validList, new Random(seed));
        for (int i = 0; i < 7; i++) {
    	    tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }
    
    
    /**
     * sets entry numbers of an automatically created entry
     * @param seed = competition ID, entry ID, or random number
     */
    public void setEntryNumbers(int seed)
    {
        entryNumbers = createNumbers(seed);
    }
    
    
    /**
     * 
     * @return string of entry numbers for an automatic entry
     */
    public String printEntryNumbers()
    {
        int [] numberArray = this.entryNumbers;
        String printedArray = String.format("%2d %2d %2d %2d %2d %2d %2d [Auto]",
                numberArray[0], numberArray[1], numberArray[2], numberArray[3],
                numberArray[4], numberArray[5], numberArray[6]);
        return printedArray;
    }
}
