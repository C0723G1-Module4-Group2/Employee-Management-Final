package com.example.employeemanagementcasestudy.dto;

import com.example.employeemanagementcasestudy.model.AppRole;
import com.example.employeemanagementcasestudy.model.AppUser;
import com.example.employeemanagementcasestudy.model.Contract;
import com.example.employeemanagementcasestudy.model.SalaryLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto2 {
    private int teacherId;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^TC-\\d{2}$", message = "Vui lòng nhập đúng định dạng TC-xx(x là số bất kì)")
    private String teacherCode;
    @NotBlank(message = "Không được để trống")
    private String teacherName;
    @Past(message = "Ngày sinh phải là ngày trong quá khứ.")
    private Date dateOfBirth;
    @NotBlank(message = "Không được để trống")
    private String address;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Vui lòng nhập đúng định dạng Email")
    private String mail;
    private boolean gender;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})", message = "Vui lòng nhập đúng định dạng (vd: 0987654321)")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[0-9]{1,12}$",message = "Căn cước công dân phải đủ 12 số")
    private String identificationCard;
    //    @NotBlank(message = "Không được để trống")
//    @Pattern(regexp = "^[0-9]{1,13}$",message = "Mức Lương Quá Lớn")
    private int basicSalary;
    private int warningCoefficient;
    private boolean status;
    private String imgOfTeacher;
    private AppUser appUser;
    private Contract contract;
}
