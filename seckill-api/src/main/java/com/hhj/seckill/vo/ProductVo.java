package com.hhj.seckill.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVo {
    @JsonProperty("category_ids")
    @NotBlank
    List<String> categoryIds;
    @JsonProperty("current_page")
    String currentPage;

    @JsonProperty("page_size")
    String pageSize;
}
