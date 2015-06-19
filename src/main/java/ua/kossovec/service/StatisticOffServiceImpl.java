package ua.kossovec.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kossovec.dao.NesDao;
import ua.kossovec.model.Ne;
import ua.kossovec.service.ssh.SshService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public boolean disableStatistic(String neName, Calendar calendar) {
        Ne ne = neDao.getNeByName(neName);
        String ip = ne.getIp();
        String error = sshService.connect(ip);

        if (error == null) {
            turnOffStatistic();
            turnOnStatistic(calendar);
        }

        return false;
    }

    private boolean turnOnStatistic(Calendar calendar) {
        if (calendar.after(new GregorianCalendar())) {
//            String dateAndTimeToEnableStatistic = parse(calendar);
//            String transferQueue = getTransferQueue();
//            String.format("stmmp -z ASN.1 -p 60 -b %s -t %s 60 2000",dateAndTimeToEnableStatistic, transferQueue);
//            String.format("stmmp -z ASN.1 -p 15 -b %s -t %s 15 2000",dateAndTimeToEnableStatistic, transferQueue);

//            "stmmp -z ASN.1 -p 60 -b 201102251600 -t SDMBSC2 60 2000";
//            "stmmp -z ASN.1 -p 15 -b 201102251600 -t SDMBSC2 15 2001";
//            sshService.sendCommand(s)
        }
        return false;
    }



    private boolean turnOffStatistic() {
        return false;
    }
}

