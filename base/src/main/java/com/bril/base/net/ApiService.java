package com.bril.base.net;



import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 接口列表
 */

public interface ApiService {
    String Base_URL = "";//网络请求
    String Base_File_Server = "";//附件服务器地址

//    /**
//     * 登录
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("portal/doLogin")
//    Observable<BaseCallModel<Account>> login(@Field("loginId") String loginName, @Field("loginPwd") String loginPwd);
//
//    /**
//     * 修改密码
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("portal/updatePwd")
//    Observable<BaseCallModel<Object>> modifyPwd(@Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd, @Field("confirm") String confirm);
//
//    /**
//     * 待办数量
//     *
//     * @return
//     */
//    @GET("workbench/handle/countMyTodo")
//    Observable<BaseCallModel<DealCount>> dealCount();
//
//    /**
//     * 通知数量
//     *
//     * @return
//     */
//    @GET("notice/common/apkIsReadCount")
//    Observable<BaseCallModel<DealCount>> noticeCount();
//
//    /**
//     * 通知列表
//     *
//     * @return
//     */
//    @GET("notice/common/apkIsReadList")
//    Observable<BaseCallModel<List<Notice>>> noticeList(@Query("type") int type);
//
//    /**
//     * 选用户列表
//     *
//     * @return
//     */
//    @GET("notice/common/apkUser")
//    Observable<BaseCallModel<List<Company>>> contactList(@Query("departId") int departId);
//
//
//    /**
//     * 公告列表
//     *
//     * @param page 未读（listToReadNotice）已读（listReadNotice）我的（listReleaseNotice）
//     * @return
//     */
//    @GET("notice/handle/{Path}")
//    Observable<BaseCallModel<List<Affiche>>> afficheList(@Path("Path") String path, @Query("page") int page, @Query("rows") int row);
//
//    /**
//     * 公告详情
//     *
//     * @return
//     */
//    @GET("notice/handle/noticeDetail")
//    Observable<BaseCallModel<Affiche>> afficheDetail(@Query("id") int id);
//
//    /**
//     * 公告附件
//     *
//     * @return
//     */
//    @GET("notice/handle/listNoticeAttachment")
//    Observable<BaseCallModel<List<Attachment>>> afficheAttachment(@Query("id") int id);
//
//    /**
//     * 公告接收状态
//     *
//     * @return
//     */
//    @GET("notice/handle/listNoticeUser")
//    Observable<BaseCallModel<List<AfficheUser>>> afficheReciveUser(@Query("id") int id);
//
//    /**
//     * 公告回复
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("notice/handle/receiveNotice")
//    Observable<BaseCallModel<Object>> afficheReply(@Field("id") int id, @Field("reply") String replyContent);
//
//    /**
//     * 公告发布
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("notice/handle/release")
//    Observable<BaseCallModel<Object>> afficheSend(@Field("title") String title, @Field("userIds") String userIds, @Field("content") String content, @Field("type") String type, @Field("urls") String urls, @Field("captions") String captions);
//
//
//    /**
//     * 检测版本更新
//     */
//    @GET
//    Observable<VersionInfo> checkVersion(@Url String url, @Query("client_id") String client_id, @Query("client_version_code") String versionCode);
//
//    /**
//     * 会议列表
//     *
//     * @param page 未读（listToReadMeetNotice）已读（listReadMeetNotice）我的（listReleaseMeetNotice）
//     * @return
//     */
//    @GET("meeting/handle/{Path}")
//    Observable<BaseCallModel<List<MeetNotice>>> meetNoticeList(@Path("Path") String path, @Query("page") int page, @Query("rows") int row);
//
//    /**
//     * 会议详情
//     *
//     * @return
//     */
//    @GET("meeting/handle/meetNoticeDetail")
//    Observable<BaseCallModel<MeetNotice>> meetDetail(@Query("id") int meetId);
//
//    /**
//     * 会议附件
//     *
//     * @return
//     */
//    @GET("meeting/handle/listMeetNoticeAttachment")
//    Observable<BaseCallModel<List<Attachment>>> meetAttachment(@Query("id") int meetId);
//
//    /**
//     * 会议反馈
//     *
//     * @param type 1（出席）2（缺席）
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("meeting/handle/receiveMeetNotice")
//    Observable<BaseCallModel<Object>> meetReply(@Field("id") int meetId, @Field("reply") String replyContent, @Field("ifAttend") String type);
//
//    /**
//     * 参会情况
//     *
//     * @return
//     */
//    @GET("meeting/handle/listMeetNoticeUser")
//    Observable<BaseCallModel<List<MeetNoticeUser>>> meetUsers(@Query("id") int meetId);
//
//    /**
//     * 会议地址
//     *
//     * @return
//     */
//    @GET("meeting/meetingroom/listAllRooms")
//    Observable<BaseCallModel<List<MeetAddr>>> meetAddress();
//
//    /**
//     * 会议发布
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("meeting/handle/releaseMeetNotice")
//    Observable<BaseCallModel<Object>> meetSend(@Field("title") String title, @Field("smsAlertFlag") String smsAlertFlag, @Field("type") int type, @Field("hostId") String hostId, @Field("content") String content, @Field("participants") String participants, @Field("meetTime") String meetTime, @Field("meetAddress") String meetAddress, @Field("urls") String urls, @Field("captions") String captions);
//
//    /**
//     * 文件上传
//     *
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("portal/upfile/upload")
//    Observable<UploadFile> uploadFile(@Part MultipartBody.Part file);
//
//
//    /**
//     * 值班搜索
//     *
//     * @param name     关键字
//     * @param type     0全部1个人
//     * @param dutyType 0白班1晚班2夜班
//     * @param dutyTime 时间
//     * @return
//     */
//    @GET("dutynotice/dutynotice/findDutyList")
//    Observable<BaseCallModel<List<Duty>>> dutySearch(@Query("name") String name, @Query("type") String type, @Query("dutyType") String dutyType, @Query("dutyTime") String dutyTime, @Query("rows") int rows, @Query("page") int page);
//
//    /**
//     * 值班详情
//     */
//    @GET("dutynotice/dutynotice/getDetail")
//    Observable<BaseCallModel<Duty>> dutyDetail(@Query("id") int id);
//
//    /**
//     * 值班新增
//     */
//    @FormUrlEncoded
//    @POST("dutynotice/dutynotice/saveDuty")
//    Observable<BaseCallModel<Object>> dutyCommit(@Field("dutyPerson") String dutyPerson, @Field("dutyTime") String dutyTime, @Field("dutyType") String dutyType, @Field("dutyLeaderPhone") String dutyLeaderPhone, @Field("dutyLeader") String dutyLeader, @Field("reminderTime") String reminderTime);
//
//    /**
//     * 值班修改
//     */
//    @FormUrlEncoded
//    @POST("dutynotice/dutynotice/updateDuty")
//    Observable<BaseCallModel<Duty>> dutyUpdate(@Field("dutyPerson") String dutyPerson, @Field("dutyTime") String dutyTime, @Field("dutyType") String dutyType, @Field("dutyLeaderPhone") String dutyLeaderPhone, @Field("dutyLeader") String dutyLeader, @Field("reminderTime") String reminderTime);
//
//    /**
//     * 日志列表
//     *
//     * @param type  0（个人)1(下属)
//     * @param subid 下属id
//     */
//    @GET("worklog/worklog/findWorkLogList")
//    Observable<BaseCallModel<List<WorkRecord>>> workRecords(@Query("type") int type, @Query("subid") String subid, @Query("workPlan") String workPlan, @Query("rows") int rows, @Query("page") int page);
//
//    /**
//     * 日志详情
//     */
//    @GET("worklog/worklog/getDetail")
//    Observable<BaseCallModel<WorkRecord>> workRecordDetail(@Query("id") int id);
//
//    /**
//     * 日志提交
//     */
//    @FormUrlEncoded
//    @POST("worklog/worklog/saveLog")
//    Observable<BaseCallModel<String>> workRecordCommit(@Field("workTime") String workTime, @Field("workPlan") String workPlan, @Field("completion") String completion, @Field("occupiedSchedule") int occupiedSchedule, @Field("workProblems") String workProblems, @Field("solveProblems") String solveProblems, @Field("remarks") String remarks);
//
//    /**
//     * 日志修改
//     */
//    @FormUrlEncoded
//    @POST("worklog/worklog/updWorkLog")
//    Observable<BaseCallModel<String>> workRecordModify(@Field("id") int id, @Field("workTime") String workTime, @Field("workPlan") String workPlan, @Field("completion") String completion, @Field("occupiedSchedule") int occupiedSchedule, @Field("workProblems") String workProblems, @Field("solveProblems") String solveProblems, @Field("remarks") String remarks);
//
//    /**
//     * 待办（listMyToDoTasks.do）已办（listMyLastHandleTasks.do）我的撰稿（listMyApplyedProcess.do）重新指派（listMyRepointTasks.do）
//     */
//    @GET("brilBpm/query/{Path}")
//    Observable<BaseCallModel<List<Document>>> docList(@Path("Path") String path, @Query("bpmUser") String bpmUser, @Query("page") int page, @Query("rows") int rows);
//
//    /**
//     * 文件信息
//     *
//     * @param path 收文(getReceiveDocFile)(情况)（getIssuesReportFile）
//     * @param id   =bizid
//     * @return
//     */
//    @GET("/officedoc/handle/{path}")
//    Observable<BaseCallModel<DocFile>> getDocFile(@Path("path") String path, @Query("id") int id);
//
//    /**
//     * 附件信息
//     *
//     * @param path 收文(listReceiveDocAttachment)情况报告(listIssuesReportAttachment)
//     * @param id   =bizid
//     * @return
//     */
//    @GET("/officedoc/handle/{path}")
//    Observable<BaseCallModel<List<Attachment>>> getDocAttachment(@Path("path") String path, @Query("id") int id);
//
//    /**
//     * 轨迹列表
//     *
//     * @return
//     */
//    @GET("/officedoc/bpm/getAuditRecordList")
//    Observable<BaseCallModel<List<HisRecord>>> getHisRecordList(@Query("bpmProcInstId") String bpmProcInstId, @Query("bpmProcKey") String procKey);
//
//    /**
//     * 办理
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("/brilBpm/operate/handleTask.do")
//    Observable<BaseCallModel<Object>> bpmCommit(@Field("bpmTaskId") String bpmTaskId, @Field("bpmUser") String bpmUser, @Field("bpmSmsNextFlag") String bpmSmsNextFlag, @Field("allowSendOut") String allowSendOut, @Field("bpmAuditRemark") String bpmAuditRemark, @Field(value = "processVars", encoded = true) Map<String, Object> processVars);
////    /**
////     * 更新公文呈批
////     *
////     * @param
////     */
////    @FormUrlEncoded
////    @POST("/officedoc/handle/updateIssuesReport")
////    Observable<BaseCallModel<Object>> updateDocComit(@Field("id") int bizId, @Field("allowSendOut") String allowSendOut);
//    /**
//     * 更新公文呈批
//     *
//     * @param
//     */
//    @FormUrlEncoded
//    @POST("/officedoc/handle/{path}")
//    Observable<BaseCallModel<Object>> updateDocComit(@Path("path") String path, @Field("bpmProcKey") String bpmProcKey, @Field("id") int bizId, @Field("allowSendOut") String allowSendOut);
//
//    /**
//     * 保存誊抄搞
//     *
//     * @param path (updateReceiveDoc)收文保存,(updateIssuesReport)情况报告
//     */
//    @FormUrlEncoded
//    @POST("/officedoc/handle/{path}")
//    Observable<BaseCallModel<Object>> copyDocComit(@Path("path") String path, @Field("id") int bizId, @Field("reWrite") String reWrite);
//
//    /**
//     * 秘书送审提交保存
//     */
//    @FormUrlEncoded
//    @POST("/officedoc/handle/saveAuditCheckin")
//    Observable<BaseCallModel<Object>> saveAuditCheckin(@Field("bizId") int bizId, @Field("bpmInstId") String bpmInstId, @Field("bpmProcKey") String bpmProcKey, @Field("title") String title);
//
//    /**
//     * 秘书登记提交保存
//     */
//    @FormUrlEncoded
//    @POST("/officedoc/handle/updateAuditCheckin")
//    Observable<BaseCallModel<Object>> updateAuditCheckin(@Field("bizId") int bizId, @Field("bpmInstId") String bpmInstId, @Field("bpmProcKey") String bpmProcKey, @Field("title") String title, @Field("code") String code);
//
//    /**
//     * 上传文笺
//     *
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("/officedoc/aip/handleFile")
//    Observable<String> uploadDocFile(@Part List<MultipartBody.Part> file);
//
//    /**
//     * 上传正文
//     *
//     * @param file
//     * @return
//     */
//    @Multipart
//    @POST("/officedoc/aip/mainfile/handleFile")
//    Observable<String> uploadMainDocFile(@Part List<MultipartBody.Part> file);
//
//    /**
//     * /**
//     * 公文传输列表
//     *
//     * @param type 0 待签收，1已签收
//     * @return
//     */
//    @GET("/document/documents/getDocSignList")
//    Observable<BaseCallModel<List<DocTrans>>> getDocTransList(@Query("type") String type, @Query("page") int page, @Query("rows") int row);
//
//    /**
//     * 公文传输详情
//     *
//     * @return
//     */
//    @GET("/document/documents/getSendDetail")
//    Observable<BaseCallModel<DocTransDetail>> getDocTransDetail(@Query("id") int id);
//
//    /**
//     * 秘书送审提交保存
//     */
//    @FormUrlEncoded
//    @POST("/document/documents/forwardingLeader")
//    Observable<BaseCallModel<Object>> sendDocTransLeader(@Field("id") int id);
//
//    /**
//     * 选环节
//     *
//     * @return
//     */
//    @GET("/officedoc/bpm/queryNextHJ")
//    Observable<BaseCallModel<List<DealNode>>> getbpmNextNode(@Query("bpmTaskId") String bpmTaskId);
//
//    /**
//     * 选办理人
//     *
//     * @return
//     */
//    @GET("/officedoc/bpm/queryNextHjUser")
//    Observable<BaseCallModel<List<DealUser>>> getbpmNextPerson(@Query("id") String id, @Query("bpmTaskId") String bpmTaskId);
//
//    /**
//     * 获取默认批示编号
//     *
//     * @return
//     */
//    @GET("/officedoc/handle/getNextAuditCheckinCode")
//    Observable<BaseCallModel<DocCode>> getNextAuditCheckinCode();
//
//    /**
//     * 获取重新指派人员列表
//     *
//     * @return
//     */
//    @GET("/officedoc/bpm/getReassignUser")
//    Observable<BaseCallModel<List<DealUser>>> getReassignUser(@Query("bpmTaskId") String bpmTaskId, @Query("id") String nodeName);
//
//    /**
//     * 重新指派提交
//     */
//    @FormUrlEncoded
//    @POST("/brilBpm/operate/assignmentTaskByStarterOrPre.do")
//    Observable<BaseCallModel<Object>> reSendDocPerson(@Field("bpmUser") String bpmUser, @Field("bpmTaskId") String bpmTaskId, @Field("bpmDelegateUser") String bpmDelegateUser);

}
