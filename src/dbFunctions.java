

import Domain.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Integer.parseInt;

public class dbFunctions {
    private List<Student> repoStudent;

    public dbFunctions() {
        this.repoStudent = new ArrayList<>();
    }

    public Connection connect_to_db(String db, String username, String password)
    {
        Connection conn = null;
        try{
            //Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db,username,password);
            if(conn != null){
                System.out.println("Connection established!");
            }
            else{
                System.out.println("Connection failed!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet rs;
        Student student = null;
        try{
            String query = String.format("Select *from %s",table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                //System.out.print(rs.getString("id")+ " ");
                //System.out.print(rs.getString("nume")+ " ");
                //System.out.print(rs.getString("prenume")+ " ");
                //System.out.print(rs.getString("grupa")+ " ");
                //System.out.println(rs.getString("medie")+ " ");
                Long id = rs.getLong("id");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String grupa = rs.getString("grupa");
                Double medie = rs.getDouble("medie");
                student = new Student(id,nume, prenume, grupa, medie);
                repoStudent.add(student);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //public void cautareCarte(){


        //Iterable<Student> carteList = repoStudent;
        //List<Student> Carte= StreamSupport.stream(carteList.spliterator(), false).collect(Collectors.toList());
        //Carte.stream().filter(c-> Objects.equals(c.getNume(), "Pop") ).collect(Collectors.toList()).
      //          forEach(x->System.out.println(x));
    //}
    public void cautareStudentDupaNume(String nume, String prenume){
        List<Student> list;
        //list = repoStudent.stream().filter(s -> s.getNume().equals(nume) && s.getPrenume().equals(prenume)).collect(Collectors.toList());
        list = repoStudent.stream()
                .filter(s -> s.getNume().contains("pop"))
                .filter(s -> s.getPrenume().contains("ion"))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }
    public void filtrareStudentiDupaGrupa(String grupa){
        List<Student> list;
        list = repoStudent.stream()
                .filter(s -> s.getGrupa().equals(grupa))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    public void filtreareDupaGrupaSiMedie(String grupa, Double medie){
        List<Student> list;
        list = repoStudent.stream()
                .filter(s -> s.getGrupa().equals(grupa) && s.getMedie() >= medie)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    public void sortareMedieDescrescator(){
        List<Student> list;
        list = repoStudent.stream()
                .sorted(Comparator.comparingDouble(Student::getMedie).reversed())
                //.sorted((s1, s2) -> s1.getPrenume().compareTo(s2.getPrenume()))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    public void sortareGrupaCrescator(){
        List<Student> list;

        list = repoStudent.stream()
                .sorted(Comparator.comparing(Student::getGrupa))
                //.sorted((s1, s2) -> s1.getPrenume().compareTo(s2.getPrenume()))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    //nume si prenume crescator
    public void sortareNP(){
        List<Student> list;

        list = repoStudent.stream()
                .sorted((s1, s2)-> s1.getPrenume().compareTo(s2.getPrenume()))
                .sorted((s1, s2)-> s1.getNume().compareTo(s2.getNume()))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    public void afisare_studenti(){
        for(Student student:repoStudent){
            System.out.println(student);
        }
    }

}