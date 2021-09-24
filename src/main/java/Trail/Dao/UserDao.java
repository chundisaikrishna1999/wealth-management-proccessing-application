package Trail.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Trail.model.Transaction;
import Trail.model.User;

@Service
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Transactional
	public int register(User user) {

		User temp = findUser(user.getUsername());
		if (temp == null) {
			String encodedPassword = passwordEncoder().encode(user.getPassword());
			user.setPassword(encodedPassword);
			try {
				sessionFactory.getCurrentSession().save(user);
			} catch (HibernateException e) {
				sessionFactory.openSession().save(user);

			}
			return 1;
		} else {
			return 0;
		}

	}

//	@Transactional
//	public User validateUser(Login login) {
//		System.out.println(login.getPassword()+"  "+login.getUsername());
//		List<User> list = list();
//
//		for (Iterator<User> itr=list.iterator();itr.hasNext();)
//	{
//
//		for(User u:list) {
//			User obj=itr.next();
//			String decodedPassword = passwordEncoder().;
//			String u2=obj.getUsername();
//			String u1=obj.getPassword();
//			System.out.println(u1+" "+ u2);
//			if((u2.equals(login.getUsername())) && (u1.equals(login.getPassword()))) {
//
//				return obj;
//			}
//		}	
//	}
////		session.close();
//	return null;
//	}

	@Transactional
	public User findUser(String username) {
		return sessionFactory.getCurrentSession().find(User.class, username);
	}
	

	@Transactional
	public List<Transaction> findTransactions(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Transaction.class);
		List<Transaction> d= criteria.add(Restrictions.eq("username", username)).list();
		
		
		return d;
	}

	@Transactional
	public List<User> list() {
		
		List<User> usersList = sessionFactory.getCurrentSession().createQuery("from User").list();
		return usersList;
	}

	public int insertDeposit(Transaction transaction) {
		double deposit = transaction.getAmount();
		User findUser = findUser(transaction.getUsername());
		double total = deposit + findUser.getBalance();
		findUser.setBalance(total);
		sessionFactory.getCurrentSession().save(findUser);

		return  sessionFactory.getCurrentSession().save(transaction) != null ? 1 : 0;
	}

	public int insertWithdraw(Transaction transaction) {
		double withdraw = transaction.getAmount();
		User findUser = findUser(transaction.getUsername());
		double total =  findUser.getBalance()-withdraw;
		findUser.setBalance(total);
		sessionFactory.getCurrentSession().save(findUser);

		return  sessionFactory.getCurrentSession().save(transaction) != null ? 1 : 0;
	}
//	@Autowired
//	SessionFactory sessionFactory;
//	
//	JdbcTemplate template;
//
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//	}
//
//	public int register(User user) {
//		// TODO Auto-generated method stub
//		try {
//			String sql = "insert into userstable values(?,?,?,?,?,?,?,?)";
//
//			template.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
//					user.getLastname(), user.getEmail(), user.getAddress(), user.getPhone(), user.getBalance() });
//			return 1;
//		} catch (Exception e) {
//			return 0;
//		}
//	}
//
//	public int insertDeposit(Transaction transaction) {
//		try {
//			//System.out.println(transaction.getUsername());
//			String sql = "insert into statements values(?,?,?,?)";
//
//			template.update(sql, new Object[] { transaction.getUsername(), transaction.getType(),
//					transaction.getAmount(), transaction.getReason() });
//
//			
//			 double realamount = bringaccountbalance(transaction.getUsername()); 
//			 double	 amount = realamount + transaction.getAmount();
//			 //System.out.println(realamount);
//			
//			 String query = "UPDATE userstable SET balance =? WHERE username = ?";
//			template.update(query, amount, transaction.getUsername());
//			 
//			return 1;
//		} catch (Exception e) {
//			System.out.println(e);
//			return 0;
//		}
//	}
//	
//	
//
//	public int insertWithdraw(Transaction transaction) {
//		try {
//			//System.out.println(transaction.getUsername());
//			String sql = "insert into statements values(?,?,?,?)";
//
//			template.update(sql, new Object[] { transaction.getUsername(), transaction.getType(),
//					transaction.getAmount(), transaction.getReason() });
//
//			
//			 double realamount = bringaccountbalance(transaction.getUsername()); 
//			 double	 amount = realamount - transaction.getAmount();
//			 //System.out.println(realamount);
//			
//			 String query = "UPDATE userstable SET balance =? WHERE username = ?";
//			template.update(query, amount, transaction.getUsername());
//			 
//			return 1;
//		} catch (Exception e) {
//			System.out.println(e);
//			return 0;
//		}
//	}
//
//	private double bringaccountbalance(String username) {
//		//System.out.println("hello");
//		// TODO Auto-generated method stub
//		String sql = "select * from userstable where username='" + username + "'";
//		List<User> users = template.query(sql, new UserMapper());
//		double balance = users.size() > 0 ? users.get(0).getBalance() : 0;
//		//System.out.println(balance);
//		return balance;
//
//	}
//
//	public User validateUser(Login login) {
//		String sql = "select * from userstable where username='" + login.getUsername() + "' and password='"
//				+ login.getPassword() + "'";
//
//		List<User> users = template.query(sql, new UserMapper());
//
//		return users.size() > 0 ? users.get(0) : null;
//	}
//
//	public List<Transaction> showTransaction(String username) {
//		String sql = "SELECT * FROM statements WHERE username = '" + username + "' ;";
//		List<Transaction> trans = template.query(sql, new TransactionMapper());
//		System.out.println(trans);
//
//		return trans;
//	}
//
//
//}
//
//class UserMapper implements RowMapper<User> {
//
//	public User mapRow(ResultSet rs, int arg1) throws SQLException {
//		User user = new User();
//
//		user.setUsername(rs.getString("username"));
//		user.setPassword(rs.getString("password"));
//		user.setFirstname(rs.getString("firstname"));
//		user.setLastname(rs.getString("lastname"));
//		user.setEmail(rs.getString("email"));
//		user.setAddress(rs.getString("address"));
//		user.setPhone(rs.getString("phone"));
//		user.setBalance(rs.getDouble("balance"));
//
//		return user;
//	}
//}
//
//class TransactionMapper implements RowMapper<Transaction> {
//
//	public Transaction mapRow(ResultSet rs, int arg1) throws SQLException {
//		Transaction tran = new Transaction();
//		tran.setUsername(rs.getString("username"));
//		tran.setType(rs.getString("type"));
//		tran.setAmount(rs.getDouble("amount"));
//		tran.setReason(rs.getString("reason"));
//
//		return tran;
//	}

}
