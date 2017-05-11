class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie newMovie, int newDaysRented) {
        movie = newMovie;
        daysRented = newDaysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    double getRentalPrice() {
        double price = 0;
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                price += 2;
                if (getDaysRented() > 2)
                    price += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                price += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                price += 1.5;
                if (getDaysRented() > 3)
                    price += (getDaysRented() - 3) * 1.5;
                break;
        }
        return price;
    }

    int getFrequentRenterPoints() {
        int points = 1;
        if ((movie.getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) {
            points++;
        }
        return points;
    }
}