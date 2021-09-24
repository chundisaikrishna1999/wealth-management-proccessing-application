package Trail.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Trail.Dao.AuthorityDaoimpl;
import Trail.Dao.UserDao;
import Trail.model.Authority;
import Trail.model.Login;
import Trail.model.Transaction;
import Trail.model.User;

@Controller
public class UserController {
	@Autowired
	UserDao userDao;

	@Autowired
	AuthorityDaoimpl authoritydao;

	@RequestMapping("/")
	public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView showloginform(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());

		return mav;

	}

	@RequestMapping("/menu")
	public ModelAndView showmenu(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("menu");

		return mav;

	}
	
	

	@RequestMapping("/register")
	public ModelAndView showregisterform(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());

		return mav;
	}

	@RequestMapping("/deposit")
	public ModelAndView showdeposit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("deposit");
		mav.addObject("deposittransaction", new Transaction());

		return mav;
	}

	@RequestMapping("/withdraw")
	public ModelAndView showwithdraw(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("withdraw");
		mav.addObject("withdrawtransaction", new Transaction());

		return mav;
	}

	@RequestMapping(value = "/register/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		ModelAndView mav = null;

		int i = userDao.register(user);

		if (i == 1) {
			authoritydao.save(new Authority(user.getUsername(), "ROLE_USER"));
			return new ModelAndView("login2", "firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("register");
			mav.addObject("message", "Username already exists!!");
			return mav;
		}

	}

	@Transactional
	@RequestMapping(value = "/transactionProcess", method = RequestMethod.POST)
	public ModelAndView addDeposit(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("deposittransaction") Transaction transaction, Principal principal) {
		ModelAndView mav = null;

		String username = principal.getName();

		transaction.setUsername(username);

		int i = userDao.insertDeposit(transaction);

		if (i == 1)
			return new ModelAndView("menu", "message", "deposit done");
		else {
			mav = new ModelAndView("deposit");
			mav.addObject("message", "Failed! enter details corectly");
			return mav;
		}

	}
	@Transactional
	@RequestMapping(value = "/withdrawtransactionProcess", method = RequestMethod.POST)
	public ModelAndView addWithdraw(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("withdrawtransaction") Transaction transaction, Principal principal) {
		ModelAndView mav = null;
		String username = principal.getName();

		transaction.setUsername(username);
		int i = userDao.insertWithdraw(transaction);

		if (i == 1)
			return new ModelAndView("menu", "message", "withdraw done");
		else {
			mav = new ModelAndView("withdraw");
			mav.addObject("message", "Failed! enter details corectly");
			return mav;
		}

	}

//	@RequestMapping(value = "/login/loginProcess", method = RequestMethod.POST)
//	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
//			@ModelAttribute("login") Login login, HttpSession session) {
//		ModelAndView mav = null;
//
//		User user = userDao.validateUser(login);
//
//		if (user != null) {
//			mav = new ModelAndView("menu");
//			mav.addObject("user", user);
//			session.setAttribute("user", user);
//			//System.out.println(session.getAttribute("user"));
//			
//			return mav;
//		} else {
//			mav = new ModelAndView("login");
//			mav.addObject("message", "Username or Password is wrong!!");
//		}
//
//		return mav;
//	}
//	
	@RequestMapping("/transaction")
	public ModelAndView showTransaction(Principal principal) {
		ModelAndView mav=null;
		String username=principal.getName();
		List<Transaction> findTransactions = userDao.findTransactions(username);
		//System.out.println(findTransactions);
		mav = new ModelAndView("transaction");
		mav.addObject("transactionobj", findTransactions);
		return mav;
	}

//	
	@RequestMapping("/showbalance")
	public ModelAndView showBalance(Principal principal) {
		ModelAndView mav=null;
		String username=principal.getName();
		User findUser = userDao.findUser(username);
		mav = new ModelAndView("showbalance");
		mav.addObject("balance", findUser.getBalance());
		return mav;
	}
//	
//	

	
//	 @RequestMapping("/transaction")
//	 public String viewTransactions(){
//		 return "transaction";
//	 
//	  }
	 
}
