package xyz.ieden.mb.resource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ieden.mb.service.ICountryService;
import xyz.ieden.mybatis.entity.Result;

/**
 *
 * @author Gavin
 * @date 2018.04.21
 */
@RestController
@RequestMapping(value = "country")
public class CountryResource {

    @Autowired
    private ICountryService countryService;

    @GetMapping(value = "")
    public Result<JSONObject> findCountryByPage(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        JSONObject reqParam = new JSONObject();
        reqParam.put("pageNum", pageNum);
        reqParam.put("pageSize", pageSize);
        Result<JSONObject> result = countryService.findCountryByPage(reqParam);
        return result;
    }

}
