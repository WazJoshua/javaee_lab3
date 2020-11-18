package cn.edu.ujn.lab3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class Customer {
    private Integer custId;

    private String custName;

    private String custPinyin;

    private Integer custUserId;

    private Integer custCreateId;

    private String custSource;

    private String custIndustry;

    private String custLevel;

    private String custLinkman;

    private String custPhone;

    private String custMobile;

    private String custZipcode;

    private String custAddress;

    private Date custCreatetime;


}