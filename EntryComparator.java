import java.util.Comparator;


/**
 * name: Janice Teguh
 * Student Id: 860294
 * username: jteguh
 *
 * Creates a comparator for an Entry object i.e. in regards to their Entry ID in 
 * ascending order
 */
public class EntryComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry o1, Entry o2) {
        Integer entryId1 = o1.getEntryId();
        Integer entryId2 = o2.getEntryId();
        return entryId1.compareTo(entryId2);
    }
    
    

}
