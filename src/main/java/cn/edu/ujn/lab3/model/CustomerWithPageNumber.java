package cn.edu.ujn.lab3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Author:Joshua
 * @Date:2020/11/8
 */
@Data
@RequiredArgsConstructor
public class CustomerWithPageNumber{
    private int pn;
    private Customer customer;
}
