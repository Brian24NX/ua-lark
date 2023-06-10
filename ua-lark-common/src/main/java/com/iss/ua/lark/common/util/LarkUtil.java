package com.iss.ua.lark.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iss.ua.lark.common.constant.LarkConstants;
import com.lark.oapi.Client;
import com.lark.oapi.core.enums.BaseUrlEnum;
import com.lark.oapi.core.request.RequestOptions;
import com.lark.oapi.core.response.RawResponse;
import com.lark.oapi.core.token.AccessTokenType;
import com.lark.oapi.service.contact.v3.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@Log4j2
@Component
public class LarkUtil {

    @Value("${lark.appId}")
    private String appId;

    @Value("${lark.appSecret}")
    private String appSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    /**
     * 常规token
     * 获取企业微信accessToken
     * @return
     */
    public  String getAccessToken(){

        JSONObject param= new JSONObject();
        param.put("app_id",appId);
        param.put("app_secret",appSecret);
        JSONObject result = restTemplate.postForObject(LarkConstants.app_access_token_url,param,JSONObject.class);
        log.info("result:"+result);
        return result.getString("app_access_token");
    }

    /**
     * 常规token
     * 获取企业微信accessToken
     * @return
     */
    public  JSONObject getUser(String code){
        JSONObject param= new JSONObject();
        param.put("code",code);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Bearer " + getAccessToken());
        HttpEntity<Object> entity = new HttpEntity<>(param, headers);
        JSONObject result = restTemplate.postForObject(LarkConstants.user_url, entity, JSONObject.class);
        log.info("code:{} header:{}",code,headers);
        log.info("result:"+result);
        return result.getJSONObject("data");
    }

    public Client getClient(){
       return Client.newBuilder(appId, appSecret).openBaseUrl(BaseUrlEnum.LarkSuite).logReqAtDebug(true).build();
    }


    /**
     * createUser 创建用户
     * @param user
     * @return
     * @throws Exception
     */
    public  CreateUserResp createUser(User user) throws Exception{
         // 创建用户请求对象
		CreateUserReq req = CreateUserReq.newBuilder()
				.userIdType("open_id")
				.departmentIdType("open_department_id")
				.user(user)
				.build();
		CreateUserResp resp = getClient().contact().user().create(req, RequestOptions.newBuilder().build());
		log.info("resp: {}",JSON.toJSONString(resp));
		return  resp;

    }


    /**
     * deleteUser 删除用户
     * @param userId
     * @return
     * @throws Exception
     */
    public  DeleteUserResp deleteUser(String  userId) throws Exception {
        DeleteUserReq req = DeleteUserReq.newBuilder().userIdType("open_id").userId(userId).build();
        DeleteUserResp resp = getClient().contact().user().delete(req, RequestOptions.newBuilder().build());
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }

    /**
     * getChildrenDepartment  获取子部门
     * @param departmentId
     * @return
     * @throws Exception
     */
    public  ChildrenDepartmentResp getChildrenDepartment(String  departmentId) throws Exception {
        //      获取子部门 size为50最大
		ChildrenDepartmentReq req = ChildrenDepartmentReq.newBuilder().departmentId(departmentId).build();
		ChildrenDepartmentResp resp = getClient().contact().department().children(req, RequestOptions.newBuilder().build());
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }

    /**
     * getUserByDepartId 获取部门直属用户列表
     * @return
     * @throws Exception
     */
   public  FindByDepartmentUserResp getUserByDepartId() throws Exception{
        //		获取部门直属用户列表
		FindByDepartmentUserReq req = FindByDepartmentUserReq.newBuilder().departmentId("0").build();
		FindByDepartmentUserResp resp = getClient().contact().user().findByDepartment(req, RequestOptions.newBuilder().build());
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }


    /**
     * updateDepartment 修改部门
     * @param department
     * @return
     * @throws Exception
     */
    public  UpdateDepartmentResp updateDepartment(Department department) throws Exception{
//    修改部门
		UpdateDepartmentReq req =   UpdateDepartmentReq.newBuilder().userIdType("open_id").departmentIdType("open_department_id").departmentId(department.getDepartmentId())
				.department(department).build();

		UpdateDepartmentResp resp = getClient().contact().department().update(req, RequestOptions.newBuilder().build());
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }


    /**
     * deleteDepartment 删除部门
     * @param departmentId
     * @return
     * @throws Exception
     */
    public  DeleteDepartmentResp deleteDepartment(String  departmentId) throws Exception{
//    删除
        DeleteDepartmentReq req = DeleteDepartmentReq.newBuilder().departmentIdType("open_department_id").departmentId(departmentId).build();
		DeleteDepartmentResp resp =getClient().contact().department().delete(req);
		log.info("resp: {}",JSON.toJSONString(resp));
		return resp;
    }

    /**
     * createDepartment 创建部门
     * @param department
     * @return
     * @throws Exception
     */
    public  CreateDepartmentResp createDepartment(Department department) throws Exception{
		//  创建部门对象
		CreateDepartmentReq req = CreateDepartmentReq.newBuilder()
				.department(department)
				.build();
		// 发起请求
		CreateDepartmentResp resp = getClient().contact().department().create(req, RequestOptions.newBuilder().build());
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }

    /**
     *  修改用户
     * @param user
     * @return
     * @throws Exception
     */
    public  UpdateUserResp updateUser(User user) throws Exception{
        //         修改用户
		UpdateUserReq req =   UpdateUserReq.newBuilder().userIdType("open_id").departmentIdType("open_department_id").userId("ou_5a28fe6ba27cb1cc5b1963ac251eb67c")
				.user(user).build();


		UpdateUserResp resp = getClient().contact().user().update(req, RequestOptions.newBuilder().build());
		log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }


    /**
     * 获取角色成员
     * @param roleId
     * @return
     * @throws Exception
     */
    public  ListFunctionalRoleMemberResp listFunctionalRoleMember(String roleId) throws Exception{
        ListFunctionalRoleMemberReq req = ListFunctionalRoleMemberReq.newBuilder().roleId(roleId).build();
		ListFunctionalRoleMemberResp resp=getClient().contact().functionalRoleMember().list(req);
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }






//		https://open.larksuite.com/open-apis/contact/v2/role/list

    /**
     * 获取角色列表
     * @return
     * @throws Exception
     */
    public  RawResponse getRoles() throws Exception {
        RawResponse resp = getClient().get(
                "https://open.larksuite.com/open-apis/contact/v2/role/list"
                , null
                , AccessTokenType.Tenant);
        log.info("resp: {}",JSON.toJSONString(resp));
        return resp;
    }

//		// 发起请求












//		Client client = Client.newBuilder("cli_a4faccf706f8902f", "TB7fVlKkPKJbPn2JSFjGqgFG1sC8WfpY").build();
////		Client client = Client.newBuilder("appId", "appSecret").build();
//		// 创建请求对象
//		SearchDepartmentReqBody searchDepartmentReqBody = SearchDepartmentReqBody.newBuilder().build();
//		searchDepartmentReqBody.setQuery("IT");
//		SearchDepartmentReq req = SearchDepartmentReq.newBuilder().searchDepartmentReqBody(searchDepartmentReqBody).departmentIdType(SearchDepartmentDepartmentIdTypeEnum.DEPARTMENT_ID)
//						.build();
//
//		SearchDepartmentResp resp = client.contact().department().search(req, RequestOptions.newBuilder().build());


    }


