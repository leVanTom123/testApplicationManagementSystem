package com.homework.appointmentsManagerSystem.web;

import com.homework.appointmentsManagerSystem.dtos.*;
import com.homework.appointmentsManagerSystem.entities.Appointment;
import com.homework.appointmentsManagerSystem.services.AppointmentService;
import com.homework.appointmentsManagerSystem.services.SpecialistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Controller
@RequestMapping("/mvc/appointments")
public class AppointmentWebController {

    private final SpecialistService specialistService;
    private final AppointmentService appointmentService;

    public AppointmentWebController(SpecialistService specialistService,
                                    AppointmentService appointmentService) {
        this.specialistService = specialistService;
        this.appointmentService = appointmentService;
    }

    @RequestMapping("/reservation")
    public String reservationPage(Model model) {
        List<SpecialistDTO> specialists = appointmentService.specialistDTOList(specialistService.getSpecialistList());
        model.addAttribute("appointmentApplicationDTO", new AppointmentApplicationDto());
        model.addAttribute("specialists", specialists);
        return "AppointmentReservation";
    }

    @PostMapping(value = "/create-appointment",
            consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ModelAndView createAppointment(Model model,
                                   @ModelAttribute AppointmentApplicationDto appointmentApplicationDto) {
        Appointment appointment = appointmentService.createAndSaveAppointmentInformation(appointmentApplicationDto);
        return showAppointmentInfo(model, appointment);
    }

    @PostMapping(value = "/check-appointment", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    ModelAndView checkCustomerReservationInfo(Model model,
                                              @ModelAttribute AppointmentInfoRequest appointmentInfoRequest) {
        Appointment appointment = appointmentService.findAppointment(appointmentInfoRequest);
        return showAppointmentInfo(model, appointment);
    }

    @RequestMapping("/searchAppointment")
    public String searchAppointment(Model model) {
        model.addAttribute("reservationSearchDto", new AppointmentInfoRequest());
        return "appointmentSearch";
    }

    @GetMapping("/{id}/remove")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.removeAppointment(id);
        return "redirect:/mvc/appointments/reservation";
    }

    private ModelAndView showAppointmentInfo(Model model, Appointment appointment) {
        AppointmentInfoDto appointmentInfoDto = appointmentService.getAppointmentInfo(appointment);
        model.addAttribute("appointmentData", appointmentInfoDto);
        return new ModelAndView("appointmentInfo");
    }

}

