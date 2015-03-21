package Resource;

public class Car implements java.io.Serializable
{
	 private int carID;
	 private String make;
	 private String model;
	 private int year;
	 private int rentalprice;
	 private int numberOfPassengers;
	 
	 
	 public Car(){}
	 
	 public Car(int carId, String make,String model,int year, int rentalPrice, int numOfPassengers)
	 {
		 this.setCarID(carId);
		  this.make = make;
		  this.model= model;
		  this.year = year;
		  this.rentalprice= rentalPrice;
		  this.numberOfPassengers=numOfPassengers;
	 }
	  
	 public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getRentalprice() {
			return rentalprice;
		}

		public void setRentalprice(int rentalprice) {
			this.rentalprice = rentalprice;
		}

		public int getNumberOfPassengers() {
			return numberOfPassengers;
		}

		public void setNumberOfPassengers(int numberOfPassengers) {
			this.numberOfPassengers = numberOfPassengers;
		}

		public String toString()
		{
			return "Car make: " + make + "Car model: " + model + "      Car Year:" + year;
		}

		public int getCarID() {
			return carID;
		}

		public void setCarID(int carID) {
			this.carID = carID;
		}
}
