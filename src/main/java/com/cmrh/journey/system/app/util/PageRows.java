/*
 * (C) 2013 NTCO Platform Milipp
 */

package com.cmrh.journey.system.app.util;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pangbohuan
 * @description PageRows
 * @date 2018-08-20 14:03
 **/
@SuppressWarnings("serial")
@Data
public class PageRows<T> implements Serializable {
    private Pagination page;
    private List<T> rows = new ArrayList<T>();
}
