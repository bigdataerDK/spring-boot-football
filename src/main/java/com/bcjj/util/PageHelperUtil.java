//package com.bcjj.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * @description 分页工具类
// * @author: By--Dk
// * @create: 2020-07-20 14:20:02
// **/
//@Component
//public class PageHelperUtil {
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    /**
//     * 分页
//     * @param redisKey
//     * @param request
//     * @param model
//     * @return
//     */
//    public void pageHelper(String redisKey, HttpServletRequest request, Model model) {
//        int pageNum = request.getParameter("pageNum") == null ? 1 : Integer.parseInt(request.getParameter("pageNum"));
//        int pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
//        List pageList = redisUtil.getListByKey(redisKey, 0, -1);
//        PageInfo pageInfo = new PageInfo();
//        pageInfo.setTotal(pageList.size()); // 总量
//        pageInfo.setSize(pageSize); // 每页条数
//        pageInfo.setPrePage(pageNum - 1 == 0 ? 1 : pageNum - 1); // 当前页
//        int pageNums = pageList.size() % pageSize == 0 ? pageList.size() / pageSize : Double.valueOf(pageList.size() / pageSize).intValue() + 1; // 总页数
//        pageInfo.setPageNum(pageNum); // 当前页
//        pageInfo.setPages(pageNums); // 总页数
//        pageInfo.setNextPage(pageNums == pageNum ? pageNums : pageNum + 1); // 下一页
//        model.addAttribute("pageInfo", pageInfo); // 放入页面
//    }
//
//    /**
//     * 返回分页list
//     * @param redisKey
//     * @param request
//     * @return
//     */
//    public List getPageHelperList(String redisKey, HttpServletRequest request) {
//        int pageNum = request.getParameter("pageNum") == null ? 1 : Integer.parseInt(request.getParameter("pageNum"));
//        int pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
//        List list = redisUtil.getListByKey(redisKey, pageSize * (pageNum - 1), (pageSize * pageNum ) - 1); // 当前页面显示条数 从pageSize * (pageNum - 1)条到 (pageSize * pageNum ) - 1条
//        return list;
//    }
//
//
//}
