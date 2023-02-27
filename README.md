# Drones
Drones techincal task using spring-boot spring cloud java11

## Technologies
+	Postgress Database  ✅ 
+	Mongo Database  ✅ 
+	Spring Framework ✅ 
+	Build Tool Maven  ✅ 
+	REST ✅ 
+	Jpa ✅ 
+	Eureka Service Discovry ✅ 


### Instructions to run the app
1. Clone the apps and each of one of them to IDE.
2. For database Just create the database with name 'dronesDB' on postgress maunally, replase user name and password on application.properties file (if you use user name and password instade 'postgress' for both, then run the application and it will create the Tables with it's entities.
3.  Then run each one of them and go to the brawser you can test using gateway swaggerapi http://localhost:8079/swagger-ui/index.html.

 #### choose drones service on the swagger you will find collection of apis under drones section:
 ## routes 
- /api/drones/available for list all availabe drones for my assumption drone only availabe if it's in IDEL state 
- /api/drones/batteryLevel/{serialNumber} return the battry capacity with state of this drone
- /api/drones/load/{serialNumber} GET get return the drone have serial number in pathVariable with all medication attached to it
- /api/drones/load/{serialNumber} POST add medication into drone have serial number in pathVariable
- /api/drones/register register a new Drone in database 


#### choose monitoring service 
- GET /api/logs return list with all logs from mongo database which aggregated by schedule fired every period of time to get all battries level for each drone 

### another apis and background jobs
- /audit api on drones service which called by monitoring service and return all battery level and safe it into mongo 
- /moveState move state also api called by monitoring service to changes state of each drone from level to another next level ex: if drone is loading it move it to loaded and if it loaded it move it into delivering for my assumption the idel state not move to any another state it ready to charge it 


### task design 
- i developed 4 services main service it's drone service and i see its bad design to add battery logging task which run every fixed amount of time in the same service if i want make multible instance from drones service it means in every instance Scheduled task will duplicated 
- monitoring sercie responsable to log and return the logs to the end use and also to change state 
- Discovery Server using Eureka
- gateway only i use it here to aggregate the swagger ui into single url :)
 

