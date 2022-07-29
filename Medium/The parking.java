import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Solver solver = new Solver();
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            solver.addRow(ROW);
        }
        solver.solve();
    }
}

class Solver {
    private CarPark carPark;
    private List<String> input;

    public Solver() {
        carPark = new CarPark();
        input = new ArrayList<>();
    }

    public void addRow(String row) {
        input.add(row);
    }

    public void solve() {
        for (String s : input) {
            char c = s.charAt(6);
            String[] split = s.split(" " + c + " ");
            String time = split[0];
            String[] registrations = split[1].split(" ");
            if (c == '>') {
                for(String r : registrations) {
                    carPark.addVehicle(r, CarPark.getType(r), time);
                }
            }
            else {
                for(String r : registrations) {
                    carPark.removeVehicle(r, time);
                }
            }
        }
        carPark.chargeRemaining();
        System.out.printf("%s %s %s", carPark.totalSum.toString(), carPark.carsNotParked, carPark.bikesNotParked);
    }


    static class CarPark {
        private final int maxCars;
        private final int maxBikes;
        private final BigDecimal carFee;
        private final BigDecimal bikeFee;
        private final BigDecimal dailyFee;
        private final List<Vehicle> vehicles;
        private int cars, carsNotParked;
        private int bikes, bikesNotParked;
        private BigDecimal totalSum;

        public CarPark() {
            maxCars = 7;
            maxBikes = 2;
            carsNotParked = 0;
            cars = 0;
            bikes = 0;
            bikesNotParked = 0;
            carFee = new BigDecimal("1.2");
            bikeFee = new BigDecimal("0.7");
            dailyFee = new BigDecimal("30");
            totalSum = new BigDecimal(0);
            vehicles = new ArrayList<>();
        }

        public void chargeRemaining() {
            totalSum = totalSum.add(dailyFee.multiply(new BigDecimal(vehicles.size())));
        }

        private boolean availableSpace(Vehicle.Type type) {
            if (type.equals(Vehicle.Type.CAR) && cars == maxCars)
                return false;
            return !type.equals(Vehicle.Type.BIKE) || bikes != maxBikes;
        }

        private Vehicle getVehicle(String registration) {
            for (Vehicle v : vehicles)
                if (v.registration.equals(registration))
                    return v;
            return null;
        }

        private static Vehicle.Type getType(String registration) {
            if (registration.charAt(0) == 'C')
                return Vehicle.Type.CAR;
            else return Vehicle.Type.BIKE;
        }

        public void addVehicle(String registration, Vehicle.Type type, String time) {
            if (!availableSpace(type)) {
                if(type.equals(Vehicle.Type.CAR))
                    carsNotParked++;
                else bikesNotParked++;
                return;
            }
            Vehicle vehicle = new Vehicle(type, registration, time);
            vehicles.add(vehicle);
            if (vehicle.type.equals(Vehicle.Type.CAR))
                cars++;
            else bikes++;
        }

        public void removeVehicle(String registration, String time) {
            totalSum = totalSum.add(collectFee(registration, time));
            vehicles.remove(getVehicle(registration));
            if (getType(registration).equals(Vehicle.Type.CAR))
                cars--;
            else bikes--;
        }

        private BigDecimal collectFee(String registration, String time) {
            Vehicle vehicle = getVehicle(registration);
            vehicle.leave(time);
            int totalTime = vehicle.getTotalTime();
            if(vehicle.type.equals(Vehicle.Type.CAR))
                return carFee.multiply(new BigDecimal(totalTime));
            return bikeFee.multiply(new BigDecimal(totalTime));
        }

        static class Vehicle {
            Type type;
            String registration;
            int timeParked;
            int timeLeft;

            public Vehicle(Type type, String registration, String time) {
                this.type = type;
                this.registration = registration;
                timeParked = parseTime(time);
                timeLeft = 0;
            }

            private int parseTime(String time) {
                String[] split = time.split(":");
                return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            }

            public void leave(String time) {
                timeLeft = parseTime(time);
            }

            public int getTotalTime() {
                if (timeLeft - timeParked < 30)
                    return 0;
                return (int) Math.ceil((timeLeft - timeParked) / 15d);
            }

            enum Type {
                CAR,
                BIKE
            }
        }
    }
}
