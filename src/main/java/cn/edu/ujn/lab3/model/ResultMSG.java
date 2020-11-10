package cn.edu.ujn.lab3.model;
import cn.edu.ujn.lab3.constant.CHttpStatus;
import cn.edu.ujn.lab3.utils.CStringUtils;
import java.util.HashMap;

/**
 * 操作的消息提醒
 *
 * @Author:Joshua
 * @Date:2020/10/30
 */
public class ResultMSG extends HashMap<String, Object> {

    //状态码
    public static final String CODE_TAG = "code";

    //返回内容
    public static final String MSG_TAG = "msg";

    //数据对象
    public static final String DATA_TAG = "data";



    /**
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResultMSG(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResultMSG(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (CStringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回消息
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultMSG success(String msg, Object data) {
        return new ResultMSG(CHttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回消息
     * @return 成功消息
     */
    public static ResultMSG success(String msg) {
        return success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
//    public static ResultMSG success(Object data) {
//        return success("操作成功!", data);
//    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResultMSG success() {
        return success("操作成功!");
    }

    /**
     * 返回错误信息
     * @param msg   返回消息
     * @param data  数据对象
     * @return  错误消息
     */
    public static ResultMSG error(String msg, Object data) {
        return new ResultMSG(CHttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * @param code  错误状态码
     * @param msg   返回内容
     * @return  错误消息
     */
    public static ResultMSG error(int code, String msg) {
        return new ResultMSG(code, msg, null);
    }

    /**
     * 返回错误消息
     * @param msg   返回消息
     * @return  错误消息
     */
    public static ResultMSG error(String msg) {
        return error(msg, null);
    }

    /**
     * 返回错误消息
     * @return  错误消息
     */
    public static ResultMSG error() {
        return error("操作失败!");
    }

}
