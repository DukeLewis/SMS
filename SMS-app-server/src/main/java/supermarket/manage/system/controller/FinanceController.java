package supermarket.manage.system.controller;



import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supermarket.manage.system.common.commons.AppResult;
import supermarket.manage.system.model.dto.AuthDTO;
import supermarket.manage.system.model.vo.AuthVO;
import supermarket.manage.system.service.auth.IAuthService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
@RestController
@Api(tags = "6.财务管理模块")
@RequestMapping("/finance")
public class FinanceController {

}
