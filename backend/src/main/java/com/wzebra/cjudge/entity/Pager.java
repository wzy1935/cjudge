package com.wzebra.cjudge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pager<T> {
    private int page;//分页起始页
    private int size;//每页记录数
    private List<T> rows;//返回的记录集合
    private int total;//总页数
}
