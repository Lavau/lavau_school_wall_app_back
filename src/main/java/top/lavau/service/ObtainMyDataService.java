//package top.lavau.service;
//
//import com.github.pagehelper.PageInfo;
//import top.lavau.model.MixedDataModel;
//
///**
// * description: 处理获取“我”发布过的、点赞的、评论的数据的业务
// * @author: Leet
// * create: 2020-11-06 21:37
// **/
//public interface ObtainMyDataService {
//
//    /**
//     * 获取发布过的
//     * @param openId 学号
//     * @param pageNum 第几页的数据
//     * @return List<MixedDataModel>
//     */
//    PageInfo<MixedDataModel> listObtainPublishedData(String openId, int pageNum);
//
//    /**
//     * 获取点赞过的
//     * @param openId 学号
//     * @param pageNum 第几页的数据
//     * @return List<MixedDataModel>
//     */
//    PageInfo<MixedDataModel> listObtainLikedData(String openId, int pageNum);
//
//    /**
//     * 获取评论过的
//     * @param openId 学号
//     * @param pageNum 第几页的数据
//     * @return List<MixedDataModel>
//     */
//    PageInfo<MixedDataModel> listObtainCommentedData(String openId, int pageNum);
//}
