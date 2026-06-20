# Tools Overview for Labs

This document provides a high-level overview of the tools and technologies used in the labs. Ensure you have the following tools installed and configured to follow along with the exercises.

## 1. **Java 21 LTS**
Java 21 is the latest Long-Term Support (LTS) version of the Java programming language. It includes new features, performance improvements, and enhanced security.

LTS stands for Long-Term Support, which means that Oracle will provide updates and bug fixes for this version for an extended period (usually several years).


### Installation:
- Download and install Java 21 from the official [Oracle JDK website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or use an open-source alternative like [OpenJDK](https://openjdk.org/).
- Verify the installation by running `java -version` in your terminal or command prompt.


## 2. **Integrated Development Environments (IDEs)**
IDEs provide a comprehensive environment for writing, debugging, and testing Java code. Two popular IDEs are recommended for these labs:

### **IntelliJ IDEA**
- A powerful and widely used IDE for Java development.
- Features include intelligent code completion, refactoring tools, and built-in support for Maven and Git.
- Download and install from the [JetBrains website](https://www.jetbrains.com/idea/).

Intelij IDEA has different versions, including the Community Edition (free) and Ultimate Edition (paid). The Community Edition is sufficient for most Java development tasks. However, the Ultimate Edition offers additional features for enterprise development and can be used for free with an academic license. You can request a free academic license on the JetBrains website using your student email address from Technical University of Cluj-Napoca.

### **NetBeans**
- An open-source IDE that supports Java, Maven, and other technologies.
- Known for its simplicity and ease of use, especially for beginners.
- Download and install from the [Apache NetBeans website](https://netbeans.apache.org/).

While IntelliJ IDEA is recommended for its extensive features and community support, NetBeans is a good alternative for those looking for a simpler development environment. Creating user interfaces with NetBeans is easier due to its drag-and-drop GUI builder.

### Why Use an IDE?
- **Code Assistance**: Auto-completion, syntax highlighting, and error detection.
- **Debugging Tools**: Step-through debugging and breakpoints.
- **Integration**: Built-in support for build tools (Maven/Gradle) and version control (Git).


## 3. **Git Client**
Git is a distributed version control system used to track changes in your code and collaborate with others.

### Recommended Git Clients:
- **Command Line Git**: Available for all platforms. Download from the [official Git website](https://git-scm.com/).
- **GitHub Desktop**: A user-friendly GUI for managing Git repositories. Download from the [GitHub Desktop website](https://desktop.github.com/).

### Basic Git Commands:
- `git clone <repository-url>`: Clone a repository to your local machine.
- `git add <file>`: Stage changes for commit.
- `git commit -m "message"`: Commit changes with a message.
- `git push`: Push changes to a remote repository.

## 4. **GitHub Account**
GitHub is a cloud-based platform for hosting and collaborating on Git repositories. It is widely used for version control and project management.

### Why Use GitHub?
- **Collaboration**: Share code with teammates and contribute to open-source projects.
- **Backup**: Store your code in the cloud for safekeeping.
- **CI/CD Integration**: Automate testing and deployment with GitHub Actions.

### Steps to Get Started:
1. Create a free account on [GitHub](https://github.com/).
2. Set up SSH keys for secure authentication (optional but recommended).

## Summary of Tools
| Tool           | Purpose                                                                 |
|----------------|-------------------------------------------------------------------------|
| **Java 21 LTS** | Programming language and runtime environment for the labs.              |
| **IntelliJ**   | IDE for writing, debugging, and testing Java code.                     |
| **NetBeans**   | Alternative IDE for Java development.                                  |
| **Git Client** | Version control system for tracking changes and collaborating on code. |
| **GitHub**     | Cloud-based platform for hosting and sharing Git repositories.         |

Ensure all tools are installed and configured before starting the labs. Happy coding!