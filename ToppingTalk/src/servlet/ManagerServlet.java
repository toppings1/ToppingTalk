package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import action.SearchAction;

import controller.AddFriendController;
import controller.DeleteMemberInfoController;
import controller.JoinFriendController;
import controller.JoinMemberController;
import controller.LoginController;
import controller.LoginLoadingInfoController;
import controller.SearchMemberInfoController;
import controller.UpdateMemberInfoController;

import test.EmailConfirmController;
import test.FriendProfileInfo;
import test.Injector;
import test.MemberInfo;


/**
 * Servlet implementation class Servlet
 */
@WebServlet(name = "mainController", urlPatterns = { 
		"/index", "/enroll", "/login", "/find", 
		"/searchUser", "/addUser", "/withdrawal", 
		"/confirmEmail","/inputMemberInfo","/friendsList"
		})
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerServlet() {
        super();
    }
    public void destory()
    {
    	Injector.getInstance().shutDown();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String mappingName = request.getServletPath();
		System.out.println(mappingName);
		String disUrl = null;
		RequestDispatcher view = null;
		boolean getFlag=true;
		
		if(mappingName.equals("/enroll"))
		{
			disUrl = "/join/confirmEmail.jsp";
		}
		else if(mappingName.equals("/login") || mappingName.equals("/index"))
		{
			disUrl = "/login/login.html";
		}
		else if(mappingName.equals("/find"))
		{
			disUrl = "/search/searchUser.html";
		}
	
		if(getFlag){
			view = request.getRequestDispatcher(disUrl);
			view.forward(request, response);
		}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean check = false;
		request.setCharacterEncoding("UTF-8");
		String mappingName = request.getServletPath();
		System.out.println("매핑네이이이이이임 "+mappingName);
		String disUrl = null;
		
		if(mappingName.equals("/enroll"))
		{
			JoinMemberController joinCon = (JoinMemberController) Injector.getInstance().getObject(JoinMemberController.class);
			
			joinCon.setRequest(request);
			boolean ch = joinCon.joinMember();
			
			if(ch == false)
			{
				disUrl = "/join/join.jsp";
			}
			else
			{
				disUrl = "/login/login.html";
			}
		}
		else if(mappingName.equals("/login"))
		{
			LoginController loginCon = (LoginController) Injector.getInstance().getObject(LoginController.class);
			
			loginCon.setReqeust(request);
			boolean ch = loginCon.login();
			
			if(ch == false)
			{
				request.setAttribute("login", "실패");
				disUrl = "/login/loginTest.jsp";
			}
			else
			{
				LoginLoadingInfoController loadCon = (LoginLoadingInfoController) Injector.getInstance().getObject(LoginLoadingInfoController.class);
				loadCon.setRequest(request);
				
				loadCon.loadLoginInfo();
				disUrl = "/main/main.jsp";
			}
		}
		else if(mappingName.equals("/searchUser"))
		{
			check = true;
			JSONObject member = null;
			response.setContentType("application/x-json; charset=utf-8");

			String condition = request.getParameter("condition");
			System.out.println(condition);
			if(condition.equals("이름"))
			{
				String name = request.getParameter("id");
				System.out.println(name);
				SearchMemberInfoController con = (SearchMemberInfoController) Injector.getInstance().getObject(SearchMemberInfoController.class);
				con.setRequest(request);
				List<MemberInfo> list = con.searchName(name);
				
				JSONArray ary = new JSONArray();
				System.out.println(list.size());
				if(list.size() == 0)
				{
					member = new JSONObject();
					member.put("result", "false");
					ary.put(member);
				}
				else
				{
					JSONObject obj = new JSONObject();
					obj.put("result", "true");
					ary.put(obj);
					for(MemberInfo info : list)
					{
						member = new JSONObject();
						member.put("memberId", info.getMemberId());
						member.put("name", info.getName());
						member.put("profilePicture", info.getProfile().getProfilePicture());
						member.put("statusMsg", info.getProfile().getStatusMsg());
						ary.put(member);
					}
				}
				System.out.println(ary.toString());
				response.getWriter().print(ary.toString());

			}
			else if(condition.equals("아이디"))
			{
				String id = request.getParameter("id");
				SearchMemberInfoController con = (SearchMemberInfoController) Injector.getInstance().getObject(SearchMemberInfoController.class);
				MemberInfo info = con.searchId(id);
				if(info == null)
				{
					member = new JSONObject();
					member.put("result", "false");
					response.getWriter().print(member.toString());
				}
				else
				{
					member = new JSONObject();
					member.put("result", "true");
					member.put("memberId", info.getMemberId());
					member.put("name", info.getName());
					member.put("profilePicture", info.getProfile().getProfilePicture());
					member.put("statusMsg", info.getProfile().getStatusMsg());
					response.getWriter().print(member.toString());
				}

			}
		}
		else if(mappingName.equals("/addUser"))
		{
			check = true;
			JSONObject obj = null;
			response.setContentType("application/x-json; charset=utf-8");

			String userId = request.getParameter("userId");
			String addUserId = request.getParameter("addUserId");
			
			System.out.println("1*"+addUserId);
			System.out.println("2*"+userId);
			
			request.setAttribute("memberId", userId);
			request.setAttribute("friendId", addUserId);
			
			
			SearchMemberInfoController searchCon = (SearchMemberInfoController) Injector.getInstance().getObject(SearchMemberInfoController.class);
			JoinFriendController joinCon = (JoinFriendController) Injector.getInstance().getObject(JoinFriendController.class);
			joinCon.setRequest(request);
			
			if(!joinCon.searchFriend(userId, addUserId))
			{
				MemberInfo info =  searchCon.searchId(addUserId);
				System.out.println("**3"+info);
				request.setAttribute("memberInfo", info);
				
				AddFriendController addCon =  (AddFriendController) Injector.getInstance().getObject(AddFriendController.class);
				addCon.setRequest(request);
				System.out.println("친구등록 에러 여부 : " + addCon.addFriend());
				
				obj = new JSONObject();
				obj.put("result", "true");
				response.getWriter().print(obj.toString());

			}
			else
			{
				obj = new JSONObject();
				obj.put("result", "false");
				response.getWriter().print(obj.toString());

			}
			
			// 1. 친구목록에서 해당 친구가 있는 지 검색
			// 2. 있으면 이미 등록된 친구라고 알려준다.
			// 3. 없으면 등록
		}
		else if(mappingName.equals("/updateMemberInfo"))
		{
			check = true;
			JSONObject member = null;
			response.setContentType("application/x-json; charset=utf-8");
			
			UpdateMemberInfoController updateCon = (UpdateMemberInfoController) Injector.getInstance().getObject(UpdateMemberInfoController.class);
			updateCon.setRequest(request);
			
			member = new JSONObject();
			if(updateCon.updateMemberInfo())
			{
				member.put("result", "true");
			}
			else
			{
				member.put("result", "false");
			}
			response.getWriter().print(member.toString());
		}
		else if(mappingName.equals("/withdrawal"))
		{
			check = true;
			JSONObject member = null;
			response.setContentType("application/x-json; charset=utf-8");
			
			DeleteMemberInfoController deleteCon = (DeleteMemberInfoController) Injector.getInstance().getObject(DeleteMemberInfoController.class);
			deleteCon.setRequest(request);
			
			member = new JSONObject();
			if(deleteCon.deleteMemberInfo())
			{
				member.put("result", "true");
			}
			else
			{
				member.put("result", "false");
			}
			response.getWriter().print(member.toString());
		}
		else if(mappingName.equals("/confirmEmail")){
			EmailConfirmController ecc = (EmailConfirmController)Injector.getInstance().getObject(EmailConfirmController.class);
			ecc.setHttpServletRequest(request);
			ecc.emailConfirm();
			disUrl = "/join/confirmEmail.jsp";
		}
		else if(mappingName.equals("/inputMemberInfo")){
			String num = request.getParameter("num");
			String email = request.getParameter("email");
			String confirmCode = "";
			
			HttpSession session = request.getSession();
			synchronized (session) {
				confirmCode = (String)session.getAttribute("confirmCode");
				session.removeAttribute("confirmCode");
			}
			
			
			if(num.equals(confirmCode)){
				request.setAttribute("email", email);
				disUrl = "/join/join.jsp";
			}else{
				request.setAttribute("error", "인증번호가 다릅니다.");
				disUrl = "/join/confirmEmail.jsp";
			}
		}
		else if(mappingName.equals("/friendsList"))
		{
			response.setContentType("application/x-json; charset=utf-8");

			 System.out.println("friendsList");
			check=true;
			PrintWriter out = response.getWriter();
			String clientId=request.getParameter("clientId");
			JSONObject friend = null;
			
			SearchAction search = (SearchAction) Injector.getInstance().getObject(SearchAction.class);
			List<FriendProfileInfo> list = search.searchFriends(clientId);
			
			JSONArray ary = new JSONArray();
			System.out.println(list.size());
			if(list.size() == 0)
			{
				friend = new JSONObject();
				friend.put("result", "false");
				ary.put(friend);
			}
			else
			{
				JSONObject obj = new JSONObject();
				obj.put("result", "true");
				ary.put(obj);
				for(FriendProfileInfo info : list)
				{
					friend = new JSONObject();
					friend.put("memberId", info.getFriendInfo().getMemberId());
					friend.put("friendId", info.getFriendInfo().getFriendId());
					friend.put("favoriteFlag", info.getFriendInfo().getFavoriteFlag());
					friend.put("hideFlag", info.getFriendInfo().getHideFlag());
					friend.put("rejectionFlag", info.getFriendInfo().getRejectionFlag());
					friend.put("nickName", info.getProfileInfo().getNickName());
					friend.put("profilePicture", info.getProfileInfo().getProfilePicture());
					friend.put("statusMsg", info.getProfileInfo().getStatusMsg());
											
					ary.put(friend);
				}
			}
			System.out.println(ary.toString());
			response.getWriter().print(ary.toString());
		}
		if(!check)
		{
			RequestDispatcher view = request.getRequestDispatcher(disUrl);
			view.forward(request, response);
		}
	}
}
