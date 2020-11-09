package cn.edu.ujn.lab3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BaseDict {
    private String dictId;

    private String dictTypeCode;

    private String dictTypeName;

    private String dictItemName;

    private String dictItemCode;

    private Integer dictSort;

    private String dictEnable;

    private String dictMemo;


}