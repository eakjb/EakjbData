EakjbData
-------------------------------------------------------------------------------------------------------------
NOTE: This README is current as of 01/06/2013 but is subject to change and may become outdated later in development.

Download Instructions
-------------------------------------------------------------------------------------------------------------
I'm not sure if I'm using GIT right, but this is how to download.

If you want the latest *DEV* version, you have to pull and compile yourself.  If you want to use a released semi-stable version,
switch to the branch for that version (I recommend the latest version) and download the JAR labeled with that version and/or the 
source.  Add the JAR to your CLASSPATH to use the API.

Information
-------------------------------------------------------------------------------------------------------------
**Purpose:**
EakjbData is a Java-based framework designed to create easy access to multiple types of Data in a consistent format.
EakjbData is based on the original data package for GradeMaster at 'com.GradeMaster.data' which had many prominant
problems leading to the development of EakjbData.

**Planned Structure:**

*Classes below are likely abstract.*

DataInterfaces - Provide methods for working with raw data by handling caching and parsing.
DataAdapters - Convert raw data (XML, YAML, SQL, etc...) to DataStructures
DataStructure - Attribute-based data tree

**TODO:**
	- Update javadoc comments for
		- ErrorLevel
		- Logger
		- ILogger
		- IDataStructure
		- DataAdapter
		- TextDataObject
		- Update try/catch to be specific
		- Finish queries
	- Add more DataAdapters
		- YAML
		- SQL
		- CSV?


