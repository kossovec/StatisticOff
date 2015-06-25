package ua.kossovec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kossovec.dao.NesDao;
import ua.kossovec.model.Ne;
import ua.kossovec.service.convertor.DateConvertor;
import ua.kossovec.service.exeption.WrongTimeExeption;
import ua.kossovec.service.ssh.CommandOutputToDataConvertor;
import ua.kossovec.service.ssh.SshService;

import java.util.Calendar;

@Component
public class StatisticOffServiceImpl implements StatisticOffService {
    private final NesDao neDao;
    private final SshService sshService;

    @Autowired
    public StatisticOffServiceImpl(NesDao neDao, SshService sshService) {
        this.neDao = neDao;
        this.sshService = sshService;

    }

    @Override
    public boolean disableStatistic(String neName, Calendar calendar) throws WrongTimeExeption {
        Ne ne = neDao.getNeByName(neName.trim());
        String ip = ne.getIp();
        String error = sshService.connect(ip);
        if (error == null) {
            Calendar neCalendar = Calendar.getInstance();
            int month = neCalendar.get(Calendar.MONTH);
            neCalendar.set(Calendar.MONTH, month + 1);
            if (calendar.compareTo(neCalendar) >= 0) {
                turnOffStatistic();
                turnOnStatistic(calendar);
            } else {
                throw new WrongTimeExeption("You have chosen wrong date or time");
            }
        }
        return true;
    }

    private boolean turnOnStatistic(Calendar calendar) {
        String transferQueue = CommandOutputToDataConvertor.getTransferQueue(sshService);
        String dateAndTimeToEnableStatistic = DateConvertor.covertToSTMMPFormat(calendar);
        String firstCommand = String.format("stmmp -z ASN.1 -p 60 -b %s -t %s 60 2000", dateAndTimeToEnableStatistic, transferQueue);
        String secondCommand = String.format("stmmp -z ASN.1 -p 15 -b %s -t %s 15 2001", dateAndTimeToEnableStatistic, transferQueue);
        sshService.sendCommand(firstCommand);
        sshService.sendCommand(secondCommand);
        return true;
    }


    private boolean turnOffStatistic() {
        sshService.sendCommand("stmmp -D 1001");
        sshService.sendCommand("stmmp -D 1000");
        return true;
    }
}

