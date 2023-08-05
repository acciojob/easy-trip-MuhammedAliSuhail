package com.driver.controllers;

import com.driver.model.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
@Service
public class Services {

    Repositorys repo=new Repositorys();
    public void addAirport(Airport a){
        if(repo.AirportsDB.containsKey(a.getAirportName())) {
            return;
        }

        repo.AirportsDB.put(a.getAirportName(),a);
        return ;

    }
    public String getLargestAirportName(){
        ArrayList<String> arr=new ArrayList<>();
        int max=0;
        for(String s:repo.AirportsDB.keySet()){
            if(max<repo.AirportsDB.get(s).getNoOfTerminals()){
                max=repo.AirportsDB.get(s).getNoOfTerminals();
            }
        }
        for(String s:repo.AirportsDB.keySet()){
            if(max==repo.AirportsDB.get(s).getNoOfTerminals()){
                arr.add(s);
            }
        }

        Collections.sort(arr);

        return arr.get(0);
    }

    public String addflight(Flight f){

        if(!repo.FlightDB.containsKey(f.getFlightDate())){
            List<Flight> flight=new ArrayList<>();
            flight.add(f);
            Date d=f.getFlightDate();
            repo.FlightDB.put(d,flight);
        }
        List<Flight> arr=repo.FlightDB.get(f.getFlightDate());
        arr.add(f);
        repo.FlightDB.put(f.getFlightDate(),arr);

return "SUCCESS";
    }


    public double getShortestDurationOfPossibleBetweenTwoCities(City from,City to){
       double duration=Double.MAX_VALUE;
        for(Date id:repo.FlightDB.keySet()) {
            for (Flight f : repo.FlightDB.get(id)) {

                City From = f.getFromCity();
            City To = f.getToCity();
            double Duration = f.getDuration();
            if (From == from && To == to && duration > Duration) {
                duration = Duration;
            }
        }
        }

        return duration;
    }

    public void addPassanger(Passenger p){
        repo.PassangerDB.put(p.getPassengerId(), p);
    }

    public String BookTickate(int flightId,int passangerId){
for(int n:repo.bookingDB.keySet()){
     Booking b =repo.bookingDB.get(n);
     if(b.getFlightId()==flightId){
         if(b.getPassangerId()==passangerId){
             return "FAILURE";
         }
     }
}
for(Date d:repo.FlightDB.keySet()){
    for(Flight f:repo.FlightDB.get(d)){
        if(flightId==f.getFlightId()){
            if(f.getNumberOfbookings()==f.getMaxCapacity()){
                return "FAILURE";
            }else{
                f.setNumberOfbookings(f.getNumberOfbookings()+1);
                Booking book=new Booking(repo.bookingId,passangerId,flightId);
                repo.bookingDB.put(repo.bookingId,book);
                repo.setBookingId(repo.getBookingId()+1);
            }
        }
    }
}
return "SUCCESS";
    }


    public String CancelTicket(int flightId,int passangerId){

        Iterator<Integer> iterator1= repo.bookingDB.keySet().iterator();
        while(iterator1.hasNext()){
            Integer n= iterator1.next();
            Booking b=repo.bookingDB.get(n);
            if(b.getPassangerId()==passangerId && flightId==b.getFlightId()){
                iterator1.remove();
                for(Date d:repo.FlightDB.keySet()){
                    for(Flight f:repo.FlightDB.get(d)){
                        if(f.getFlightId()==flightId){
                            f.setNumberOfbookings(f.getNumberOfbookings()-1);
                        }
                    }
                }
                return "SUCCESS";
            }
        }
       return "FAILURE";
    }

    public int calculateFlightFare(Integer flightId){
for(Date d:repo.FlightDB.keySet()){
    for(Flight f:repo.FlightDB.get(d)){
        if(f.getFlightId()==flightId){
            return 3000+f.getNumberOfbookings()*50;
        }
    }
}
return 0;
    }

    public  int getNumberOfPeopleOn(Date date,String airportName){
        City city=repo.AirportsDB.get(airportName).getCity();

        int countOfpeople=0;
        for(Flight f:repo.FlightDB.get(date)){
            if(f.getFromCity()==city || f.getToCity()==city){
                countOfpeople+=f.getNumberOfbookings();
            }
        }
        return countOfpeople;
    }


    public int countOfBookingsDoneByPassengerAllCombined(Integer passangerId){
        int count=0;
        for(int b:repo.bookingDB.keySet()){
            Booking B=repo.bookingDB.get(b);
            if(B.getPassangerId()==passangerId){
                count++;
            }
        }
        return count;
    }
    public String getAirportNameFromFlightId(Integer flightid){
        for(Date d:repo.FlightDB.keySet()){
            for(Flight f:repo.FlightDB.get(d)){
                if(f.getFlightId()==flightid){
                    City city=f.getFromCity();
                    for(String airportName:repo.AirportsDB.keySet()){
                        Airport a=repo.AirportsDB.get(airportName);
                        if(a.getCity()==city){
                            return airportName;
                        }
                    }
                }
            }
        }
        return "";
    }

public int  calculateRevenueOfAFlight(int flightId){
        int revanu=0;
        for(Date d:repo.FlightDB.keySet()){
            for(Flight f:repo.FlightDB.get(d)){
                if(f.getFlightId()==flightId){
                    int n=f.getNumberOfbookings();
                    for(int i=0;i<n;i++){
                        revanu+=3000+i*50;
                    }
                    return revanu;
                }
            }
        }
        return 0;
}
}
