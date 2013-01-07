EakjbData
=========

-------------------------------------------------------------------------------------------------------------
NOTE:
This README is current as of 01/06/2013 but is subject to change and may become outdated later in development.
-------------------------------------------------------------------------------------------------------------
Purpose:
EakjbData is a Java-based framework designed to create easy access to multiple types of Data in a consistent format.
EakjbData is based on the original data package for GradeMaster at 'com.GradeMaster.data' which had many prominant
problems leading to the development of EakjbData.
-------------------------------------------------------------------------------------------------------------
Planned Structure:

Classes below are likely abstract.

DataInterfaces - Provide methods for working with raw data by handling caching and parsing.
DataAdapters - Convert raw data (XML, YAML, SQL, etc...) to DataStructures
DataStructure - Attribute-based data tree
DataField - Contains data within an attribute which may be a primitive (likely a java.lang.String) or a DataStructure
-------------------------------------------------------------------------------------------------------------
