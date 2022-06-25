import pl.coderslab.entity.DbUtil;
import pl.coderslab.entity.User;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    User[] users = new User[0];

    public String hashPassword(String password) {
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(user_name, user_email, user_password) VALUES (?, ?, ?)";

    private static final String READ_USER_QUERY =
            "SELECT user_id, user_name, user_email, user_password FROM users WHERE user_id = ? ";

    public User read(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setString(1, String.valueOf(id));
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("user_email"));
                user.setPassword(resultSet.getString("user_password"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                System.out.println(user.getId());
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static final String UPDATE_USER_QUERY = "UPDATE users " +
            "SET user_name = ?, user_email = ?, user_password = ? " +
            " WHERE user_id = ? ";

    public void update(User user) {

        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4,user.getId() + 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String UPDATE_USER_QUERY1 = "UPDATE users " +
            "SET user_name = ?, user_email = ? " +
            " WHERE user_id = ? ";

    public void update(User user, int user_id, String user_name, String user_email ) {

        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY1);
            statement.setInt(3,user_id);
            statement.setString(1, user_name);
            statement.setString(2, user_email);
            //statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String FINDALL_USER_QUERY = "SELECT * FROM users";
    public User[] findAll() {
        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement statement =  conn.prepareStatement(FINDALL_USER_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user1 = new User();

                user1.setId(resultSet.getInt("user_id"));
                System.out.println(resultSet.getInt("user_id"));
                user1.setUserName(resultSet.getString("user_name"));
                user1.setEmail(resultSet.getString("user_email"));
                user1.setPassword(resultSet.getString("user_password"));
                users = addToArray(user1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private User[] addToArray (User u) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }


    private static final String DELETE_QUERY = "DELETE FROM users where user_id = ?";
    public void delete(int userId) throws SQLException {
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1,userId);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


