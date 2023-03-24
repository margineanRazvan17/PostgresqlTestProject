import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        dbFunctions dbFunctions = new dbFunctions();
        Connection conn = dbFunctions.connect_to_db("student", "postgres", "margineanrazvan17");
        dbFunctions.read_data(conn, "student");
        dbFunctions.afisare_studenti();

        System.out.println();
        dbFunctions.cautareStudentDupaNume("pop", "ion");

        System.out.println();
        dbFunctions.filtrareStudentiDupaGrupa("322");

        System.out.println();
        dbFunctions.filtreareDupaGrupaSiMedie("322", 5.00);

        System.out.println();
        dbFunctions.sortareMedieDescrescator();

        System.out.println();
        dbFunctions.sortareGrupaCrescator();

        System.out.println();
        dbFunctions.sortareNP();
    }
}