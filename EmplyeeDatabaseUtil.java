package com.exc24_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmplyeeDatabaseUtil {

	public static final String INSERT_EMP="INSERT INTO EMPLOYEES(socialSecurityNumber,firstName,lastName,birthDay,employeeType,departmentName) values(?,?,?,?,?,?)";
	public static final String INSERT_SALARIED_EMP="INSERT INTO SALARIEDEMPLOYEES(socialSecurityNumber,weeklysalary,bonus) values(?,?,?)";
	public static final String INSERT_HOURLY_EMP="INSERT INTO HOURLYEMPLOYEES(socialSecurityNumber,hours,wage,bonus) values(?,?,?,?)";
	public static final String INSERT_COMMISSION_EMP="INSERT INTO COMMISSIONEMPLOYEES(socialSecurityNumber,grossSales,commissionRate,bonus) values(?,?,?,?)";
	public static final String INSERT_BASE_COMMIS_EMP="INSERT INTO BASEPLUSCOMMISSIONEMPLOYEES(socialSecurityNumber,grossSales,commissionRate,baseSalary,bonus) values(?,?,?,?,?)";
	public static void main(String args[])
	{
		try {
			Connection connection=DriverManager.getConnection("DB_URL","username","password");
			String reEnterFlag;
			Scanner sc = new Scanner(System.in);
			do
			{
				System.out.println("------- Adding Employee to the Database --------");
				System.out.print("Enter Employee SSN: ");
				long ssn = sc.nextLong();
				System.out.print("Enter Employee firstName: ");
				String fName = sc.nextLine();
				System.out.print("Enter Employee lastName: ");
				String lName = sc.nextLine();
				System.out.print("Enter Employee birthday(DD/MM/YYYY): ");
				String dob = sc.nextLine();
				
				System.out.print("Employee Type \n1-Salaried\n2-Hourly\n3-Commission\n4-BasePlusCommisssion\n Enter your option(1/2/3/4): ");
				int empOption = sc.nextInt();
				System.out.print("Enter Employee department name: ");
				String dName = sc.nextLine();
				PreparedStatement ps = connection.prepareStatement(INSERT_EMP);
				int parameterIndex=1;
				ps.setLong(parameterIndex++, ssn);
				ps.setString(parameterIndex++, fName);
				ps.setString(parameterIndex++, lName);
				ps.setString(parameterIndex++, dob);
				if(empOption==1)
				{
					ps.setString(parameterIndex++, "Salaried");
				}
				else if(empOption==2)
				{
					ps.setString(parameterIndex++, "Hourly");
				}
				else if(empOption==3)
				{
					ps.setString(parameterIndex++, "Commision");
				}
				else if(empOption==4)
				{
					ps.setString(parameterIndex++, "BasePlusCommission");
				}
				else
				{
					ps.setString(parameterIndex++, "Invalid");
				}
				ps.setString(parameterIndex++, dName);
				
				if(ps.executeUpdate()==1)
				{
					System.out.println("------ Inserted successfully in Employee table ------");
				}
				else
				{
					System.out.println("Error occurred while inserting to Employee table");
					break;
				}
				if(empOption==1)
				{
					System.out.print("Enter Employee Weekly salary: ");
					double weeklySalary=sc.nextDouble();
					System.out.print("Enter Emlpoyee bonus: ");
					double bonus = sc.nextDouble();
					PreparedStatement ps2=connection.prepareStatement(INSERT_SALARIED_EMP);
					ps2.setLong(1, ssn);
					ps2.setDouble(2, weeklySalary);
					ps2.setDouble(3, bonus);
					if(ps2.executeUpdate()==1)
					{
						System.out.println("------ Inserted successfully in SalariedEmployee table ------");
					}
					else
					{
						System.out.println("Error occurred while inserting to SalariedEmployee table");
						break;
					}
				}
				else if(empOption==2)
				{
					System.out.print("Enter Employee number of hours worked: ");
					double hours = sc.nextDouble();
					System.out.print("Enter Employee wage per hour");
					double wage = sc.nextDouble();
					System.out.print("Enter Emlpoyee bonus: ");
					double bonus = sc.nextDouble();
					PreparedStatement ps2=connection.prepareStatement(INSERT_HOURLY_EMP);
					ps2.setLong(1, ssn);
					ps2.setDouble(2, hours);
					ps2.setDouble(3, wage);
					ps2.setDouble(4, bonus);
					if(ps2.executeUpdate()==1)
					{
						System.out.println("------ Inserted successfully in HourlyEmployee table ------");
					}
					else
					{
						System.out.println("Error occurred while inserting to HourlyEmployee table");
						break;
					}
				}
				else if(empOption==3)
				{
					System.out.print("Enter Employee gross sales: ");
					double grossSales=sc.nextDouble();
					System.out.print("Enter Employee commission rate: ");
					double commissionRate = sc.nextDouble();
					System.out.print("Enter Emlpoyee bonus: ");
					double bonus = sc.nextDouble();
					PreparedStatement ps2=connection.prepareStatement(INSERT_COMMISSION_EMP);
					ps2.setLong(1, ssn);
					ps2.setDouble(2, grossSales);
					ps2.setDouble(3, commissionRate);
					ps2.setDouble(4, bonus);
					if(ps2.executeUpdate()==1)
					{
						System.out.println("------ Inserted successfully in CommissionEmployee table ------");
					}
					else
					{
						System.out.println("Error occurred while inserting to CommissionEmployee table");
						break;
					}
				}
				else if(empOption==4)
				{
					System.out.print("Enter Employee gross sales: ");
					double grossSales=sc.nextDouble();
					System.out.print("Enter Employee commission rate: ");
					double commissionRate = sc.nextDouble();
					System.out.print("Enter Employee base salary: ");
					double baseSalary = sc.nextDouble();
					System.out.print("Enter Emlpoyee bonus: ");
					double bonus = sc.nextDouble();
					PreparedStatement ps2=connection.prepareStatement(INSERT_BASE_COMMIS_EMP);
					ps2.setLong(1, ssn);
					ps2.setDouble(2, grossSales);
					ps2.setDouble(3, commissionRate);
					ps2.setDouble(4, baseSalary);
					ps2.setDouble(5, bonus);
					if(ps2.executeUpdate()==1)
					{
						System.out.println("------ Inserted successfully in BasePlusCommissionEmployee table ------");
					}
					else
					{
						System.out.println("Error occurred while inserting to BasePlusCommissionEmployee table");
						break;
					}
				}
				else
				{
					System.out.println("Invalid option for Employee Type");
				}
				
				System.out.print("Do you want to add one more empoyee to the database(Y/N) :");
				
				reEnterFlag = sc.next();
			}while(reEnterFlag.equalsIgnoreCase("Y"));
			System.out.println("Thank you!");
			sc.close();
		} catch (SQLException e) {
			System.out.println("SQL Exception occurred");
			e.printStackTrace();
		}
	}
}
