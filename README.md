### **Project README**

#### **1. Project Overview**

This project is a Java-based application built using the Maven build automation tool. It is designed to manage inventory
for a retail store, featuring a two-tiered stock system (shelf and warehouse) and automated replenishment processes. The
following guide provides step-by-step instructions to set up and run the application on your local machine .

#### **2. Prerequisites**

Before starting, ensure you have **Java Development Kit (JDK)** version 11 or higher installed on your system. You can
verify this by running `java -version` in your terminal or command prompt.

#### **3. Installation & Execution Guide**

Follow these steps to build and start the application.

**Step 1: Install Apache Maven**
Maven is required to manage dependencies and build the project. If it is not already installed, you need to install it
first.

1. Download the latest version of Apache Maven from the official website: https://maven.apache.org/download.cgi
2. Follow the installation instructions for your operating system from the official
   website: https://maven.apache.org/install.html
3. Verify the installation was successful by opening a new terminal window and running:
   ```bash
   mvn -version
   ```
   This command should display the installed Maven version .

**Step 2: Build the Project JAR File**
Once Maven is installed, you can build the project into an executable JAR file.

1. Open your terminal or command prompt.
2. Navigate to the root directory of the project. This is the folder that contains the `pom.xml` file .
3. Execute the following Maven command to compile the source code, run tests, and package the application:
   ```bash
   mvn package -f pom.xml
   ```
   This command tells Maven to read the `pom.xml` file (the project configuration file), manage all dependencies, and
   create a JAR file in the `target/` directory. The build process may take a few moments .

**Step 3: Run the Application**
After a successful build, you can launch the Java application.

1. In the terminal, change to the `target` directory, which was created during the previous step:
   ```bash
   cd target
   ```
2. Inside the `target` directory, run the following command to start the application. The JAR file name might be
   slightly different, so you can list the directory contents (`ls` on Linux/macOS or `dir` on Windows) to confirm the
   exact filename.
   ```bash
   java -jar java-final-0.0.1-SNAPSHOT.jar
   ```
   This command executes the packaged application. You should see startup logs in the terminal, indicating that the
   system is initializing and is ready to use .

#### **4. Additional Information**

* **Database**: The application uses an embedded H2 database. The database instance is created automatically when the
  application starts and is stored in the project directory .
* **H2-console**: http://localhost:8080/h2-console
* **SwaggerUI**: http://localhost:8080/swagger-ui/index.html
