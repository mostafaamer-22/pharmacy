package com.example.pharmacy.DatabaseConnection;
import com.example.pharmacy.DataBaseManipulation.DataBaseManipulation;

import  java.sql.*;
public class DataBaseDriver {


  static public Statement statement = null;
  static public Connection connection;

  static public Statement  connectDataBase(){
         try {
             Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","2002");
             statement = connection.createStatement();
             printAlterSuccess();
         }catch (Exception e)
         {
             printException(e);
         }

      return returnStatement(statement);
  }

 static public  void  printAlterSuccess()
  {
      System.out.println("ConnectedDataBase");
  }


  static  public  Statement returnStatement(Statement statement)
  {
      return  statement;
  }

  static public  void  printException(Exception e)
  {
        e.printStackTrace();
  }


}

