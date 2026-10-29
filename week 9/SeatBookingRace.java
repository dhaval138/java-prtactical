class SeatBooking {
    int seatsLeft = 5;

    boolean book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }

            seatsLeft--;
            return true;
        }
        return false;
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws Exception {
        SeatBooking booking = new SeatBooking();
        Thread[] threads = new Thread[10];

        System.out.println("25AIML039");
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                if (booking.book()) {
                    System.out.println("Seat booked");
                } else {
                    System.out.println("No seat");
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Seats left: " + booking.seatsLeft);
    }
}