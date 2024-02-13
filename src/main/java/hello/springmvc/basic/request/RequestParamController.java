package hello.springmvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username={}, age={}",username,age);

    response.getWriter().write("ok");
  }

  @ResponseBody // 리턴값의 문자 자체를 http 응답 메세지에 넣어 반환한다.
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
          @RequestParam("username")String memberName,
          @RequestParam("age")int memberAge) {
    log.info("username={}, age={}",memberName, memberAge);

    return "ok";
  }

  // 변수명과 파라미터 이름이 같으면 생략 가능
  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
          @RequestParam String username,
          @RequestParam int age) {
    log.info("username={}, age={}",username, age);

    return "ok";
  }

  // String int Integer 등의 단순 타입이면 @RequestParam 도 생략 가능하다.
  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    log.info("username={}, age={}",username, age);

    return "ok";
  }


// required = true 면 값이 무조건 있어야함
// required = false 면 값이 없어도 오류 안뜸
  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
          @RequestParam(required = false) String username,
          @RequestParam(required = false) Integer age)  { //int 에 null 값이 들어갈 수 없다. Integer 사용
    log.info("username={}, age={}",username, age);

    return "ok";
  }

  // 파라미터 값이 안넘어오면 기본값을 guest 로 하겠다는 뜻.
  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
          @RequestParam(required = true, defaultValue = "guest") String username,
          @RequestParam(required = false, defaultValue = "-1") Integer age)  {
    log.info("username={}, age={}",username, age);

    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap)  {
    log.info("username={}, age={}",paramMap.get("username"), paramMap.get("age"));

    return "ok";
  }
}
