import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


/**
 * name: Janice Teguh
 * Student Id: 860294
 * username: jteguh
 *
 * Stores attributes of every competition being held in SimpleCompetitions
 */
public class Competition {
    private String competitionName; //competition name
    private int competitionId; //competition Identifier
    private String competitionStatus; //competition status
    
    private ArrayList<Entry> entryList = new ArrayList<Entry>(); // stores all entries of one
                                                                 // competition
    private final int INPUT_LENGTH = 7; // required entry number length
    private final int SIX_DIGIT = 6; // required member and bill ID length
    private String memberId; // member ID
    private int entryId; // entry ID
    
    private Random randObj = new Random();
    private int numberOfEntries = 0; // total number of entries in one competition
    private int numberOfWinningEntries = 0; // total number of winning entries in one competition
    private int totalPrize = 0; // total prize awarded in one competition

    
    /**
     * allows checking on a competition status
     * @return status = "active" or "completed"
     */
    public String getCompetitionStatus()
    {
        return this.competitionStatus;
    }
    
    
    /**
     * sets competition ID of a competition
     * @param newCompetitionId = competition ID number
     */
    public void setId(int newCompetitionId)
    {
        this.competitionId = newCompetitionId;
    }
    
    
    /**
     * sets competition name of a competition
     * @param newName = competition name
     */
    public void setName(String newName)
    {
        this.competitionName = newName;
    }
    
    
    /**
     * 
     * @return competition ID number
     */
    public int getCompetitionId()
    {
        return this.competitionId;
    }
    
    
    /**
     * 
     * @return competition name
     */
    public String getName()
    {
        return this.competitionName;
    }
    
    
    /**
     * 
     * @return total number of entries
     */
    public int getNumberOfEntries()
    {
        return entryList.size();
    }
    
    
    /**
     * sets competition status
     * @param status = "active" or "completed"
     */
    public void setStatus(String status)
    {
       this.competitionStatus = status;
    }
    
    /**
     * 
     * @return competition status = "active" or "completed"
     */
    public String getStatus()
    {
        return this.competitionStatus;
    }
    
    
    /**
     * 
     * @return last entry ID created when there is a new lucky draw/addition of entries
     */
    public int getCurrentEntryId()
    {
        return entryId;
    }
    
    
    /**
     * Add entries to current competition
     * @param keyboard = scanner object
     * @param mode = testing or normal
     * @param currentEntryId = last entry ID from previous competition/entry addition
     */
    public void addEntries(Scanner keyboard, String mode, int currentEntryId) 
    {
        entryId = currentEntryId;
        
        addNewEntryLoop:
        while(true)
        {
            // stores unprinted entries
            ArrayList<Entry> recentlyAdded = new ArrayList<Entry>();
            
            memberIdLoop:
            while(true)
            {
                System.out.println("Member ID: ");
                memberId = keyboard.nextLine();
                boolean IdIsValid = checkId(memberId, "member");    
                if (IdIsValid)
                {
                    break memberIdLoop;
                }
            }
            
            billIdLoop:
            while(true)
            {
                System.out.println("Bill ID: "); 
                String billId = keyboard.nextLine();
                boolean IdisValid = checkId(billId, "bill");
                if (IdisValid)
                {
                    break billIdLoop;
                }  
            }
            
            billAmountLoop:
            while(true)
            {
                System.out.println("Total amount: ");
                double billAmount = Double.parseDouble(keyboard.nextLine());
                
                if(billAmount < 50)
                {
                    System.out.println("This bill is not eligible for an entry. "
                            + "The total amount is smaller than $50.0");
                    break billAmountLoop;
                }
                else
                {
                    int totalEntryCount = (int) Math.floor(billAmount) / 50;
                    System.out.printf("This bill is eligible for %d entries. "
                            + "How many manual entries did the customer "
                            + "fill up?: %n", totalEntryCount);
                    
                    int remainingManualEntry = Integer.parseInt(keyboard.nextLine());
                    int entryFilledUp = 0; // counts how many entries have been filled
                    
                    addManualEntryLoop:
                    while(true)
                    {
                        if(remainingManualEntry == 0)
                        {
                            break addManualEntryLoop;
                        }
                        
                        
                        Entry newManualEntry = new Entry();
                        System.out.println("Please enter 7 different numbers "
                                + "(from the range 1 to 35) separated by whitespace.");
                        
                        String manualEntry = keyboard.nextLine();
                        boolean entryIsValid = checkManualEntry(manualEntry);
                        
                        if(entryIsValid)
                        {
                            entryId++;
                            newManualEntry.setMemberId(memberId);
                            newManualEntry.setEntryId(entryId);
                            newManualEntry.setEntryNumbers(manualEntry);
                            entryList.add(newManualEntry);
                            recentlyAdded.add(newManualEntry);
                            entryFilledUp++;
                            remainingManualEntry--;  
                        }  
                    }
                    
                   addAutoEntryLoop:
                   while(true)
                   {
                       if(entryFilledUp == totalEntryCount)
                       {
                           break addAutoEntryLoop;
                       }
                       
                       int seed = 0; // initialize seed
                       switch(mode)
                       {
                           case("T"):   //testing mode
                               seed = entryList.size();
                               break;   
                           case("N"):   //normal mode
                               seed = randObj.nextInt(Integer.MAX_VALUE);
                           default:
                               break;
                       }
                       
                       AutoEntry newAutoEntry = new AutoEntry();
                       entryId++;
                       newAutoEntry.setEntryId(entryId);
                       newAutoEntry.setMemberId(memberId);
                       newAutoEntry.setEntryNumbers(seed);
                       entryList.add(newAutoEntry);
                       recentlyAdded.add(newAutoEntry);
                       entryFilledUp++; 
                   }
                   
                   System.out.println("The following entries have been added:");
                   
                   int printedEntry = 0;
                   while(true)
                   {
                       printedEntry++;
                       
                       System.out.printf("Entry ID: %-6d Numbers: %s%n", 
                               recentlyAdded.get(printedEntry - 1).getEntryId(), 
                               recentlyAdded.get(printedEntry - 1).printEntryNumbers());
                       
                       if(printedEntry == totalEntryCount)
                       {
                           break billAmountLoop;
                       }
                   }
                }    
            }
            
            addMoreEntriesLoop:
            while(true)
            {
                System.out.println("Add more entries (Y/N)?");
                String input = keyboard.nextLine().toUpperCase();
                        
                switch(input)
                {
                    case("Y"):  // continue adding entries
                        break addMoreEntriesLoop;
                    case("N"):  // stop adding entries
                        break addNewEntryLoop;
                    default:
                        System.out.println("Unsupported option. Please try again!");
                        break;
                }  
            } 
        }
        numberOfEntries = entryList.size();    
    }
            

