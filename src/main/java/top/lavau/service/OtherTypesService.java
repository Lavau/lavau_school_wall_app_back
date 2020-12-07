//package top.lavau.service;
//
//import com.github.pagehelper.PageInfo;
//import top.lavau.miniprogram.controller.login.OtherTypesController.PostForm;
//import top.lavau.model.LostAndFoundModel;
//import top.lavau.model.MixedDataModel;
//
///**
// * description: 对 mixed_data 表的业务进行封装
// * @author Leet
// * create: 2020/11/8 13:56
// */
//public interface OtherTypesService {
//
//    /**
//     * 添加数据
//     * @param form form
//     * @param openId openId
//     */
//    void insertOthers(PostForm form, String openId);
//
//    /**
//     * 根据 typeId、id 获取某个类别的某一实体
//     * @param typeId typeId
//     * @param id id
//     * @param openId openId
//     * @return MixedDataModel
//     */
//    MixedDataModel getTypeEntityByTypeIdAndId(String typeId, String id, String openId);
//
//    /**
//     * 根据 typeId 获取数据
//     * @param typeId typeId
//     * @param pageNum pageNum
//     * @return PageInfo<MixedDataModel>
//     */
//    PageInfo<MixedDataModel> listDataByTypeId(String typeId, int pageNum);
//
//    /**
//     * 根据 id 删除某一类别的某一数据
//     * @param typeId typeId
//     * @param id id
//     */
//    void deleteDataById(String typeId, String id);
//
//    /**
//     * 更新 mixed_data 中的数据
//     * @param mixedDataModel mixedDataModel
//     */
//    void updateData(MixedDataModel mixedDataModel);
//
//    /**
//     * 处理点赞
//     * @param id id
//     * @param openId openId
//     * @param isLike isLike
//     */
//    void like(String id, String openId, boolean isLike);
//
//    /**
//     * 认领物品
//     * @param lostAndFoundModel lostAndFoundModel
//     * @param openId openId
//     */
//    void claimThing(LostAndFoundModel lostAndFoundModel, String openId);
//}
