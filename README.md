**Tourist Guide Online Platform**
Overview
The project aims to develop an online platform for a tourist guide, enabling users to explore and discover destinations worldwide. The platform consists of two main parts:

**Content Management System (CMS)**
User Roles:
        Content Editor: Can write, review, edit, and delete tourist articles.
        Administrator: Has all the capabilities of a Content Editor and can also manage users.
        
Public Platform for Reading Articles
Allows users to browse all published articles, filter them by destinations and activities (e.g., skiing, hiking, swimming), and comment on articles.

**CMS Functionalities**

Authentication and Authorization
Login: Users must log in with their email and password to access the CMS.
User Management: Administrators can add new users, edit user details, and activate/deactivate content editors.

Content Management
Destinations: View, create, edit, and delete destinations.
Articles: View all articles, create new articles, edit, and delete articles along with their comments.

Public Platform Functionalities
Home Page: Displays the 10 newest articles.
Most Read: Shows the 10 most read articles from the last 30 days.
Destinations: Lists all articles about a selected destination.
Article Details: Displays the full article text, author, activities, and comments. Users can add new comments.


**Technologies Used**
Backend: Java with Maven
Frontend: Vue.js
Server: Tomcat
Database:
![image](https://github.com/kvucicevic/Web-Project---Tourist-guide/assets/115493179/142cffe9-7263-4cee-aed2-c1faf51463e8)


**Getting Started**
Prerequisites
Java Development Kit (JDK)
Maven
Tomcat
Node.js and npm (for the frontend)
Installation
Clone the repository:

bash
git clone <repository_url>

Backend Setup:
cd backend
mvn clean install

Deploy to Tomcat:
Copy the generated WAR file to the Tomcat webapps directory.
Start Tomcat.
Frontend Setup:

bash
cd frontend
npm install
npm run serve

**Usage**
CMS: Accessible via /cms.
Public Platform: Accessible via the root URL.

**Contributing**
Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Commit your changes (git commit -m 'Add some feature').
Push to the branch (git push origin feature/your-feature).
Open a pull request.

