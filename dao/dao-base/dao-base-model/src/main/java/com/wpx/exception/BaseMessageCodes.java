package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * @Description: 基础模块错误信息
 */
public class BaseMessageCodes {

    public static final String USER_NOT_EXIST_EXCEPTION_MESSAGE = "用户不存在";
    public static final String USER_SAVE_EXCEPTION_MESSAGE = "用户保存失败";
    public static final String USER_UPDATE_EXCEPTION_MESSAGE = "用户更新失败";
    public static final String USER_DELETE_EXCEPTION_MESSAGE = "用户删除失败";

    public static final String NOT_ROOT_EDIT_OTHER_INFO_MESSAGE = "非超管只能编辑自己的信息";
    public static final String NOT_ROOT_CHECK_OTHER_INFO_MESSAGE = "非超管只能查看自己的信息";
    public static final String ALLOW_ADD_WPX_EXCEPT_ROOT_MESSAGE = "不允许添加wpx之外的超管";
    public static final String MANAGER_NOT_EXIST_EXCEPTION_MESSAGE = "管理员记录不存在";
    public static final String MANAGER_UPDATE_EXCEPTION_MESSAGE = "更新管理员信息失败";
    public static final String MANAGER_DELETE_EXCEPTION_MESSAGE = "管理员记录删除失败";
    public static final String MANAGER_SAVE_EXCEPTION_MESSAGE = "管理员记录保存失败";
    public static final String ROOT_DELETE_EXCEPTION_MESSAGE_MESSAGE = "超管不可删除";
    public static final String NOT_ROOT_CANNOT_DELETE_MESSAGE = "非超管不可删除账号";
    public static final String ROOT_CANNOT_DISABLED_MESSAGE = "超管不可禁用";
    public static final String NOT_ROOT_CANNOT_DISABLED_MESSAGE = "非超管不可禁用账号";
    public static final String NOT_ALLOW_SUPERIOR_ROLE_MESSAGE = "不能给同级或上级设权限";

}
