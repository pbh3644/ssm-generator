/*
 * (C) 2013 NTCO Platform Milipp
 */

package com.journey.cmrh.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Pagination implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 16;

    private int totalRows;
    private int curPage;
    private int pageSize;

    private int maxPage;
    private int startRowIdx;
    private int endRowIdx;
    private int displayingPageLimit = 5;

    public Pagination() {
        this(0, 1, DEFAULT_PAGE_SIZE);
    }

    public Pagination(int total) {
        this(total, 1, DEFAULT_PAGE_SIZE);
    }

    public Pagination(int total, int page) {
        this(total, page, DEFAULT_PAGE_SIZE);
    }

    public Pagination(int total, int page, int size) {
        totalRows = total;
        curPage = page;
        pageSize = size;

        calculate();
    }


    public void setTotalRows(int total) {
        totalRows = total;
        calculate();
    }

    public void setCurPage(int page) {
        curPage = page;
        calculate();
    }

    public void setPageSize(int size) {
        pageSize = size;
        calculate();
    }


    public int getEndRowIdx() {
        return endRowIdx;
    }

    public int getStartRowIdx() {
        return startRowIdx;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public boolean hasNextPage() {
        return curPage < maxPage;
    }

    public boolean isFirstPage() {
        return curPage <= 1;
    }

    public boolean isLastPage() {
        return curPage >= maxPage;
    }

    public List<Integer> getDisplayingPages() {
        List<Integer> displayingPages = new ArrayList<Integer>();
        int startPage = Math.max(1, curPage - displayingPageLimit / 2);
        int endPage = Math.min(startPage + displayingPageLimit, maxPage);
        for (int i = startPage; i <= endPage; i++) {
            displayingPages.add(i);
        }
        return displayingPages;
    }

    private void calculate() {
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        //cal the max page
        maxPage = totalRows / pageSize;
        if (totalRows % pageSize > 0) {
            maxPage++;
        }

        //cal the current page num
        if (curPage > maxPage) {
            curPage = maxPage;
        }
        if (curPage < 1) {
            curPage = 1;
        }

        //cal the start and end row idx
        startRowIdx = pageSize * (curPage - 1);
        endRowIdx = pageSize * curPage - 1;
        if (endRowIdx > totalRows - 1) {
            endRowIdx = totalRows - 1;
        }
        if (endRowIdx < 0) {
            endRowIdx = 0;
        }
    }

}