    /**
     * To draw winners from an active competition
     * @param mode = "testing" or "normal"
     * @param currentEntryId = last entry ID from previous competition/addition of entries
     */
    public void drawWinners(String mode, int currentEntryId) 
    { 
        entryId = currentEntryId;
        
        System.out.printf("Lucky entry for Competition ID: %d,"
                + " Competition Name: %s%n", this.competitionId, this.competitionName);
        
        AutoEntry luckyEntry = new AutoEntry(); // generate lucky entry number
        
        switch(mode)
        {
            case("T"):  // testing mode
                luckyEntry.setEntryNumbers(this.getCompetitionId());
                break;
            case("N"):  // normal mode
                int seed = randObj.nextInt(Integer.MAX_VALUE); 
                luckyEntry.setEntryNumbers(seed);
                break;
            default:
                break;        
        }
        
        System.out.printf("Numbers: %s%n", luckyEntry.printEntryNumbers());
        entryId++;
        
        // store winning entries
        ArrayList<Entry> winningEntries = new ArrayList<Entry>();

       // filter entry list for winning entries
        for(Entry entry: entryList)
        {
            int match = 0;
            
            //counts number of matches an entry has with the lucky entry
            for(int luckyNumber: luckyEntry.entryNumbers)
            {
                for(int number: entry.entryNumbers)
                {
                  if(luckyNumber == number)
                  {
                      match += 1;
                  }
                }  
            } 
            entry.setMatchCount(match);
           
            // put entry in winningEntries array, prevent duplicates of entries and member ID's
            if (entry.getMatchCount() >= 2)
            {
                if(!winningEntries.contains(entry))
                {
                    if (containsMemberId(winningEntries, entry.getMemberId()))
                    {
                        int index = findIndex(winningEntries, entry.getMemberId());
                       
                        if(winningEntries.get(index).getMatchCount() < entry.getMatchCount())
                        {
                            winningEntries.set(index, entry);
                        }
                       
                        else if(winningEntries.get(index).getMatchCount() == entry.getMatchCount()
                                && winningEntries.get(index).getEntryId() > 
                               entry.getEntryId())
                        {
                            winningEntries.set(index, entry);
                        }
                    }
                    else
                    {
                        winningEntries.add(entry);
                    }
                }
            }  
        }
       
        // sort array of winning entries based on entry ID
        Collections.sort(winningEntries, new EntryComparator());
        
        // print winning entries
        System.out.println("Winning entries:");
        for(Entry winningEntry: winningEntries)
        {
            switch(winningEntry.getMatchCount())
            {
                case(2):
                    winningEntry.setPrize(50);
                    break;
                case(3):
                    winningEntry.setPrize(100);
                    break;
                case(4):
                    winningEntry.setPrize(500);
                    break;
                case(5):
                    winningEntry.setPrize(1000);
                    break;
                case(6):
                    winningEntry.setPrize(5000);
                    break;
                case(7):
                    winningEntry.setPrize(50000);
                    break;
                default:
                    break;
            }
            
            System.out.printf("Member ID: %s, Entry ID: %-6d, Prize: %-5d, "
                    + "Numbers: %s%n", winningEntry.getMemberId(), winningEntry.getEntryId(),
                    winningEntry.getPrize(), winningEntry.printEntryNumbers());
            
            numberOfWinningEntries++;
            totalPrize += winningEntry.getPrize();
        }
        entryList.clear();   
    }

    
    /**
     * Prints a report for an individual competition in SimpleCompetitions
     */
    public void report() 
    {
        System.out.println();
        switch(competitionStatus)
        {
            case("active"):
                System.out.printf("Competition ID: %d, name: %s, active: yes%n"
                        + "Number of entries: %d%n", competitionId, competitionName, 
                        numberOfEntries);
                break;
            case("completed"):
                System.out.printf("Competition ID: %d, name: %s, active: no%n"
                        + "Number of entries: %d%n"
                        + "Number of winning entries: %d%n"
                        + "Total awarded prizes: %d%n", competitionId, competitionName, 
                        numberOfEntries, numberOfWinningEntries, totalPrize);
                break;
            default:
                break;
        }  
    }
    
    
    /**
     * checks if in a winningEntries array there is an entry with a certain member ID
     * @param array = winningEntries
     * @param newMemberId = member ID of interest
     * @return true if winningEntries contains an entry with the same member ID
     */
    private boolean containsMemberId(ArrayList<Entry> array, String newMemberId)
    {
        boolean yesItContains = false;
        for(Entry entry: array)
        {
           if (entry.getMemberId() == newMemberId)
           {
               yesItContains = true;
               break;
           }
        }
       return yesItContains; 
    }
    
    
    /**
     * To find the index of an entry that contains a certain member ID in winningEntries array
     * @param array = winningEntries
     * @param newMemberId = member ID of interest
     * @return index of entry
     */
    private int findIndex(ArrayList<Entry> array, String newMemberId)
    { 
        int index = 0;
        for(int i = 0; i < array.size(); i++)
        {
            if (array.get(i).getMemberId() == newMemberId)
            {
                index = i;
                break;
            }
        } 
        return index;
    }
    
    
    /**
     * Checks if an ID is valid
     * @param IdNumbers = input string for member and bill ID
     * @param type = "bill" or "member"
     * @return true if ID is valid
     */
    private boolean checkId(String IdNumbers, String type) 
    {
        boolean validId = true;
        char[] IdCharList = IdNumbers.toCharArray();
        for(int i = 0; i < IdNumbers.length(); i++)
        {
            if (IdCharList.length != SIX_DIGIT || 
                    !Character.isDigit(IdCharList[i]))
            {
                System.out.printf("Invalid %s id! It must be a 6-digit "
                        + "number. Please try again.%n", type);
                validId = false;
                break;
            }
        }
        return validId; 
    }
    
    
    /**
     * Check if manual entry numbers are valid
     * @param manualEntry = input numbers
     * @return true if manual entry is valid
     */
    private boolean checkManualEntry(String manualEntry)
    {
        boolean validEntry = true;
        String[] entryNumbers = manualEntry.split(" ");
        ArrayList<Integer> previousNumbers = new ArrayList<Integer>(); //stores numbers that have 
                                                                       // been checked in the array
        
        for(int i = 0; i < entryNumbers.length; i++)
        {
            int inputNumber = Integer.parseInt((String) Array.get(entryNumbers, i));
            if (entryNumbers.length < INPUT_LENGTH)
            {
                System.out.println("Invalid input! Fewer than 7 numbers are provided. " 
                            + "Please try again!");
                validEntry = false;
                break;
            }
            else if (entryNumbers.length > INPUT_LENGTH)
            {
                System.out.println("Invalid input! More than 7 numbers are provided."
                        + " Please try again!");
                validEntry = false;
                break;
            }
            else if (previousNumbers.contains(inputNumber))
            {
                System.out.println("Invalid input! All numbers must be different!");
                validEntry = false;
                break;
            }
            
            previousNumbers.add(inputNumber);  
        }
        return validEntry;
    }
}
