package xyz.ieden.mb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ieden.mb.entity.Country;
import xyz.ieden.mb.entity.CountryExample;
import xyz.ieden.mb.mapper.CountryMapper;
import xyz.ieden.mb.service.ICountryService;
import xyz.ieden.mybatis.entity.Result;
import xyz.ieden.mybatis.exception.EdenException;
import xyz.ieden.mybatis.util.ResultUtils;

import java.util.List;

/**
 *
 * @author Gavin
 * @date 2018.04.21
 */
@Service
public class CountryServiceImpl implements ICountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired(required = false)
    private CountryMapper countryMapper;

    /**
     * 通过 page 查询 Country
     *
     * @param reqParam
     * @return
     * @throws EdenException
     */
    @Override
    public Result<JSONObject> findCountryByPage(JSONObject reqParam) throws EdenException {
        LOGGER.info("Find Country By Page :[{}].", reqParam.toString());
        Result<JSONObject> result = ResultUtils.createResult();

        try {
            Integer pageNum = reqParam.getInteger("pageNum");
            Integer pageSize = reqParam.getInteger("pageSize");

            pageNum = pageNum < 0 ? 0 : pageNum;
            pageSize = pageSize < 0 ? 10 : pageSize;

            PageHelper.startPage(pageNum, pageSize);
            CountryExample countryExample = new CountryExample();
            CountryExample.Criteria criteria = countryExample.createCriteria();

            List<Country> countryList = countryMapper.selectByExample(countryExample);
            PageInfo<Country> pageInfo = new PageInfo<>(countryList);

            JSONObject resData = new JSONObject();
            resData.put("pageInfo", pageInfo);

            result.setData(resData);
            return result;
        } catch (EdenException e) {
            LOGGER.error("Msg:{}", e.getMsg());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            LOGGER.error("Msg:{}", e.getMessage());
            e.printStackTrace();
            throw new EdenException(e.getMessage(), e);
        }
    }
}
