# Algorithms_Divide_and_Conquer

## Overview

This project, **Algorithms_Divide_and_Conquer**, implements algorithms based on the *Divide and Conquer* strategy to solve computational problems efficiently. One focus of the project is solving the "Closest Pair of Points" problem, which calculates the smallest distance between two points in a Cartesian plane. Various approaches, including a pure Divide and Conquer method and a generic algorithm, are applied and compared.

This project uses **Java 17**, and integrates several libraries like **Tablesaw** for data analysis and visualization, and **Apache Commons Lang** for utility functions.

---

## Features

- Implementation of Divide and Conquer algorithms to solve complex problems.
- Analysis and comparison of algorithmic approaches.
- Built-in utilities for measuring performance.
- Visualization capabilities with **Tablesaw**.
- Fully unit-tested for functionality and correctness.

---

## Project Structure

The project is organized as follows:
```aiignore
Algorithms_Divide_and_Conquer
├── .idea # IntelliJ IDEA configuration files 
├── src # Source code directory │ 
                                ├── main # Application source code │ 
                                                                   └── test # Unit test cases 
                                ├── target # Compiled build output (generated automatically) 
                                ├── pom.xml # Maven configuration file 
                                └── .gitignore # Git ignore configuration
``` 

---

## Requirements

Before setting up the project, ensure you have the following installed on your system:

1. **Java Development Kit (JDK 17)**: This version is required to build and run the application.
2. **Maven**: Used to manage dependencies and build the project.
3. **IntelliJ IDEA** (optional): Recommended for development with convenient tools to simplify configuration and debugging.

---

## Getting Started

### Step 1: Clone the Repository
Clone this repository to your local system using Git:

bash git clone <repository-url> cd Algorithms_Divide_and_Conquer
 

---

### Step 2: Import Project into IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Select **Open or Import** and navigate to the directory where the repository is cloned.
3. Select the **pom.xml** file and import as a Maven project.
4. IntelliJ will automatically download dependencies and set up the project.

---

### Step 3: Run Build and Tests
To build the project and execute all unit tests, run the following Maven command:

bash mvn clean install
 
This command performs the following tasks:
- Cleans the previous build artifacts.
- Compiles the source code.
- Resolves all dependencies as defined in `pom.xml`.
- Runs all unit tests.

---

### Step 4: Run the Application
The main application can be run from IntelliJ IDEA by locating the respective `main` class or adding a Run Configuration. Alternatively, use this Maven command:

bash mvn exec:java
 
---

## Key Libraries Used

The project makes use of the following dependencies:
- **JUnit (3.8.1)**: Testing framework for creating and running unit tests.
- **Tablesaw (0.42.0)**: Library for data analysis and charting, used for visual representation of results.
    - `tablesaw-core`: Core features for managing and manipulating data.
    - `tablesaw-jsplot`: Tools for graphical visualization.
- **Apache Commons Lang (3.14.0)**: Useful utility methods for Java.

All dependencies are defined in the `pom.xml` file and managed via Maven. The Maven build system automatically resolves and downloads them.

---

## Contributing

Contributions to this project are welcome! If you encounter issues or have new feature suggestions, feel free to open a GitHub Issue or submit a Pull Request.

---

## License

This project is licensed under the **MIT License**. See the accompanying LICENSE file for more information.

