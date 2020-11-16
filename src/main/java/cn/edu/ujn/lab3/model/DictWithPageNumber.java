package cn.edu.ujn.lab3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Author:bbimix
 * Date:2020/11/14
 */

@Data
@RequiredArgsConstructor
public class DictWithPageNumber {
    private int page;
    private BaseDict baseDict;
}
