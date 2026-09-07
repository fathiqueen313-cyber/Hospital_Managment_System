# Mini Hospital Emergency Management System

## Project Title
Mini Hospital Emergency Management System

## Project Description
This project is a Java-based console application designed to simulate a small hospital emergency management system. It demonstrates the use of four important data structures:
- Binary Search Tree (BST) for patient records
- Queue for emergency patient management
- Stack for treatment history
- Singly Linked List for patient visit history

## Objectives
- Manage patient records efficiently using a custom BST.
- Handle emergency patient triage using FIFO queue logic.
- Record completed treatments using LIFO stack behavior.
- Maintain each patient's visit history using a singly linked list.
- Provide a beginner-friendly console system suitable for study and demonstration.

## Technologies Used
- Java
- Scanner for console input
- Standard Java classes only
- No external libraries

## Data Structures Used
1. Binary Search Tree (BST)
   - Stores patients by unique Patient ID.
   - Supports insertion, search, deletion, and in-order display.

2. Queue
   - Stores emergency patients waiting for treatment.
   - Uses FIFO order.

3. Stack
   - Stores treatment records in LIFO order.
   - Records the latest completed treatment at the top.

4. Singly Linked List
   - Stores all visits for each patient.
   - Supports add, search, remove, and display.

## System Features
- Register new patients
- Search patient records
- Delete patient records
- Display all patients in ascending ID order
- Enqueue emergency patients
- View the emergency queue
- Call the next patient for treatment
- Complete treatment and push record onto stack
- View treatment history
- Add patient visits
- Remove patient visits
- Search patient visits
- Display patient visit history
- Input validation for invalid menu choices and data

## Class Structure
- Main.java
- Patient.java
- PatientNode.java
- PatientBST.java
- QueueNode.java
- EmergencyQueue.java
- TreatmentRecord.java
- StackNode.java
- TreatmentStack.java
- Visit.java
- VisitNode.java
- VisitHistory.java

## How Each Data Structure Works in This Project
### BST (Patient Records)
Patients are stored in a binary search tree based on their ID. The tree allows fast searching and ordering by ID.

### Queue (Emergency Patients)
Patients are added to the end of the queue. The next patient is removed from the front, following FIFO behavior.

### Stack (Treatment History)
Each completed treatment is pushed onto the top of the stack. The most recently completed treatment is popped first.

### Singly Linked List (Visit History)
Each patient owns a linked list of their previous visits. Each visit is added to the end, and the linked list allows traversal, search, and removal.

## How to Compile and Run
1. Open a terminal in the project folder.
2. Compile all Java files:
   ```bash
   javac -d bin src/*.java
   ```
3. Run the program:
   ```bash
   java -cp bin Main
   ```

## Sample Operations
- Register patient with ID 130
- Search for patient ID 105
- Add patient 115 to emergency queue
- View emergency queue
- Call next patient for treatment
- Complete treatment
- Add a visit for patient 101
- Display patient visit history

## Testing
The program includes sample test data for demonstration:
- At least 5 patients inserted into the BST
- Search for existing and missing patients
- Delete a patient
- Display patients in ascending ID order
- Enqueue several emergency patients
- Dequeue to show FIFO behaviour
- Push multiple treatment records
- Pop treatment to show LIFO behaviour
- Add multiple visits
- Search and remove visits

## Author
Your Name

## Suggested GitHub Commit Sequence
1. Initial project structure
2. Added Patient class
3. Implemented Patient BST insertion
4. Added BST search and traversal
5. Added BST deletion
6. Implemented emergency queue
7. Implemented treatment stack
8. Implemented patient visit linked list
9. Added input validation
10. Added testing
11. Updated README

## Notes
This project is intentionally beginner-friendly and designed for clear explanation in class demonstrations and assignment presentations.
