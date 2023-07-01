package ru.itis.repositories.impl;


import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.io.*;



public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;


    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayOfParseLine = line.split("\\|");
                if (arrayOfParseLine[1].equals(emailUser)) {
                    return User.builder()
                            .id(arrayOfParseLine[0])
                            .email(arrayOfParseLine[1])
                            .password(arrayOfParseLine[2])
                            .build();
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}

