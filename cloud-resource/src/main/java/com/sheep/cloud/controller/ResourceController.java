package com.sheep.cloud.controller;



import com.sheep.cloud.request.IResourceAddVO;
import com.sheep.cloud.request.IResourceModifyVO;
import com.sheep.cloud.request.IResourcePaymentVO;
import com.sheep.cloud.response.ApiResult;
import com.sheep.cloud.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/add")
    public ApiResult addOne(@RequestBody @Valid IResourceAddVO vo) {
        return resourceService.addOne(vo);
    }

    @DeleteMapping("/{id}")
    public ApiResult deleteResource(@PathVariable Integer id) {
        return resourceService.deleteResourceById(id);
    }

    @PostMapping("/modifyResource")
    public ApiResult modifyResource(@RequestBody @Valid IResourceModifyVO vo) {
        return resourceService.modifyResource(vo);
    }

    @GetMapping("/find/one")
    public ApiResult findOne(Integer id) {
        return resourceService.findOne(id);
    }

    @PostMapping("/payment")
    public ApiResult payment(@RequestBody @Valid IResourcePaymentVO vo) {
        return resourceService.payment(vo);
    }
    @GetMapping("/label")
    public ApiResult lable( @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "pageNum", required = false) Integer pageNum,
                            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<Integer> labels = Arrays.asList(id);
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 10;
        }
        return resourceService.label(labels, pageNum - 1, pageSize);
    }

    @PostMapping("/add/collect")
    public ApiResult addCollect(@RequestParam Integer uid, @RequestParam Integer rid) {
        return resourceService.addCollect(uid, rid);
    }

    @DeleteMapping("/collect/{id}")
    public ApiResult deleteCollectById(@PathVariable Integer id) {
        return resourceService.deleteCollectById(id);
    }

    @DeleteMapping("/delete/collect")
    public ApiResult deleteByResourceIdAndUserUid(@RequestParam Integer uid, @RequestParam Integer rid) {
        return resourceService.deleteByResourceIdAndUserUid(uid,rid);
    }

    @GetMapping("/find/collect")
    public ApiResult findCollectById(Integer uid) {
        return resourceService.findAllByListIn(uid);
    }


    @GetMapping("/count")
    public ApiResult countDistinctByLabelsId(@RequestParam Integer id) {
        return resourceService.countDistinctByLabelsId(id);
    }

    @GetMapping("/find/all")
    public ApiResult findAllResources(@RequestParam(value = "order", required = false) int order,
                                      @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 10;
        }
        return resourceService.findAllResources(order,pageNum - 1, pageSize);
    }

//    @GetMapping("/find/all/collect")
//    public ApiResult findAllResourcesOrderByCollect() {
//        return resourceService.findAllResourcesOrderByCollect();
//    }
}
