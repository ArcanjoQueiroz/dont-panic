import kotlin.collections.MutableList
import kotlin.collections.Map

// Observers
interface Observer {
  fun update(args: Map<String, Any>)
}

// Observable
interface Subject {
  fun addObserver(observer: Observer)
  fun removeObserver(observer: Observer)
  fun notifyObservers()
}

class WeatherDate(
  val observers: MutableList<Observer> = ArrayList<Observer>(),
  var temperature: Float = 0F,
  var humidity: Float = 0F,
  var pressure: Float = 0F) : Subject {

  override fun addObserver(observer: Observer) {
    this.observers.add(observer)
  }

  override fun removeObserver(observer: Observer) {
    this.observers.remove(observer)
  }

  override fun notifyObservers() {
    this.observers.forEach {
      it.update(mapOf(
        "temperature" to this.temperature,
        "humidity" to this.humidity,
        "pressure" to this.pressure
      ))
    }
  }

  fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
    this.temperature = temperature
    this.humidity = humidity
    this.pressure = pressure
    this.notifyObservers()
  }
}

class GeneralDisplay : Observer {
  override fun update(args: Map<String, Any>) {
    println("%s updated. Temperature: %5.2f, Humidity: %5.2f, Pressure: %5.2f"
      .format("General Display", args["temperature"], args["humidity"], args["pressure"]))

  }
}

class StatisticsDisplay : Observer {
  override fun update(args: Map<String, Any>) {
    println("%s updated. Temperature: %5.2f, Humidity: %5.2f, Pressure: %5.2f"
      .format("Statistics Display", args["temperature"], args["humidity"], args["pressure"]))
  }
}

class ForecastDisplay : Observer {
  override fun update(args: Map<String, Any>) {
    println("%s updated. Temperature: %5.2f, Humidity: %5.2f, Pressure: %5.2f"
      .format("Forecast Display", args["temperature"], args["humidity"], args["pressure"]))
  }
}

// Observable Design Pattern
fun main(args: Array<String>) {
    val weatherDate = WeatherDate()
    val displays = listOf(GeneralDisplay(), StatisticsDisplay(), ForecastDisplay())
    displays.forEach { weatherDate.addObserver(it) }
    weatherDate.setMeasurements(80.0f, 65.0f, 30.4f)
    weatherDate.setMeasurements(82.0f, 70.0f, 29.9f)
    weatherDate.setMeasurements(78.0f, 90.0f, 29.9f)
}