package ua.kossovec.service;

import ua.kossovec.service.exeption.WrongTimeExeption;

import java.util.Calendar;

public interface StatisticOffService {
    boolean disableStatistic(String neName, Calendar calendar) throws WrongTimeExeption;
}
