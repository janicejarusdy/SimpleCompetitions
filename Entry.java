import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * name: Janice Teguh
 * Student Id: 860294
 * username: jteguh
 *
 * Stores attributes of an entry in a competition
 */
public class Entry {
    protected int entryId; // Entry Identifier
    protected int[] entryNumbers; // e.g [1 2 3 4 5 6 7]
    protected String memberId; // member ID of the entry
    protected int match; // number of matches with lucky entry numbers
    protected int prize; // prize won by an entry
    
    
    /**
     * 
     * @return entry ID
     */
    public int getEntryId()
    {
        return entryId;
    }
    
    
    /**
     * sets entry ID of an entry
     * @param newId = new ID number
     */
    public void setEntryId(int newId)
    {
        entryId = newId;
    } 
    
    /**
     * sets member ID of an entry
     * @param newMemberId
     */
    public void setMemberId(String newMemberId)
    {
        memberId = newMemberId;
    }
    
    
    /**
     * 
     * @return member ID of an entry
     */
    public String getMemberId()
    {
        return memberId;
    }
    
    
    /**
     * sets entry numbers of an manually created entry
     * @param newEntryNumbers
     */
    public void setEntryNumbers(String newEntryNumbers)
    {
            entryNumbers = new int[7];
            String [] inputNumbers = newEntryNumbers.split(" ");
            
            for(int i = 0; i < 7; i++)
            {
                int inputNumber = Integer.parseInt((String)Array.get(inputNumbers, i));
                Array.set( entryNumbers, i, inputNumber);
            }
            Arrays.sort(entryNumbers);
    }
    
    
    /**
     * 
     * @return string of entry numbers for a manual entry
     */
    public String printEntryNumbers()
    {
        int [] numberArray = entryNumbers;
        String printedArray = String.format("%2d %2d %2d %2d %2d %2d %2d",
                numberArray[0], numberArray[1], numberArray[2], numberArray[3],
                numberArray[4], numberArray[5], numberArray[6]);
        return printedArray;
    }
    
    
    /**
     * set the number of matches an entry has with a lucky entry
     * @param match = number of matches
     */
    public void setMatchCount(int matchCount)
    {
        match = matchCount;
    }
    
    
    /**
     * 
     * @return the number of matches an entry has with a lucky entry
     */
    public int getMatchCount()
    {
        return match;
    }
    
    
    /**
     * set the prize won by an entry
     * @param newPrize = division prize
     */
    public void setPrize(int newPrize)
    {
        prize = newPrize;
    }
    
    
    /**
     * 
     * @return prize won by entry
     */
    public int getPrize()
    {
        return prize;
    }
}
