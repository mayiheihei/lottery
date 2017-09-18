package com.example.lottery.util;

/**
 * 服务工具类
 *
 * @author 唐明
 * @create 2017-09-17 16:42
 */
public class ServiceUtil {

    /**
     * 判断字符串是否包含在目标字符串数组的方法，包含返回真，不包含返回假
     *
     * @param arr 目标数组
     * @param targetValue 传入参数字符串
     * @return
     * @throws Exception
     */
    public static boolean judgeContainCode(String[] arr, String targetValue) throws Exception {
        if (arr != null && arr.length > 0) {
            if (targetValue != null && !targetValue.equals("")) {
                for (String s1 : arr) {
                    if (s1 != null) {
                        if (s1.equals(targetValue))
                            return true;
                    }
                }
                return false;
            } else {
                throw new Exception("targetValue为空");
            }
        } else {
            throw new Exception("arr为空");
        }
    }



}
