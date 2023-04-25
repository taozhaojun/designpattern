
public class BuilderPattern {

    public static void main(String[] args) {
        // I want have vehicle that has 2400 cc, 4 wheels, 3 air bags

//		Vehicle v = new Vehicle("2400 cc", 4, 3);
//		System.out.println(v);

        // builder pattern

        Vehicle v = new Vehicle.VehicleBuilder("2400 cc", 4).setNumAirBags(3).setNumWheels(3).build();

        v.reBuild(new Vehicle.VehicleBuilder("1600 cc", 4).setNumAirBags(2));

        System.out.println(v);

    }
}

class Vehicle {

    private String engine;
    private int numWheels;
    private int numAirBags;

//	public Vehicle(String engine, int numWheels, int numAirBags) {
//		this.engine = engine;
//		this.numWheels = numWheels;
//		this.numAirBags = numAirBags;
//
//	}

    public Vehicle reBuild(VehicleBuilder b) {
        this.engine = b.engine;
        this.numAirBags = b.numAirBags;
        this.numWheels = b.numWheels;
        return this;
    }

    public String getEngine() {
        return engine;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public int getNumAirBags() {
        return numAirBags;
    }

    public String toString() {
//		return "Engine: " + engine + " Wheels: " + numWheels;
        return String.format("Engine: %s, Wheels: %d, AirBags: %d", engine, numWheels, numAirBags);
    }

    private Vehicle(VehicleBuilder builder) {
        this.engine = builder.engine;
        this.numAirBags = builder.numAirBags;
        this.numWheels = builder.numWheels;
    }

    // nested class
    static class VehicleBuilder {

        private String engine;
        private int numWheels;
        private int numAirBags;

        public VehicleBuilder(String engine, int numWheels) {
            this.engine = engine;
            this.numWheels = numWheels;
        }

        public VehicleBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public VehicleBuilder setNumWheels(int numWheels) {
            this.numWheels = numWheels;
            return this;
        }

        public VehicleBuilder setNumAirBags(int numAirBags) {
            this.numAirBags = numAirBags;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }

    }

}
