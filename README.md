# ShopBabyProductsWithSpring

Project Title
shopbabyproducts

Shopping for Baby Products by comparing prices at different stores. This program that accepts a price file of baby products(format below) as CSV file, and a list of products that someone wants to buy, and outputs the shop they should go to, and the total price it will cost them. It is okay to purchase extra products, as long as the total cost is minimized.

Getting Started

This project require Eclipse, Tomcat8 and JDK7 installed on your machine to run.

Prerequisites

A csv file require in D drive which has data for shops, products they are selling and price. The project is created using Spring MVC.

Data File data.csv

1, 4.00, teddy_bear 1, 8.00, baby_powder 2, 5.00, teddy_bear 2, 6.50, baby_powder 3, 4.00, pampers_diapers 3, 8.00, johnson_wipes 4, 5.00, johnson_wipes 4, 2.50, cotton_buds 5, 4.00, bath_towel 5, 8.00, scissor 6, 5.00, scissor 6, 6.00, bath_towel, cotton_balls, powder_puff

Below are the inouts and it's result:

Example 1:

Enter below text on GUI and click button Search teddy_bear,baby_powder

Output 2, 11.5

Example 2:

Enter below text on GUI and click button Search pampers_diapers,baby_soap

Output none

Example 3: Enter below text on GUI and click button Search scissor,bath_towel

Output 6, 11.0

Deployment : 
1. Clone project in Eclipse in Git perspecive using below url :
https://github.com/JavaDevlpr/ShopBabyProductsWithSpring.git
2. Import project 'shopbabyproducts' from your workspace where git copied master branch
3. Configure build path by right clicking on project,set JRE system Library and Apache Tomcat 8.0. If Runtime Enviornment is not set then 1st set Windows-Preferences-Server-Runtime Enviornment- Add Apache Tomcat 8.0
If still there are any errors for servlet then Configure build path and set Project Facest, set Runtimes. 

Now everything looks great. Just right click on project and Run as - Run on server, select Apache server, select tomcat 8.0 and click finish. You will see home page where you can insert above inputs and see the result.

You either can make it's war file and deploy on Tomcat server.
