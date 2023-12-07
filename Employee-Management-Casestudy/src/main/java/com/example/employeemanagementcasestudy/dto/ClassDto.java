package com.example.employeemanagementcasestudy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto implements Validator {
    private int classId;
    private String className;
    private boolean status;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClassDto classDto = (ClassDto) target;
        if("".equals(classDto.getClassName())){
            errors.rejectValue("className",null,"Vui long nhap lai");
        } else if(!classDto.getClassName().matches("^[A,B,C]{1}[A-Z0-9]{6}$"))  {
            errors.rejectValue("className",null,"Vui long nhap lai");
        }
    }
}
