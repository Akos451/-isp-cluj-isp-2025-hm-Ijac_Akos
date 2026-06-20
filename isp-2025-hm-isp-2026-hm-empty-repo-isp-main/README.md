# Purpose of This Repository

This repository is created to store all lab projects for the ISP class. Each lab project will be downloaded, worked on, and submitted using GitHub.

## Repository Structure

The repository follows a structured format with subfolders for each lab project. Each subfolder follows a standard Maven repository structure:

```
/isp-lab-1/
    /src/
    /pom.xml
    /README.md

/isp-lab-2/
    /src/
    /pom.xml
    /README.md
```

Each subfolder contains:
- A `src/` directory for source code.
- A `pom.xml` file for Maven project management.
- A `README.md` file with specific instructions for the lab.

## Procedure for Adding a New Lab Project

1. **Download the Lab Project Archive**
   - A ZIP archive containing the lab project will be provided.
   - Download it into this repository.

2. **Unpack and Remove the ZIP File**
   - Extract the contents of the ZIP file.
   - Delete the ZIP file after extraction to keep the repository clean.

3. **Open the Project in Your IDE**
   - Open the extracted project folder in your preferred IDE.
   - Start working on the exercises found in the README file inside the project.

4. **Complete and Submit Your Work**
   - Solve all exercises as instructed.
   - Once finished, commit and push your changes using the following commands:
     ```sh
     git add .
     git commit -m "Completed Lab Project X"
     git push origin main
     ```

## Important Notes
- Always ensure your work is properly committed and pushed.
- Check the README of each project for specific instructions.
- Keep your repository updated by pulling the latest changes before starting a new lab.

If you have any questions, ask during class or refer to GitHub documentation.
