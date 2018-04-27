package xyz.ieden.mb.service;

import com.alibaba.fastjson.JSONObject;
import xyz.ieden.mybatis.entity.Result;
import xyz.ieden.mybatis.exception.EdenException;

/**
 *
 * @author Gavin
 * @date 2018.04.21
 */
public interface ICountryService {
    /**
     * 通过 page 查询 Country
     *
     * @param reqParam
     * @return
     * @throws EdenException
     */
    Result<JSONObject> findCountryByPage(JSONObject reqParam) throws EdenException;
}
