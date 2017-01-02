package com.brainacad.studyproject.gui.view;

import com.brainacad.studyproject.service.LoginService;
import com.brainacad.studyproject.service.impl.LoginServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.brainacad.studyproject.gui.view.View.LOGIN;
import static com.brainacad.studyproject.gui.view.View.USERS;

/**
 * Created by User on 11/8/2016.
 */
public class LoginView extends RefreshableView {

    private JTextField usernameField;
    private JTextField passwordField;

    private LoginService loginService;

    public LoginView() {
        loginService = new LoginServiceImpl();

        content.setBorder(new EmptyBorder(5, 5, 5, 5));

        usernameField = new JTextField();
        usernameField.setBounds(188, 51, 99, 20);
        content.add(usernameField);
        usernameField.setColumns(10);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(70, 54, 86, 14);
        content.add(usernameLabel);

        passwordField= new JTextField();
        passwordField.setBounds(188, 106, 99, 20);
        content.add(passwordField);
        passwordField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 109, 86, 14);
        content.add(passwordLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(131, 165, 89, 23);
        content.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                boolean login = loginService.login(username, password);
                if (login) {
                    ViewRouter viewRouter = ViewRouter.getInstance();
                    viewRouter.switchView(getName(), USERS);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                    refresh();
                }
            }
        });
    }

    @Override
    public View getName() {
        return LOGIN;
    }

    @Override
    public void refresh(Object... params) {
        usernameField.setText("");
        passwordField.setText("");
    }
}

