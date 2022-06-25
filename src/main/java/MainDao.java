import pl.coderslab.entity.DbUtil;
import pl.coderslab.entity.User;

import java.sql.SQLException;
import java.util.Arrays;

public class MainDao {

    static String query = "Select user_id, user_name, user_email, user_password from users";
    static String columnNames = "user_id, user_name, user_email, user_password from users";

    public static void main(String[] args) throws SQLException {

        /*try {
            DbUtil.printData(DbUtil.conn(),query,columnNames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        //DbUtil.createTable();
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserName("krzysiek");
        user.setEmail("sgrttfa.nawrocki@scorrslab.pl");
        user.setPassword("krek");
        userDao.create(user);

        //userDao.delete(3);
        //User userToUpdate = userDao.read(1);
        //System.out.print(userToUpdate.getId());
        //System.out.println(userToUpdate.getUserName());
        //userDao.update(userToUpdate,1, "Ar", "kjkgj");
        //System.out.println(userToUpdate.getUserName());
        //userToUpdate.setUserName("Arkadiusz");
        //userToUpdate.setEmail("arek@coderslab.pl");
        //userToUpdate.setPassword("superPassword");
        //userDao.update(userToUpdate);
        userDao.findAll();
        for (User u : userDao.users) {
            System.out.println(u.getId() + " " +u.getUserName() + " " + u.getEmail());
        }


    }

}
