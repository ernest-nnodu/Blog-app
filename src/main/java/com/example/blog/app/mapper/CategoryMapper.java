package com.example.blog.app.mapper;

import com.example.blog.app.domain.PostStatus;
import com.example.blog.app.domain.dto.CategoryDto;
import com.example.blog.app.domain.dto.CreateCategoryRequest;
import com.example.blog.app.domain.entity.Category;
import com.example.blog.app.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (posts == null) {
            return 0;
        }
        return posts.stream()
                .filter(post -> post.getStatus().equals(PostStatus.PUBLISHED))
                .count();
    }
}
