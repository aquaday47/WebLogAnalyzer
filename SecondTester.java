import java.io.*;
import java.util.*;
/**
 * Write a description of SecondTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondTester {
    public void testCountsPerIP(){
        LogAnalyzer la = new  LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> countsPerIP = la.countVisitsPerIP();
        System.out.println(countsPerIP);
        int uniqueCount = la.countUniqueIPs();
        System.out.println("#4 unique IPs " + uniqueCount);
        ArrayList<String> uniqueIPvis = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("#5 size array list is " + uniqueIPvis.size());
        int count = la.countUniqueIPsInRange(400, 499);
        System.out.println("#6 First count is " + count);
        int numVisitsMax = la.mostNumberVisitsByIP(countsPerIP);
        System.out.println("number 7 answer " + numVisitsMax);
        ArrayList<String> ipsMost = la.iPsMostVisits(countsPerIP);
        System.out.println("#8 most frequent visitors: " + ipsMost);
        HashMap<String,ArrayList<String>> visitsByDay = la.iPsForDays();
        //System.out.println("visits by day " + visitsByDay);
        String dayOfMost = la.dayWithMostIPVisits(visitsByDay);
        System.out.println("#9 day of most visits " + dayOfMost);
        
        ArrayList<String> ipMostVisits = la.iPsWithMostVisitsOnDay(visitsByDay,"Sep 30");
        System.out.println("#10" + ipMostVisits);
        
    }
    
}
