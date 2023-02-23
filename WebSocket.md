HTTP 통신은 양방향이 아닌 단방향 통신입니다.

클라이언트의 요청이 있을 때 해당 요청에 대해서 서버가 응답을 하게 되고 응답이 끝난 뒤엔 자연스럽게 통신이 끊기게 됩니다.

이를 통해서 우리는 서버로부터 다운받게 된 각 콘텐츠들을 보게 됩니다.

그러나 채팅 서버는 이와 다르게 기본적으로 통신이 유지되어야 합니다.

따라서 양방향 통신을 위한 소켓이 사용됩니다.

소켓통신은 서버와 클라이언트간의 연결이 유지 된 채 양방향으로 통신이 이뤄지기에 실시간으로 이뤄지는 다양한 서비스에서 이용됩니다.

웹 소켓은 80포트를 사용하는 HTTP 프로토콜과 호환되며 단방향 통신인 HTTP통신을 양방향으로 사용하기 위해서 제공되었습니다.

HTTP를 사용하기 때문에 방화벽이라는 제약사항에서 자유롭습니다.


# SocketConfig
@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
    }

웹소켓 CORS정책으로 setAllowedOrigins메소드로 허용 도메인을 지정 단, *와 같은 와일드 카드로 지정했을 경우 보안상 문제가 발생함으로 브라우저가 보낸 Origin : 요청 헤더의 값과 동일한 값으로 Access-Control-Allow-Origin: 응답헤더를 지정해야 한다.

1번. String[] origins = {"https://www.url1.com", "https://m.url2.com", "https://url3.com"};
stompEndpointRegistry.addEndpoint("/ws-stomp").setAllowedOrigins(origins)

2번. stompEndpointRegistry.addEndpoint("/ws-stomp").setAllowedOrigins("https://www.url1.com", "https://m.url2.com", "https://url3.com")

setAllowedOrigins("*") 메서드를 사용했을 때 발생한 에러, 해결 방법으론 해당 메서드 자체를 지워주는 방법을 선택했다. 후에 도메인을 추가하게 된다면 위 1, 2번 방법으로 메서드를 추가해서 진행해보자.

error: "Internal Server Error"

message: "When allowCredentials is true, allowedOrigins cannot contain the special value \"*\" since that cannot be set on the \"Access-Control-Allow-Origin\" response header. To allow credentials to a set of origins, list them explicitly or consider using \"allowedOriginPatterns\" instead."
path: "/ws-stomp/668/cnbomwi3/xhr_send"

status: 500

timestamp: "2023-02-23T12:35:19.359+00:00"

trace: "java.lang.IllegalArgumentException: When allowCredentials is true, allowedOrigins cannot contain the special value \"*\" since that cannot be set on the \"Access-Control-Allow-Origin\" response header. To allow credentials to a set of origins, list them explicitly or consider using \"allowedOriginPatterns\" instead.\r\n\tat org.springframework.web.cors.CorsConfiguration.validateAllowCredentials(CorsConfiguration.java:520)\r\n\tat org.springframework.web.servlet.handler.AbstractHandlerMapping.getHandler(AbstractHandlerMapping.java:537)\r\n\tat org.springframework.web.servlet.DispatcherServlet.getHandler(DispatcherServlet.java:1274)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1056)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:973)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1011)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:731)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)\r\n\tat jakarta.servlet.http.HttpServlet.service(HttpServlet.java:814)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:223)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:185)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:158)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:177)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:119)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:400)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:859)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1734)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.base/java.lang.Thread.run(Unknown Source)\r\n"


