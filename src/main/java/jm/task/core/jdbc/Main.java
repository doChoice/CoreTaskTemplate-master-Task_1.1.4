package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    public static void main(String[] args) {
//        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        UserDaoHibernateImpl user = new UserDaoHibernateImpl();
        user.createUsersTable();
        user.saveUser("Ivan", "Petrov", (byte) 23);
        user.saveUser("Petr", "Ivanov", (byte) 43);
        user.saveUser("Ivanka", "Petrova", (byte) 27);

        System.out.println(user.getAllUsers());
        user.removeUserById(3);
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
