/*
 * (C) 2013 NTCO Platform Milipp
 */

package com.journey.cmrh.util;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
@SuppressWarnings("serial")
@Data
public class PageRows<T> implements Serializable {
    private Pagination page;
    private List<T> rows = new ArrayList<T>();
}
