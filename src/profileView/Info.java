package profileView;

import database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tanvir on 8/13/2016.
 */
public class Info {
    private String id,name,department,cgpa,vemail,dob,email,phone;
    private String gname,gemail,gphone,address;

    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public Info() {
    }

    public Info(String id) {
        this.id = id;
        setOtherField();

    }


    private void setOtherField(){
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student where dbStudentID = '"+id+"';");
            while (resultSet.next()){
                name=resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname");
                department = resultSet.getString("dbStudentDepartment");
                cgpa = resultSet.getString("dbStudentCgpa");
                vemail=name.substring(0,1)+resultSet.getString("dbStudentLname")+id.substring(3)+"@bs"+department.toLowerCase()+".uiu.ac.bd";
                dob = resultSet.getString("dbStudentDOB");//resultSet.getString("");
                email = resultSet.getString("dbStudentEmail");
                phone = resultSet.getString("dbStudentPhone");
                address = resultSet.getString("dbStudentAddress");

                gname = resultSet.getString("dbGuardianFname")+" "+resultSet.getString("dbGuardianLname");
                gemail = resultSet.getString("dbGuardianEmail");
                gphone = resultSet.getString("dbGuardianPhone");


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    public String getId() {
        return id.substring(0,3)+" "+id.substring(3,6)+" "+id.substring(6);
    }

    public String getfullId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCgpa() {
        return "("+cgpa+")";
    }

    public String getVemail() {
        return vemail;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGname() {
        return gname;
    }

    public String getGemail() {
        return gemail;
    }

    public String getGphone() {
        return gphone;
    }

    public String getAddress() {
        return address;
    }


}
