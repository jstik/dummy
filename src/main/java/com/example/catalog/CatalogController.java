package com.example.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Julia on 16.05.2017.
 */
@Controller
@RequestMapping(value= "catalog")
public class CatalogController {
    @Autowired CatalogItemRepository catalogItemRepository;

    @RequestMapping(value = "/roots")
    public @ResponseBody Object getRoots(){
        return catalogItemRepository.findAllByParentIsNull();
    }

    @RequestMapping(value = "/{parent}")
    public @ResponseBody Object getChildren(@PathVariable(name = "parent") Long parent){
        return catalogItemRepository.findByParentId(parent);
    }
}
