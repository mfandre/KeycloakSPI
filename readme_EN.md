# Service Provider Interfaces (SPI) 
They are interfaces that make it possible to extend/increase Keycloak functionalities 

# API Mock User (mock-user-node-api folder) 
It is an express api (nodejs) to serve as an external authenticator. There are only 3 users who are stored in a list in memory. They are (username/password)
- andre / andre
- emanoel / emanoel
- santini / santin 

> It's a very simple project just to "mock" an example of an external authenticator service! 

#External User SPI (external-user-spi folder) 
JAVA project that implements a User Storage Provider to allow the Keycloak to authenticate with our external api. 

# Compiling
Open the external-user-spi project and compile it to generate the JAR. Copy the JAR to the deployments folder, this folder is a volume on the docker that points to the keycloak deployments folder. Any JAR in that folder will be deployed on the server and the functionality will be incorporated into the keycloak.

# Running the project 
- There is a compose docker in the project's root folder, to run it, open a command prompt and navigate to the folder for this file. 
- Run the command: docker-compose up --build -d
- Go to http://localhost:3000 to verify that the external authentication API is working
- Go to http://localhost:8989 to check if the keycloak is running
- Log in to the keycloak with the user admin / admin
- In the side menu of the keycloak access "User Federation"
- Add the "external-user-provider" provider (see the 2 first images)
- Open an incognito tab in your browser and access the keycloak (http://localhost:8989)
- Log in with any of the existing users in the Mock user API e.g. andre/andre
- You can see that the user has been "integrated" into the keycloak (see the Users menu of the keycloak, last image) 

> Note: To stop the docker just run "docker-compose down" at the command prompt

# Important points of the code 
- The class "ExternalUserStorageProviderFactory" is responsible for the name that will appear on the screen of the User Federation
- The "ExternalUserStorageProvider" class is the main one, all authentication logic is done in it. See the "isValid" method that validates the user in the EXTERNAL API
- In this example whenever the user is validated in the EXTERNAL API and it is correct, I add it to the LocalStorage of the Keycloak with the ROLE ADMIN! See the "isValid" and "addToStorage" method.
  - For some cookie/cache reason when you logged in the first time you get a message that you dont have permission, refresh the page some times (ctrl + shift+ r) after the logout and try again, you will be able to login directly to admin panel of keycloak.
- If do you want to add a new role to the user at the moment he is inserted in the keycloak, just change the method "addToStorage"
- The classes responsible for calling our API Mock User REST are "ExternalUserProviderClient" and "ExternalUserProviderService" 

 

# Deploying in Production
To deploy in production, just take the generated JAR in your project and add it to the deployments folder of your keycloak installation. This folder varies depending on your installation. For example, the keycloak installed in Linux environments the folder is "/opt/jboss/keycloak/standalone/deployments /"


# Want to learn more than me? Study the links ...
- https://www.keycloak.org/docs/4.8/server_development/index.h...
- https://github.com/thomasdarimont/keycloak-extension-playgro...
- https://www.programcreek.com/java-api-example/?api=org.keycl...

# Questions? 
