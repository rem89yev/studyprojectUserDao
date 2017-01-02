package com.brainacad.studyproject.data.core;

import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.data.domain.User;

import java.util.Collection;
import java.util.HashSet;

import static com.brainacad.studyproject.data.domain.Role.ADMIN;
import static com.brainacad.studyproject.data.domain.Role.USER;

/**
 * Created by User on 11/1/2016.
 */
public class StubDataHolder {

    private static Collection<User> users;

    private static boolean created = false;

    public static void createData() {
        if (!created) {
            User admin = new User();
            admin.setId(1);
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(ADMIN);

            User user = new User();
            user.setId(2);
            user.setUsername("user");
            user.setPassword("user");
            user.setRole(USER);

            users = new HashSet();
            users.add(admin);
            users.add(user);

            created = true;
        }
    }

    public static int add(User user) {
        if (users.add(user)) {
            return user.getId();
        } else return 0;
    }

    public static Collection<User> getUsers() {
        return users;
    }

}
