package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.dto.MedicationDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class MedicationServiceImplTest {

    @InjectMocks
    private MedicationServiceImpl medicationService;

    @Mock
    private MedicationRepository medicationRepository;


    @Test
    public void shouldSaveMedicians() throws ValidationException {

        Medication medication = new Medication();
        ReflectionTestUtils.setField(medicationService, "uploadPath", "/");
        BeanUtils.copyProperties(getMedicineDto(), medication);
        Mockito.when(medicationRepository.save(ArgumentMatchers.any())).thenReturn(medication);
        ResponseDto responseDto = medicationService.saveMedicine(getMedicineDto());
        assertNotNull(responseDto);

    }

    private MedicationDto getMedicineDto() {
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setCode("C0001");
        medicationDto.setWeight(35);
        medicationDto.setName("Name");
        byte[] inputArray = "Test String".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("tempFileName", inputArray);
        medicationDto.setFile(mockMultipartFile);
        return medicationDto;
    }


}