package com.hhj.seckill.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchVo {
    @JsonProperty("search")
    @NotBlank
    String search;
    @JsonProperty("current_page")
    String currentPage;

    @JsonProperty("page_size")
    String pageSize;
}
