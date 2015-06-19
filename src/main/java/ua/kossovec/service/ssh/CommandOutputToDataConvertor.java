package ua.kossovec.service.ssh;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandOutputToDataConvertor {
    private CommandOutputToDataConvertor() {

    }
    public static String getTransferQueue(SshService sshService) {
        String afplsResulsString = sshService.sendCommand("afpls");
        Pattern pattern = Pattern.compile("(SDM.*\\d)");
        Matcher matcher = pattern.matcher(afplsResulsString);
        if(matcher.find()) {
            return matcher.group(1);
        }else {
            throw new RuntimeException("Cannot find TransferQueue in 'afpls' command");
        }
    }
}
