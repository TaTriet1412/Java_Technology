package com.demoApp.DAO;

import com.demoApp.DTO.Account;
import com.demoApp.Repository.Repository;
import com.demoApp.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class AccountDAO implements Repository<Account,Long> {
    Session session = HibernateUtils.getSessionFactory().openSession();
    public AccountDAO(){}

    public Long add(Account ac){
        session.beginTransaction();
        Long id = (Long) session.save(ac);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public boolean update(Account item, Long id) {
        return false;
    }

    @Override
    public boolean remove(Account item) {
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        return false;
    }

    public Account get(Long id){
        return session.find(Account.class,id);
    }

    public List<Account> getAll(){
        return session.createQuery("FROM Account", Account.class).list();
    }

    public Account get(String username, String password){
        List<Account> accountList = getAll();
        if(accountList.isEmpty()){
            System.err.println("No account has been added");
        }else{
            for(Account a:accountList){
                if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                    return a;
                }
            }
        }
        return null;
    }
}
