package ua.kossovec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kossovec.dao.NesDao;
import ua.kossovec.model.Ne;
import ua.kossovec.service.convertor.DateConvertor;
import ua.kossovec.service.ssh.CommandOutputToDataConvertor;
import ua.kossovec.service.ssh.SshService;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
      String transferQueue = CommandOutputToDataConvertor.getTransferQueue(sshService);
      String dateAndTimeToEnableStatistic = DateConvertor.covertToSTMMPFormat(calendar);
      String firstCommand = String.format("stmmp -z ASN.1 -p 60 -b %s -t %s 60 2000", dateAndTimeToEnableStatistic, transferQueue);
      String secondCommand = String.format("stmmp -z ASN.1 -p 15 -b %s -t %s 15 2001", dateAndTimeToEnableStatistic, transferQueue);
      sshService.sendCommand(firstCommand);
      sshService.sendCommand(secondCommand);
      return true;
    }
    else {
      throw new RuntimeException("You have choose wrong date or time");
    }
  }


  private boolean turnOffStatistic() {
    sshService.sendCommand("");
    sshService.sendCommand("");
    return false;
  }
}

