Memory management.
======================

What is problem?:
Program fails because infinite loop created many big empty arrays of byte and added each reference to collection, therefore we have a memory leak. 

Solution:
Perhaps we need set actual number of creation arrays. 
