# Chandler User Guide

![Screenshot 2025-09-27 110015.png](Screenshot%202025-09-27%20110015.png)

Chandler is a chatbot for managing your tasks efficiently. 
It helps you keep track of todos, deadlines, and events through a simple 
text-based interface. With Chandler, you can easily add, delete, mark, 
and find tasks while enjoying his unique, slightly sarcastic personality.

## Adding a Todo

Adds a simple todo task without any date/time.

Format: `todo DESCRIPTION`
Example: `todo buy groceries`

Expected outcome:
```
    ____________________________________________________________
    Got it. I've added this task:
      [T][ ] buy groceries
    Now you have 4 tasks in the list.
    ____________________________________________________________
```

## Adding a Deadline

Adds a task that needs to be completed by a specific date.

Format: `deadline DESCRIPTION /by DATE`
Example: `deadline submit report /by 3/10/2025`

Expected outcome:
```
    ____________________________________________________________
    Got it. I've added this task:
      [D][ ] submit report (by: Dec 15 2024)
    Now you have 5 tasks in the list.
    ____________________________________________________________
```

## Adding an Event

Adds a task that occurs during a specific time period.

Format: `event DESCRIPTION /from START_DATE /to END_DATE`
Example: `event team meeting /from 20/12/2024 2pm /to 20/12/2024 4pm`

Expected outcome:
```
    ____________________________________________________________
    Got it. I've added this task:
      [E][ ] team meeting (from: Dec 20 2024, 2pm to: Dec 20 2024, 4pm)
    Now you have 6 tasks in the list.
    ____________________________________________________________
```

## Marking a Task as Done

Marks a specific task as completed.

Format: `mark TASK_NUMBER`
Example: `mark 2`

Expected outcome:
```
    ____________________________________________________________
    Nice! I've marked this task as done:
      [D][X] return book (by: June 6th)
    ____________________________________________________________
```

# Marking a Task as Not Done

Marks a specific task as not completed.

Format: `unmark TASK_NUMBER`
Example: `unmark 2`

Expected outcome:
```
    ____________________________________________________________
    OK, I've marked this task as not done yet:
      [D][ ] return book (by: June 6th)
    ____________________________________________________________
```

## Listing All Tasks

Displays all tasks in your task list with their current status.

Example: `list`

Expected outcome:
```
    ____________________________________________________________
    Here are the tasks in your list:
    1.[T][ ] read book
    2.[D][X] return book (by: June 6th)
    3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm
    ____________________________________________________________
```

# Deleting a Task

Removes a task from the list permanently.

Format: `delete TASK_NUMBER`
Example: `delete 3`

Expected outcome:
```
    ____________________________________________________________
    Noted. I've removed this task:
      [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
    Now you have 5 tasks in the list.
    ____________________________________________________________
```

# Finding Tasks

Searches for tasks containing specific keywords in their descriptions.

Format: `find KEYWORD`
Example: `find book`

Expected outcome:
```
    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[T][ ] read book
    2.[D][ ] return book (by: June 6th)
    ____________________________________________________________
```

# Exiting the Program

Saves all tasks and exits the application.

Example: `bye`

Expected outcome:
```
    ____________________________________________________________
    Bye. Hope I don't see you again!
    ____________________________________________________________
```

Have fun using Chandler!