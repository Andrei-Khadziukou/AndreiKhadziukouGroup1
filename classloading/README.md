Classloading.
======================
Classloader task:
Create console application for dynamic loading of new development functionality. New functionality should be placed in specified directory and should be archived in jar. The application should have console menu for choosing option, the output should be done through usage of log4j library.

======================
Task description:
  - There will be a classloader which will scan specified directory and register additional functionalities (options) 
  - By special command (for instance, menu) application should print a list of possible options (a list of loaded functionalities)
  - By special command (for instance, call) application should run option (selected functionality)
  - You can provide common interface for all libraries which are placed in specified directory and will be registered like additional functionalities
  - You can use any external libraries except the libraries for managing classloding (You have to implement custom classloader)
