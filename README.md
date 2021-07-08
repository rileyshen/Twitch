<!-- ABOUT THE PROJECT -->
## About The Project

![[product-screenshot]](https://is5-ssl.mzstatic.com/image/thumb/PurpleSource124/v4/bc/65/32/bc6532ce-673e-2413-b6e8-27740d70e4d8/bf0e35b4-b457-4c86-9e31-176721c5ec3f_02_Twitch-iPad9.7_SplashScreen-US.png/643x0w.jpg)

Overview of First Project

Twitch+: A Personalized Twitch Resources Recommendation Engine

Designed and built a full-stack web application for users to search twitch resources (stream/video/clip) and get recommendations.

Built a web page with rich + user friendly experience using React and Ant Design

Implemented RESTful APIs using Java servlets, retrieved real Twitch resources using Twitch API and stored data in MySQL.
Support login/logout and favorite collection.

Explored multiple recommendation algorithms and extracted game information from Twitch resources to implement a Content-based algorithm.
Deployed the service to AWS EC2 for better stability.

### Development Environment Setup


* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [IntelliJ IDEA Ultimate as the IDE](https://www.google.com/url?q=http://jetbrains.com/idea/download/&sa=D&source=editors&ust=1625729463070000&usg=AOvVaw1P7zvuULFhlxnoAh5aaH26)
* [Apache Tomcat](https://tomcat.apache.org/download-90.cgi)
* [Postman](https://www.postman.com/downloads/)



<!-- GETTING STARTED -->
## Getting Started

1. Create New Project in IDEA
   
2. On the New Project page,
 + **Java Enterprise**
   
 + **Build Tool: Maven**
   
 + **Languages: Java**
   
 + Project **SDK** field, choose the **JDK** you’ve installed on your laptop/desktop, and click OK.

 + libraries and Frameworks:
   - [x] Servlet (4.0.1)

  
3. add Apache Tomcat as a Run/Debug Configuration
   
  + Tomcat Server
     + Local

Click the Configure… button for Application Server: 

Now specify the Tomcat Home. Locate where the Tomcat directory you saved on your disk:

Notice the warning at the bottom? Click Fix, and choose jupiter: war exploded.

Click OK or Apply

clicking the green ▶️ button:



<!-- USAGE EXAMPLES -->
## First Servlet

1. Right-click on the java source folder, then choose New, then Create New Servlet

Now we have a default empty servlet


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Riley Shen - [ripple.shen31@gmail.com](to:ripple.shen31@gmail.com)

issues log [...](w)





