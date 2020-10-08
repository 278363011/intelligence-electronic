//package com.sydl.test;
//
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.middleware.platform.docserver.mapper.FrameCategoryMapper;
//import com.middleware.platform.docserver.model.FrameCategory;
//import com.middleware.platform.docserver.result.Result;
//import com.middleware.platform.docserver.service.FrameCategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * <p>
// *  前端控制器
// * </p>
// *
// * @author zengzhiqiang
// * @since 2020-04-10
// */
//@RestController
//@RequestMapping("/frameCategory")
//public class FrameCategoryController {
//
//
//    @Autowired
//    FrameCategoryService frameCategoryService;
//
//    @Autowired
//    FrameCategoryMapper frameCategoryMapper;
//
//    @PostMapping("/insert")
//    public Result<Boolean> insertHomePageInfo(FrameCategory frameCategory){
//
//        boolean save = frameCategoryService.save(frameCategory);
//        return Result.success(save);
//    }
//
//    @PostMapping("/delete")
//    public Result<Boolean> deleteHomePageInfo(String  id){
//        boolean delete = frameCategoryService.removeById(id);
//        return Result.success(delete);
//    }
//
//    @PostMapping("/update")
//    public Result<Boolean> updateHomePageInfo(FrameCategory frameCategory){
//        boolean update = frameCategoryService.updateById(frameCategory);
//        return Result.success(update);
//    }
//
//    @PostMapping("/queryall")
//    public Result<List<FrameCategory>> queryAllHomePageInfo(){
//        List<FrameCategory> list = frameCategoryService.list();
//        return Result.success(list);
//    }
//
//    @PostMapping("/queryone")
//    public Result<FrameCategory> queryOneHomePageInfo(String  id){
//        FrameCategory byId = frameCategoryService.getById(id);
//        return Result.success(byId);
//    }
//
//
//    @PostMapping("/queryallPage")
//    public Result<Object> queryallPage(Integer pageNow,Integer pageSize){
//        IPage<FrameCategory> userPage = new Page<>(pageNow, pageSize);
//        frameCategoryMapper.selectPage(userPage,null);
//        return Result.success(userPage);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//}
