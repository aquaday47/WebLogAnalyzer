
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
        
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String s : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
            
            ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le: records){
             
             String ip = le.getIpAddress();
             if (!uniqueIps.contains(ip)){
                uniqueIps.add(ip);
                
                }
            }
            return uniqueIps.size();
        }
        public HashMap<String, Integer> countVisitsPerIP(){
            HashMap<String, Integer> counts = new HashMap<String, Integer>();
            for (LogEntry le : records){
                String ip = le.getIpAddress();
                if (!counts.containsKey(ip)){
                    counts.put(ip, 1);
                }
                else {
                    counts.put(ip, counts.get(ip)+1);
                }
            }
            return counts;
        }
        public int mostNumberVisitsByIP(HashMap<String,Integer> hm){
            int  highCount = 0;
            for (String str: hm.keySet()){
                int currCount = hm.get(str);
                if (currCount>highCount){
                    highCount = currCount;
                }
            } 
            return highCount;
        }
        public HashMap<String,ArrayList<String>> iPsForDays(){
            HashMap<String,ArrayList<String>> iPsOnDay = new HashMap<String,ArrayList<String>>();
            
            for (LogEntry le : records){
               ArrayList<String> iPsList = new ArrayList<String>();
                Date date = le.getAccessTime();
                String dS = date.toString().substring(4,10);
                String ip = le.getIpAddress();
                
                if (!iPsOnDay.containsKey(dS)){
                    iPsList.add(ip);
                    iPsOnDay.put(dS,iPsList);
                }
                else {
                    ArrayList<String> tempIpList = iPsOnDay.get(dS);
                    tempIpList.add(ip);
                    iPsOnDay.put(dS, tempIpList);
                }
                               
            }
            return iPsOnDay;
        }
        public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> hm){
            int mostVisits = 0;
            String dayOfMost = "";
            for  (String str : hm.keySet()){
                int currVisits = hm.get(str).size();
                if(currVisits>mostVisits){
                mostVisits = currVisits;
                dayOfMost = str;
                }
            }
            return dayOfMost;
        }
        public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> hm, String day){
           //take as input result of iPsForDays
           // in that file, string is day, arrayList is ips on that day
           // get ArrayList
           ArrayList<String> ipListForDay = hm.get(day);
           
           HashMap<String,Integer> visitsByIp = new HashMap<String,Integer>();
           for (String str: ipListForDay){
               if (!visitsByIp.containsKey(str)){
                   visitsByIp.put(str,1);
                }
               else {
                   visitsByIp.put(str, visitsByIp.get(str)+1);
                }
            }
           
           ArrayList<String> ipMostVisits = iPsMostVisits(visitsByIp); 
           return ipMostVisits;
           
        }
        public ArrayList<String> iPsMostVisits(HashMap<String,Integer> hm){
            ArrayList<String> ipMostVisits = new ArrayList<String>();
            int mostVisits = mostNumberVisitsByIP(hm);
            for (String str : hm.keySet()){
            if (mostVisits == hm.get(str)){
                ipMostVisits.add(str);
            }
        }
        return ipMostVisits;
        }
        public void  printAllHigherThanNum(int num){
            for (LogEntry le : records){
                int statCode = le.getStatusCode();
                if (statCode>num){
                System.out.println(le);
                }
            }
        }
        public ArrayList<String> uniqueIPVisitsOnDay(String someday){
            ArrayList<String> uniqOnDay = new ArrayList<String>();
            for (LogEntry le :  records){
                Date date = le.getAccessTime();
                String dateS = date.toString();
                if (dateS.contains(someday)&& !uniqOnDay.contains(le.getIpAddress())){
                    uniqOnDay.add(le.getIpAddress());
                }
            }
            return uniqOnDay;
        }
        public int countUniqueIPsInRange(int low, int high){
            
            ArrayList<String> uniqIPs = new ArrayList<String>();
            ArrayList<Integer> uniqCount = new ArrayList<Integer>();
            for (LogEntry le : records){
                int statCode = le.getStatusCode();
                String ipAdd = le.getIpAddress();
                
                if (statCode >= low && statCode <= high && !uniqIPs.contains(ipAdd)){
                    if (!uniqCount.contains(statCode)){uniqCount.add(statCode);}
                uniqIPs.add(ipAdd);
                }
            }
            System.out.println(uniqCount.size());
            return uniqIPs.size();
        }
}
