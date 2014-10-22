package Hotel;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by OriginalEvil on 23.10.2014.
 */
public class Hotel implements RoomsAndGuests {

    private int RoomCount;

    private List<Accommodation> waitroom = new LinkedList<Accommodation>();

    private int NumberOfPeople;
    private List<String> listOfPeople = new LinkedList<String>();


    public synchronized boolean add(List<String> surname, long time, Hotel hotel, String typeOfRoom){
        if (NumberOfPeople + surname.size() > MAX_NUMBER_OF_PEOPLE){
            SortOnWait(surname, time, hotel, typeOfRoom);
            for(String x: surname){
                System.out.println(x);
            }
            System.out.println("wait");
            System.out.println("<--------->");
            return false;
        }
        if (addROOM(surname, time, hotel, typeOfRoom)) return true;

        SortOnWait(surname, time, hotel, typeOfRoom);
        for(String x: surname){
            System.out.println(x);
        }
        System.out.println("wait");
        System.out.println("<--------->");
        return false;


    }

    public boolean addROOM(List<String> surname, long time, Hotel hotel, String typeOfRoom)
    {
        if( (RoomCount < ROOMS)){
            RoomCount++;
            NumberOfPeople += surname.size();
            for(String x: surname){
                listOfPeople.add(x);
                System.out.println(x);
            }
            System.out.println("Time " + time);
            System.out.println("Availability " + (MAX_NUMBER_OF_PEOPLE - NumberOfPeople));
            System.out.println("settled");
            System.out.println("<--------->");
            new Accommodation(surname, time, hotel, typeOfRoom).start();
            return true;
                }
        return false;
    }

    public void SortOnWait(List<String> surname, long time, Hotel hotel, String typeOfRoom)
    {
        if (typeOfRoom.equals(room)){
            waitroom.add(new Accommodation(surname, time, hotel, typeOfRoom));
        }
    }

    public synchronized void remove(List<String> surname, String typeOfRoom, long time){
        NumberOfPeople -= surname.size();
        for(String s: surname){
            listOfPeople.remove(s);
            System.out.println(s);
        }
        System.out.println("Time " + time);
        System.out.println("Availability " + (MAX_NUMBER_OF_PEOPLE - NumberOfPeople));
        System.out.println("evicted");
        System.out.println("<--------->");

    }
}
