package ru.itis;

import ru.itis.models.Event;
import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.EventsRepositoryFileImpl;
import ru.itis.repositories.impl.UsersRepositoryFileImpl;
import ru.itis.services.AppService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");
        EventsRepository eventsRepository = new EventsRepositoryFileImpl("events.txt", "events_users.txt");
        AppService appService = new AppService(usersRepository, eventsRepository);

        appService.signUp("admin@gmail.com", "qwerty007");
        appService.signUp("marsel@gmail.com", "qwerty008");
        appService.signUp("user@gmail.com", "qwerty123");

        appService.addEvent("Практика по Java", LocalDate.now());
        appService.addEvent("Практика по Golang", LocalDate.now().plusDays(1));
        appService.addEvent("День открытых дверей ITIS", LocalDate.now().plusDays(3));

        appService.addUserToEvent("marsel@gmail.com", "Практика по Java");
        appService.addUserToEvent("marsel@gmail.com", "День открытых дверей ITIS");

        appService.addUserToEvent("user@gmail.com", "Практика по Java");
        appService.addUserToEvent("user@gmail.com", "Практика по Golang");
        appService.addUserToEvent("user@gmail.com", "День открытых дверей ITIS");

        List<Event> listOfMarselEvent = appService.getAllEventsByUser("marsel@gmail.com");
        List<Event> listOfUserEvent = appService.getAllEventsByUser("user@gmail.com");
        System.out.println(listOfMarselEvent);
        System.out.println(listOfUserEvent);


    }
}
