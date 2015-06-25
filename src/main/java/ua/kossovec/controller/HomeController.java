package ua.kossovec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ua.kossovec.dao.NesDao;
import ua.kossovec.model.Ne;
import ua.kossovec.service.StatisticOffService;
import ua.kossovec.service.convertor.DateConvertor;
import ua.kossovec.service.exeption.WrongTimeExeption;

import java.util.Calendar;
import java.util.List;

@Controller
public class HomeController {
    private final NesDao nesDao;
    private List<Ne> allNotLinuxAxeBsc;
    private StatisticOffService statisticOffService;

    @Autowired
    public HomeController(NesDao nesDao, StatisticOffService statisticOffService) {
        this.nesDao = nesDao;
        this.statisticOffService = statisticOffService;
        allNotLinuxAxeBsc = nesDao.getAllNotLinuxAxeBsc();
    }

    @RequestMapping("/")
    public String home(Model model, @ModelAttribute(value = "error") String error,
                                    @ModelAttribute(value = "message") String message) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

        model.addAttribute("nes", allNotLinuxAxeBsc);
        return "home";
    }

    @RequestMapping(value = "/off", method = RequestMethod.POST)
    public RedirectView statisticOff(@RequestParam("name") String name, @RequestParam("date") String date,
                                     final RedirectAttributes redirectAttributes) {
        Calendar calendar = DateConvertor.covertFrom12to24(date);
        try {
            statisticOffService.disableStatistic(name, calendar);
            redirectAttributes.addFlashAttribute("message", "Statistic is OFF on: " + name);
        } catch (WrongTimeExeption e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        RedirectView redirectView = new RedirectView("/StatisticOff/", true);
        redirectView.setExposeModelAttributes(false);
        return redirectView;
    }
}
