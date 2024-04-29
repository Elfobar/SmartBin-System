#include "humidity.hpp"
#include "ultrasonic.hpp"
#include "led_indicator.hpp"
#include "MqttHandler.hpp"

//Humidity
#define DHT_PIN D2
#define DHT_TYPE DHT11
//Ultrasonic
#define ULS_PIN D3
//LED strip
#define NEOPIXEL_PIN PIN_WIRE_SCL //using I2C as a digital port
#define NEOPIXEL_TYPE NEO_GRB + NEO_KHZ800
const int PIXELS = 10;
const int TURN_ON_DISTANCE_CM = 50;
//wifi
const char* ssid = "Galaxy S21";
const char* password = "84491337";
//mqtt
const char* ID = "Wio-Terminal-group11"; 
const char* pubTopic1 = "Sensors/Humidity";
const char* pubTopic2 = "Sensors/Ultrasonic";
const char* subTopic = "WioTerminal";
const char* broker = "test.mosquitto.org";
const int port = 1883;

Humidity humidSensor(DHT_PIN, DHT_TYPE);
UltrasonicRanger ulsSensor(ULS_PIN);
LedIndicator led(PIXELS, NEOPIXEL_PIN, NEOPIXEL_TYPE, TURN_ON_DISTANCE_CM);
// wifiManager wifiManager(ssid, password);
MqttHandler mqttHandler(ssid, password, ID, pubTopic1, subTopic, broker, port);

void setup(){
  Serial.begin(115200);
  while (!Serial);
  humidSensor.setup();
  led.setup();
  mqttHandler.setup();
}

void loop(){
  mqttHandler.loop();
  float humidity = humidSensor.read();
  Serial.println(humidity);
  int distance = ulsSensor.measureDistance();
  Serial.println(distance);
  led.turnOn(distance);
  std::string humidityStr = std::to_string(humidity);
  const char* humidityPayload = humidityStr.c_str();
  mqttHandler.publish(pubTopic1, humidityPayload);
  std::string ultrasonicStr = std::to_string(distance);
  const char* ultrasonicPayload = ultrasonicStr.c_str();
  mqttHandler.publish(pubTopic2, ultrasonicPayload);
  delay(500);
}