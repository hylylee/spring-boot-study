package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.totalPage = totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
        this.page = page;
        if (page < 1) {
            page = 1;
        }
        if (page > this.totalPage) {
            page = this.totalPage;
        }

        pages.add(page);
        for (int i = 1; i <= 3; i ++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= this.totalPage) {
                pages.add(page + i);
            }
        }

        this.showPrevious = page == 1 ? false : true;
        this.showNext = page == this.totalPage ? false : true;
        this.showFirstPage = pages.contains(1) ? false : true;
        this.showEndPage = pages.contains(this.totalPage) ? false : true;
    }
}
