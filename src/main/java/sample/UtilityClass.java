package sample;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class UtilityClass {


    private static final String USERNAME = "root";
    private static final String PASSWORD = "ruut";

    private static List<Employee> employees;
    private static  List<Leave> leaves;
    private static List<Payroll> payroll;
    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource ds;
    private static final  Pattern pattern = Pattern.compile("\'(.*?)\'");
    private static CharSequence title;
    private static  CharSequence bloodgroups;
    private static CharSequence genders;
    private static CharSequence departments;
    private static  String outputFile = "employees_email";

    UtilityClass(boolean t){
       // hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/hmis");
        hikariConfig.setUsername(USERNAME);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.addDataSourceProperty("useSSL", false);
        ds = new HikariDataSource(hikariConfig);
    }

    UtilityClass(){

    }

    static Parent loadClass(String resName){

        try {
            return FXMLLoader.load(UtilityClass.class.getResource("/"+resName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    static void initDatabaseData() {

        employees = new ArrayList<>();

        final String SQL_QUERY = "Select *, DepartmentName, SupervisorId " +
                "from Employees " +
                "inner join Departments on Employees.departmentId=Departments.departmentId";

        final String TITLE_QUERY = "select column_type from information_schema.columns where table_name like 'Employees' AND column_name like 'Title'";
        final String BG_QUERY = "select column_type from information_schema.columns where table_name like 'Employees' AND column_name like 'BloodGroup'";
        final String GENDER_QUERY = "select column_type from information_schema.columns where table_name like 'Employees' AND column_name like 'Gender'";
        final String DEPARTMENT_QUERY = "select column_type from information_schema.columns where table_name like 'Departments' AND column_name like 'DepartmentName'";

        try(Connection connection = ds.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            PreparedStatement title_query = connection.prepareStatement(TITLE_QUERY);
            ResultSet title_result = title_query.executeQuery();

            PreparedStatement bloodgroup_query = connection.prepareStatement(BG_QUERY);
            ResultSet bloodgroup_result = bloodgroup_query.executeQuery();

            PreparedStatement gender_query = connection.prepareStatement(GENDER_QUERY);
            ResultSet gender_result = gender_query.executeQuery();

            PreparedStatement department_query = connection.prepareStatement(DEPARTMENT_QUERY);
            ResultSet department_result = department_query.executeQuery();

            title_result.next();
            title = title_result.getString("column_type");

            bloodgroup_result.next();
            bloodgroups = bloodgroup_result.getString("column_type");

            gender_result.next();
            genders = gender_result.getString("column_type");

            department_result.next();
            departments = department_result.getString("column_type");

            System.out.println(connection.isValid(1000));
            while(resultSet.next()){
                String empid = resultSet.getString("EmployeeId");
                String title = resultSet.getString("Title");
                String fname = resultSet.getString("FirstName");
                String mName = resultSet.getString("MiddleName");
                String lName = resultSet.getString("LastName");
                String bgroup = resultSet.getString("BloodGroup");
                String bDate = resultSet.getString("BirthDate");
                String gender = resultSet.getString("Gender");
                String nationality = resultSet.getString("Nationality");
                String address = resultSet.getString("Address");
                String state = resultSet.getString("State");
                String city = resultSet.getString("City");
                String email = resultSet.getString("Email");
                String phone  = resultSet.getString("PhoneNumber");
                String status = resultSet.getString("Status");
                String mStatus = resultSet.getString("MaritalStatus");
                String supervisor = resultSet.getString("SupervisorId");
                String department = resultSet.getString("DepartmentName");
                String image =  resultSet.getString("image");
                String payroll = resultSet.getString("payrollId");

                Employee employee = new Employee(empid, title, fname, mName, lName, bgroup, bDate, gender, nationality, address, state, city, phone, email, status, mStatus, image, supervisor, department, payroll);

                employees.add(employee);


            }



        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null ,"Error: " + e.getMessage());
        }
//        finally {
//            ds.close();
//        }

        Stream.of(employees).forEach(System.out::println);



    }


    static void payrollData(){

        payroll = new ArrayList<>();

        String payroll_query=  "select *, EmployeeId, concat(FirstName, '     ',  LastName) as fName " +
                "from Payroll " +
                "inner join Employees on Payroll.payrollId=Employees.payrollId";

        try(Connection connection = ds.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(payroll_query);

            ResultSet set = preparedStatement.executeQuery();

            while(set.next()){

                String empid = set.getString("EmployeeId");
                String payid = set.getString("payrollId");
                String payAmt = set.getString("payableAmt");
                String fName = set.getString("fName");

                Payroll  pay = new Payroll(empid, payid, fName, payAmt);

                payroll.add(pay);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static List<Payroll> getPays(){
        return payroll;
    }


    static void leaveData(){

        leaves = new ArrayList<>();

        String leave_query = "select *, concat(FirstName, '     ',  LastName) as fName from LeaveT inner join Employees on LeaveT.EmployeeId=Employees.EmployeeId";

        try(Connection connection = ds.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(leave_query);

            ResultSet set = preparedStatement.executeQuery();


            while(set.next()){

                String id = set.getString("EmployeeId");
                String startDate = set.getString("startDate");
                String endDate = set.getString("endDate");
                String category = set.getString("leave_cat");
                String approved = set.getString("approved");
                String fName = set.getString("fName");

                Leave leave = new Leave(id, fName, startDate, endDate,  category, approved);

                leaves.add(leave);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static void loadAnother(AnchorPane anchorPane, String file){
        AnchorPane root = (AnchorPane) UtilityClass.loadClass(file);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        ObservableList<Node> node = anchorPane.getChildren();
        System.err.println(node.size());
        if(node.size() > 0){
            anchorPane.getChildren().remove(node.get(0));
            anchorPane.getChildren().add(root);
        }else{
            anchorPane.getChildren().add(root);
        }


        FadeTransition transition = new FadeTransition(Duration.seconds(1.0));
        transition.setNode(anchorPane);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);

        transition.play();

    }

    static void makePdf() throws IOException{


        String employee_email_query = "select * from employees_email";


        try(Connection connection = ds.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(employee_email_query);
            ResultSet set = preparedStatement.executeQuery();

            Document document = new Document();


            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outputFile));

            document.open();

            Paragraph paragraph1 = new Paragraph();
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            Font font1 = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD | Font.UNDERLINE);
            Font font2 = new Font(Font.FontFamily.COURIER, 15, Font.UNDERLINE);
            Font font3 = new Font(Font.FontFamily.COURIER, 15);

            Chunk chunk = new Chunk("EMPLOYEE EMAILS REPORT", font1);

            paragraph1.add(chunk);

            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));

            document.add(paragraph1);

            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));


            PdfPTable pdfPTable = new PdfPTable(4);

            PdfPCell title_cell = new PdfPCell(new Phrase("Title"));
            title_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(title_cell);


            PdfPCell fName_cell = new PdfPCell(new Phrase("First Name"));
            fName_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(fName_cell);


            PdfPCell lName_cell = new PdfPCell(new Phrase("Last Name"));
            lName_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(lName_cell);


            PdfPCell email_cell = new PdfPCell(new Phrase("Email"));
            email_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(email_cell);
            pdfPTable.setHeaderRows(1);



            while(set.next()){
//                document.add(new Chunk("Title:  ", font3));
//                Chunk title_chunk = new Chunk(set.getString("title"));
//                title_chunk.setFont(font2);
//                document.add(title_chunk);
//                document.add(new Phrase("\n"));
//
//                System.out.println(set.getString("title"));
//
//
//                document.add(new Chunk("First Name:  ", font3));
//                Chunk fName_chunk = new Chunk(set.getString("FirstName"));
//                fName_chunk.setFont(font2);
//                document.add(fName_chunk);
//                document.add(new Phrase("\n"));
//
//
//                document.add(new Chunk("Last Name:  ", font3));
//                Chunk lName_chunk = new Chunk(set.getString("LastName"));
//                lName_chunk.setFont(font2);
//                document.add(lName_chunk);
//                document.add(new Phrase("\n"));
//
//
//                document.add(new Chunk("Email:  ", font3));
//                Chunk email_chunk = new Chunk(set.getString("Email"));
//                email_chunk.setFont(font2);
//                document.add(email_chunk);
//                document.add(new Phrase("\n\n"));

                pdfPTable.addCell(set.getString("title"));
                pdfPTable.addCell(set.getString("FirstName"));
                pdfPTable.addCell(set.getString("LastName"));
                pdfPTable.addCell(set.getString("Email"));




            }
            document.add(pdfPTable);
            document.close();



        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
        }finally {
           //Desktop.getDesktop().open(new File(outputFile));
        }
    }

    static List<String> getTitleData(){

        return getData(title);

    }

    static List<Leave> getLeaves(){
        return leaves;
    }

    static List<String> getBloodData(){
        return getData(bloodgroups);
    }

    static List<String> getGenderData(){

       return getData(genders);

    }


    static List<String> getDepartmentData(){
        return getData(departments);
    }

    private static List<String> getData(CharSequence data){

        Matcher matcher = pattern.matcher(data);

        List<String> title_data = new ArrayList<>();

        while(matcher.find()){
            title_data.add(matcher.group(1));
        }

        title_data.forEach(System.out::println);

        return title_data;
    }

    static String getDepartmentId(String dept_name){

        String deptId_query = "select departmentId from Departments where DepartmentName like '" + dept_name + "'";
        try(Connection connection = ds.getConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(deptId_query);
            ResultSet set = preparedStatement.executeQuery();
            set.next();

            return set.getString("departmentId");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "0";
    }


    static void insertData(String...  args){

        String INSERT_QUERY = "insert into Employees (Title, FirstName, MiddleName, LastName, BloodGroup, BirthDate, Gender, Nationality, Address, State, City, PhoneNumber, Email, Status, MaritalStatus, departmentId)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = ds.getConnection()){
            PreparedStatement  preparedStatement =  connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, args[0]);
            preparedStatement.setString(2, args[1]);
            preparedStatement.setString(3, args[2]);
            preparedStatement.setString(4, args[3]);
            preparedStatement.setString(5, args[4]);
            preparedStatement.setString(6, args[5]);
            preparedStatement.setString(7, args[6]);
            preparedStatement.setString(8, args[7]);
            preparedStatement.setString(9, args[8]);
            preparedStatement.setString(10, args[9]);
            preparedStatement.setString(11, args[10]);
            preparedStatement.setString(12, args[11]);
            preparedStatement.setString(13, args[12]);
            preparedStatement.setString(14, args[13]);
            preparedStatement.setString(15, args[14]);
            preparedStatement.setString(16, args[15]);
            preparedStatement.executeUpdate();

            System.out.println("Executed Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Employee new Employee()

    }


    static List<Employee> getEmployees(){

        return employees;
    }


    static void CloseHikari(){
        ds.close();
    }


}
