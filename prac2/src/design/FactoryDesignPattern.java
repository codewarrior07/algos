package design;

import java.util.HashMap;
import java.util.Map;

public class FactoryDesignPattern {

}

class CarFactory {
	public static final CarFactory CARFACTORY_INSTANCE = new CarFactory(); //Singleton
	private CarFactory() {}
	private static Map<String,Car> map = new HashMap<>();
	public void register(String carID,Car car) {
		map.put(carID, car);
	}
	public Car getCar(String carID) {
		if(map.containsKey(carID))
			return map.get(carID);
		return null;
	}
}

interface Car {}

class Toyota implements Car {
	static {
		CarFactory.CARFACTORY_INSTANCE.register("Toyota", new Toyota());
	}
}

class BMW implements Car {
	static {
		CarFactory.CARFACTORY_INSTANCE.register("BMW", new BMW());
	}
}
