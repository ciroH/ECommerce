# ECommerce WebApp

![JavaEE](https://img.shields.io/badge/javaEE-%23ED8B00.svg?style=flat&logo=java&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%23316192.svg?style=flat&logo=mysql&logoColor=white) ![HTML5](https://img.shields.io/badge/html5-%23D12127.svg?style=flat&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%23204A87.svg?style=flat&logo=css3&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=flat&logo=git&logoColor=white) ![GPL](https://img.shields.io/badge/-GPLv2-yellow) 



**Instructions for Eclipse:**

1. In the Project Explorer go to "New" --> "Project"
   Select "Dynamic Web Project".
2. Name the project "ECommerce".
3. Target runtime must be Apache Tomcat v8.0 (Create a new Runtime if necessary).
4. Select version 3.1 or above for the Dynamic Web module version.
5. Press *Next*, and in the Build path's Source Folder selection, remove all the folders and add a "src" folder.
6. Press *Next*, and in the Web Module settings:
   Leave the Context root as "ECommerce"
   Change the Content directory to "WebContent"
   Press *Finish*.
7. Right-click the Project and select "Build Path" --> "Configure Build Path".
8. Make sure the selected *JRE System Library* is one you have installed on your IDE (is not "unbound"), else:
   Remove it.
   Select "Add Library" --> "JRE System Library" --> "Workspace default JRE" or "Alternate JRE"
   (A good example of a correct JRE in an amd64-based device can be java-11-openjkd-amd64).
9. *Apply and Close*.
