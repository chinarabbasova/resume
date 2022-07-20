package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public  Connection connect() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/rasullulesson";
        String username = "root";
        String password = "Chnr1470";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }


}
