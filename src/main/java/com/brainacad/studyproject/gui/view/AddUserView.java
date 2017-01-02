package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.data.domain.User;
import com.brainacad.studyproject.service.UserService;
import com.brainacad.studyproject.service.impl.UserServiceImpl;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.ADD_USER;
import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by User on 11/12/2016.
 */
public class AddUserView extends RefreshableView {

    private UserService userService;

    private JTextField userIdField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel userIdLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton createButton;

    public AddUserView() {
        userService = new UserServiceImpl();

        userIdLabel = new JLabel("ID");
        userIdLabel.setBounds(70, 54, 86, 14);
        content.add(userIdLabel);
        userIdField = new JTextField();
        userIdField.setColumns(10);
        content.add(userIdField);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(70, 54, 86, 14);
        content.add(usernameLabel);
        usernameField = new JTextField();
        usernameField.setColumns(10);
        content.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 54, 86, 14);
        content.add(passwordLabel);
        passwordField = new JTextField();
        passwordField.setColumns(10);
        content.add(passwordField);

        createButton = new JButton("CREATE");
        content.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(Role.USER);

                if (userService.addUser(user) == 0) {
                    JOptionPane.showMessageDialog(null, "Failed to create user");
                } else {
                    ViewRouter viewRouter = ViewRouter.getInstance();
                    viewRouter.switchView(ADD_USER, USERS);
                }
            }
        });
    }

    @Override
    public View getName() {
        return ADD_USER;
    }

    @Override
    public void refresh(Object... params) {

    }

}
