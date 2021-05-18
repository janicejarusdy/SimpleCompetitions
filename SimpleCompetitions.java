import java.util.ArrayList;
import java.util.Scanner;

/**
 * name: Janice Teguh
 * Student Id: 860294
 * username: jteguh
 *
 * SimpleCompetitions is a program to keep track of the progress of several
 * "lucky-draw" type of competitions, but currently it cannot support concurrent competitions.
 */

public class SimpleCompetitions 
{   
    // array to store all competitions in SimpleCompetitions
    private static ArrayList<Competition> competitionList = new ArrayList<Competition>();
     
    
    /**
     * To create a new competition in SimpleCompetitions
     * @return a new Competition object
     */
    public Competition addNewCompetition() 
    {
        Competition newCompetition = new Competition();
        
        return newCompetition;
    }
    
    
    /**
     *  Prints a summary report of all the competitions in SimpleCompetitions
     */
    public void report() 
    {
        int completed = 0;
        int active = 0;
        for (Competition competition: competitionList)
        {
            switch(competition.getStatus())
            {
                case("completed"):
                    completed++;
                    break;
                case("active"):
                    active++;
                    break;
                default:
                    break;
            }
        }
        System.out.println("----SUMMARY REPORT----");
        System.out.printf("+Number of completed competitions: %d%n"
                + "+Number of active competitions: %d%n", completed, active);
        
        for (Competition competition: competitionList)
        {
            competition.report();
        }
    }

    /**
    * Main program that uses the main SimpleCompetitions class
    * @param args main program arguments
    */
    public static void main(String[] args) 
    {
        int currentEntryId = 0; // to keep track of entry ID's while program is running
        Scanner keyboard = new Scanner(System.in);
        SimpleCompetitions sc = new SimpleCompetitions();
        Competition activeCompetition = null; // initialize current competition
        
        System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");
        
        String mode;
        firstLoop:
        while(true)
        { 
            System.out.println("Which mode would you like to run? (Type T for Testing, and N for "
                    + "Normal mode):");
            mode = keyboard.nextLine().toUpperCase();
            
            switch(mode)
            {
                case("T"):   // testing mode
                    break firstLoop;
                case("N"):   // normal mode
                    break firstLoop;
                default:
                    System.out.println("Invalid mode! Please choose again.");
                    break;  
            }       
        }
        
        int competitionId = 1; // initialize competition ID
        while(true)
        {
            System.out.printf("Please select an option. Type 5 to exit.%n"
                    + "1. Create a new competition%n"
                    + "2. Add new entries%n"
                    + "3. Draw winners%n"
                    + "4. Get a summary report%n"
                    + "5. Exit%n");
            
            String option = keyboard.nextLine();
            switch(option) // user input when choosing a menu option
            {
                // Create a new competition
                case("1"):
                    if (sc.competitionIsActive())
                    {
                        System.out.println("There is an active competition. "
                                + "SimpleCompetitions does not support concurrent competitions!");
                    }
                    else
                    {
                        activeCompetition = sc.addNewCompetition();
                        activeCompetition.setId(competitionId);
                        
                        System.out.println("Competition name: ");
                        String competitionName = keyboard.nextLine();
                        activeCompetition.setName(competitionName);
                        activeCompetition.setStatus("active");
                        
                        System.out.printf("A new competition has been created!%n"
                                + "Competition ID: %d, Competition Name: %s%n", competitionId,
                                competitionName);
                        
                        competitionList.add(activeCompetition);
                        competitionId++;  
                    }
                    break;
                
                // Add new entries to active competition
                case("2"):
                    if (!sc.competitionIsActive())
                    {
                        System.out.println("There is no active competition. Please create one!");  
                    }
                    
                    else 
                    {
                        activeCompetition.addEntries(keyboard, mode, currentEntryId);
                        
                        //keep track of the last entry ID from this addition of entries
                        currentEntryId = activeCompetition.getCurrentEntryId();
                    }
                    break;
                    
                // Draw winners from current competition    
                case("3"):
                    if (!sc.competitionIsActive())
                    {
                        System.out.println("There is no active competition. Please create one!");
                    }
                    else if(activeCompetition.getNumberOfEntries() == 0)
                    {
                        System.out.println("The current competition has no entries yet!");
                    }
                    else 
                    {
                       activeCompetition.drawWinners(mode, currentEntryId);
                       
                       // keep track of entry ID of lucky entry
                       currentEntryId = activeCompetition.getCurrentEntryId();
                       
                       activeCompetition.setStatus("completed");
                    }
                    break;
                    
                // print summary report    
                case("4"):
                    if (competitionList.size() == 0)
                    {
                        System.out.println("No competition has been created yet!");
                    }
                    else
                    {
                        sc.report();
                    }
                    break;
                    
                //exit program
                case("5"):
                    System.out.println("Goodbye!");
                    keyboard.close();
                    System.exit(0);
                    break;
                       
                default:
                    System.out.println("Unsupported option. Please try again!"); 
                    break;   
            }   
        }   
    }
    
    /**
    * To check if there is an active competition
    * @return true if there is an active competition
    */
    private boolean competitionIsActive()
    {
        boolean isActive = false;
        
        if (competitionList.size() != 0)
        {
            for(Competition competition: competitionList)
            {
               if(competition.getStatus().equals("active"))
               {
                   isActive = true;
                   break;
               }
            }
        }
        return isActive;
    }
}
