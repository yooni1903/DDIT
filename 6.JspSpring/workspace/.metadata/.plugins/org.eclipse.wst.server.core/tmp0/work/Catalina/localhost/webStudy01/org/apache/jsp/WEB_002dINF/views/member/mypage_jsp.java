/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.63
 * Generated at: 2021-03-31 07:07:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kr.or.ddit.vo.MemberVO;

public final class mypage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("kr.or.ddit.vo.MemberVO");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- useBean의 특성 받아오는 객체가 null이라면 강제로 비어있는 객체를 생성하기 때문에 view에서는 null 문자가 뜬다. \r\n");
      out.write("\t-> null Point를 한번 막으려는 것\r\n");
      out.write(" -->\r\n");
      kr.or.ddit.vo.MemberVO member = null;
      member = (kr.or.ddit.vo.MemberVO) _jspx_page_context.getAttribute("member", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (member == null){
        member = new kr.or.ddit.vo.MemberVO();
        _jspx_page_context.setAttribute("member", member, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("<h4>");
      out.print(member.getMem_name() );
      out.write("님의 마이페이지</h4>\r\n");
      out.write("<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>회원 아이디</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_id());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>비밀번호</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_pass());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>이름</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_name());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>주민번호1</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_regno1());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>주민번호2</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_regno2());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>생일</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_bir());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>우편번호</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_zip());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>주소1</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_add1());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>주소2</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_add2());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>집 전화번호</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_hometel());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>회사 전화번호</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_comtel());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>휴대폰</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_hp());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>이메일</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_mail());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>직업</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_job());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>취미</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_like());
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>기념일</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_memorial() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>기념일자</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_memorialday() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>마일리지</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_mileage() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<th>탈퇴여부</th>\r\n");
      out.write("\t\t\t<td>");
      out.print(member.getMem_delete() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- // \tMemberVO member = (MemberVO) session.getAttribute(\"authMember\"); -->\r\n");
      out.write("<!-- // \tif(member != null){ -->\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<!-- \t<table> -->\r\n");
      out.write("<!-- \t\t<tr> -->\r\n");
      out.write("<!-- \t\t\t<th>아이디</th> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t</tr> -->\r\n");
      out.write("<!-- \t\t<tr> -->\r\n");
      out.write("<!-- \t\t\t<th>주소</th> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t</tr> -->\r\n");
      out.write("<!-- \t\t<tr> -->\r\n");
      out.write("<!-- \t\t\t<th>생일</th> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t</tr> -->\r\n");
      out.write("<!-- \t\t<tr> -->\r\n");
      out.write("<!-- \t\t\t<th>집 전화번호</th> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t</tr> -->\r\n");
      out.write("<!-- \t</table> -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
