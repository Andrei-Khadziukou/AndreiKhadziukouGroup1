Multithreading and concurrency.
======================
Tasks:
    1. Deadlock
    2. Multithreading array sorting

======================
Task 1 description
Write 2 small applications to demonstrate and to solve deadlock issue for multithreading environment. It will be great if the application prints a state for blocked threads ("<Thread name> is waiting <resource name>").
If there are more than one solutions for resolving deadlock issue describe them in the README file.

Task 2 description
Write the application to sort the array using multithreading environment:
    - Enter a big array.
    - Divide the array to 4 snippets and sort each of them in a different thread (use concurrent model to run threads)
    - Divide 4 sorted arrays to 2 group and join the arrays in each group in separated thread (be careful with sort order, use concurrent model to run threads)
    - Repeat the previous action with the arrays we’ve got as a result of the previous step
    - Print the sorted array
