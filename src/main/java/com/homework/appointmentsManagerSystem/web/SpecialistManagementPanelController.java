package com.homework.appointmentsManagerSystem.web;


import com.homework.appointmentsManagerSystem.dtos.SpecialistAppointmentInfoDto;
import com.homework.appointmentsManagerSystem.dtos.StateUpdateRequest;
import com.homework.appointmentsManagerSystem.entities.enums.State;
import com.homework.appointmentsManagerSystem.services.AppointmentService;
import com.homework.appointmentsManagerSystem.services.SpecialistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Controller
@RequestMapping("/mvc/specialist")
public class SpecialistManagementPanelController {
    private final SpecialistService specialistService;
    private final AppointmentService appointmentService;

    public SpecialistManagementPanelController(SpecialistService specialistService,
                                               AppointmentService appointmentService) {
        this.specialistService = specialistService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/managementPanel")
    public String getManagementPanelPage(Model model) {
        List<SpecialistAppointmentInfoDto> specialistAppointmentInfoDtos = specialistService.getSpecialistAppointmentsInfo();
        model.addAttribute("appointmentsInfo", specialistAppointmentInfoDtos);
        model.addAttribute("stateOptions", State.values());
        return "specialistManagementPanel";
    }

    @GetMapping("/appointments/{id}/remove")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.removeAppointment(id);
        return "redirect:/mvc/specialist/managementPanel";
    }

    @PostMapping(value = "/appointments/{id}/update",
            consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public String update(@PathVariable Long id, StateUpdateRequest stateUpdateRequest) {
        appointmentService.updateAppointmentState(id, stateUpdateRequest.getState());
        return "redirect:/mvc/specialist/managementPanel";
    }
}
