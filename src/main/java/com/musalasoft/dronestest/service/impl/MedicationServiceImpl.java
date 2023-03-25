package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.constants.AppConstants;
import com.musalasoft.dronestest.dto.MedicationDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.MedicationRepository;
import com.musalasoft.dronestest.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    @Value("${upoload.path}")
    private String uploadPath;

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public ResponseDto saveMedicine(MedicationDto medicationDto) throws ValidationException {
        ResponseDto responseDto = new ResponseDto();
        Medication medication = new Medication();
        validateRequest(medicationDto);
        BeanUtils.copyProperties(medicationDto, medication);
        String path = uploadPath.concat(medicationDto.getFile().getOriginalFilename());
        try {
            uploadFile(medicationDto.getFile());
            medication.setImagePath(path);
            Medication medicationSave = medicationRepository.save(medication);
            if (Objects.nonNull(medicationSave)) {
                responseDto = new ResponseDto();
                responseDto.setStatus(AppConstants.SUCCESS);
                responseDto.setDescription(AppConstants.RECORD_SAVED);

            }
        } catch (IOException e) {

        }
        return responseDto;
    }

    private void validateRequest(MedicationDto medicationDto) throws ValidationException {
        if (medicationDto.getName().isEmpty()) {
            throw new ValidationException("Name can not be empty");
        }
        if (!medicationDto.getName().matches("^[a-zA-Z0-9_-]+$")) {
            throw new ValidationException("Name is in the wrong fromat");
        }

    }

    private void uploadFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath + file.getOriginalFilename());
        Files.write(path, bytes);

    }
}
