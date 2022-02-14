# OrderHardware-backend

OnBoarding internal project to simplify the process of hardware choice for new employees.  
New employees should be able to send their working equipment preferences directly to the hardware management of senacor.

Requirements version 1:
Hier die kurze Liste an Anforderungen:
Backend, das die Verwaltung von Hardware-Bestellungen vornimmt. Relevante Werte können dem Screenshot entnommen werden.
Neben einer Übersicht über die bislang schon erstellten Bestellungen soll ein neuer Eintrag hinzugefügt werden können.
Um die Einträge später beispielsweise in andere Systeme wie mySenacor zu exportieren, sollen diese persistiert werden.
Das Ganze soll mithilfe einer REST API umgesetzt werden - natürlich mit Unit- und Integrationstests versehen :)

Technologies: Java, Spring Boot, REST API, MySQL

```
src
└── main
    └── java
    │    └── com.senacor.hardware.management
    │           ├── controller    
    │           │       └──...
    │           ├── entity
    │           │       └──...
    │           ├── exception
    │           │       └──...
    │           ├── repository
    │           │       └──...
    │           ├── security
    │           │       └──...
    │           └──...
    │
    ├── resources
    │    │
    │    ├──templates
    │    │       └──...
    │    └──... 
    │
    │
    ├── test
    │       └──java
    │            └──com.senacor.hardware.management
    │                    └──...  
    │ 
    │                   
    └── ...                       
```

# Before you run it

Currently, there are two ways to execute this project:

### 1. Docker containerization

You can run the entire project including the necessary database with a single docker command. If it is the first time
running it you may use ``docker-compose up``. This starts all necessary components, exposes the database to the local
host on port ``3306`` and the api on port ``8080``.  
Since ``docker-compose up`` does not rebuild the source on sequential executions you should
use ```docker-compose build api && docker-compose up``` to rebuild the project and restart the project container if
necessary. Use the ```-d``` switch on ```docker-compose up``` to start the containers in the background.  
```docker-compose down``` is used to gracefully shut down the containers.

### 2. Local execution of services

If you want to execute the project locally you need to start a mySQL instance which is accessible with the credentials
specified in ```application.properties```.  
For Windows you could use [XAMPP](https://www.apachefriends.org/index.html) to achieve this.  
For MacOS you could use [Homebrew](https://brew.sh/) with ```brew install mysql``` and ```brew services run mysql```.  
The database will be created automatically and will have the name also specified in ```application.properties``` (
currently "orderhardware_projekt").

## Access API

If you want to try the endpoints of the API you will need to pass credentials to use it. They are hard coded currently
and not divided into different roles:

User = ```user```  
Password = ```user```

The default location for this setup is ```localhost:8080```. The given path is currently ```/api/v1/order```. You could
try it out using [Postman](https://www.postman.com/) for example. Make a GET request on the
address [http://localhost:8080/api/v1/order](http://localhost:8080/api/v1/order), choose the authorization type "Basic
Auth" and enter the credentials.

# Good to know

Only some basic functionalities are implemented in this project. In brief there are simple functionalities to manage
orders and different hardware as well.

# Aspects to be worked on

As you might have noticed, there needs to be more work done, to use this application in production. The following will
show a few aspects that are worth to be looked at.

- Security: Different user roles, with different access permissions. How are credentials going to be saved/managed?
- Security 2: Checking endpoint inputs
- Stability: more tests to extend and refactor code with confidence
- Deployment: How to run this application in production