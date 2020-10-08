//package com.sydl.test;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.middleware.platform.docserver.model.FrameInfoVo;
//import org.apache.ibatis.jdbc.SQL;
//
//
//public class FrameInfoProvider {
//
////    SELECT fc.`id`AS fid ,fc.`frame_name` AS fname,fi.* FROM frame_info fi LEFT JOIN frame_category fc ON fi.`frame_cate_id`=fc.`id` ORDER BY fi.`createtime` ASC
//    public String getReseList(IPage<FrameInfoVo> page){
//        return new SQL(){
//            {
//                SELECT("fc.`id`AS fid ,fc.`frame_name` AS fname,fi.*");
//                        FROM("frame_info fi");
//                             LEFT_OUTER_JOIN("frame_category fc ON fi.`frame_cate_id`=fc.`id` ORDER BY fi.`createtime` ASC");
//            }
//        }.toString();
//    }
//
////    SELECT fc.`id`AS fid ,fc.`frame_name` AS fname, fi.* FROM frame_info fi
////    LEFT JOIN frame_category fc
////    ON fi.`frame_cate_id`=fc.`id`
////    WHERE fi.`infoStatus`=1 ORDER BY fi.`createtime` ASC
//    public String getReseListGroupByCategory(){
//        return new SQL(){
//            {
//               SELECT("fc.`id`AS fid ,fc.`frame_name` AS fname, fi.*");
//                    FROM("frame_info fi");
//                        LEFT_OUTER_JOIN("frame_category fc ON fi.`frame_cate_id`=fc.`id` ");
//                            WHERE("fi.`infoStatus`=1");
//                                ORDER_BY("fi.`createtime` ASC");
//            }
//        }.toString();
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
//}
