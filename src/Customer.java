
import java.lang.*;
import java.util.*;

class Customer {
    private String name;
    private Vector rentals = new Vector();

    public Customer (String newName){
        name = newName;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName (){
        return name;
    }

    public String statement() {
        double totalPrice = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();	    
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            double thisPrice = each.getRentalPrice();
            //add frequent renter points
            frequentRenterPoints += each.getFrequentRenterPoints();
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + "\t" + each.getDaysRented() + "\t" + String.valueOf(thisPrice) + "\n";
            totalPrice += thisPrice;
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

}
    