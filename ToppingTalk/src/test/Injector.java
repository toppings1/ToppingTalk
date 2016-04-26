package test;

import java.net.UnknownHostException;

import validator.FriendInfoValidation;
import validator.LoginInfoValidation;
import validator.MemberInfoValidation;
import validator.ProfileInfoValidation;
import validator.Validation;
import validator.Validator;

import DAO.FriendProfileInfoDAO;
import DAO.FriendProfileInfoDAOImpl;
import DAO.LoginDAO;
import DAO.LoginDAOImpl;
import DAO.MemberInfoDAO;
import DAO.MemberInfoDAOImpl;

import action.AddAction;
import action.DeleteAction;
import action.SearchAction;
import action.UpdateAction;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import controller.AddFriendController;
import controller.DeleteMemberInfoController;
import controller.JoinFriendController;
import controller.JoinMemberController;
import controller.LoginController;
import controller.LoginLoadingInfoController;
import controller.SearchMemberInfoController;
import email.EmailSendable;
import email.EmailSender;


public class Injector
{
	private static Injector instance;
	static
	{
		instance = new Injector();
		instance.start();
	}
	private DB dataSource;
	private MongoClient mongoClient;
	
	private Injector(){
		
	}
	public void start()
	{
		mongoClient = null;
		
		
		try {
			mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
			dataSource = mongoClient.getDB("testdb");
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(MongoException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void shutDown()
	{
		mongoClient.close(); // ????/
	}
	
	public Object getObject(Class type)
	{
		if(type == MemberInfoDAOImpl.class)
		{
			return createMemberInfoDAO();
		}
		else if(type == LoginDAOImpl.class)
		{
			return createLoginDAO();
		}
		else if(type == FriendProfileInfoDAOImpl.class)
		{
			return createFriendProfileInfoDAO();
		}
		else if(type == JoinMemberController.class)
		{
			return createJoinMemberController();
		}
		else if(type == LoginController.class)
		{
			return createLoginController();
		}
		else if(type == LoginLoadingInfoController.class)
		{
			return createLoginLoadingInfoController();
		}
		else if(type == JoinFriendController.class)
		{
			return createJoinFriendController();
		}
		else if(type == AddFriendController.class)
		{
			return createAddFriendController();
		}
		else if(type == SearchMemberInfoController.class)
		{
			return createSearchMemberInfoController();
		}
		else if(type == DeleteMemberInfoController.class)
		{
			return createDeleteMemberInfoController();
		}
		else if(type == Validation.class)
		{
			return createValidation();
		}
		else if(type == MemberInfoValidation.class)
		{
			return createMemberInfoValidation();
		}
		else if(type == LoginInfoValidation.class)
		{
			return createLoginInfoValidation();
		}
		else if(type == ProfileInfoValidation.class)
		{
			return createProfileInfoValidation();
		}
		else if(type == FriendInfoValidation.class)
		{
			return createFriendInfoValidation();
		}
		else if(type == AddAction.class)
		{
			return createAddAction();
		}
		else if(type == SearchAction.class)
		{
			return createSearchAction();
		}
		else if(type == UpdateAction.class)
		{
			return createUpdateAction();
		}
		else if(type == DeleteAction.class)
		{
			return createDeleteAction();
		}
		else if(type == EmailSendable.class){
			return createEmailSendable();
		}
		else if(type == EmailConfirmController.class){
			return createEmailConfirmController();
		}
		return null;
	}
	
	public static Injector getInstance()
	{
		return instance;
	}
	
	private Validator createValidation()
	{
		Validator validator = new Validation();
		return validator;
	}
	
	private MemberInfoValidation createMemberInfoValidation()
	{
		MemberInfoValidation validator = new MemberInfoValidation();
		return validator;
	}
	
	private LoginInfoValidation createLoginInfoValidation()
	{
		LoginInfoValidation validator = new LoginInfoValidation();
		return validator;
	}
	//// 잠시 멈춰
	private ProfileInfoValidation createProfileInfoValidation()
	{
		ProfileInfoValidation validator = new ProfileInfoValidation();
		return validator;
	}
	
	private FriendInfoValidation createFriendInfoValidation()
	{
		FriendInfoValidation validator = new FriendInfoValidation();
		return validator;
	}
	
	
	private LoginLoadingInfoController createLoginLoadingInfoController()
	{
		LoginLoadingInfoController loginCon = new LoginLoadingInfoController();
		return loginCon;
	}
//	
//	private EmailConfirmController createEmailConfirmController()
//	{
//	
//	}
	
	private JoinMemberController createJoinMemberController()
	{
		JoinMemberController joinMember = new JoinMemberController();
		return joinMember;
	}
	
	private LoginController createLoginController()
	{
		LoginController login = new LoginController();
		return login;
	}
	
	private JoinFriendController createJoinFriendController()
	{
		JoinFriendController joinFriend = new JoinFriendController();
		return joinFriend;
	}
	private AddFriendController createAddFriendController()
	{
		AddFriendController addFriend = new AddFriendController();
		return addFriend;
	}
	private SearchMemberInfoController createSearchMemberInfoController()
	{
		SearchMemberInfoController searchMember = new SearchMemberInfoController();
		return searchMember;
	}
	private DeleteMemberInfoController createDeleteMemberInfoController()
	{
		DeleteMemberInfoController deleteMember = new DeleteMemberInfoController();
		return deleteMember;
	}
	private AddAction createAddAction()
	{
		AddAction addAction = new AddAction();
		return addAction;
	}
	
	private SearchAction createSearchAction()
	{
		SearchAction searchAction = new SearchAction();
		return searchAction;
	}
	private DeleteAction createDeleteAction()
	{
		DeleteAction deleteAction = new DeleteAction();
		return deleteAction;
	}
	
	private UpdateAction createUpdateAction()
	{
		UpdateAction updateAction = new UpdateAction();
		return updateAction;
	}
	
	private LoginDAO createLoginDAO()
	{
		LoginDAO dao = new LoginDAOImpl();
		dao.setDB(dataSource);
		
		return dao;
	}
	
	private MemberInfoDAO createMemberInfoDAO()
	{
		MemberInfoDAO dao = new MemberInfoDAOImpl();
		dao.setDB(dataSource);
		
		return dao;
	}
	private FriendProfileInfoDAO createFriendProfileInfoDAO()
	{
		FriendProfileInfoDAO dao = new FriendProfileInfoDAOImpl();
		dao.setDB(dataSource);
		
		return dao;
	}
	
	private EmailSendable createEmailSendable(){
		return new EmailSender();
	}
	
	private EmailConfirmController createEmailConfirmController(){
		return new EmailConfirmController();
	}
}
