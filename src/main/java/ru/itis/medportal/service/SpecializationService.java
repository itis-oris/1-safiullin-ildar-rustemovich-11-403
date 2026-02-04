package ru.itis.medportal.service;


import jakarta.servlet.http.HttpServletRequest;
import ru.itis.medportal.model.Specialization;
import ru.itis.medportal.repository.SpecializationRepository;

import java.util.List;

public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public List<Specialization> findAll() {return specializationRepository.findAll();}


    public void save(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Specialization spec = new Specialization();
        spec.setName(name.trim());
        spec.setDescription(description != null ? description.trim() : null);
        specializationRepository.save(spec);
    }

    public void update(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Long specId = Long.parseLong(req.getParameter("specId"));
        Specialization spec = new Specialization();
        spec.setSpecId(specId);
        spec.setName(name.trim());
        spec.setDescription(description != null ? description.trim() : null);
        specializationRepository.update(spec);
    }

    public void deleteSpecialization(Long specId) {
        specializationRepository.delete(specId);
    }
}