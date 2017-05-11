import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dannynator on 10.05.17.
 */

public class TestResultString {
    static Customer c = new Customer("John Smith");

    static Movie m1 = new Movie("Cinderella", Movie.CHILDRENS);
    static Movie m2 = new Movie("Bad Cops", Movie.REGULAR);
    static Movie m3 = new Movie("Guardians of the Galaxy 2", Movie.NEW_RELEASE);
    static Movie[] movies = {m1, m2, m3};
    static int[] daysExpected = {2, 5, 11};
    static double[] priceExpected = {1.5, 6.5, 33.};
    static String[] resultLines = new String[movies.length+4];
    static Integer[] daysActual = new Integer[movies.length];
    static Double[] priceActual = new Double[movies.length];
    int expectedFRP = 4;

    @BeforeAll
    public static void getStrings() {

        Rental[] rentals = new Rental[movies.length];
        for (int i = 0; i < movies.length; i++ ) {
            rentals[i] = new Rental(movies[i], daysExpected[i]);
            c.addRental(rentals[i]);
        }

        String result = c.statement();
        System.out.println(result);

        // Get separate result lines
        resultLines = result.split("\n");

        // Get separate values for each movie
        for (int i=2; i<movies.length+2; i++) {
            int lastTabIndex = resultLines[i].lastIndexOf("\t");
            int middleTabIndex = resultLines[i].substring(0, lastTabIndex-1).lastIndexOf("\t");
            daysActual[i-2] = new Integer(resultLines[i].substring(middleTabIndex+1, lastTabIndex));
            priceActual[i-2] = new Double(resultLines[i].substring(lastTabIndex+1, resultLines[i].length()));
        }
    }

    // Test customer name

    @Test
    public void testCustomerName(){
        assertEquals(resultLines[0], "Rental Record for " + c.getName());
    }

    // Test movie names

    @Test
    public void testMovieTitles(){
        for (int i=0; i<movies.length; i++) {
            String line = resultLines[i+2];
            assertEquals("\t" + movies[i].getTitle(), line.substring(0, movies[i].getTitle().length()+1));
        }
    }

    @Test
    public void testRentDays(){
        for (int i=0; i<movies.length; i++) {
            assertEquals(daysExpected[i], daysActual[i].intValue());
        }
    }

    @Test
    public void testPrices(){
        for (int i=0; i<movies.length; i++) {
            assertEquals(priceExpected[i], priceActual[i].doubleValue());
        }
    }

    @Test
    public void testTotalPrice(){
        String line = resultLines[2+movies.length];
        Double actualTotal = new Double(line.substring(line.lastIndexOf(" "), line.length()));
        double expectedTotal = 0;
        for (int i=0; i<movies.length; i++) {
            expectedTotal += priceExpected[i];
        }
        assertEquals(expectedTotal, actualTotal.doubleValue());
    }

    @Test
    public void testPoints(){
        String line = resultLines[resultLines.length-1];
        Integer actualFRP = new Integer(line.substring(11, line.indexOf("f")-1));
        assertEquals(expectedFRP, actualFRP.intValue());
    }

}
