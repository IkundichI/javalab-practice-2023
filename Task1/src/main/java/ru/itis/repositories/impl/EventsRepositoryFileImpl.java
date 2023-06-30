package ru.itis.repositories.impl;

import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventFileName;
    private final String eventsAndUsersFileName;

    public EventsRepositoryFileImpl(String eventFileName, String eventsAndUsersFileName) {
        this.eventFileName = eventFileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventFileName, true))) {
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Event findEventBy(String string, int lineNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(eventFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayOfParseLine = line.split("\\|");
                if (arrayOfParseLine[lineNumber].equals(string)) {
                    return Event.builder()
                            .id(arrayOfParseLine[0])
                            .name(arrayOfParseLine[1])
                            .date(LocalDate.parse(arrayOfParseLine[2]))
                            .build();
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public Event findByName(String nameEvent) {

        return findEventBy(nameEvent, 1);

    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))) {
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Event> findAllByMembersContains(User user) {
        List<Event> allEventByUser = new ArrayList<>();
        String idOfCurrentUser = user.getId();
        try (BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayOfParseLine = line.split("\\|");
                if (arrayOfParseLine[0].equals(idOfCurrentUser)) {
                    Event currentEvent = findEventBy(arrayOfParseLine[1], 0);
                    if (currentEvent != null) {
                        allEventByUser.add(currentEvent);
                    }
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return allEventByUser;
    }
}
