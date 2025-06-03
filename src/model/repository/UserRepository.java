package model.repository;

import model.dto.UpdateProductDto;
import model.dto.UpdateUserDto;
import model.entities.Product;
import model.entities.User;
import utils.DatabaseConnectionConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserRepository implements Repository<User,Integer>{
    @Override
    public User save(User user) {
        String sql = """
                INSERT INTO users(user_name,email,uuid,password,created_date)
                VALUES(?,?,?,?,?)
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            assert con != null;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getEmail());
            pre.setString(3, user.getUuid());
            pre.setString(4,user.getPassword());
            pre.setDate(5,user.getCreatedDate());
            int rowAffected = pre.executeUpdate();
            return rowAffected>0 ? user: null;
        }catch (Exception exception){
            System.out.println("[!] Error during insert data to User table: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = """
                SELECT * FROM users
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (result.next()){
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("user_name"));
                user.setEmail(result.getString("email"));
                user.setUuid(result.getString("uuid"));
                user.setPassword(result.getString("password"));
                user.setCreatedDate(result.getDate("created_date"));
                users.add(user);
            }
            return users;
        }catch (Exception exception){
            System.out.println("[!] Error during get all users: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            assert con != null;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,  id);
            int rowAffected = pre.executeUpdate();
            return rowAffected>0? rowAffected: 0;
        }catch (Exception exception){
            System.out.println("[!] Error during delete user: " + exception.getMessage());
        }
        return 0;
    }

    public User findByUserUuid(String uuid){
        String sql = """
                SELECT * FROM users
                WHERE uuid = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, uuid);
            ResultSet result = pre.executeQuery();
            User user = new User();
            while (result.next()){;
                user.setId(result.getInt("id"));
                user.setUserName(result.getString("user_name"));
                user.setEmail(result.getString("email"));
                user.setUuid(result.getString("uuid"));
                user.setPassword(result.getString("password"));
                user.setCreatedDate(result.getDate("created_date"));
            }
            return user;
        }catch (Exception exception){
            System.out.println("[!] Error during get product by uuid: " + exception.getMessage());
        }
        return null;
    }

    public User updateUserByUuid(String uuid, UpdateUserDto updateUserDto){
        User user = findByUserUuid(uuid);
        if(user!=null){
            String sql = """
                    UPDATE users
                    SET user_name=?
                    WHERE uuid = ?
                    """;
            try(Connection con = DatabaseConnectionConfig.getConnection()){
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setString(1, updateUserDto.user_name());
                pre.setString(2, uuid);
                int rowAffected = pre.executeUpdate();
                if(rowAffected>0){
                    return findByUserUuid(uuid);
                }
            }catch (Exception exception){
                System.err.println("[!] Error during update user by uuid: " + exception.getMessage());
            }
            return null;
        }
        throw new NoSuchElementException("Cannot find User");
    }

}
