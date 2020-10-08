//package com.sydl.test;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.middleware.platform.docserver.model.FrameInfo;
//import com.middleware.platform.docserver.model.FrameInfoVo;
//import com.middleware.platform.docserver.provider.FrameInfoProvider;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.SelectProvider;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * <p>
// *  Mapper 接口
// * </p>
// * @author zengzhiqiang
// * @since 2020-04-14
// */
//@Mapper
//@Component("frameInfoMapper")
//public interface FrameInfoMapper extends BaseMapper<FrameInfo> {
//        @SelectProvider(method = "getReseList",type = FrameInfoProvider.class)
//        public IPage<FrameInfoVo> getReseList(Page<FrameInfoVo> page);
//
//
//        @SelectProvider(method = "getReseListGroupByCategory",type = FrameInfoProvider.class)
//        public List<FrameInfoVo> getReseListGroupByCategory();
//}
