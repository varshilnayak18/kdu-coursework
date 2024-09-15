package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logging logger = new Logging();
        TicketReservation mmt = new TicketReservation();
        String ticketBooked = "Ticket booked: ";
        String ticketCancelled = "Ticket cancelled: ";
        String businessClass = "business";
        String economyClass = "economy";
        logger.logInfo(ticketBooked + mmt.bookFlight("Varshil","Nayak",21,"male",businessClass,"A1"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Jeel","Patel",21,"male",businessClass,"A2"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Bhavya","Patel",20,"male",businessClass,"A3"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Kush","Jadeja",22,"male",businessClass,"A4"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Virat","Kohli",35,"male",businessClass,"A5"));
        logger.logInfo(ticketBooked + mmt.bookFlight("MS","Dhoni",40,"male",businessClass,"A6"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Rahul","Suthar",21,"male",economyClass,"A7"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Priya","Shah",20,"female",economyClass,"A8"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Vandan","Rajput",21,"male",economyClass,"A9"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Rohit","Sharma",36,"male",economyClass,"A10"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Shivam","Doshi",21,"male",businessClass,"A11"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Kartik","Tyagi",21,"male",economyClass,"A12"));
        logger.logInfo(ticketBooked + mmt.bookFlight("Ankush","Nair",21,"male",businessClass,"A13"));

        logger.logInfo(ticketBooked + mmt.bookFlight("Brij","Joshi",21,"male",economyClass,"A14"));

        logger.logInfo(ticketCancelled + mmt.cancel("A12"));

        logger.logInfo(ticketBooked + mmt.bookFlight("Naman","Modi",21,"male",economyClass,"A15"));

        List<Passenger> cnf = mmt.getConfirmedList();
        logger.logInfo(ticketCancelled + mmt.cancel("A3"));
        logger.logInfo(ticketCancelled + mmt.cancel("A6"));
        logger.logInfo(ticketCancelled + mmt.cancel("A10"));
        logger.logInfo("Confirmation List:- ");
        for(Passenger p: cnf){
            logger.logInfo(p.getConfirmationNumber());
        }
    }
}