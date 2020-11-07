package cn.edu.ujn.lab3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class Customer {
    private Integer custId;

    private String custName;

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