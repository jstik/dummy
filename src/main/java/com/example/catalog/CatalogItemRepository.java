package com.example.catalog;

import com.example.model.CatalogItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Julia on 16.05.2017.
 */
public interface CatalogItemRepository extends PagingAndSortingRepository<CatalogItem, Long> {

    List<CatalogItem> findAllByParentIsNull();

    @Query("select p from CatalogItem  p  where p.parent.id = :parentId")
    List<CatalogItem> findByParentId(@Param(value = "parentId") Long parentId);
}
