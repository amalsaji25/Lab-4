# ThreadLocal with Lambdas and Execute-Around Pattern

## 1. **ThreadLocal with Lambdas**

### Overview

`ThreadLocal` ensures that each thread using it, gets its own independent instance of a variable. It is used to manage non-thread-safe objects like `SimpleDateFormat`. In the task  **`ThreadLocal.withInitial()`** is used to initialize a `SimpleDateFormat` instance for each thread via a lambda expression.

### Code Summary

- A `ThreadLocal` variable is created using **`ThreadLocal.withInitial()`** which sets up a `SimpleDateFormat` for each thread.
- Each thread, calls `get()` on the `ThreadLocal` to get its own instance of `SimpleDateFormat`, which is thread-safe, i.e the variable is assigned to each thread.
- Each thread parses and formats a date (`01-January-1970`), here no thread interferes with the other's instance of `SimpleDateFormat`.

### Key Points

- Each thread has its own **isolated instance** of `SimpleDateFormat`, ensuring thread safety.
- This method avoids potential race conditions and simplifies thread-safe access to non-thread-safe objects.

## 2. **Execute-Around Pattern**

### Overview

The **Execute-Around Pattern** centralizes resource management (such as opening and closing files), while allowing different behaviors (such as reading or counting lines) to be parameterized and passed in as lambdas. This pattern helps eliminate repetitive code and ensures that resources (like files, databases) are properly managed.

### Code Summary

- **functional interface** (`BufferedReaderProcessor`) is defined to encapsulate behavior for processing files.
- The `processFile` method takes care of opening and closing a file (`BufferedReader`), ensuring that resources are handled correctly.
- Different behaviors, such as reading the first line or counting the number of lines, are passed to `processFile` using **lambda expressions**.

### Key Points

- **Functional Interface**: The core logic of how to process a file is passed as a lambda expression, allowing different behaviors without duplicating the file-handling code.
- **Centralized Resource Management**: The setup and cleanup (opening and closing the file) are handled inside `processFile`, therefore no need to repeat that logic for every file operation.
- **Flexible Usage**: By parameterizing the behavior, you can easily adapt the code for different file processing tasks, such as reading specific lines, counting lines, or handling different file operations.
